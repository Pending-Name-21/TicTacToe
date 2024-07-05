package com.tic_tac_toe.listeners;

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
    private final KeyboardEventManager keyboardEventManager;
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

    private void waitForScreen() {
        try {
            System.out.println("Waiting for screen...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupBoardAndTransmitter() {
        Path socketPath = Path.of(SCREEN_SOCKET);
        SocketClient socketClient = new SocketClient(socketPath);
        transmitter = new Transmitter(socketClient);
        Board board = new Board(transmitter);
        board.initBoard();
        BoardValidator boardValidator = new BoardValidator(board);
        keyboardEventManager.subscribe(new BoardPosition(board));
        keyboardEventManager.subscribe(new GameController(board, boardValidator, transmitter));
    }

    public void startConnection() {
        while (!isSocketRunning()) {
            waitForScreen();
        }
        setupBoardAndTransmitter();
    }
}
