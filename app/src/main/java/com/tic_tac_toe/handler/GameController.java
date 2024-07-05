package com.tic_tac_toe.handler;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.Transmitter;
import com.bridge.processinputhandler.IEventSubscriber;
import com.bridge.renderHandler.builders.SoundBuilder;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SoundRepository;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sound.Sound;
import com.bridge.renderHandler.sprite.Sprite;
import com.tic_tac_toe.listeners.BoardValidator;
import com.tic_tac_toe.model.Cell;
import com.tic_tac_toe.model.Coordinate;
import com.tic_tac_toe.model.Player;
import com.tic_tac_toe.model.winner.Winner;
import com.tic_tac_toe.utils.Sizes;
import com.tic_tac_toe.utils.SourcePaths;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameController implements IEventSubscriber<Keyboard> {

    private final Board board;
    private Player currentPlayer;
    private final BoardValidator boardValidator;
    private final Transmitter transmitter;
    private final Queue<String> pathsO;
    private final Queue<String> pathsX;

    public GameController(Board board, BoardValidator boardValidator, Transmitter transmitter) {
        this.board = board;
        this.boardValidator = boardValidator;
        this.transmitter = transmitter;
        this.pathsO = initializePaths("osymbol");
        this.pathsX = initializePaths("xsymbol");
        this.currentPlayer = Player.PLAYER_X;
    }

    private Queue<String> initializePaths(String symbol) {
        return new LinkedList<>(List.of(
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "a.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "b.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "c.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "d.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "e.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "f.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "g.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "h.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/" + symbol + "i.png")
        ));
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Player.PLAYER_X) ? Player.PLAYER_O : Player.PLAYER_X;
    }

    public String getPath(Player player) {
        return player == Player.PLAYER_X ? pathsX.poll() : pathsO.poll();
    }

    @Override
    public void doNotify(Keyboard keyboard) {
        if ("KeyPressed".equals(keyboard.type()) && ("Return".equals(keyboard.key()) || "Space".equals(keyboard.key()))) {
            handleKeyPress();
        }
    }

    private void handleKeyPress() {
        Cell cell = board.getCurrentCell();
        if (!cell.wasUsed()) {
            Coordinate coordinate = cell.getCoordinate();
            Sprite sprite = createSprite(coordinate.getX(), coordinate.getY(), getPath(currentPlayer));
            sendFrame(List.of(sprite), List.of());
            cell.setPlayer(currentPlayer);
            switchPlayer();
            checkGameState();
        }
    }

    private Sprite createSprite(int x, int y, String path) {
        SpriteBuilder builder = new SpriteBuilder(new SpriteRepository());
        builder.buildSize(Sizes.SYMBOL_SIZE_H, Sizes.SYMBOL_SIZE_W)
                .buildCoord(x, y);
        try {
            builder.buildPath(path);
        } catch (NonExistentFilePathException e) {
            throw new RuntimeException(e);
        }
        return builder.assemble();
    }

    private void sendFrame(List<Sprite> sprites, List<Sound> sounds) {
        try {
            transmitter.send(new Frame(sprites, sounds));
        } catch (RenderException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkGameState() {
        if (boardValidator.isGameOver()) {
            handleGameOver();
        }
    }

    private void handleGameOver() {
        Winner winner = boardValidator.getWinner();
        if (!winner.getWinnerLine().isEmpty()) {
            displayWinnerLine(winner.getWinnerLine());
            pause(1000);
        }
        displayWinner(winner.getWinnerPlayer());
        playWinSound();
    }

    private void displayWinnerLine(String winnerLine) {
        Sprite sprite = createFullScreenSprite(winnerLine);
        sendFrame(List.of(sprite), List.of());
    }

    private void displayWinner(String winnerPath) {
        Sprite sprite = createFullScreenSprite(winnerPath);
        sendFrame(List.of(sprite), List.of());
    }

    private Sprite createFullScreenSprite(String path) {
        SpriteBuilder builder = new SpriteBuilder(new SpriteRepository());
        builder.buildSize(Sizes.HEIGHT_APP, Sizes.WIDTH_APP)
                .buildCoord(0, 0);
        try {
            builder.buildPath(path);
        } catch (NonExistentFilePathException e) {
            throw new RuntimeException(e);
        }
        return builder.assemble();
    }

    private void playWinSound() {
        SoundBuilder soundBuilder = new SoundBuilder(new SoundRepository());
        try {
            soundBuilder.buildPath(SourcePaths.BASE_PATH.concat("/sounds/win-game-2.mp3"));
        } catch (NonExistentFilePathException e) {
            throw new RuntimeException(e);
        }
        Sound sound = soundBuilder.assemble();
        sendFrame(List.of(), List.of(sound));
    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
