package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.inputsuscription.EventType;
import com.bridge.piece.Coord;
import com.bridge.piece.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    private GameController gameController;
    private Board board;
    private BoardValidator boardValidator;
    private BoardPosition boardPosition;

    @BeforeEach
    public void setUp() {
        board = new Board(new Coord(0, 0), new Size(100, 100), "path");
        boardValidator = new BoardValidator(board);
        boardPosition = new BoardPosition();
        gameController = new GameController(board, boardValidator, boardPosition);
    }

    @Test
    public void testCheckCellEmpty() {
        boardPosition.notify(new EventType("Right"));
        boardPosition.notify(new EventType("Up"));
        assertTrue(gameController.checkCellEmpty());

        board.getBoard()[1][1] = 'X';
        assertFalse(gameController.checkCellEmpty());
    }

    @Test
    public void testSwitchPlayer() {
        gameController.switchPlayer();
        assertEquals(Player.PLAYER_O, gameController.currentPlayer);
        gameController.switchPlayer();
        assertEquals(Player.PLAYER_X, gameController.currentPlayer);
    }

    @Test
    public void testNotify() {
        boardPosition.notify(new EventType("Right"));
        boardPosition.notify(new EventType("Up"));
        EventType enterEvent = new EventType("Enter");

        gameController.notify(enterEvent);
        assertEquals('X', board.getBoard()[1][1]);
        assertEquals(Player.PLAYER_O, gameController.currentPlayer);
    }
}
