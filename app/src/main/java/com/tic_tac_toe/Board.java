package com.tic_tac_toe;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.*;
import com.bridge.renderHandler.sprite.Sprite;

public class Board{
    private SpriteBuilder builder;
    private Cell[][] board;
    private Cell currentCell;

    public Board(SpriteRepository repository) throws NonExistentFilePathException {
        this.board = new Cell[3][3];
        builder = new SpriteBuilder(repository);
        builder.buildSize(5,5);
        builder.buildCoord(0,0);
        builder.buildPath("/com/tic_tac_toe/Images/board/EmptyBoard.png");
        initializeSprites();
    }


    void placeSymbol(int x, int y, Player player) throws NonExistentFilePathException {

        builder.buildSize(5,5);
        builder.buildCoord(x,y);
        builder.buildPath(player.getSymbol() == 'X' ? "com/tic_tac_toe/Images/assets/general/X.png" : "com/tic_tac_toe/Images/assets/general/O.png");
        builder.assemble().setZ_index(3);

        board[x][y].setSymbol(player.getSymbol());
    }
    public void changingSpriteHiddenByUserPosition(int x, int y){
        currentCell.setSpriteHidden(true);
        currentCell = board[x][y];
        currentCell.setSpriteHidden(false);
    }

    private void initializeSprites() throws NonExistentFilePathException {
        String basePath = "com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String imagePath = basePath + (i + 1) + (j + 1) + ".png";

                builder.buildCoord(i, j);
                builder.buildSize(5, 5);
                builder.buildPath(imagePath);
                Sprite sprite = builder.assemble();
                sprite.setZ_index(2);
                board[i][j] = new Cell(sprite);
            }
        }

        currentCell = board[0][0];
        currentCell.setSpriteHidden(false);
    }
    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }
}
