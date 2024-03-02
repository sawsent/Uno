package io.codeforall.bootcamp.filhosdamain.uno.messages;

public interface Message {
    String SERVER_COMMAND_HELP = "'help': lists all commands\n'start': starts the game with the current number of players\n'players': lists all connected players and their names";
    String GAME_RULES = "go internet";

    void send();
}
