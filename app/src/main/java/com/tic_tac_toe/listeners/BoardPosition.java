package com.tic_tac_toe.listeners;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.processinputhandler.IEventSubscriber;
import com.tic_tac_toe.handler.Board;

public class BoardPosition implements IEventSubscriber<Keyboard> {

    private int x;
    private int y;
    private final Board board;

    public BoardPosition(Board board) {
        x = 0;
        y = 0;
        this.board = board;
    }

    @Override
    public void doNotify(Keyboard keyboard) {
        if (keyboard.type().equals("KeyPressed")) {
            String keyCode = keyboard.key();
            switch (keyCode) {
                case "D", "Right" -> // Right
                        y = Math.min(y + 1, 2);
                case "A", "Left" -> // Left
                        y = Math.max(y - 1, 0);
                case "W", "Up" -> // Up
                        x = Math.max(x - 1, 0);
                case "S", "Down" -> // Down
                        x = Math.min(x + 1, 2);
                default -> {}
            }
            board.setCurrentCell(x, y);
        }
    }
}
