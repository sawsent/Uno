package io.codeforall.bootcamp.filhosdamain.uno.prompts;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class DrawOrPlay implements InputGetter {
    private final Player player;
    private final MenuInputScanner menu;

    public DrawOrPlay(Player player, int cardsToDraw, String mandatoryCardType) {
        this.player = player;
        this.menu = new MenuInputScanner(buildOptions(cardsToDraw, mandatoryCardType));
        this.menu.setMessage(buildMessage());
    }

    public DrawOrPlay(Player player) {
        this.player = player;
        this.menu = new MenuInputScanner(buildOptions(1, "a"));
        this.menu.setMessage(buildMessage());
    }

    private String buildMessage() {
        return "Do you want to draw or play a card? ";
    }

    private String[] buildOptions(int cardsToDraw, String mandatoryCardType) {

        String playCardOption = "Play " + mandatoryCardType + " card.";
        String drawCardOption = "Draw " + cardsToDraw + ((cardsToDraw == 1) ? " card." : " cards.");

        return new String[]{playCardOption, drawCardOption};
    }

    @Override
    public int getInput() {
        return player.getPrompt().getUserInput(menu);
    }
}
