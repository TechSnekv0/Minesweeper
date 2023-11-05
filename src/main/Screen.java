package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Utilities.MouseHandler;
import Utilities.Constants.AppConstants;

public class Screen extends JPanel{
 
    App app;

    public Screen(App app, MouseHandler mouseHandler) {
        
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        setPreferredSize(new Dimension(AppConstants.WIDTH, AppConstants.HEIGHT));

        this.app = app;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        app.render(g);
    }
    
}
