package com.tic.tac.toe;

import com.bridge.processinputhandler.KeyboardEventManager;
import com.bridge.renderHandler.sprite.Coord;
import com.bridge.renderHandler.sprite.Size;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        KeyboardEventManager keyboardSuscriber = new KeyboardEventManager();
        BoardPosition boardPosition = new BoardPosition();
        URL imageUrl = App.class.getResource("/com/tic/tac/toe/Images/board/EmptyBoard.png");
        Board board = new Board(new Coord(0, 0), 0, new Size(3, 3), Path.of(imageUrl.toURI()));
        BoardValidator validator = new BoardValidator(board);
        GameController gameController = new GameController(board, validator, boardPosition);
        // keyboardSuscriber.suscribe();
        // keyboardSuscriber.suscribe();
        // keyboardSuscriber.suscribe();
        // keyboardSuscriber.suscribe();
        // keyboardSuscriber.suscribe();
    }
}
