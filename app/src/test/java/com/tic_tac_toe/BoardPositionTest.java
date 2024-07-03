package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardPositionTest {

    private BoardPosition boardPosition;
    private Board board;

    @BeforeEach
    public void setUp() throws NonExistentFilePathException {
        SpriteRepository repository = new SpriteRepository();
        board = new Board(repository);
        boardPosition = new BoardPosition(board);
    }

    @Test
    public void testGetXPosition() {
        assertEquals(0, boardPosition.getXPosition());
    }

    @Test
    public void testGetYPosition() {
        assertEquals(0, boardPosition.getYPosition());
    }

}
