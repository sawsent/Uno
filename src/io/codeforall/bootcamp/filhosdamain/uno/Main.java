package io.codeforall.bootcamp.filhosdamain.uno;

import io.codeforall.bootcamp.filhosdamain.uno.server.Server;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Prompt prompt = new Prompt(System.in, System.out);
        while (true) {
            try {
                new Server(getPort(prompt));
                break;
            } catch (Exception e) {
                System.out.println("Illegal port number!");
            }
        }

    }

    private static int getPort(Prompt prompt) {
        IntegerInputScanner scanner = new IntegerInputScanner();
        scanner.setMessage("Choose a port for the game \n>> ");
        return prompt.getUserInput(scanner);
    }
}
