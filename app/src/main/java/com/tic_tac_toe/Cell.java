package com.tic_tac_toe;

import com.bridge.renderHandler.sprite.Sprite;

public class Cell {

    private Sprite sprite;
    private Coordinate coordinate;
    private Player player;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
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

    public void setSpriteHidden(boolean isHidden) {
        sprite.setHidden(isHidden);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
