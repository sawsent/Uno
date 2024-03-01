package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class Player {

    private final List<Card> hand = new LinkedList<>();

    public Player(String name, InputStream in, OutputStream out) {

    }

    public Card getCard() {
        return null;
    }

    public Choice choose() {
        return null;
    }

    public void giveCard(Card card) {
        hand.add(card);
    }

    public Color getColor() {
        return null;
    }
}
