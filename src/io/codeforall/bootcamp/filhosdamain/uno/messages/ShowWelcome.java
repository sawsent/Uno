package io.codeforall.bootcamp.filhosdamain.uno.messages;

import io.codeforall.bootcamp.filhosdamain.uno.game.Color;

import java.io.PrintStream;

public class ShowWelcome implements Message{

    private final PrintStream out;

    public ShowWelcome (PrintStream out){
        this.out = out;

    }
    @Override
    public void send() {
        String welcome = "\n" +
                "\n" +
                Color.RESET + "░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗░░░██████████╗░█████╗░░░░" + Color.BLUE.ANSI_CODE + "██╗░░██╗███╗░░░██║░█████╗░" + Color.RESET+ "\n" +
                "░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝░░░░░░░██╔═══╝██╔══██╗░░░" + Color.YELLOW.ANSI_CODE + "██║░░██║████╗░░██║██╔══██╗" + Color.RESET+ "\n" +
                "░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░░░░░░░░██║░░░░██║░░██║░░░" + Color.RED.ANSI_CODE + "██║░░██║██╔██╗░██║██║░░██║" + Color.RESET+ "\n" +
                "░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░░░░░░░░██║░░░░██║░░██║░░░" + Color.GREEN.ANSI_CODE + "██║░░██║██║╚██╗██║██║░░██║" + Color.RESET+ "\n" +
                "░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗░░░░░░░██║░░░░╚█████╔╝░░░" + Color.CYAN.ANSI_CODE + "███████║██║░╚████║╚█████╔╝" + Color.RESET+ "\n" +
                "░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝░░░░░░░╚═╝░░░░░╚════╝░░░░" + Color.INFO + "╚══════╝╚═╝░░╚═══╝░╚════╝░" + Color.RESET+ "\n";
        out.print(welcome);

    }
}
