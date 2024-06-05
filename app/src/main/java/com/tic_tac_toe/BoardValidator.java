package com.tic_tac_toe;

import com.bridge.game.Game;

public class BoardValidator extends Game {
    private boolean isGameWon;

    public BoardValidator() {
        isGameWon = false;
    }

    public boolean checkWin(Board board){
        char[][] gameBoard = board.board;
        int size = gameBoard.length;

        for (int i = 0; i < size; i++) {
            if (gameBoard[i][0] != '\0' && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2]) {
                isGameWon = true;
                return true;
            }
        }

        for (int i = 0; i < size; i++) {
            if (gameBoard[0][i] != '\0' && gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i]) {
                isGameWon = true;
                return true;
            }
        }

        if (gameBoard[0][0] != '\0' && gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) {
            isGameWon = true;
            return true;
        }

        if (gameBoard[0][2] != '\0' && gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) {
            isGameWon = true;
            return true;
        }

        isGameWon = false;
        return false;
    }

    @Override
    public boolean isGameWon() {
        return isGameWon;
    }
}
