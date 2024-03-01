package io.codeforall.bootcamp.filhosdamain.uno;

import io.codeforall.bootcamp.filhosdamain.uno.server.Server;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            new Server(getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getPort() {
        while (true) {
            System.out.print("Port?\n>> ");
            try {
                return (new Scanner(System.in)).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid port Integer");
            }
        }
    }
}
