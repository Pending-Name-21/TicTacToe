package com.tic_tac_toe;

import com.bridge.gamesettings.AGameSettings;

public class BoardValidator extends AGameSettings {
    private Board board;

    public BoardValidator(Board board) {
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        String result = checkWinner();
        return !result.isEmpty();
    }
    public String checkWinner() {
        Cell[][] gameBoard = board.getBoard();
        int size = gameBoard.length;
        String result = "";

        // Check rows
        for (int i = 0; i < size; i++) {
            if (gameBoard[i][0] != null && gameBoard[i][1] != null && gameBoard[i][2] != null) {
                char symbol0 = gameBoard[i][0].getSymbol();
                char symbol1 = gameBoard[i][1].getSymbol();
                char symbol2 = gameBoard[i][2].getSymbol();
                if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                    result = "Row " + (i + 1);
                    break;
                }
            }
        }

        // Check columns
        if (result.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (gameBoard[0][i] != null && gameBoard[1][i] != null && gameBoard[2][i] != null) {
                    char symbol0 = gameBoard[0][i].getSymbol();
                    char symbol1 = gameBoard[1][i].getSymbol();
                    char symbol2 = gameBoard[2][i].getSymbol();
                    if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                        result = "Column " + (i + 1);
                        break;
                    }
                }
            }
        }

        // Check diagonals
        if (result.isEmpty()) {
            if (gameBoard[0][0] != null && gameBoard[1][1] != null && gameBoard[2][2] != null) {
                char symbol0 = gameBoard[0][0].getSymbol();
                char symbol1 = gameBoard[1][1].getSymbol();
                char symbol2 = gameBoard[2][2].getSymbol();
                if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                    result = "Diagonal 1";
                }
            }
        }

        if (result.isEmpty()) {
            if (gameBoard[0][2] != null && gameBoard[1][1] != null && gameBoard[2][0] != null) {
                char symbol0 = gameBoard[0][2].getSymbol();
                char symbol1 = gameBoard[1][1].getSymbol();
                char symbol2 = gameBoard[2][0].getSymbol();
                if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                    result = "Diagonal 2";
                }
            }
        }

        return result;
    }
}
