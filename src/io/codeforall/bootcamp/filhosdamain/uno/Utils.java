package io.codeforall.bootcamp.filhosdamain.uno;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static void emptyStream(InputStream in) {
        try {
            while (in.available() > 0) {
                int i = in.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
