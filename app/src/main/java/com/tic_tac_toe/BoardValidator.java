package com.tic_tac_toe;

import com.bridge.game.Game;

public class BoardValidator extends Game {
    private boolean isGameWon;

    public BoardValidator() {
        isGameWon = false;
    }

    public boolean checkWin(Board board) {
        char[][] gameBoard = board.getBoard();
        int size = gameBoard.length;
        boolean foundWin = false;

        for (int i = 0; i < size; i++) {
            if (gameBoard[i][0] != '\0'
                    && gameBoard[i][0] == gameBoard[i][1]
                    && gameBoard[i][1] == gameBoard[i][2]) {
                foundWin = true;
                break;
            }
        }

        if (!foundWin) {
            for (int i = 0; i < size; i++) {
                if (gameBoard[0][i] != '\0'
                        && gameBoard[0][i] == gameBoard[1][i]
                        && gameBoard[1][i] == gameBoard[2][i]) {
                    foundWin = true;
                    break;
                }
            }
        }

        if (!foundWin) {
            if (gameBoard[0][0] != '\0'
                    && gameBoard[0][0] == gameBoard[1][1]
                    && gameBoard[1][1] == gameBoard[2][2]) {
                foundWin = true;
            }
        }

        if (!foundWin) {
            if (gameBoard[0][2] != '\0'
                    && gameBoard[0][2] == gameBoard[1][1]
                    && gameBoard[1][1] == gameBoard[2][0]) {
                foundWin = true;
            }
        }

        isGameWon = foundWin;
        return isGameWon;
    }

    @Override
    public boolean isGameWon() {
        return isGameWon;
    }
}
