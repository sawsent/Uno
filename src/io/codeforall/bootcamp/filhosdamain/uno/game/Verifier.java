package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect;

public class Verifier {
    public boolean isValid(Card playerChoice, Card last) {
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
}
