package com.tic.tac.toe;

import com.bridge.processinputhandler.IEventSubscriber;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements IEventSubscriber {
    Player currentPlayer;
    private Board board;
    private BoardValidator boardValidator;
    private BoardPosition boardPosition;
    private static final Logger logger = Logger.getLogger(GameController.class.getName());

    public GameController(Board board, BoardValidator boardValidator, BoardPosition boardPosition) {
        this.board = board;
        this.boardPosition = boardPosition;
        this.boardValidator = boardValidator;
        currentPlayer = Player.PLAYER_X;
    }

    public boolean checkCellEmpty() {
        boolean isCellEmpty = false;
        int x = boardPosition.getXPosition();
        int y = boardPosition.getYPosition();

        if (board.getBoard()[x][y] == '\0') {
            isCellEmpty = true;
        }
        return isCellEmpty;
    }

    public void switchPlayer() {
        if (currentPlayer == Player.PLAYER_X) {
            currentPlayer = Player.PLAYER_O;
        } else {
            currentPlayer = Player.PLAYER_X;
        }
    }

    @Override
    public void doNotify(Object eventType) {
        if (eventType.equals("Enter") && checkCellEmpty()) {
            board.placeSymbol(boardPosition, currentPlayer);
            switchPlayer();
        } else {
            logger.log(
                    Level.INFO, "Condition not met: eventType is not 'Enter' or cell is not empty");
        }
    }
}
