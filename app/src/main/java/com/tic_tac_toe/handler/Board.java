package com.tic_tac_toe.handler;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.Transmitter;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.Sprite;
import com.tic_tac_toe.model.Cell;
import com.tic_tac_toe.model.Coordinate;
import com.tic_tac_toe.utils.SourcePaths;

import java.util.List;

public class Board {

    private final Cell[][] board;
    private Cell currentCell;
    private final Transmitter transmitter;

    public Board(Transmitter transmitter) {
        this.transmitter = transmitter;
        this.board = new Cell[3][3];
    }

    public void setCurrentCell(int x, int y) {
        if (currentCell.getCoordinate().getX() != x || currentCell.getCoordinate().getY() != y) {
            currentCell = board[x][y];
            sendSprite(currentCell.getBoardSquare());
        }
    }

    public void initBoard() {
        int des = 200;
        board[0][0] = new Cell(new Coordinate(-des - 55, des - 10), getBoardSquarePath(1, 1));
        board[0][1] = new Cell(new Coordinate(0, des - 10), getBoardSquarePath(1, 2));
        board[0][2] = new Cell(new Coordinate(des + 55, des - 10), getBoardSquarePath(1, 3));
        board[1][0] = new Cell(new Coordinate(-des - 55, 0), getBoardSquarePath(2, 1));
        board[1][1] = new Cell(new Coordinate(0, 0), getBoardSquarePath(2, 2));
        board[1][2] = new Cell(new Coordinate(des + 55, 0), getBoardSquarePath(2, 3));
        board[2][0] = new Cell(new Coordinate(-des - 55, -des), getBoardSquarePath(3, 1));
        board[2][1] = new Cell(new Coordinate(0, -des), getBoardSquarePath(3, 2));
        board[2][2] = new Cell(new Coordinate(des + 55, -des), getBoardSquarePath(3, 3));

        currentCell = board[0][0];
        sendSprite(currentCell.getBoardSquare());
    }

    private String getBoardSquarePath(int row, int col) {
        return SourcePaths.BASE_PATH.concat(String.format("/board/selectedCells/Board-%d%d.png", row, col));
    }

    private void sendSprite(String path) {
        sendSprites(path);
    }

    private void sendSprites(String path) {
        SpriteBuilder spriteBuilder = new SpriteBuilder(new SpriteRepository());
        spriteBuilder.buildSize(600.0, 800);
        spriteBuilder.buildCoord(0, 0);
        try {
            spriteBuilder.buildPath(path);
        } catch (NonExistentFilePathException e) {
            throw new RuntimeException(e);
        }
        Sprite sprite = spriteBuilder.assemble();
        try {
            transmitter.send(new Frame(List.of(sprite), List.of()));
        } catch (RenderException e) {
            throw new RuntimeException(e);
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }
}