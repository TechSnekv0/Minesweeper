package main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ArrayBlockingQueue;

import static Utilities.Constants.GameConstants.*;
import static Utilities.Constants.TileType.*;
import Utilities.LoadSave;
import Utilities.MouseHandler;

public class Game {
    private BufferedImage atlas;
    private Map map;

    private MouseHandler mouseHandler;

    private boolean gameOver;
    
    public boolean isGameOver() {
        return gameOver;
    }

    private boolean gameWon;

    public boolean isGameWon() {
        return gameWon;
    }

    private int tilesShown = 0;

    public int getTilesShown() {
        return tilesShown;
    }

    public Game(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;

        atlas = LoadSave.LoadAssets();
        map = new Map();
    }

    public void render(Graphics g) {
        BufferedImage renderImage = null;
        Tile[][] grid = map.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getType() == HIDDEN) {
                    renderImage = atlas.getSubimage(48, 16, 16, 16);
                } else if (grid[i][j].getType() == FLAGGED) {
                    renderImage = atlas.getSubimage(64, 16, 16, 16);
                } else if (grid[i][j].getType() == SHOWN) {
                    if (grid[i][j].getNumber() == MINE) {
                        renderImage = atlas.getSubimage(80, 16, 16, 16);
                    } else if (grid[i][j].getNumber() == ONE) {
                        renderImage = atlas.getSubimage(0, 0, 16, 16);
                    } else if (grid[i][j].getNumber() == TWO) {
                        renderImage = atlas.getSubimage(16, 0, 16, 16);
                    } else if (grid[i][j].getNumber() == THREE) {
                        renderImage = atlas.getSubimage(32, 0, 16, 16);
                    } else if (grid[i][j].getNumber() == FOUR) {
                        renderImage = atlas.getSubimage(48, 0, 16, 16);
                    } else if (grid[i][j].getNumber() == FIVE) {
                        renderImage = atlas.getSubimage(64, 0, 16, 16);
                    } else if (grid[i][j].getNumber() == SIX) {
                        renderImage = atlas.getSubimage(80, 0, 16, 16);
                    } else if (grid[i][j].getNumber() == SEVEN) {
                        renderImage = atlas.getSubimage(0, 16, 16, 16);
                    } else if (grid[i][j].getNumber() == EIGHT) {
                        renderImage = atlas.getSubimage(16, 16, 16, 16);
                    } else if (grid[i][j].getNumber() == ZERO) {
                        renderImage = atlas.getSubimage(32, 16, 16, 16);
                    }
                }
                g.drawImage(renderImage, i*TILESIZE, j*TILESIZE, TILESIZE, TILESIZE, null);
            }
        }
    }

    public void update() {

        ArrayBlockingQueue<int[]> showQueue = new ArrayBlockingQueue<int[]>(9999999);

        if (mouseHandler.isFlagClicked()) {
            int i = mouseHandler.getMouseX()/TILESIZE;
            int j = mouseHandler.getMouseY()/TILESIZE;
            
            if (mouseHandler.getButton() == MouseEvent.BUTTON1){
                showQueue.add(new int[]{i, j});
            } else if (mouseHandler.getButton() == MouseEvent.BUTTON3) {
                if (map.getGrid()[i][j].getType() == HIDDEN) map.getGrid()[i][j].setType(FLAGGED);
                else map.getGrid()[i][j].setType(HIDDEN);
            }

            mouseHandler.setFlagClicked(false);
        }

        while (!showQueue.isEmpty()) {
            int[] coords = showQueue.poll();
            int x = coords[0];
            int y = coords[1];
            System.out.println(x + ", " + y);
            Tile[][] grid = map.getGrid();
            Tile tile = grid[x][y];
            if (tile.getType() == FLAGGED || tile.getType() == SHOWN) {
                continue;
            }
            tile.setType(SHOWN);
            tilesShown++;
            if (tile.number == MINE) {
                gameOver = true;
            } else if (tile.number == ZERO) {
                if (x > 0) {
                    if (y > 0) {
                        if (grid[x-1][y-1].getType() == HIDDEN) {
                            if (!showQueue.contains(new int[]{x-1, y-1})) {
                                showQueue.add(new int[]{x-1, y-1});
                            }
                        }
                    }
                    if (grid[x-1][y].getType() == HIDDEN) {
                            if (!showQueue.contains(new int[]{x-1, y})) {
                                showQueue.add(new int[]{x-1, y});
                            }
                    }
                    if (y + 1 < MAP_HEIGHT) {
                        if (grid[x-1][y+1].getType() == HIDDEN) {
                            if (!showQueue.contains(new int[]{x-1, y+1})) {
                                showQueue.add(new int[]{x-1, y+1});
                            }
                        }
                    }
                }

                if (y > 0) {
                    if (grid[x][y-1].getType() == HIDDEN) {
                        if (!showQueue.contains(new int[]{x, y-1})) {
                            showQueue.add(new int[]{x, y-1});
                        }
                    }
                }
                if (y + 1 < MAP_HEIGHT) {
                    if (grid[x][y+1].getType() == HIDDEN) {
                        if (!showQueue.contains(new int[]{x, y+1})) {
                            showQueue.add(new int[]{x, y+1});
                        }
                    }
                }

                if (x + 1 < MAP_WIDTH) {
                    if (y > 0) {
                        if (grid[x+1][y-1].getType() == HIDDEN) {
                            if (!showQueue.contains(new int[]{x+1, y-1})) {
                                showQueue.add(new int[]{x+1, y-1});
                            }
                        }
                    }
                    if (grid[x+1][y].getType() == HIDDEN) {
                            if (!showQueue.contains(new int[]{x+1, y})) {
                                showQueue.add(new int[]{x+1, y});
                            }
                    }
                    if (y + 1 < MAP_HEIGHT) {
                        if (grid[x+1][y+1].getType() == HIDDEN) {
                            if (!showQueue.contains(new int[]{x+1, y+1})) {
                                showQueue.add(new int[]{x+1, y+1});
                            }
                        }
                    }
                }
            }
        }

        if (tilesShown == MAP_WIDTH*MAP_HEIGHT-MINES) gameWon = true;

    }
}
