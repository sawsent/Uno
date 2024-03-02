package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

import java.util.LinkedList;

public class ShowTurnSkipped implements Message {

    private final LinkedList<Player> players = new LinkedList<>();
    private final Player player;
    private final String mode;

    public ShowTurnSkipped(PlayerList players, Player player) {
        this.players.addAll(players);
        this.player = player;
        this.mode = "all";
    }
    @Override
    public void send() {
        if (mode.equals("all")) {
            for (Player p : players) {
                if (p.equals(player)) {
                    continue;
                }
                p.getPrintStream().print(Color.GREEN.ANSI_CODE + player.getName() + "'s turn has been skipped!" + Color.RESET);
            }
        }
        player.getPrintStream().print(Color.RED.ANSI_CODE + "Your turn has been skipped!!" + Color.RESET);
    }
    }
