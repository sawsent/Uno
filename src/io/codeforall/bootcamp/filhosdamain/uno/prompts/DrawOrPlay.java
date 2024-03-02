package io.codeforall.bootcamp.filhosdamain.uno.prompts;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
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
        StringBuilder out = new StringBuilder();
        out.append("Do you want to");
        out.append(Color.RED.ANSI_CODE).append("draw").append(Color.RESET);
        out.append(" or ");
        out.append(Color.GREEN.ANSI_CODE).append("play").append(Color.RESET);
        out.append(" a card? ");
        return out.toString();
    }

    private String[] buildOptions(int cardsToDraw, String mandatoryCardType) {

        String playCardOption = "Play " + mandatoryCardType + " card.";
        String drawCardOption = "Draw " + ((cardsToDraw == 1) ? "a card." : (cardsToDraw + " cards."));

        return new String[]{playCardOption, drawCardOption};
    }

    @Override
    public int getInput() {
        return player.getPrompt().getUserInput(menu);
    }
}
