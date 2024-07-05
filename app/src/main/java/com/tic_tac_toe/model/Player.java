package com.tic_tac_toe.model;

import com.tic_tac_toe.utils.SourcePaths;

public enum Player {
    PLAYER_X('X', SourcePaths.BASE_PATH.concat("/assets/general/symbols/X/X")),
    PLAYER_O('O', SourcePaths.BASE_PATH.concat("/assets/general/symbols/O/O"));

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
