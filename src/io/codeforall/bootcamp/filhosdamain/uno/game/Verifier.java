package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect;

public class Verifier {
    public boolean isValid(Card playerChoice, Card last, Restriction restriction) {
        if (last.getBeforePlayEffect() == Effect.PLUS_2 && playerChoice.getBeforePlayEffect() != Effect.PLUS_2) {
            return false;
        }

        if (last.getBeforePlayEffect() == Effect.PLUS_4 && playerChoice.getBeforePlayEffect() != Effect.PLUS_4) {
            return false;
        }

        if (last.getColor() == playerChoice.getColor() || last.getValue() == playerChoice.getValue() || playerChoice.getColor() == Color.BLACK) {
            return true;
        }
        return false;
    }
    public boolean hasPlusTwo (Player player) {
        for (Card c : player.getHand()){
            if (c.getBeforePlayEffect() == Effect.PLUS_2){
                return true;
            }
        }
        return false;
    }

    public boolean hasPlusFour (Player player) {
        for (Card c : player.getHand()){
            if (c.getBeforePlayEffect() == Effect.PLUS_4){
                return true;
            }
        }
        return false;
    }
    public enum Restriction {
        ONLY_PLUS_2,
        ONLY_PLUS_4,
        NO_RESTRICTION;
    }
}
