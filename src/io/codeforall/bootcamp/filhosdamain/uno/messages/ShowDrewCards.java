package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

public class ShowDrewCards implements Message {
    private final PlayerList players;
    private final Player player;
    private final int cardsDrawn;

    public ShowDrewCards(PlayerList players, Player player, int cardsDrawn) {
        this.players = players;
        this.player = player;
        this.cardsDrawn = cardsDrawn;
    }

    private String buildMessage(Player player, int cardsDrawn) {
        return player.getName() + " drew " + cardsDrawn + " cards.\n";
    }

    private String buildMessageForCurrentPlayer(int cardsDrawn) {
        return Color.GREEN.ANSI_CODE + "YOU" + Color.RESET + "drew " + cardsDrawn + ((cardsDrawn == 1) ? " card.\n" : " cards.\n");
    }


    @Override
    public void send() {
        String fullMessage = buildMessage(player, cardsDrawn);
        for (Player p : players) {
            if (p.equals(player)) {
                p.getPrintStream().print(buildMessageForCurrentPlayer(cardsDrawn));
                continue;
            }
            p.getPrintStream().print(fullMessage);
        }
    }
}
