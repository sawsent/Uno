package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;

public class ShowCannotDraw implements Message {
    private String fullMessage;
    private final Player player;
    public ShowCannotDraw(Player player) {
        this.player = player;
        fullMessage = player.getName() + ", you can't draw right now!\n";
    }
    @Override
    public void send() {
        player.getPrintStream().print(fullMessage);
    }
}
