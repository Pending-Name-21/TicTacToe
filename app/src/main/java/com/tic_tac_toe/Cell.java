package com.tic_tac_toe;

import com.bridge.renderHandler.sprite.Sprite;

public class Cell {

    private Sprite selectedSprite;
    private char symbol;
    public Cell(Sprite selectedSprite){
        this.selectedSprite = selectedSprite;
        this.selectedSprite.setHidden(true);
    }

    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
    public void setSpriteHidden(boolean isHidden){
        selectedSprite.setHidden(isHidden);
    }

    public char getSymbol() {
        return symbol;
    }
}
