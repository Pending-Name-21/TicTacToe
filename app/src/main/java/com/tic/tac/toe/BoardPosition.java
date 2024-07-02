package com.tic.tac.toe;

import com.bridge.processinputhandler.IEventSubscriber;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardPosition implements IEventSubscriber {
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
    public void doNotify(Object eventType) {
        if (eventType.equals("Up")) {
            y = Math.min(y + 1, 2);
        } else if (eventType.equals("Down")) {
            y = Math.max(y - 1, 0);
        } else if (eventType.equals("Left")) {
            x = Math.max(x - 1, 0);
        } else if (eventType.equals("Right")) {
            x = Math.min(x + 1, 2);
        } else {
            logger.log(Level.INFO, "Unhandled event type: %s", eventType.getClass());
        }
    }
}
