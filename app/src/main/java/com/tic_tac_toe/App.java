package com.tic_tac_toe;

import com.bridge.processinputhandler.EventType;
import com.bridge.processinputhandler.listeners.KeyboardListener;
import java.net.URL;

public class App {

    public static void main(String[] args) {
        KeyboardListener keyboardSuscriber = new KeyboardListener();
        BoardPosition boardPosition = new BoardPosition();
        URL imageUrl = App.class.getResource("/com/tic_tac_toe/Images/board/EmptyBoard.png");
        Board board = new Board();
        BoardValidator validator = new BoardValidator(board);
        GameController gameController = new GameController(board, validator, boardPosition);
        keyboardSuscriber.suscribe(new EventType("Up"), boardPosition);
        keyboardSuscriber.suscribe(new EventType("Down"), boardPosition);
        keyboardSuscriber.suscribe(new EventType("Left"), boardPosition);
        keyboardSuscriber.suscribe(new EventType("Right"), boardPosition);
        keyboardSuscriber.suscribe(new EventType("Enter"), gameController);
    }
}