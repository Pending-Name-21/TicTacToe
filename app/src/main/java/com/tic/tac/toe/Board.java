package com.tic.tac.toe;

import com.bridge.renderHandler.sprite.*;
import java.nio.file.Path;

public class Board extends Sprite {
    private char[][] board;

    public Board(Coord position, int z_index, Size size, Path path) {
        super(position, z_index, size, path);
        this.board = new char[3][3];
    }

    void placeSymbol(BoardPosition boardPosition, Player player) {
        int x = boardPosition.getXPosition();
        int y = boardPosition.getYPosition();
        board[x][y] = player.getSymbol();
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
