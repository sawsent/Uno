package io.codeforall.bootcamp.filhosdamain.uno.game;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerList extends CopyOnWriteArrayList<Player> {

    public void reverse(Player player) {
        List<Player> correctOrder = new LinkedList<>();

        while (this.size() != 0) {

            int currentIndex = indexOf(player) - 1;

            if (currentIndex < 0) {
                currentIndex = size()-1;
            }
            correctOrder.add(this.remove(currentIndex));
        }

        for (Player p : correctOrder) {
            this.add(p);
        }

    }

}
