package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;

public class ShowHand implements Message {

    public ShowHand(Player player) {


    }

    public static String getPlayerHand(Player player) {

        return "";
    }

    @Override
    public void send() {

    }
}
