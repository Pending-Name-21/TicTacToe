package com.tic.tac.toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.renderHandler.sprite.Coord;
import com.bridge.renderHandler.sprite.Size;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    private GameController gameController;
    private Board board;
    private BoardValidator boardValidator;
    private BoardPosition boardPosition;

    @BeforeEach
    public void setUp() {
        board = new Board(new Coord(0, 0), 0, new Size(100, 100), Path.of("path"));
        boardValidator = new BoardValidator(board);
        boardPosition = new BoardPosition();
        gameController = new GameController(board, boardValidator, boardPosition);
    }

    @Test
    public void testCheckCellEmpty() {
        boardPosition.doNotify("Right");
        boardPosition.doNotify("Up");
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
        boardPosition.doNotify("Right");
        boardPosition.doNotify("Up");
        // EventType enterEvent = new EventType("Enter");

        gameController.doNotify("Enter");
        assertEquals('X', board.getBoard()[1][1]);
        assertEquals(Player.PLAYER_O, gameController.currentPlayer);
    }
}
