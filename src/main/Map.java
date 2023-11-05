package main;

import java.util.Random;

import Utilities.Constants.GameConstants;
import Utilities.Constants.TileType;

public class Map {

    private Random random = new Random();
    
    private Tile[][] grid = new Tile[GameConstants.MAP_WIDTH][GameConstants.MAP_HEIGHT];

    public Tile[][] getGrid() {
        return grid;
    }

    public Map() {
        generateMap();
    }

    private void generateMap() {

        for (int i = 0; i < GameConstants.MINES; i++) {
            generateMine(grid);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = new Tile(TileType.HIDDEN, checkForMines(i, j, grid));
                }
            }
        }
    }

    private TileType checkForMines(int i, int j, Tile[][] grid) {

        int mineCount = 0;


        if (i > 0 && j > 0 && grid[i-1][j-1]!= null && grid[i-1][j-1].getNumber() == TileType.MINE) {
            mineCount++;
        }
        if (i > 0 && grid[i-1][j]!= null && grid[i-1][j].getNumber() == TileType.MINE) {
            mineCount++;
        }
        if (i > 0 && j + 1 < GameConstants.MAP_HEIGHT && grid[i-1][j+1]!= null && grid[i-1][j+1].getNumber() == TileType.MINE) {
            mineCount++;
        }

        if (j > 0 && grid[i][j-1]!= null && grid[i][j-1].getNumber() == TileType.MINE) {
            mineCount++;
        }
        if (j + 1 < GameConstants.MAP_HEIGHT && grid[i][j+1]!= null && grid[i][j+1].getNumber() == TileType.MINE) {
            mineCount++;
        }

        if (i + 1 < GameConstants.MAP_WIDTH && j > 0 && grid[i+1][j-1]!= null && grid[i+1][j-1].getNumber() == TileType.MINE) {
            mineCount++;
        }
        if (i + 1 < GameConstants.MAP_WIDTH && grid[i+1][j]!= null && grid[i+1][j].getNumber() == TileType.MINE) {
            mineCount++;
        }
        if (i + 1 < GameConstants.MAP_WIDTH && j + 1 < GameConstants.MAP_HEIGHT && grid[i+1][j+1]!= null && grid[i+1][j+1].getNumber() == TileType.MINE) {
            mineCount++;
        }

        switch (mineCount) {
            case 1:
                return TileType.ONE;
            case 2:
                return TileType.TWO;
            case 3:
                return TileType.THREE;
            case 4:
                return TileType.FOUR;
            case 5:
                return TileType.FIVE;
            case 6:
                return TileType.SIX;
            case 7:
                return TileType.SEVEN;
            case 8:
                return TileType.EIGHT;
            default:
                return TileType.ZERO;
        }


    }

    private void generateMine(Tile[][] grid) {
        int[] coord = {random.nextInt(GameConstants.MAP_WIDTH), random.nextInt(GameConstants.MAP_HEIGHT)};

        while (grid[coord[0]][coord[1]] != null && grid[coord[0]][coord[1]].getType() == TileType.MINE) {
            coord[0] = random.nextInt(GameConstants.MAP_WIDTH);
            coord[1] = random.nextInt(GameConstants.MAP_HEIGHT);
        }

        Tile tile = new Tile(TileType.HIDDEN, TileType.MINE);
        
        grid[coord[0]][coord[1]] = tile;
    }
}
