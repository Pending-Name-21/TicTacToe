package com.tic_tac_toe;

import com.tic_tac_toe.Library.inputsuscription.EventType;
import com.tic_tac_toe.Library.inputsuscription.IProcessInputSubscriber;
import com.tic_tac_toe.Library.piece.*;


public class Board extends Sprite{

    char[][] board;

    public Board(Coord position, Size size, String path) {
        super(position, size, path);
    }

    void placeSymbol(BoardPosition boardPosition, Player player){
        int x = boardPosition.getXPosition();
        int y = boardPosition.getYPosition();
        board[x][y] = player == Player.PLAYER_X ? 'X' : 'O';
    }

}
