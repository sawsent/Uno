package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

public interface Message {
    String SERVER_COMMAND_HELP = "'help': lists all commands\n'start': starts the game with the current number of players\n'players': lists all connected players and their names";
    String GAME_RULES =
            "Rules:\n" +
            Color.INFO + "Objective:\n" + Color.RESET +
            "The objective of Uno is to be the first player to play all of the cards from your hand by matching them with the top card of the discard pile either by number, color, or symbol.\n" +
            "\n" +
            Color.INFO + "Setup:\n" + Color.RESET +
            "\n" +
            "Uno is typically played with a deck of 108 cards, \n" +
                    "consisting of number cards (0-9) in four colors (red, yellow, green, blue), \n" +
                    "along with special action cards (Skip [X], Reverse [R], Draw Two [+2]) \n" +
                    "and wild cards (Wild [C] (choose Color), Wild Draw Four [+4]).\n" +
            Color.INFO + "\nGameplay:\n" + Color.RESET +
            "\n" +
            "A random order of play is defined. Play proceeds downwards.\n" +
                    "Each player is dealt 7 cards from the deck.\n" +
            "On their turn, a player must play one card from their hand onto the discard pile, \n" +
                    "following one of the following rules:\n\n" +
            Color.YELLOW.ANSI_CODE + "-> " + Color.RESET + "Play a card that matches the color, number, or symbol of \n" +
                    "   the top card of the discard pile.\n" +
            Color.YELLOW.ANSI_CODE + "-> " + Color.RESET + "Play a Wild card (Wild or Wild Draw Four) regardless of color.\n" +
            Color.YELLOW.ANSI_CODE + "-> " + Color.RESET + "If a player doesn't have a playable card, they must draw a card from the draw pile. \n" +
                    "   They draw until they have a playable card to play.\n" +
            Color.YELLOW.ANSI_CODE + "-> " + Color.RESET + "The player can always draw more cards, I.E if they have a Wild Draw Four \n" +
                    "   that they don't want to play immediately.\n\n" +
            "\n" +
            Color.INFO + "Special Cards:\n" + Color.RESET +
            "\n" +
            Color.YELLOW.ANSI_CODE + "[X]: " + Color.RESET + "When played, the next player in turn is skipped.\n" +
            Color.YELLOW.ANSI_CODE + "[R]: " + Color.RESET + "Changes the direction of play.\n" +
            Color.YELLOW.ANSI_CODE + "[+2]: " + Color.RESET + "The next player in turn must draw two cards and forfeit their turn. \n" +
                    "       However, if they also have a Draw Two, they can play it on top and end their turn. \n" +
                    "       The next player would then need to draw 4. \n" +
                    "       This can keep going until one player doesn't have a Draw Two and has to draw cards from the deck.\n" +
            Color.YELLOW.ANSI_CODE + "[C]: " + Color.RESET + "Can be played on any card, and the player chooses the next color to continue play.\n" +
            Color.YELLOW.ANSI_CODE + "[+4]: " + Color.RESET + "Can be played on any card, and the player chooses the next color to continue play. \n" +
                    "       Additionally, the next player must draw four cards and forfeit their turn. \n" +
                    "       If they also have a Wild Draw Four, the same logic applies as the Draw Two.\n" +
            Color.INFO +"\nWinning:\n" + Color.RESET +
            "\n" +
            "The first player to play all their cards wins the round.\n" +
            "\n";

    void send();
}
