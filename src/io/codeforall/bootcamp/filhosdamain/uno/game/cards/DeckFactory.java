package io.codeforall.bootcamp.filhosdamain.uno.game.cards;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

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
                deck.add(new SpecialCard(color, NO_EFFECT, REVERSE));
                deck.add(new SpecialCard(color, PLUS_2, NO_EFFECT));
                deck.add(new SpecialCard(color, SKIP_TURN, NO_EFFECT));
            }

        }

        for (int i = 0; i < 4; i++) {
            deck.add(new SpecialCard(BLACK, PLUS_4, SET_COLOR));
            deck.add(new SpecialCard(BLACK, NO_EFFECT, SET_COLOR));

        }

        return deck;
    }
}
