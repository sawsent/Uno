package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

import java.util.LinkedList;

public class ShowBoard implements Message {
    private final LinkedList<Player> players = new LinkedList<>();
    private final Card topCard;
    private final Player player;
    private final String mode;


    public ShowBoard(PlayerList players, Player player, Card topCard, String mode) {
        this.players.addAll(players);
        this.topCard = topCard;
        this.player = player;
        this.mode = mode;
    }

    private String buildBoardForCurrentPlayer() {
        StringBuilder board = new StringBuilder();

        for (Player player : players) {

            if (player.equals(this.player)) {
                board.append("-> ");
                board.append(Color.GREEN.ANSI_CODE + "YOU" + Color.RESET);
                board.append(" ".repeat(Math.max(0, 17 - player.getName().length())));
                board.append("🂡 ").append((player.getHand().size() > 4) ? "4+" : ("x" + player.getHand().size())).append("\n");
                continue;
            }

            board.append(player.getName());
            board.append(" ".repeat(Math.max(0, 20 - player.getName().length())));
            board.append("🂡 ").append((player.getHand().size() > 4) ? "4+" : ("x" + player.getHand().size())).append("\n");
        }

        board.append("\n   Top Card -> [").append(topCard.repr()).append("]\n");
        board.append(ShowHand.getPlayerHand(player));

        return board.toString();
    }


    private String buildBoard() {
        StringBuilder board = new StringBuilder();

        for (Player player : players) {

            if (player.equals(this.player)) {
                board.append("-> ");
                board.append(Color.GREEN.ANSI_CODE + player.getName() + Color.RESET);
                board.append(" ".repeat(Math.max(0, 17 - player.getName().length())));
                board.append("🂡 ").append((player.getHand().size() > 4) ? "4+" : ("x" + player.getHand().size())).append("\n");
                continue;
            }

            board.append(player.getName());
            board.append(" ".repeat(Math.max(0, 20 - player.getName().length())));
            board.append("🂡 ").append((player.getHand().size() > 4) ? "4+" : ("x" + player.getHand().size())).append("\n");
        }

        board.append("\n   Top Card -> [").append(topCard.repr()).append("]\n");

        return board.toString();
    }

    @Override
    public void send() {
        if (mode.equals("all")) {
            String currentBoard = buildBoard();
            for (Player p : players) {
                if (p.equals(player)) {
                    continue;
                }
                p.getPrintStream().print(currentBoard);
            }
        }
        player.getPrintStream().print(buildBoardForCurrentPlayer());
    }

}
