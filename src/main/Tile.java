package main;

import Utilities.Constants.TileType;

public class Tile {
    
    private TileType type;
    
    public void setType(TileType type) {
        this.type = type;
    }

    TileType number;

    public TileType getNumber() {
        return number;
    }

    public TileType getType() {
        return type;
    }

    public Tile(TileType type, TileType number) {
        this.type = type;
        this.number = number;
    }

}
