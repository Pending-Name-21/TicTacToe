package com.tic_tac_toe.handler;

import com.tic_tac_toe.model.Cell;

public abstract class AbstractBoard {

    protected Cell[][] board;

    public AbstractBoard() {
        this.board = new Cell[3][3];
    }

    public Cell[][] getBoard() {
        return board;
    }

    public abstract void initBoard();
}
