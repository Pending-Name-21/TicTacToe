package com.tic_tac_toe;

public enum WinnerPlayer {

    WINNER_PLAYER_X (Utils.BASE_PATH.concat("/assets/general/Player_X_Won.png")),
    WINNER_PLAYER_O (Utils.BASE_PATH.concat("/assets/general/Player_O_Won.png")),
    TIE (Utils.BASE_PATH.concat("/assets/general/Tie.png"));

    private  final  String path;

    WinnerPlayer (String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
