package io.codeforall.bootcamp.filhosdamain.uno.server;

import io.codeforall.bootcamp.filhosdamain.uno.game.Message;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Connection implements Runnable {
    public final InputStream in;
    public final PrintStream out;
    private boolean ready = false;
    private String playerName = "placeholder";


    public Connection(Socket socket) throws IOException {
        in = socket.getInputStream();
        out = new PrintStream(socket.getOutputStream());
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isReady() {
        return ready;
    }


    @Override
    public void run() {

        StringInputScanner askName = new StringInputScanner();
        askName.setMessage("What is your name? \n>> ");

        String[] options = {"Ready up!", "Change Name", "Check Rules"};
        MenuInputScanner getCommand = new MenuInputScanner(options);

        Prompt prompt = new Prompt(in, out);

        out.println("Hello! ");

        playerName = prompt.getUserInput(askName);

        while (true) {

            int choice = prompt.getUserInput(getCommand);
            if (choice == 1) {
                ready = true;
                break;
            }
            switch (choice) {
                case 2 -> playerName = prompt.getUserInput(askName);
                case 3 -> out.print(Message.GAME_RULES);
            }
        }
    }
}