package com.tic_tac_toe;

import com.bridge.renderHandler.Coord;
import com.bridge.renderHandler.Size;
import com.bridge.renderHandler.sprite.Sprite;
import com.bridge.renderHandler.sprite.SpriteRenderer;

public class Board extends SpriteRenderer {

    private Sprite[][] board;

    private Sprite[][] sprites;
    private Sprite currentSprite;

    public Board() {
        this.sprites = new Sprite[3][3];
        this.board = new Sprite[3][3];
        initializeSprites();
    }


    void placeSymbol(BoardPosition boardPosition, Player player) {
        int x = boardPosition.getXPosition();
        int y = boardPosition.getYPosition();

        Size spriteSize = new Size(1, 1);
        String spritePath = player.getSymbol() == 'X' ? "com/tic_tac_toe/Images/assets/general/X.png" : "com/tic_tac_toe/Images/assets/general/O.png";

        Coord spritePosition = new Coord(0,0);
        Sprite sprite = new Sprite(spritePosition, spriteSize, spritePath);

        board[x][y] = sprite;
    }
    public void changingSpriteByUserPosition(int x, int y){
        this.updateSprite(currentSprite,sprites[x][y]);
        currentSprite = sprites[x][y];
    }

    private void initializeSprites(){
        sprites[0][0] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected11.png");
        sprites[0][1] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected12.png");
        sprites[0][2] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected13.png");
        sprites[1][0] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected21.png");
        sprites[1][1] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected22.png");
        sprites[1][2] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected23.png");
        sprites[2][0] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected31.png");
        sprites[2][1] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected32.png");
        sprites[2][2] = new Sprite(new Coord(0,0),new Size(1,1),"com/tic_tac_toe/Images/assets/specificSelectedSquare/BoardSelected33.png");

        this.addSprite(new Sprite(new Coord(0,0),new Size(1,1),"/com/tic_tac_toe/Images/board/EmptyBoard.png"));
        this.addSprite(sprites[0][0]);
        currentSprite = sprites[0][0];

    }
    public Sprite[][] getBoard() {
        return board;
    }

    public void setBoard(Sprite[][] board) {
        this.board = board;
    }
}
