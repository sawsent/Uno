package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;

public class CannotDraw implements Message {
    private String fullMessage;
    private final Player player;
    public CannotDraw(Player player) {
        this.player = player;
        fullMessage = player.getName() + ", you can't draw right now!\n";
        send();
    }
    @Override
    public void send() {
        player.getPrintStream().print(fullMessage);
    }
}
