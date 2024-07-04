/*
package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.renderHandler.repository.SpriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    private GameController gameController;
    private Board board;
    private BoardValidator boardValidator;
    private BoardPosition boardPosition;

    @BeforeEach
    public void setUp() throws NonExistentFilePathException {
        board = new Board();
        boardValidator = new BoardValidator(board);
        boardPosition = new BoardPosition(board);
        gameController = new GameController(board, boardValidator, boardPosition);
    }

    @Test
    public void testCheckCellEmpty() {
        boardPosition.setX(boardPosition.getXPosition() + 1); // Right
        boardPosition.setY(boardPosition.getYPosition() + 1); // Up
        assertTrue(gameController.checkCellEmpty());

        board.getBoard()[1][1].setSymbol('X'); // Marking the cell as non-empty
        assertFalse(gameController.checkCellEmpty());
    }

    @Test
    public void testSwitchPlayer() {
        gameController.switchPlayer();
        assertEquals(Player.PLAYER_O, gameController.currentPlayer);
        gameController.switchPlayer();
        assertEquals(Player.PLAYER_X, gameController.currentPlayer);
    }
}
*/
