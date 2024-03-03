package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

import java.util.LinkedList;

public class ShowBoard implements Message {
    private final LinkedList<Player> players = new LinkedList<>();
    private final Card topCard;
    private final Player currentPlayer;
    private final String mode;


    public ShowBoard(PlayerList players, Player player, Card topCard, String mode) {
        this.players.addAll(players);
        this.topCard = topCard;
        this.currentPlayer = player;
        this.mode = mode;
    }

    private String buildBoard(Player player) {
        StringBuilder board = new StringBuilder();

        for (Player p : players) {

            if (p.equals(this.currentPlayer)) {
                board.append("-> ");
                board.append(Color.GREEN.ANSI_CODE + ((p == player) ? "YOU" : p.getName()) + Color.RESET);
                board.append(" ".repeat(Math.max(0, 17 - ((p == player) ? 3 : p.getName().length()))));
                board.append("🂡 ").append((p.getHand().size() > 4 & p != player) ? "4+" : ("x" + p.getHand().size())).append("\n");
                continue;
            }
            board.append("   ");
            board.append(((p == player) ? "YOU" : p.getName()));
            board.append(" ".repeat(Math.max(0, 17 - ((p == player) ? 3 : p.getName().length()))));
            board.append("🂡 ").append((p.getHand().size() > 4 && p != player) ? "4+" : ("x" + p.getHand().size())).append("\n");
        }

        board.append("\n   Top Card -> [").append(topCard.repr()).append("]\n");

        if (currentPlayer == player) {
            board.append("\n" + ShowHand.getPlayerHand(player));
        }

        return board.toString();
    }

    @Override
    public void send() {
        if (mode.equals("all")) {

            for (Player p : players) {
                if (p.equals(currentPlayer)) {
                    continue;
                }
                p.getPrintStream().print(buildBoard(p));
            }
        }
        currentPlayer.getPrintStream().print(buildBoard(currentPlayer));
    }

}
