package Utilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class LoadSave {
    
    public LoadSave() {

    }

    public BufferedImage LoadAssets() {

        BufferedImage atlas = null;

        try {
            ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("minesweeper_atlas.png"));
            atlas = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = atlas.getGraphics();
            image.paintIcon(null, g, 0, 0);

            // atlas = ImageIO.read(new File(System.getProperty("user.dir") + "/minesweeper_atlas.png"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return atlas;
    }

}
