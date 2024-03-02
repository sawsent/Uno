package io.codeforall.bootcamp.filhosdamain.uno.prompts;

import io.codeforall.bootcamp.filhosdamain.uno.Utils;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class ChooseCard implements InputGetter {
    private final MenuInputScanner menu;
    private final Player player;
    public ChooseCard(Player player) {
        this.player = player;
        menu = new MenuInputScanner(buildOptions());
        menu.setMessage(buildMessage());
    }

    private String buildMessage() {
        return "Choose the card you want to play!";
    }

    private String[] buildOptions() {
        return Utils.toStringArray(player.getHand());
    }

    @Override
    public int getInput() {
        return player.getPrompt().getUserInput(menu)-1;
    }
}
