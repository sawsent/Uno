package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.DeckFactory;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.SpecialCard;
import io.codeforall.bootcamp.filhosdamain.uno.messages.CannotDraw;
import io.codeforall.bootcamp.filhosdamain.uno.messages.DrewCards;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.DrawOrPlay;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.InputGetter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;

import static io.codeforall.bootcamp.filhosdamain.uno.Utils.STARTING_HAND_SIZE;
import static io.codeforall.bootcamp.filhosdamain.uno.game.Verifier.Restriction.*;
import static io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect.*;

public class Game {


    private PlayerList players = new PlayerList();
    private LinkedList<Card> deck;
    private final Verifier verifier = new Verifier();

    public boolean addPlayer(String name, InputStream in, PrintStream out) {
        return players.add(new Player(name, in, out));
    }


    public void run() {
        prepare();

        Effect beforePlayEffect = Effect.NO_EFFECT;
        Effect afterPlayEffect = Effect.NO_EFFECT;

        Card topCard;
        boolean gameEnded = false;
        Player winner = null;
        boolean canDraw;
        Verifier.Restriction restriction;

        while (!gameEnded) {
            for (Player player : players) {
                canDraw = true;
                restriction = NO_RESTRICTION;

                // print board to all players
                topCard = deck.getFirst();

                if (beforePlayEffect.equals(PLUS_2)) {
                    if (tookPlus2(player)) {
                        giveCards(player, 2);
                        beforePlayEffect = NO_EFFECT;
                        (new DrewCards()).send();
                        continue;
                    }
                    restriction = ONLY_PLUS_2;
                    canDraw = false;
                }
                if (beforePlayEffect.equals(PLUS_4)) {
                    if (tookPlus4(player)) {
                        giveCards(player, 4);
                        beforePlayEffect = NO_EFFECT;
                        (new DrewCards()).send();
                        continue;
                    }
                    restriction = ONLY_PLUS_4;
                    canDraw = false;
                }

                if (beforePlayEffect.equals(SKIP_TURN)) {
                    beforePlayEffect = NO_EFFECT;
                    continue;
                }

                Card playedCard = getCard(player, topCard, canDraw, restriction);

                deck.addFirst(playedCard);
                player.getHand().remove(playedCard);

                beforePlayEffect = playedCard.getBeforePlayEffect();
                afterPlayEffect = playedCard.getAfterPlayEffect();

                if (afterPlayEffect.equals(REVERSE)) {
                    players.reverse(player);

                } else if (afterPlayEffect.equals(SET_COLOR)) {
                    Color color = player.chooseColor();
                    ((SpecialCard) playedCard).setColor(color);
                }

                if (player.getHand().size() == 0) {
                    winner = player;
                    gameEnded = true;
                    break;
                }
            }
        }

    }

    private boolean tookPlus4(Player player) {
        if (verifier.hasPlusFour(player)) {
            InputGetter w = new DrawOrPlay(player, 4, "+4");
            return w.getInput() == 2;
        }
        return true;
    }

    private boolean tookPlus2(Player player) {
        if (verifier.hasPlusTwo(player)) {
            InputGetter w = new DrawOrPlay(player, 2, "+2");
            return w.getInput() == 2;
        }
        return true;

    }

    private Card getCard(Player player, Card topCard, boolean canDraw, Verifier.Restriction restriction) {
        boolean valid = false;
        Choice playerChoice = null;

        while (!valid) {

            playerChoice = player.choose();

            if (playerChoice.type == Choice.Type.PLAY_CARD) {
                valid = verifier.isValid(playerChoice.card, topCard, restriction);
                continue;
            }

            // PLAYER TOOK A CARD.
            if (player.getHand().size() < 40 && canDraw && deck.size() > 1) {
                player.giveCard(deck.removeLast());

                (new DrewCards()).send();

                continue;
            }

            (new CannotDraw(player)).send();

        }
        return playerChoice.card;
    }

    private void giveCards(Player player, int amount) {
        for (int i = 0; i < amount; i++) {
            player.giveCard(deck.removeLast());
        }
    }


    private void prepare() {

        deck = DeckFactory.getDeck(players.size());

        Collections.shuffle(deck);
        Collections.shuffle(players);
        assignInitialCards();

        for (Card c : deck) {
            System.out.println(c.repr());
        }
        System.out.println(deck.size());
        // message goes here \/


    }

    private void assignInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < STARTING_HAND_SIZE; i++) {
                player.giveCard(deck.removeLast());
            }
        }
    }


}
