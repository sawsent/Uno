package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

public class ShowCardNotValid implements Message {
    private String fullMessage;

    public ShowCardNotValid(Player player, Card card) {
        buildMessage();
    }

    private void buildMessage() {

    }

    //private

    @Override
    public void send() {

    }
}
