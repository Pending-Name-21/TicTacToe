package com.tic_tac_toe.listeners;

import com.bridge.gamesettings.AGameSettings;
import com.tic_tac_toe.handler.AbstractBoard;
import com.tic_tac_toe.model.Cell;
import com.tic_tac_toe.model.winner.Winner;
import com.tic_tac_toe.model.winner.WinnerLine;
import com.tic_tac_toe.model.winner.WinnerPlayer;

public class BoardValidator extends AGameSettings {

    private AbstractBoard abstractBoard;
    private Winner winner;

    public BoardValidator(AbstractBoard abstractBoard) {
        this.abstractBoard = abstractBoard;
    }

    @Override
    public boolean isGameOver() {
        winner = checkWinner();
        return winner != null;
    }

    public Winner getWinner() {
        return winner;
    }

    private String getPlayerWin(char symbol) {
        return symbol == 'X'
                ? WinnerPlayer.WINNER_PLAYER_X.getPath()
                : WinnerPlayer.WINNER_PLAYER_O.getPath();
    }

    public Winner checkWinner() {
        Cell[][] gameBoard = abstractBoard.getBoard();
        int size = gameBoard.length;

        // Check rows
        for (int i = 0; i < size; i++) {
            if (gameBoard[i][0] != null && gameBoard[i][1] != null && gameBoard[i][2] != null) {
                char symbol0 =
                        gameBoard[i][0].wasUsed() ? gameBoard[i][0].getPlayer().getSymbol() : '\0';
                char symbol1 =
                        gameBoard[i][1].wasUsed() ? gameBoard[i][1].getPlayer().getSymbol() : '\0';
                char symbol2 =
                        gameBoard[i][2].wasUsed() ? gameBoard[i][2].getPlayer().getSymbol() : '\0';
                if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                    winner = new Winner(WinnerLine.ROW.getPath(i), getPlayerWin(symbol0));
                    break;
                }
            }
        }

        // Check columns
        if (winner == null) {
            for (int i = 0; i < size; i++) {
                if (gameBoard[0][i] != null && gameBoard[1][i] != null && gameBoard[2][i] != null) {
                    char symbol0 =
                            gameBoard[0][i].wasUsed()
                                    ? gameBoard[0][i].getPlayer().getSymbol()
                                    : '\0';
                    char symbol1 =
                            gameBoard[1][i].wasUsed()
                                    ? gameBoard[1][i].getPlayer().getSymbol()
                                    : '\0';
                    char symbol2 =
                            gameBoard[2][i].wasUsed()
                                    ? gameBoard[2][i].getPlayer().getSymbol()
                                    : '\0';
                    if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                        winner = new Winner(WinnerLine.COLUMN.getPath(i), getPlayerWin(symbol0));
                        break;
                    }
                }
            }
        }

        // Check diagonals
        if (winner == null) {
            if (gameBoard[0][0] != null && gameBoard[1][1] != null && gameBoard[2][2] != null) {
                char symbol0 =
                        gameBoard[0][0].wasUsed() ? gameBoard[0][0].getPlayer().getSymbol() : '\0';
                char symbol1 =
                        gameBoard[1][1].wasUsed() ? gameBoard[1][1].getPlayer().getSymbol() : '\0';
                char symbol2 =
                        gameBoard[2][2].wasUsed() ? gameBoard[2][2].getPlayer().getSymbol() : '\0';
                if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                    winner = new Winner(WinnerLine.DIAGONAL.getPath(0), getPlayerWin(symbol0));
                    return winner;
                }
            }
        }

        if (winner == null) {
            if (gameBoard[0][2] != null && gameBoard[1][1] != null && gameBoard[2][0] != null) {
                char symbol0 =
                        gameBoard[0][2].wasUsed() ? gameBoard[0][2].getPlayer().getSymbol() : '\0';
                char symbol1 =
                        gameBoard[1][1].wasUsed() ? gameBoard[1][1].getPlayer().getSymbol() : '\0';
                char symbol2 =
                        gameBoard[2][0].wasUsed() ? gameBoard[2][0].getPlayer().getSymbol() : '\0';
                if (symbol0 != '\0' && symbol0 == symbol1 && symbol1 == symbol2) {
                    winner = new Winner(WinnerLine.DIAGONAL.getPath(1), getPlayerWin(symbol0));
                    return winner;
                }
            }
        }

        // Check for a tie
        boolean isTie = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!gameBoard[i][j].wasUsed()) {
                    isTie = false;
                    break;
                }
            }
            if (!isTie) break;
        }

        if (isTie) {
            winner = new Winner("", WinnerPlayer.TIE.getPath());
        }

        return winner;
    }
}
