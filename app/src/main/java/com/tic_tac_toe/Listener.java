package com.tic_tac_toe;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.SocketClient;
import com.bridge.ipc.Transmitter;
import com.bridge.processinputhandler.IEventSubscriber;
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


public class Listener implements IEventSubscriber<Keyboard> {

    private Transmitter transmitter;
    private final String BASE_PATH = "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images";

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
                    board.initializeSprites();
                } catch (NonExistentFilePathException e) {
                    throw new RuntimeException(e);
                }
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

    @Override
    public void doNotify(Keyboard keyboard) {
        if (transmitter != null) {
            if (keyboard.type().equals("KeyPressed") && keyboard.key().equals("S")) {
                System.out.println("___________________________RECEIVED___________________________");
                SpriteRepository squareRepository = new SpriteRepository();
                SpriteBuilder builder = new SpriteBuilder(squareRepository);
                List<Sprite> sprites = new ArrayList<>();
                builder.buildSize(600, 800);
                builder.buildCoord(0, 0);
                try {
                    builder.buildPath(BASE_PATH + "/board/selectedCells/Board-21.png");
                } catch (NonExistentFilePathException e) {
                    throw new RuntimeException(e);
                }
                builder.assemble();
                try {
                    transmitter.send(new Frame(squareRepository.retrieve(), List.of()));
                } catch (RenderException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
