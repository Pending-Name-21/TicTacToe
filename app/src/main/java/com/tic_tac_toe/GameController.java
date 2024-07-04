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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class GameController implements IEventSubscriber<Keyboard> {

    private Player currentPlayer;
    private Board board;
    private BoardValidator boardValidator;
    private Transmitter transmitter;
    private static final Logger logger = Logger.getLogger(GameController.class.getName());
    private final int SYM_SIZE_W = 235;
    private final int SYM_SIZE_H = 200;
    private Queue<String> pathsO;
    private Queue<String> pathsX;

    public GameController(Board board, BoardValidator boardValidator, Transmitter transmitter) {
        this.board = board;
        this.boardValidator = boardValidator;
        this.transmitter = transmitter;
        pathsO = new LinkedList<>(List.of("/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbola.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbolb.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbolc.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbold.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbole.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbolf.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbolg.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymbolh.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/osymboli.png"));
        pathsX = new LinkedList<>(List.of(
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbola.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbolb.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbolc.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbold.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbole.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbolf.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbolg.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymbolh.png",
                "/home/fundacion/University/Fifth/SoftwareDevelopment/TicTacToe/app/src/main/java/com/tic_tac_toe/Images/assets/general/xsymboli.png"
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
                builder.buildSize(SYM_SIZE_H, SYM_SIZE_W);
                builder.buildCoord(coordinate.getX(), coordinate.getY());
                try {
                    builder.buildPath(getPath(currentPlayer));
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
                switchPlayer();

                boolean wasFinished = boardValidator.isGameOver();
                if (wasFinished) {
                    Winner winner = boardValidator.getWinner();
                    if (!winner.getWinnerLine().isEmpty()) {
                        builder = new SpriteBuilder(new SpriteRepository());
                        builder.buildSize(Utils.HEIGHT_APP, Utils.WIDTH_APP);
                        builder.buildCoord(0, 0);
                        try {
                            builder.buildPath(winner.getWinnerLine());
                        } catch (NonExistentFilePathException e) {
                            throw new RuntimeException(e);
                        }
                        sprite = builder.assemble();
                        sprite.setZ_index(4);

                        try {
                            transmitter.send(new Frame(List.of(sprite), List.of()));
                        } catch (RenderException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    builder = new SpriteBuilder(new SpriteRepository());
                    builder.buildSize(Utils.HEIGHT_APP, Utils.WIDTH_APP);
                    builder.buildCoord(0, 0);
                    try {
                        builder.buildPath(winner.getWinnerPlayer());
                    } catch (NonExistentFilePathException e) {
                        throw new RuntimeException(e);
                    }
                    sprite = builder.assemble();
                    sprite.setZ_index(4);

                    try {
                        transmitter.send(new Frame(List.of(sprite), List.of()));
                    } catch (RenderException e) {
                        throw new RuntimeException(e);
                    }

                }
                for (Cell[] cells : board.getBoard()) {
                    System.out.println(Arrays.toString(cells));
                }
                System.out.println();
            }

        }
    }
}
