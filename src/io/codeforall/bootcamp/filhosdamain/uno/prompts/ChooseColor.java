package io.codeforall.bootcamp.filhosdamain.uno.prompts;

import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class ChooseColor implements InputGetter {
    private final MenuInputScanner menu;
    private final Player player;
    public ChooseColor(Player player) {
        this.player = player;
        menu = new MenuInputScanner(buildOptions());
        menu.setMessage(buildMessage());
    }

    private String buildMessage() {
        return "What color do you want to be played next? ";
    }

    private String[] buildOptions() {
        return new String[]{"Yellow", "Green", "Blue", "Red"};
    }

    @Override
    public int getInput() {
        return player.getPrompt().getUserInput(menu);
    }
}
