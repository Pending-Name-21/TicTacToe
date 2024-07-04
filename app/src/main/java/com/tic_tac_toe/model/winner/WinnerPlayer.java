package com.tic_tac_toe.model.winner;

import com.tic_tac_toe.utils.SourcePaths;

public enum WinnerPlayer {

    WINNER_PLAYER_X(SourcePaths.BASE_PATH.concat("/assets/general/Player_X_Won.png")),
    WINNER_PLAYER_O(SourcePaths.BASE_PATH.concat("/assets/general/Player_O_Won.png")),
    TIE(SourcePaths.BASE_PATH.concat("/assets/general/Tie.png"));

    private final String path;

    WinnerPlayer(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
