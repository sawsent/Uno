package io.codeforall.bootcamp.filhosdamain.uno.game.cards;

public enum Effect {
    PLUS_2("+2"),
    PLUS_4("+4"),
    SKIP_TURN("X"),
    REVERSE("R"),
    SET_COLOR("SC"),
    NO_EFFECT(":D");
    public final String repr;
    Effect(String repr) {
        this.repr = repr;
    }

}
