package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.DeckFactory;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.SpecialCard;
import io.codeforall.bootcamp.filhosdamain.uno.messages.*;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.DrawOrPlay;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.InputGetter;
import io.codeforall.bootcamp.filhosdamain.uno.server.MessageSender;

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
    private Card firstPlayedCard;
    private final Verifier verifier = new Verifier();

    private Effect beforePlayEffect = Effect.NO_EFFECT;
    private Effect afterPlayEffect;

    private Card topCard;
    private boolean gameEnded = false;
    private Player winner = null;
    private boolean canDraw;
    private Verifier.Restriction restriction;
    private int cardsToEat = 0;

    public boolean addPlayer(String name, InputStream in, PrintStream out) {
        return players.add(new Player(name, in, out));
    }


    public void run() {
        prepare();

        int currentRound = 0;

        while (!gameEnded) {

            for (Player player : players) {

                currentRound++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                canDraw = true;
                restriction = NO_RESTRICTION;
                topCard = deck.getFirst();


                MessageSender.send(new ClearTerminal(players), new ShowCurrentRound(players, currentRound), new ShowBoard(players, player, topCard, "all"));

                if (manageBeforePlayEffect(player)) {
                    continue;
                }

                Card playedCard = getCard(player, topCard, canDraw, restriction);

                deck.addFirst(playedCard);
                player.getHand().remove(playedCard);

                beforePlayEffect = playedCard.getBeforePlayEffect();
                afterPlayEffect = playedCard.getAfterPlayEffect();

                if (player.getHand().size() == 0) {
                    winner = player;
                    gameEnded = true;
                    break;
                }

                if (manageAfterPlayEffect(player, playedCard)) {
                    break;
                }
            }
        }
        MessageSender.send(new ShowWinner(players, winner));
        System.out.println("Game ended. Winner: " + winner.getName());
        System.exit(1);
    }

    private boolean manageAfterPlayEffect(Player player, Card playedCard) {
        if (afterPlayEffect.equals(REVERSE)) {
            players.reverse(player);
            MessageSender.send(new ShowOrderReversed(players));
            return true;
        }

        if (afterPlayEffect.equals(SET_COLOR)) {
            Color color = player.chooseColor();
            ((SpecialCard) playedCard).setColor(color);
        }
        return false;
    }

    private boolean manageBeforePlayEffect(Player player) {
        if (beforePlayEffect.equals(PLUS_2)) {
            cardsToEat += 2;
            if (tookPlus2(player)) {
                giveCards(player, cardsToEat);
                beforePlayEffect = NO_EFFECT;

                MessageSender.send(new ShowDrewCards(players, player, cardsToEat));
                cardsToEat = 0;
                return true;
            }
            restriction = ONLY_PLUS_2;
            canDraw = false;
        }

        if (beforePlayEffect.equals(PLUS_4)) {
            cardsToEat += 4;
            if (tookPlus4(player)) {
                giveCards(player, cardsToEat);
                beforePlayEffect = NO_EFFECT;
                MessageSender.send(new ShowDrewCards(players, player, cardsToEat));
                cardsToEat = 0;
                return true;
            }

            restriction = ONLY_PLUS_4;
            canDraw = false;
        }

        if (beforePlayEffect.equals(SKIP_TURN)) {
            MessageSender.send(new ShowTurnSkipped(players, player));
            beforePlayEffect = NO_EFFECT;
            return true;
        }
        return false;
    }

    private boolean tookPlus4(Player player) {
        if (verifier.hasPlusFour(player)) {
            InputGetter w = new DrawOrPlay(player, cardsToEat, "+4");
            return w.getInput() == 2;
        }
        return true;
    }

    private boolean tookPlus2(Player player) {
        if (verifier.hasPlusTwo(player)) {
            InputGetter w = new DrawOrPlay(player, cardsToEat, "+2");
            return w.getInput() == 2;
        }
        return true;

    }

    private Card getCard(Player player, Card topCard, boolean canDraw, Verifier.Restriction restriction) {
        Choice playerChoice;

        while (true) {

            playerChoice = player.choose();

            if (playerChoice.type == Choice.Type.PLAY_CARD) {

                if (verifier.isValid(playerChoice.card, topCard, restriction)) {
                    break;
                }

                MessageSender.send(new ClearTerminal(player), new ShowBoard(players, player, topCard, "solo"), new ShowCardNotValid(player, playerChoice.card));
                continue;
            }

            if (deck.size() > 1 && canDraw) {
                giveCards(player, 1);

                MessageSender.send(new ClearTerminal(player), new ShowBoard(players, player, topCard, "solo"), new ShowDrewCards(players, player, 1));
                continue;
            }

            MessageSender.send(new ClearTerminal(player), new ShowBoard(players, player, topCard, "solo"), new ShowCannotDraw(player));
        }
        return playerChoice.card;
    }

    private void giveCards(Player player, int amount) {
        for (int i = 0; i < amount; i++) {

            if (deck.getLast() == firstPlayedCard) {
                reshuffleDeck();
            }

            if (deck.size() > 1) {
                if (deck.getLast() instanceof SpecialCard specialCard) {
                    specialCard.refresh();
                }
                player.giveCard(deck.removeLast());
            }

        }
    }


    private void prepare() {

        deck = DeckFactory.getShuffledDeck(players.size());
        Collections.shuffle(players);

        assignInitialCards();

        firstPlayedCard = deck.getFirst();

        MessageSender.send(new ShowGameStarted(players));

    }

    private void reshuffleDeck() {
        firstPlayedCard = deck.removeFirst();

        Collections.shuffle(deck);
        deck.addFirst(firstPlayedCard);

    }

    private void assignInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < STARTING_HAND_SIZE; i++) {
                player.giveCard(deck.removeLast());
            }
        }
    }


}
