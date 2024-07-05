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

    private Board board;
    private Player currentPlayer;
    private BoardValidator boardValidator;
    private Transmitter transmitter;
    private Queue<String> pathsO;
    private Queue<String> pathsX;

    public GameController(Board board, BoardValidator boardValidator, Transmitter transmitter) {
        this.board = board;
        this.boardValidator = boardValidator;
        this.transmitter = transmitter;
        pathsO = new LinkedList<>(List.of(
                SourcePaths.BASE_PATH.concat("/assets/general/osymbola.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbolb.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbolc.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbold.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbole.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbolf.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbolg.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymbolh.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/osymboli.png")));
        pathsX = new LinkedList<>(List.of(
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbola.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbolb.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbolc.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbold.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbole.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbolf.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbolg.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymbolh.png"),
                SourcePaths.BASE_PATH.concat("/assets/general/xsymboli.png")
        ));
        currentPlayer = Player.PLAYER_X;
    }

    public void switchPlayer() {
        if (currentPlayer == Player.PLAYER_X) {
            currentPlayer = Player.PLAYER_O;
        } else {
            currentPlayer = Player.PLAYER_X;
        }
    }

    public String getPath(Player player) {
        return player.getSymbol() == Player.PLAYER_X.getSymbol()
                ? pathsX.poll() : pathsO.poll();
    }

    @Override
    public void doNotify(Keyboard keyboard) {
        if (keyboard.type().equals("KeyPressed")
                && keyboard.key().equals("O")) {

            Cell cell = board.getCurrentCell();
            if (!cell.wasUsed()) {
                Coordinate coordinate = cell.getCoordinate();
                SpriteBuilder builder = new SpriteBuilder(new SpriteRepository());
                builder.buildSize(Sizes.SYMBOL_SIZE_H, Sizes.SYMBOL_SIZE_W);
                builder.buildCoord(coordinate.getX(), coordinate.getY());
                try {
                    builder.buildPath(getPath(currentPlayer));
                } catch (NonExistentFilePathException e) {
                    throw new RuntimeException(e);
                }
                Sprite sprite = builder.assemble();
                Frame frame = new Frame(List.of(sprite), List.of());
                System.out.println(sprite.getPath());
                System.out.println(frame.sprites());

                try {
                    transmitter.send(new Frame(List.of(sprite), List.of()));
                } catch (RenderException e) {
                    throw new RuntimeException(e);
                }

                board.getCurrentCell().setPlayer(currentPlayer);
                switchPlayer();
                checkGameState();
                board.printBoard();
            }
        }
    }

    private void checkGameState() {
        Sprite sprite;
        SpriteBuilder builder;
        boolean wasFinished = boardValidator.isGameOver();

        if (wasFinished) {
            Winner winner = boardValidator.getWinner();
            if (!winner.getWinnerLine().isEmpty()) {
                builder = new SpriteBuilder(new SpriteRepository());
                builder.buildSize(Sizes.HEIGHT_APP, Sizes.WIDTH_APP);
                builder.buildCoord(0, 0);
                try {
                    builder.buildPath(winner.getWinnerLine());
                } catch (NonExistentFilePathException e) {
                    throw new RuntimeException(e);
                }
                sprite = builder.assemble();

                try {
                    transmitter.send(new Frame(List.of(sprite), List.of()));
                } catch (RenderException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            builder = new SpriteBuilder(new SpriteRepository());
            builder.buildSize(Sizes.HEIGHT_APP, Sizes.WIDTH_APP);
            builder.buildCoord(0, 0);
            try {
                builder.buildPath(winner.getWinnerPlayer());
            } catch (NonExistentFilePathException e) {
                throw new RuntimeException(e);
            }

            sprite = builder.assemble();

            SoundBuilder soundBuilder = new SoundBuilder(new SoundRepository());
            try {
                soundBuilder.buildPath(SourcePaths.BASE_PATH.concat("/sounds/win-game-2.mp3"));
            } catch (NonExistentFilePathException e) {
                throw new RuntimeException(e);
            }

            Sound sound = soundBuilder.assemble();

            try {
                transmitter.send(new Frame(List.of(sprite), List.of()));
            } catch (RenderException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
