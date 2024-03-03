package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

public class ShowCurrentRound implements Message {
    private final PlayerList players;
    private final int currentRound;
    public ShowCurrentRound(PlayerList players, int currentRound) {
        this.players = players;
        this.currentRound = currentRound;
    }

    @Override
    public void send() {
        for (Player p : players) {
            p.getPrintStream().print(Color.INFO + "START OF TURN " + currentRound + Color.RESET + "\n");
        }
    }
}
