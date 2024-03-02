package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;

public class ShowCardNotValid implements Message {
    private final Player player;
    private final Card card;

    public ShowCardNotValid(Player player, Card card) {
        this.player = player;
        this.card = card;
    }

    private String buildMessage() {
        return Color.INFO + "\nThe card [" + card.repr() + Color.INFO + "] is not valid!\n" + Color.RESET;
    }

    @Override
    public void send() {
        player.getPrintStream().print(buildMessage());
    }
}
