package Utilities;

public class Constants {
    
    public static class AppConstants {

        public static final int WIDTH = 1024;
        public static final int HEIGHT = 512+256;

        public static final int SCALE = 4;

    }

    public static enum TileType {
        HIDDEN, SHOWN, FLAGGED, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE
    }

    public static class GameConstants {
        public static final int MAP_WIDTH = AppConstants.WIDTH/16/AppConstants.SCALE;
        public static final int MAP_HEIGHT = AppConstants.HEIGHT/16/AppConstants.SCALE;

        public static final int MINES = 24;

        public static final int TILESIZE = 16*AppConstants.SCALE;
    }

}
