package io.codeforall.bootcamp.filhosdamain.uno.game.cards;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static io.codeforall.bootcamp.filhosdamain.uno.game.Color.*;
import static io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect.*;

public class DeckFactory {

    public static LinkedList<Card> getDeck() {


        LinkedList<Card> deck = new LinkedList<>();

        for (Color color : new Color[]{YELLOW, RED, GREEN, BLUE}) {
            deck.add(new NormalCard(0, color));

            for (int i = 1; i < 10; i++) {
                deck.add(new NormalCard(i, color));
                deck.add(new NormalCard(i, color));
            }

            for (int i = 0; i < 2; i++) {
                deck.add(new SpecialCard(-1, color, NO_EFFECT, REVERSE));
                deck.add(new SpecialCard(-2, color, PLUS_2, NO_EFFECT));
                deck.add(new SpecialCard(-3, color, SKIP_TURN, NO_EFFECT));
            }

        }

        for (int i = 0; i < 4; i++) {
            deck.add(new SpecialCard(-4, BLACK, PLUS_4, SET_COLOR));
            deck.add(new SpecialCard(-5, BLACK, NO_EFFECT, SET_COLOR));

        }

        return deck;
    }

    public static LinkedList<Card> getDeck(int nrOfPlayers) {
        LinkedList<Card> deck = new LinkedList<>();

        for (double i = 0; i < nrOfPlayers / 6.0; i++) {
            deck.addAll(getDeck());
        }
        return deck;
    }

    public static LinkedList<Card> getShuffledDeck(int nrOfPlayers) {

        LinkedList<Card> deck = getDeck(nrOfPlayers);
        Collections.shuffle(deck);
        while (deck.getFirst().getAfterPlayEffect() == SET_COLOR) {
            deck.addLast(deck.removeFirst());
        }
        return deck;

    }
}
