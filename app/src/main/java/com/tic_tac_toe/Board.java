package com.tic_tac_toe;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.Transmitter;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.Sprite;

import java.util.List;

public class Board {

    private Cell[][] board;
    private Cell currentCell;
    private SpriteBuilder builder;
    private Transmitter transmitter;
    private SpriteRepository boardRepository;
    private final int SYM_SIZE_W = 235;
    private final int SYM_SIZE_H = 200;

    public Board(Transmitter transmitter) {
        this.transmitter = transmitter;
        this.board = new Cell[3][3];
        this.boardRepository = new SpriteRepository();
    }

    public void placeSymbol(int x, int y, Player player) throws NonExistentFilePathException {
        builder.buildSize(SYM_SIZE_H, SYM_SIZE_W);
        builder.buildCoord(x, y);
        builder.buildPath(player == Player.PLAYER_X
                ? Utils.BASE_PATH.concat("/assets/general/X.png")
                : Utils.BASE_PATH.concat("/assets/general/O.png"));
        Sprite sprite = builder.assemble();
        sprite.setZ_index(3);

        board[x][y].setPlayer(player);
    }

    public void changingSpriteHiddenByUserPosition(int x, int y) {
        currentCell.setSpriteHidden(true);
        currentCell = board[x][y];
        currentCell.setSpriteHidden(false);
    }

    public void initBoard() throws NonExistentFilePathException {
        builder = new SpriteBuilder(boardRepository);
        builder.buildSize(600, 800);
        builder.buildCoord(0, 0);
        builder.buildPath(Utils.BASE_PATH.concat("/board/selectedCells/Board-11.png"));
        Sprite sprite1 = builder.assemble();

        try {
            transmitter.send(new Frame(List.of(sprite1), List.of()));
        } catch (RenderException e) {
            throw new RuntimeException(e);
        }

        int des = 225;
        board[0][0] = new Cell(new Coordinate(-des, des));
        board[0][1] = new Cell(new Coordinate(0, des));
        board[0][2] = new Cell(new Coordinate(des, des));

        board[1][0] = new Cell(new Coordinate(-des, 0));
        board[1][1] = new Cell(new Coordinate(0, 0));
        board[1][2] = new Cell(new Coordinate(des, 0));

        board[2][0] = new Cell(new Coordinate(des, -des));
        board[2][1] = new Cell(new Coordinate(0, -des));
        board[2][2] = new Cell(new Coordinate(-des, -des));

    }

    public Cell[][] getBoard() {
        return board;
    }

}
