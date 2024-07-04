package com.tic_tac_toe;

public class Winner {

    private String winnerLine;
    private String winnerPlayer;

    public Winner(String winnerLine, String winnerPlayer) {
        this.winnerLine = winnerLine;
        this.winnerPlayer = winnerPlayer;
    }

    public String getWinnerLine() {
        return winnerLine;
    }

    public String getWinnerPlayer() {
        return winnerPlayer;
    }

    @Override
    public String toString() {
        return winnerLine + "\n" + winnerPlayer;
    }
}
