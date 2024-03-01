package io.codeforall.bootcamp.filhosdamain.uno.game.cards;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

public interface Card {

    Effect getBeforePlayEffect();
    Effect getAfterPlayEffect();
    int getValue();
    Color getColor();
    String repr();

}
