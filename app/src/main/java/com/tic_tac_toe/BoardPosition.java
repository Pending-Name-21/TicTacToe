package com.tic_tac_toe;

import com.bridge.inputsuscription.EventType;
import com.bridge.inputsuscription.IProcessInputSubscriber;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardPosition implements IProcessInputSubscriber {

    private int x;
    private int y;
    private static final Logger logger = Logger.getLogger(BoardPosition.class.getName());

    public BoardPosition() {
        x = 0;
        y = 0;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    @Override
    public void notify(EventType eventType) {
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
}
