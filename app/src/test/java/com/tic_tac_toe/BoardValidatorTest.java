package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.piece.Coord;
import com.bridge.piece.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardValidatorTest {

    private BoardValidator boardValidator;
    private BoardPosition boardPosition;
    private GameController gameController;
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(new Coord(0, 0), new Size(100, 100), "path");
        board.setBoard(new char[3][3]);
        boardValidator = new BoardValidator();
        boardPosition = new BoardPosition();
        gameController = new GameController(board, boardValidator, boardPosition);
    }


    @Test
    public void testCheckWin() {
        board.getBoard()[0][0] = 'X';
        board.getBoard()[0][1] = 'X';
        board.getBoard()[0][2] = 'X';
        assertTrue(boardValidator.checkWin(board));
        assertTrue(boardValidator.isGameWon());

        board.getBoard()[0][0] = '\0';
        assertFalse(boardValidator.checkWin(board));
        assertFalse(boardValidator.isGameWon());
    }

    @Test
    public void testIsGameWon() {
        assertFalse(boardValidator.isGameWon());
        board.getBoard()[0][0] = 'X';
        board.getBoard()[0][1] = 'X';
        board.getBoard()[0][2] = 'X';
        boardValidator.checkWin(board);
        assertTrue(boardValidator.isGameWon());
    }
}
