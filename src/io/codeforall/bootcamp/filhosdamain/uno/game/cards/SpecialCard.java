package io.codeforall.bootcamp.filhosdamain.uno.game.cards;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

public class SpecialCard implements Card {
    private Color color;
    private final Effect beforePlayEffect;
    private final Effect afterPlayEffect;
    private final int value;

    public SpecialCard(int value, Color color, Effect beforePlayEffect, Effect afterPlayEffect) {
        this.value = value;
        this.color = color;
        this.beforePlayEffect = beforePlayEffect;
        this.afterPlayEffect = afterPlayEffect;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Effect getBeforePlayEffect() {
        return beforePlayEffect;
    }

    @Override
    public Effect getAfterPlayEffect() {
        return afterPlayEffect;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String repr() {
        return color.ANSI_CODE + ((beforePlayEffect == Effect.NO_EFFECT) ? afterPlayEffect.repr : beforePlayEffect.repr) + Color.RESET;
    }

    public void refresh() {
        if (afterPlayEffect == Effect.SET_COLOR) {
            setColor(Color.BLACK);
        }
    }
}
