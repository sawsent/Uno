package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.DeckFactory;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.SpecialCard;
import io.codeforall.bootcamp.filhosdamain.uno.server.MessageSender;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;

import static io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect.*;

public class Game {

    private final MessageSender messageSender;
    private PlayerList players = new PlayerList();
    private final LinkedList<Card> deck = DeckFactory.getDeck();
    private final Verifier verifier = new Verifier();


    public Game(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean addPlayer(String name, InputStream in, OutputStream out) {
        return players.add(new Player(name, in, out));
    }


    public void run() {
        prepare();

        Effect beforePlayEffect = Effect.NO_EFFECT;
        Effect afterPlayEffect = Effect.NO_EFFECT;

        Card topCard;

        while (true) {


            for (Player player : players) {

                // print board to all players
                topCard = deck.getFirst();

                if (beforePlayEffect.equals(PLUS_2)) {
                    // verify if player has +2 card
                    // option: wanna play or take +2?

                } else if (beforePlayEffect.equals(PLUS_4)) {
                    // verify if player has +4 card
                    // option: wanna play or take +4?

                } else if (beforePlayEffect.equals(SKIP_TURN)) {
                    continue;
                }


                Card playedCard = getCard(player, topCard);

                deck.addFirst(playedCard);

                beforePlayEffect = playedCard.getBeforePlayEffect();
                afterPlayEffect = playedCard.getAfterPlayEffect();

                if (afterPlayEffect.equals(REVERSE)) {
                    players.reverse(player);
                    break;
                } else if (afterPlayEffect.equals(SET_COLOR)) {
                    Color color = player.getColor();
                    ((SpecialCard) playedCard).setColor(color);
                }


            }



        }

    }

    private Card getCard(Player player, Card topCard) {
        boolean valid = false;
        Choice playerChoice = null;

        while (!valid) {
            // print some message

            playerChoice = player.choose();

            if (playerChoice.type == Choice.Type.PLAY_CARD) {
                valid = verifier.isValid(playerChoice.card, topCard);
                continue;
            }

            // PLAYER TOOK A CARD.
            player.giveCard(deck.removeLast());

        }
        return playerChoice.card;
    }


    private void prepare() {
        Collections.shuffle(deck);
        Collections.shuffle(players);
        assignInitialCards();

        // message goes here \/


    }

    private void assignInitialCards() {
        for (Player player : players) {
            for (int i=0; i<7;i++) {
                player.giveCard(deck.removeLast());
            }
        }
    }



}
