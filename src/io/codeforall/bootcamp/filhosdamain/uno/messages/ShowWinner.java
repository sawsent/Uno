package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

import java.util.LinkedList;

public class ShowWinner implements Message {

    private final LinkedList<Player> players = new LinkedList<>();
    private final Player winner;
    private final String mode;
    public ShowWinner(PlayerList players, Player winner) {
        this.players.addAll(players);
        this.winner = winner;
        this.mode = "all";
    }

    @Override
    public void send() {
        if (mode.equals("all")) {
            for (Player p : players) {
                if (p.equals(winner)) {
                    continue;
                }
                p.getPrintStream().print(Color.CYAN.ANSI_CODE + winner.getName() + "HAS WON!!" + Color.RESET);
            }
        }
        winner.getPrintStream().print(Color.CYAN.ANSI_CODE + "YOU WON!! 🎉🎊" + Color.RESET);
    }
}
