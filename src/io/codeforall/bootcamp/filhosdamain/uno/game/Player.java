package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.ChooseCard;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.ChooseColor;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.DrawOrPlay;
import io.codeforall.bootcamp.filhosdamain.uno.prompts.InputGetter;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Player {
    public static final Player impossiblePlayer = new Player("the one who is nobody but at the same time exists always", System.in, System.out);
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

    public String getName() {
        return name;
    }

    public Choice choose() {
        InputGetter q = new DrawOrPlay(this);
        int input = q.getInput();
        if (input == 2) {
            return new Choice(Choice.Type.TAKE_CARD);
        }

        InputGetter v = new ChooseCard(this);
        input = v.getInput();
        return new Choice(Choice.Type.PLAY_CARD, hand.get(input));


    }

    public Prompt getPrompt() {
        Utils.emptyStream(in);
        return prompt;
    }

    public Color chooseColor() {
        InputGetter getColor = new ChooseColor(this);
        int input = getColor.getInput();
        return Color.convertToCardColor(input);
    }


    public void giveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return this.hand;
    }

}
