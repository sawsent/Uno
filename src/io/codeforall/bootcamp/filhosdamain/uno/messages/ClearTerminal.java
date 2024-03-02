package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

import java.util.LinkedList;

public class ClearTerminal implements Message {
    private LinkedList<Player> players = new LinkedList<>();
    public ClearTerminal(Player player) {
        players.add(player);
    }
    public ClearTerminal(PlayerList players) {
        this.players.addAll(players);
        send();
    }

    @Override
    public void send() {
        for (Player p : players) {
            p.getPrintStream().print("\n".repeat(100));
        }
    }
}
