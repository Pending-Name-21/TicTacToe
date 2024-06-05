package com.tic_tac_toe;

import com.bridge.piece.*;

public class Board extends Sprite {

    private char[][] board;

    public Board(Coord position, Size size, String path) {
        super(position, size, path);
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
