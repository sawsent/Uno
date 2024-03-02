package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;

import java.util.Arrays;

public class ShowHand implements Message {
    private final Player player;
    public ShowHand(Player player) {
        this.player = player;
    }

    public static String getPlayerHand(Player player) {
        return "\nYou have: " + Arrays.toString(Utils.toStringArray(player.getHand())) + "\n";
    }

    @Override
    public void send() {
        player.getPrintStream().print(getPlayerHand(player));
    }
}
