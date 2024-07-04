package com.tic_tac_toe;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.Transmitter;
import com.bridge.processinputhandler.IEventSubscriber;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.Sprite;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements IEventSubscriber<Keyboard> {

    private Player currentPlayer;
    private Board board;
    //    private BoardValidator boardValidator;
    private Transmitter transmitter;
    private static final Logger logger = Logger.getLogger(GameController.class.getName());
    private final int SYM_SIZE_W = 235;
    private final int SYM_SIZE_H = 200;

    public GameController(Board board, Transmitter transmitter) {
        this.board = board;
        this.transmitter = transmitter;
//        this.boardValidator = boardValidator;
        currentPlayer = Player.PLAYER_X;
    }

    public void switchPlayer() {
        if (currentPlayer == Player.PLAYER_X) {
            currentPlayer = Player.PLAYER_O;
        } else {
            currentPlayer = Player.PLAYER_X;
        }
    }

    @Override
    public void doNotify(Keyboard keyboard) {
        if (keyboard.type().equals("KeyPressed")
                && keyboard.key().equals("O")) {

            Cell cell = board.getCurrentCell();
            if (!cell.wasUsed()) {
                switchPlayer();
            }

            Coordinate coordinate = cell.getCoordinate();
            SpriteBuilder builder = new SpriteBuilder(new SpriteRepository());
            builder.buildSize(SYM_SIZE_H, SYM_SIZE_W);
            builder.buildCoord(coordinate.getX(), coordinate.getY());
            try {
                builder.buildPath(currentPlayer.getPath());
            } catch (NonExistentFilePathException e) {
                throw new RuntimeException(e);
            }
            Sprite sprite = builder.assemble();
            sprite.setZ_index(3);

            board.getCurrentCell().setPlayer(currentPlayer);
            try {
                transmitter.send(new Frame(List.of(sprite), List.of()));
            } catch (RenderException e) {
                throw new RuntimeException(e);
            }
        } else {
            logger.log(
                    Level.INFO, "Condition not met: eventType is not 'Enter' or cell is not empty");
        }
    }
}
