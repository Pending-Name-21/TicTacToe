package com.tic_tac_toe;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.processinputhandler.IEventSubscriber;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardPosition implements IEventSubscriber<Keyboard> {

    private int x;
    private int y;
    private Board board;
    private static final Logger logger = Logger.getLogger(BoardPosition.class.getName());

    public BoardPosition(Board board) {
        x = 0;
        y = 0;
        this.board = board;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void doNotify(Keyboard keyboard) {
        String keyCode = keyboard.key();
        switch (keyCode) {
            case "71": // Up
                y = Math.min(y + 1, 2);
                break;
            case "73": // Down
                y = Math.max(y - 1, 0);
                break;
            case "70": // Left
                x = Math.max(x - 1, 0);
                break;
            case "72": // Right
                x = Math.min(x + 1, 2);
                break;
            default:
                logger.log(Level.INFO, "Unhandled key code: {0}", keyCode);
                break;
        }
        board.changingSpriteHiddenByUserPosition(x,y);
    }
}
