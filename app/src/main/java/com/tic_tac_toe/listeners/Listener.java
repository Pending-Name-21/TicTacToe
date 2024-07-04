package com.tic_tac_toe.listeners;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.ipc.SocketClient;
import com.bridge.ipc.Transmitter;
import com.bridge.processinputhandler.KeyboardEventManager;
import com.tic_tac_toe.handler.Board;
import com.tic_tac_toe.handler.GameController;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.UnixDomainSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;

public class Listener {

    private Transmitter transmitter;
    private KeyboardEventManager keyboardEventManager;
    private final String SCREEN_SOCKET = "/tmp/socket_console";

    public Listener(KeyboardEventManager keyboardEventManager) {
        this.keyboardEventManager = keyboardEventManager;
    }

    private boolean isSocketRunning() {
        Path socketPath = Path.of(SCREEN_SOCKET);
        SocketAddress address = UnixDomainSocketAddress.of(socketPath);

        try (SocketChannel socketChannel = SocketChannel.open(address)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void startConnection() {
        boolean successConnection = false;
        while (!successConnection) {
            if (isSocketRunning()) {
                Path socketPath = Path.of(SCREEN_SOCKET);
                SocketClient socketClient = new SocketClient(socketPath);
                transmitter = new Transmitter(socketClient);
                Board board = new Board(transmitter);
                try {
                    board.initBoard();
                } catch (NonExistentFilePathException e) {
                    throw new RuntimeException(e);
                }
                BoardValidator boardValidator = new BoardValidator(board);
                keyboardEventManager.subscribe(new BoardPosition(board));
                keyboardEventManager.subscribe(new GameController(board, boardValidator, transmitter));
                successConnection = true;
            } else {
                try {
                    System.out.println("Waiting for screen...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
