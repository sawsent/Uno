package io.codeforall.bootcamp.filhosdamain.uno.game.cards;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

public class NormalCard implements Card {

    private final int value;
    private final Color color;

    protected NormalCard(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public Effect getBeforePlayEffect() {
        return Effect.NO_EFFECT;
    }

    @Override
    public Effect getAfterPlayEffect() {
        return Effect.NO_EFFECT;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
