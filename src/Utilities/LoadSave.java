package Utilities;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class LoadSave {
    
    public LoadSave() {

    }

    public static BufferedImage LoadAssets() {

        BufferedImage atlas = null;

        try {
            atlas = ImageIO.read(new File(System.getProperty("user.dir") + "/res/minesweeper_atlas.png"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return atlas;
    }

}
