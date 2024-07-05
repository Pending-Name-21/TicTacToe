package com.tic_tac_toe;

import com.bridge.Game;
import com.bridge.core.exceptions.GameException;
import com.bridge.ipc.Receiver;
import com.bridge.ipc.SocketServer;
import com.bridge.processinputhandler.InputVerifier;
import com.bridge.renderHandler.builders.SoundBuilder;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.tic_tac_toe.handler.Board;
import com.tic_tac_toe.handler.GameController;
import com.tic_tac_toe.listeners.BoardPosition;
import com.tic_tac_toe.listeners.BoardValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {

    public static final Path NAMESPACE = Path.of(System.getProperty("java.io.tmpdir"), "test-events-socket.sock");

    public static void main(String[] args) {
        Board board = new Board();
        BoardValidator boardValidator = new BoardValidator(board);
        Game game = new Game(boardValidator);

        BoardPosition boardPosition = new BoardPosition(board);
        GameController gameController = new GameController(board, boardValidator, game);

        game.getKeyboardEventManager().subscribe(boardPosition);
        game.getKeyboardEventManager().subscribe(gameController);
        board.initBoard(new SpriteBuilder(game.getSpriteIRepository()));

//        KeyboardEventManager keyboardEventManager = new KeyboardEventManager();
//        Listener listener = new Listener(keyboardEventManager);
//        Receiver receiver = new Receiver();
//        receiver.addBuffer(keyboardEventManager);
//        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
//        startServer(receiver, atomicBoolean);
//        listener.startConnection();
//        InputVerifier verifier = new InputVerifier(List.of(keyboardEventManager));
//        runVerifierLoop(verifier);

        try {
            game.run();
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    private static void startServer(Receiver receiver, AtomicBoolean atomicBoolean) {
        try {
            Files.deleteIfExists(NAMESPACE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SocketServer socketServer = new SocketServer(receiver, NAMESPACE, atomicBoolean);
        Thread serverThread = new Thread(socketServer);
        serverThread.start();
        waitForNamespaceCreation(serverThread);
    }

    private static void waitForNamespaceCreation(Thread serverThread) {
        while (!Files.exists(NAMESPACE)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                serverThread.interrupt();
            }
        }
    }

    private static void runVerifierLoop(InputVerifier verifier) {
        while (true) {
            verifier.check();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
