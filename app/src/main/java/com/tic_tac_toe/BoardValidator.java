package com.tic_tac_toe;

import com.bridge.gamesettings.AGameSettings;
import com.bridge.renderHandler.sprite.Sprite;

public class BoardValidator extends AGameSettings {
    private Board board;

    public BoardValidator(Board board) {
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        Sprite[][] gameBoard = board.getBoard();
        int size = gameBoard.length;
        boolean foundWin = false;
        String path0;
        String path1;
        String path2;

        for (int i = 0; i < size; i++) {
            if (gameBoard[i][0] != null && gameBoard[i][1] != null && gameBoard[i][2] != null) {
                path0 = gameBoard[i][0].getPath();
                path1 = gameBoard[i][1].getPath();
                path2 = gameBoard[i][2].getPath();
                if (path0.equals(path1) && path1.equals(path2)) {
                    foundWin = true;
                    break;
                }
            }
        }

        if (!foundWin) {
            for (int i = 0; i < size; i++) {
                if (gameBoard[0][i] != null && gameBoard[1][i] != null && gameBoard[2][i] != null) {
                    path0 = gameBoard[0][i].getPath();
                    path1 = gameBoard[1][i].getPath();
                    path2 = gameBoard[2][i].getPath();
                    if (path0.equals(path1) && path1.equals(path2)) {
                        foundWin = true;
                        break;
                    }
                }
            }
        }

        if (!foundWin) {
            if (gameBoard[0][0] != null && gameBoard[1][1] != null && gameBoard[2][2] != null) {
                path0 = gameBoard[0][0].getPath();
                path1 = gameBoard[1][1].getPath();
                path2 = gameBoard[2][2].getPath();
                if (path0.equals(path1) && path1.equals(path2)) {
                    foundWin = true;
                }
            }
        }

        if (!foundWin) {
            if (gameBoard[0][2] != null && gameBoard[1][1] != null && gameBoard[2][0] != null) {
                path0 = gameBoard[0][2].getPath();
                path1 = gameBoard[1][1].getPath();
                path2 = gameBoard[2][0].getPath();
                if (path0.equals(path1) && path1.equals(path2)) {
                    foundWin = true;
                }
            }
        }
        return foundWin;
    }
}
