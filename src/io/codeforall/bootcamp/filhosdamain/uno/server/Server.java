package io.codeforall.bootcamp.filhosdamain.uno.server;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Game;
import io.codeforall.bootcamp.filhosdamain.uno.messages.Message;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ServerSocket serverSocket;
    private final List<Connection> connections = new LinkedList<>();
    private final ExecutorService connectionManager = Executors.newFixedThreadPool(6);
    private Game game;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        awaitCommands();
        awaitPlayers();
    }

    private boolean canStart() {

        if (!allPlayersReady()) {
            System.out.println("Not all players are ready!");
            return false;
        }

        if (connections.size() <= 1) {
            System.out.println("Not enough players have joined! (" + connections.size() + " joined, minimum 2)");
            return false;
        }

        return true;
    }

    private boolean allPlayersReady() {
        boolean out = true;
        for (Connection c : connections) {
            if (!c.isReady()) {
                out = false;
            }
        }
        return out;
    }

    private void awaitCommands() {
        Thread t = new Thread(() -> {
            boolean gameStarted = false;
            Prompt prompt = new Prompt(System.in, System.out);
            MenuInputScanner menu = new MenuInputScanner(new String[]{"Players (Show all connected players. GREEN means ready, RED means not ready)", "Rules: display the rules of the game", "Start: start the game with the current number of players (all need to be ready)"});
            menu.setMessage("Command?");

            while (!gameStarted) {

                int command = prompt.getUserInput(menu);

                switch (command) {
                    case 1 -> {
                        if (connections.size() == 0) {
                            System.out.println("No players have joined yet! ");
                        }
                        for (Connection connection : connections) {
                            System.out.println(((connection.isReady()) ? Color.GREEN.ANSI_CODE : Color.RED.ANSI_CODE) + "-> " + connection.getPlayerName() + Color.RESET);
                        }
                    }
                    case 2 -> System.out.println(Message.GAME_RULES);
                    case 3 -> {
                        if (canStart()) {
                            startGame();
                            gameStarted = true;
                        }
                    }
                }
            }
        });
        t.start();
    }

    private void awaitPlayers() {
        Thread t = new Thread(() -> {

            while (true) {

                try {

                    Socket clientSocket = serverSocket.accept();

                    Connection c = new Connection(clientSocket);

                    connectionManager.submit(c);
                    connections.add(c);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void startGame() {
        Thread t = new Thread(() -> {
            this.game = new Game();
            System.out.println("Game starting. The players playing are: ");
            for (Connection c : connections) {
                game.addPlayer(c.getPlayerName(), c.in, c.out);
                System.out.println(c.getPlayerName());
            }
            game.run();
        });
        t.start();
    }
}
