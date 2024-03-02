package io.codeforall.bootcamp.filhosdamain.uno.game;

public enum Color {
    YELLOW("\u001b[33m"),
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    BLUE("\u001b[36m"),
    BLACK("\u001b[1;30m"),
    WHITE("\u001b[37m"),
    CYAN("\u001b[36m");

    public static final String RESET = "\u001b[1;37m";
    public static final String INFO = "\u001b[35m";
    public final String ANSI_CODE;
    Color(String colorCode) {
        this.ANSI_CODE = colorCode;
    }

    public static Color convertToCardColor(int input) {
        return switch (input) {
            case 1 -> YELLOW;
            case 2 -> GREEN;
            case 3 -> BLUE;
            case 4 -> RED;
            default -> BLACK;
        };
    }
}
