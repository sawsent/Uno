package io.codeforall.bootcamp.filhosdamain.uno.server;

import io.codeforall.bootcamp.filhosdamain.uno.game.Game;
import io.codeforall.bootcamp.filhosdamain.uno.game.Message;

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
    private final MessageSender messageSender = new MessageSender();
    private Game game;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        awaitCommands();
        awaitPlayers();
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
            Scanner scanner = new Scanner(System.in);
            boolean gameStarted = false;
            while (!gameStarted) {
                System.out.print("Command (help to list commands)?\n>> ");

                String command = scanner.nextLine().toLowerCase();

                switch (command) {
                    case "help" -> System.out.println(Message.SERVER_COMMAND_HELP);
                    case "players" -> {
                        for (Connection connection : connections) {
                            System.out.println(connection.getPlayerName());
                        }
                    }
                    case "start" -> {
                        if (allPlayersReady()) {
                            startGame();
                            gameStarted = true;
                            break;
                        }
                        System.out.println("Not all players are ready! ");
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
            this.game = new Game(messageSender);
            for (Connection c : connections) {
                game.addPlayer(c.getPlayerName(), c.in, c.out);
            }
            game.run();
        });
        t.start();
    }
}
