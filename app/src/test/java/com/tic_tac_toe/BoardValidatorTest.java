/*
package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.renderHandler.repository.SpriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardValidatorTest {

    private BoardValidator boardValidator;
    private Board board;

    @BeforeEach
    public void setUp() throws NonExistentFilePathException {
        SpriteRepository repository = new SpriteRepository();
        board = new Board(repository);
        boardValidator = new BoardValidator(board);
    }

    @Test
    public void testIsGameOver() {
        assertFalse(boardValidator.isGameOver());
        board.getBoard()[0][0].setSymbol('X');
        board.getBoard()[0][1].setSymbol('X');
        board.getBoard()[0][2].setSymbol('X');
        assertTrue(boardValidator.isGameOver());
    }

    @Test
    public void testCheckWinnerRow() {
        board.getBoard()[0][0].setSymbol('X');
        board.getBoard()[0][1].setSymbol('X');
        board.getBoard()[0][2].setSymbol('X');
        assertEquals("Row 1", boardValidator.checkWinner());
    }

    @Test
    public void testCheckWinnerColumn() {
        board.getBoard()[0][0].setSymbol('X');
        board.getBoard()[1][0].setSymbol('X');
        board.getBoard()[2][0].setSymbol('X');
        assertEquals("Column 1", boardValidator.checkWinner());
    }

    @Test
    public void testCheckWinnerDiagonal1() {
        board.getBoard()[0][0].setSymbol('X');
        board.getBoard()[1][1].setSymbol('X');
        board.getBoard()[2][2].setSymbol('X');
        assertEquals("Diagonal 1", boardValidator.checkWinner());
    }

    @Test
    public void testCheckWinnerDiagonal2() {
        board.getBoard()[0][2].setSymbol('X');
        board.getBoard()[1][1].setSymbol('X');
        board.getBoard()[2][0].setSymbol('X');
        assertEquals("Diagonal 2", boardValidator.checkWinner());
    }

    @Test
    public void testNoWinner() {
        board.getBoard()[0][0].setSymbol('X');
        board.getBoard()[0][1].setSymbol('O');
        board.getBoard()[0][2].setSymbol('X');
        board.getBoard()[1][0].setSymbol('O');
        board.getBoard()[1][1].setSymbol('X');
        board.getBoard()[1][2].setSymbol('O');
        board.getBoard()[2][0].setSymbol('O');
        board.getBoard()[2][1].setSymbol('X');
        board.getBoard()[2][2].setSymbol('O');
        assertEquals("", boardValidator.checkWinner());
    }
}
*/
