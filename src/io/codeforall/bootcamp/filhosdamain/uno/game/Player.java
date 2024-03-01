package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Player {

    private final List<Card> hand = new LinkedList<>();
    private final PrintStream out;
    private final InputStream in;
    private String name;
    private final Prompt prompt;

    public Player(String name, InputStream in, PrintStream out) {
        this.name = name;
        this.out = out;
        this.in = in;
        this.prompt = new Prompt(in, out);
    }

    public PrintStream getPrintStream() {
        return out;
    }

    public Card getCard() {
        return null;
    }

    public String getName() {
        return name;
    }

    public Choice choose() {
        MenuInputScanner q = new MenuInputScanner(new String[] {"Play a card", "Take a card from the deck"});
        q.setMessage("What do you want to do? ");

        if (prompt.getUserInput(q) == 2) {
            return new Choice(Choice.Type.TAKE_CARD);
        }

        MenuInputScanner v = new MenuInputScanner(Utils.toStringArray(hand));
        v.setMessage("Choose your card! ");
        int input = prompt.getUserInput(v);

        return new Choice(Choice.Type.PLAY_CARD, hand.get(input-1));


    }

    public Color chooseColor() {
        return null;
    }

    public void giveCard(Card card) {
        hand.add(card);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public int getInput(MenuInputScanner w) {
        return prompt.getUserInput(w);
    }
}
