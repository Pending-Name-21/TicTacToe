package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.piece.Coord;
import com.bridge.piece.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardValidatorTest {

    private BoardValidator boardValidator;
    private Board board;

    @BeforeEach
    public void setUp() {
        boardValidator = new BoardValidator();
        board = new Board(new Coord(0, 0), new Size(100, 100), "path");
        board.board = new char[3][3];
    }

    @Test
    public void testCheckWin() {
        board.board[0][0] = 'X';
        board.board[0][1] = 'X';
        board.board[0][2] = 'X';
        assertTrue(boardValidator.checkWin(board));
        assertTrue(boardValidator.isGameWon());

        board.board[0][0] = '\0';
        assertFalse(boardValidator.checkWin(board));
        assertFalse(boardValidator.isGameWon());
    }

    @Test
    public void testIsGameWon() {
        assertFalse(boardValidator.isGameWon());
        board.board[0][0] = 'X';
        board.board[0][1] = 'X';
        board.board[0][2] = 'X';
        boardValidator.checkWin(board);
        assertTrue(boardValidator.isGameWon());
    }
}
