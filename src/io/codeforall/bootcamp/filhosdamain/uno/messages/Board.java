package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

public class Board implements Message {
    private final PlayerList players;
    private final Card topCard;

    public Board(PlayerList players, Card topCard) {
        this.players = players;
        this.topCard = topCard;
    }

    private String buildBoard() {
        StringBuilder s  = new StringBuilder();


        String v = "" +
                "                     PLAYERNAME" +
                "                       🂡🂡🂡🂡🂡🂡🂡" +
                "" +
                "PLAYER2                                          PLAYER3" +
                "🂡🂡🂡🂡🂡🂡🂡                {card}                   🂡🂡🂡🂡🂡🂡🂡" +
                "" +
                "" +
                "                  ->   PLAYER4    <-" +
                "                       🂡🂡🂡🂡🂡🂡🂡  "

                ;


        return s.toString();
    }

    @Override
    public void send() {
        Thread t = new Thread(() -> {
            String currentBoard = buildBoard();
            for (Player p : players) {
                p.getPrintStream().print(currentBoard);
            }
        });
        t.start();
    }
}
