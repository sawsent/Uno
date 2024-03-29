package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

public class Choice {
    public final Type type;
    public final Card card;

    public Choice(Type type, Card card) {
        this.type = type;
        this.card = card;
    }
    public Choice(Type type) {
        this.type = type;
        this.card = null;
    }

    enum Type {
        TAKE_CARD,
        PLAY_CARD;
    }

}
