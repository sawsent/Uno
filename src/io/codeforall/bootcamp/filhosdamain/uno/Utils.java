package io.codeforall.bootcamp.filhosdamain.uno;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Utils {
    public static final int STARTING_HAND_SIZE = 7;
    public static void emptyStream(InputStream in) {
        try {
            while (in.available() > 0) {
                int i = in.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] toStringArray(List<Card> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).repr();
        }
        return array;
    }
}
