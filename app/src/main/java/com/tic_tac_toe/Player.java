package com.tic_tac_toe;

public enum Player {

    PLAYER_X('X', Utils.BASE_PATH.concat("/assets/general/X.png")),
    PLAYER_O('O', Utils.BASE_PATH.concat("/assets/general/O.png"));

    private final char symbol;
    private final String path;

    Player(char symbol, String path) {
        this.symbol = symbol;
        this.path = path;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getPath() {
        return path;
    }
}
