package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.tic_tac_toe.handler.AbstractBoard;
import com.tic_tac_toe.listeners.BoardValidator;
import com.tic_tac_toe.model.Cell;
import com.tic_tac_toe.model.Player;
import com.tic_tac_toe.model.winner.Winner;
import com.tic_tac_toe.model.winner.WinnerLine;
import com.tic_tac_toe.model.winner.WinnerPlayer;
import org.junit.jupiter.api.Test;

public class BoardValidatorTest {

    @Test
    public void testCheckWinnerRow() {
        AbstractBoard abstractBoard =
                new AbstractBoard() {
                    @Override
                    public void initBoard() {
                        board[0][0] = new Cell(Player.PLAYER_O);
                        board[0][1] = new Cell(Player.PLAYER_O);
                        board[0][2] = new Cell(Player.PLAYER_O);
                        board[1][0] = new Cell();
                        board[1][1] = new Cell();
                        board[1][2] = new Cell();
                        board[2][0] = new Cell();
                        board[2][1] = new Cell();
                        board[2][2] = new Cell();
                    }
                };
        abstractBoard.initBoard();
        BoardValidator boardValidator = new BoardValidator(abstractBoard);
        Winner actualWinner = boardValidator.checkWinner();
        Winner expectedWinner =
                new Winner(WinnerLine.ROW.getPath(0), WinnerPlayer.WINNER_PLAYER_O.getPath());

        assertEquals(expectedWinner.getWinnerLine(), actualWinner.getWinnerLine());
    }

    @Test
    public void testCheckWinnerColumn() {
        AbstractBoard abstractBoard =
                new AbstractBoard() {
                    @Override
                    public void initBoard() {
                        board[0][0] = new Cell(Player.PLAYER_X);
                        board[1][0] = new Cell(Player.PLAYER_X);
                        board[2][0] = new Cell(Player.PLAYER_X);
                        board[0][1] = new Cell();
                        board[1][1] = new Cell();
                        board[2][1] = new Cell();
                        board[0][2] = new Cell();
                        board[1][2] = new Cell();
                        board[2][2] = new Cell();
                    }
                };
        abstractBoard.initBoard();
        BoardValidator boardValidator = new BoardValidator(abstractBoard);
        Winner actualWinner = boardValidator.checkWinner();
        Winner expectedWinner =
                new Winner(WinnerLine.COLUMN.getPath(0), WinnerPlayer.WINNER_PLAYER_X.getPath());

        assertEquals(expectedWinner.getWinnerLine(), actualWinner.getWinnerLine());
    }

    @Test
    public void testCheckWinnerMainDiagonal() {
        AbstractBoard abstractBoard =
                new AbstractBoard() {
                    @Override
                    public void initBoard() {
                        board[0][0] = new Cell(Player.PLAYER_O);
                        board[1][1] = new Cell(Player.PLAYER_O);
                        board[2][2] = new Cell(Player.PLAYER_O);
                        board[0][1] = new Cell();
                        board[0][2] = new Cell();
                        board[1][0] = new Cell();
                        board[1][2] = new Cell();
                        board[2][0] = new Cell();
                        board[2][1] = new Cell();
                    }
                };
        abstractBoard.initBoard();
        BoardValidator boardValidator = new BoardValidator(abstractBoard);
        Winner actualWinner = boardValidator.checkWinner();
        Winner expectedWinner =
                new Winner(WinnerLine.DIAGONAL.getPath(0), WinnerPlayer.WINNER_PLAYER_O.getPath());

        assertEquals(expectedWinner.getWinnerLine(), actualWinner.getWinnerLine());
    }

    @Test
    public void testCheckWinnerAntiDiagonal() {
        AbstractBoard abstractBoard =
                new AbstractBoard() {
                    @Override
                    public void initBoard() {
                        board[0][2] = new Cell(Player.PLAYER_X);
                        board[1][1] = new Cell(Player.PLAYER_X);
                        board[2][0] = new Cell(Player.PLAYER_X);
                        board[0][0] = new Cell();
                        board[0][1] = new Cell();
                        board[1][0] = new Cell();
                        board[1][2] = new Cell();
                        board[2][1] = new Cell();
                        board[2][2] = new Cell();
                    }
                };
        abstractBoard.initBoard();
        BoardValidator boardValidator = new BoardValidator(abstractBoard);
        Winner actualWinner = boardValidator.checkWinner();
        Winner expectedWinner =
                new Winner(WinnerLine.DIAGONAL.getPath(1), WinnerPlayer.WINNER_PLAYER_X.getPath());

        assertEquals(expectedWinner.getWinnerLine(), actualWinner.getWinnerLine());
    }

    @Test
    public void testCheckTie() {
        AbstractBoard abstractBoard =
                new AbstractBoard() {
                    @Override
                    public void initBoard() {
                        board[0][0] = new Cell(Player.PLAYER_X);
                        board[0][1] = new Cell(Player.PLAYER_O);
                        board[0][2] = new Cell(Player.PLAYER_X);
                        board[1][0] = new Cell(Player.PLAYER_X);
                        board[1][1] = new Cell(Player.PLAYER_X);
                        board[1][2] = new Cell(Player.PLAYER_O);
                        board[2][0] = new Cell(Player.PLAYER_O);
                        board[2][1] = new Cell(Player.PLAYER_X);
                        board[2][2] = new Cell(Player.PLAYER_O);
                    }
                };
        abstractBoard.initBoard();
        BoardValidator boardValidator = new BoardValidator(abstractBoard);
        Winner actualWinner = boardValidator.checkWinner();
        Winner expectedWinner = new Winner("", WinnerPlayer.TIE.getPath());

        assertEquals(expectedWinner.getWinnerLine(), actualWinner.getWinnerLine());
    }
}
