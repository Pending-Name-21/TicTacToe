package com.tic_tac_toe;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.processinputhandler.KeyboardEventManager;
import com.bridge.renderHandler.repository.SpriteRepository;

import java.net.URL;

public class App {

    public static void main(String[] args) throws NonExistentFilePathException {
        SpriteRepository spriteRepository = new SpriteRepository();
        KeyboardEventManager keyboardSuscriber = new KeyboardEventManager();

        Board board = new Board(spriteRepository);
        BoardPosition boardPosition = new BoardPosition(board);
        BoardValidator validator = new BoardValidator(board);
        GameController gameController = new GameController(board, validator, boardPosition);
    }
}