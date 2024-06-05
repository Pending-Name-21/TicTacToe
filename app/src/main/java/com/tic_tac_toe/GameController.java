package com.tic_tac_toe;

import com.bridge.inputsuscription.EventType;
import com.bridge.inputsuscription.IProcessInputSubscriber;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GameController implements IProcessInputSubscriber {

    Player currentPlayer;
    private Board board;
    private BoardValidator boardValidator;
    private BoardPosition boardPosition;
    private static final Logger logger = Logger.getLogger(GameController.class.getName());


    public GameController(Board board, BoardValidator boardValidator, BoardPosition boardPosition) {
        this.board = board;
        this.boardValidator = boardValidator;
        this.boardPosition = boardPosition;
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
    public void notify(EventType eventType) {
        if (eventType.getName().equals("Enter") && checkCellEmpty()) {
            board.placeSymbol(boardPosition, currentPlayer);
            boardValidator.checkWin(board);
            switchPlayer();
        } else {
            logger.log(Level.INFO, "Condition not met: eventType is not 'Enter' or cell is not empty");
        }
    }
}
