package com.tic_tac_toe.model;

public class Cell {
    private Coordinate coordinate;
    private Player player;
    private String boardSquare;

    public Cell(Coordinate coordinate, String boardSquare) {
        this.coordinate = coordinate;
        this.boardSquare = boardSquare;
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
        return boardSquare;
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
