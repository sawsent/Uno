package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

public class ShowGameStarted implements Message {
    private final String defaultColor = Color.YELLOW.ANSI_CODE;
    private final PlayerList players;

    public ShowGameStarted(PlayerList players) {
        this.players = players;
    }

    private String buildMessage(Player player) {
        StringBuilder builder = new StringBuilder(defaultColor);
        builder.append("\n\nGame is starting! Good luck! \nPlayer order: ");
        builder.append(getOrderedPlayersString(player));
        builder.append(Color.RESET);
        return builder.toString();
    }

    private String getOrderedPlayersString(Player player) {
        StringBuilder builder = new StringBuilder();

        if (player.equals(players.get(0))) {
            builder.append(Color.GREEN.ANSI_CODE);
            builder.append("YOU");
            builder.append(defaultColor);
        } else {
            builder.append(players.get(0).getName());
        }

        for (int i = 1; i < players.size(); i++) {
            builder.append(" -> ");
            if (player.equals(players.get(i))) {
                builder.append(Color.GREEN.ANSI_CODE);
                builder.append("YOU");
                builder.append(defaultColor);
            } else {
                builder.append(players.get(i).getName());
            }
        }
        builder.append(".");
        return builder.toString();
    }


    @Override
    public void send() {
        for (Player p : players) {
            p.getPrintStream().print(buildMessage(p));
        }

        try {
            for (int i = 5; i > 0; i--) {
                Thread.sleep(1000);
                for (Player p : players) {
                    p.getPrintStream().print(defaultColor + "\n" + i + Color.RESET);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
