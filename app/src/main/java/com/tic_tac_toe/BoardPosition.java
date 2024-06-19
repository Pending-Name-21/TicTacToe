package com.tic_tac_toe;

import com.bridge.processinputhandler.IProcessInputSubscriber;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.bridge.processinputhandler.EventType;
public class BoardPosition implements IProcessInputSubscriber {

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
    private void makeMovement(EventType eventType){
        switch (eventType.getName()) {
            case "Up":
                y = Math.min(y + 1, 2);
                break;
            case "Down":
                y = Math.max(y - 1, 0);
                break;
            case "Left":
                x = Math.max(x - 1, 0);
                break;
            case "Right":
                x = Math.min(x + 1, 2);
                break;
            default:
                logger.log(Level.INFO, "Unhandled event type: %s", eventType.getName());
                break;
        }
    }

    @Override
    public void notify(EventType eventType) {
        makeMovement(eventType);
        board.changingSpriteByUserPosition(x,y);
    }
}
