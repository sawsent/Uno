package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

public class Board implements Message {
    private final PlayerList players;
    private final Card topCard;
    private final Player player;


    public Board(PlayerList players, Player player, Card topCard) {
        this.players = players;
        this.topCard = topCard;
        this.player = player;
    }

    private String buildBoard() {
        StringBuilder board = new StringBuilder();

        board.append("\n".repeat(100));

        // Add top card
        board.append("\n          [").append(topCard.repr()).append("]\n\n");

        // Add player names and card counts
        for (Player player : players) {

            if (player.equals(this.player)) {
                board.append("-> ");
                board.append(player.getName());
                board.append(" ".repeat(Math.max(0, 17 - player.getName().length())));
                board.append("🂡 x").append(player.getHand().size()).append("\n");
                continue;
            }

            board.append(player.getName());
            board.append(" ".repeat(Math.max(0, 20 - player.getName().length())));
            board.append("🂡 x").append(player.getHand().size()).append("\n");
        }

        return board.toString();
    }

    @Override
    public void send() {
        String currentBoard = buildBoard();
        for (Player p : players) {
            p.getPrintStream().print(currentBoard);
        }
    }
}
