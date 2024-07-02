package com.tic.tac.toe;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testDoNotify() {
        boardPosition.doNotify("Up");
        assertEquals(1, boardPosition.getYPosition());

        boardPosition.doNotify("Down");
        assertEquals(0, boardPosition.getYPosition());

        boardPosition.doNotify("Down");
        assertEquals(0, boardPosition.getYPosition());

        boardPosition.doNotify("Left");
        assertEquals(0, boardPosition.getXPosition());

        boardPosition.doNotify("Right");
        assertEquals(1, boardPosition.getXPosition());
    }
}
