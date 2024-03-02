package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

import java.io.PrintStream;

public class ShowIsReady implements Message {
    private final PrintStream out;
    private final String playerName;
    public ShowIsReady(PrintStream out, String playerName) {
        this.out = out;
        this.playerName = playerName;
    }

    @Override
    public void send() {
        out.print(Color.INFO + "\nWaiting for other players. You are playing as '" + playerName + "'. Good Luck.\n" + Color.RESET);
    }
}
