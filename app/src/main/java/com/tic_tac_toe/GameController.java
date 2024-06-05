package com.tic_tac_toe;

import com.bridge.inputsuscription.EventType;
import com.bridge.inputsuscription.IProcessInputSubscriber;

public class GameController implements IProcessInputSubscriber {

    Player currentPlayer;
    private Board board;
    private BoardValidator boardValidator;
    private BoardPosition boardPosition;

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

        if (board.board[x][y] == '\0') {
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
            switchPlayer();
        }
    }
}
