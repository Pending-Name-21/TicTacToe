package com.tic_tac_toe.listeners;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.processinputhandler.IEventSubscriber;
import com.tic_tac_toe.handler.Board;

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
        if (keyboard.type().equals("KeyPressed")) {
            String keyCode = keyboard.key();
            switch (keyCode) {
                case "D": // Up
                    y = Math.min(y + 1, 2);
                    break;
                case "A": // Down
                    y = Math.max(y - 1, 0);
                    break;
                case "W": // Left
                    x = Math.max(x - 1, 0);
                    break;
                case "S": // Right
                    x = Math.min(x + 1, 2);
                    break;
                default:
                    break;
            }
            System.out.println("--------------------");
            System.out.printf("%d, %d\n", x, y);
            System.out.println("--------------------");
            board.setCurrentCell(x,y);
        }
    }
}
