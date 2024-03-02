package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;

public class ShowCannotDraw implements Message {
    private String fullMessage;
    private final Player player;
    public ShowCannotDraw(Player player) {
        this.player = player;
        fullMessage = Color.INFO + "\nYou can't draw right now!\n" + Color.RESET;
    }
    @Override
    public void send() {
        player.getPrintStream().print(fullMessage);
    }
}
