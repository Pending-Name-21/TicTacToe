package com.tic_tac_toe;

import static org.junit.jupiter.api.Assertions.*;

import com.bridge.inputsuscription.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardPositionTest {

    private BoardPosition boardPosition;

    @BeforeEach
    public void setUp() {
        boardPosition = new BoardPosition();
    }

    @Test
    public void testGetXPosition() {
        assertEquals(0, boardPosition.getXPosition());
    }

    @Test
    public void testGetYPosition() {
        assertEquals(0, boardPosition.getYPosition());
    }

    @Test
    public void testNotify() {
        boardPosition.notify(new EventType("Up"));
        assertEquals(1, boardPosition.getYPosition());

        boardPosition.notify(new EventType("Down"));
        assertEquals(0, boardPosition.getYPosition());

        boardPosition.notify(new EventType("Left"));
        assertEquals(1, boardPosition.getXPosition());

        boardPosition.notify(new EventType("Right"));
        assertEquals(0, boardPosition.getXPosition());
    }
}
