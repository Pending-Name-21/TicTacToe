package com.tic_tac_toe.handler;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.Transmitter;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.Coord;
import com.bridge.renderHandler.sprite.Size;
import com.bridge.renderHandler.sprite.Sprite;
import com.tic_tac_toe.model.Cell;
import com.tic_tac_toe.model.Coordinate;
import com.tic_tac_toe.utils.SourcePaths;
import java.nio.file.Path;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private Cell[][] board;
    private Cell currentCell;
    private SpriteBuilder builder;
    private Transmitter transmitter;
    private SpriteRepository boardRepository;


    public Board(Transmitter transmitter) {
        this.transmitter = transmitter;
        this.board = new Cell[3][3];
        this.boardRepository = new SpriteRepository();
    }

    public void setCurrentCell(int x, int y) {
        if (currentCell.getCoordinate().getX() != x || currentCell.getCoordinate().getY() != y) {
            currentCell = board[x][y];

            SpriteBuilder spriteBuilder = new SpriteBuilder(new SpriteRepository());
            spriteBuilder.buildSize(600.0, 800);
            spriteBuilder.buildCoord(0, 0);
            try {
                spriteBuilder.buildPath(currentCell.getBoardSquare());
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
    }

    public void initBoard() throws NonExistentFilePathException {
        int des = 200;
        board[0][0] = new Cell(
                new Coordinate(-des - 55, des - 10),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-11.png"));
        board[0][1] = new Cell(
                new Coordinate(0, des - 10),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-12.png"));
        board[0][2] = new Cell(
                new Coordinate(des + 55, des - 10),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-13.png"));

        board[1][0] = new Cell(
                new Coordinate(-des - 55, 0),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-21.png"));
        board[1][1] = new Cell(
                new Coordinate(0, 0),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-22.png"));
        board[1][2] = new Cell(
                new Coordinate(des + 55, 0),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-23.png"));

        board[2][0] = new Cell(
                new Coordinate(-des - 55, -des),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-31.png"));
        board[2][1] = new Cell(
                new Coordinate(0, -des),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-32.png"));
        board[2][2] = new Cell(
                new Coordinate(des + 55, -des),
                SourcePaths.BASE_PATH.concat("/board/selectedCells/Board-33.png"));

        currentCell = board[0][0];

        SpriteBuilder spriteBuilder = new SpriteBuilder(new SpriteRepository());
        spriteBuilder.buildSize(600.0, 800);
        spriteBuilder.buildCoord(0, 0);
        spriteBuilder.buildPath(currentCell.getBoardSquare());
        Sprite sprite = spriteBuilder.assemble();
        sprite.setZ_index(5);
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

    public void printBoard() {
        for (Cell[] cells : board) {
            System.out.println(Arrays.toString(cells));
        }
        System.out.println();
    }
}
