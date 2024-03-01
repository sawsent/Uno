package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

import java.io.InputStream;

import java.io.PrintStream;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private final List<Card> hand = new LinkedList<>();
    private String name;

    public Player(String name, InputStream in, PrintStream out) {

    }

    public Card getCard() {
        return null;
    }

    public String getName() {
        return name;
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

    public List<Card> getHand() {
        return hand;
    }

    public void setName(String name) {
        this.name = name;

    }
}
