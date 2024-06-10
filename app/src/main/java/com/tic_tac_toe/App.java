package com.tic_tac_toe;

import com.bridge.piece.Coord;
import com.bridge.piece.Size;
import com.bridge.processinputhandler.listeners.KeyboardListener;
import java.net.URL;

public class App {

    public static void main(String[] args) {
        KeyboardListener keyboardSuscriber = new KeyboardListener();
        BoardPosition boardPosition = new BoardPosition();
        URL imageUrl = App.class.getResource("/com/tic_tac_toe/Images/board/EmptyBoard.png");
        Board board = new Board(new Coord(0, 0), new Size(3, 3), imageUrl.toString());
        BoardValidator validator = new BoardValidator(board);
        GameController gameController = new GameController(board, validator, boardPosition);
        keyboardSuscriber.listen();
        keyboardSuscriber.listen();
        keyboardSuscriber.listen();
        keyboardSuscriber.listen();
        keyboardSuscriber.listen();
    }
}
