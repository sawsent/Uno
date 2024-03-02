package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

import java.util.LinkedList;

public class ShowOrderReversed implements Message {

    private final LinkedList<Player> players = new LinkedList<>();

    public ShowOrderReversed(PlayerList players) {
        this.players.addAll(players);
    }
    @Override
    public void send() {

        for (Player p : players) {
            p.getPrintStream().print(Color.INFO + "The order is reversed!!" + Color.RESET);
        }
    }
}
