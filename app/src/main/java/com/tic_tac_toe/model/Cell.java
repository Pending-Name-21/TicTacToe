package com.tic_tac_toe.model;

import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.sprite.Sprite;

public class Cell {
    private Coordinate coordinate;
    private Player player;
    private String boardSquare;
    private Sprite cellSprite;
    private SpriteBuilder spriteBuilder;

    public Cell(Coordinate coordinate, Sprite cellSprite) {
        this.coordinate = coordinate;
//        this.boardSquare = boardSquare;
        this.cellSprite = cellSprite;

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getBoardSquare() {
        return cellSprite.getPath().toString();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean wasUsed() {
        return player != null;
    }

    @Override
    public String toString() {
        return player != null ? player.getSymbol() + "" : "_";
    }
}
