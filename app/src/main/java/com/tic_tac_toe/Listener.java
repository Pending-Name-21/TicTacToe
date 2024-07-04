package com.tic_tac_toe;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.SocketClient;
import com.bridge.ipc.Transmitter;
import com.bridge.processinputhandler.IEventSubscriber;
import com.bridge.processinputhandler.KeyboardEventManager;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.sprite.Sprite;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.UnixDomainSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Listener {

    private Transmitter transmitter;
    private KeyboardEventManager keyboardEventManager;

    public Listener(KeyboardEventManager keyboardEventManager) {
        this.keyboardEventManager = keyboardEventManager;
    }

    private boolean isSocketRunning() {
        Path socketPath = Path.of("/tmp/socket_console");
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
                Path socketPath = Path.of("/tmp/socket_console");
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
