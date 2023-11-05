package main;

import javax.swing.JFrame;

public class Window extends JFrame{
    
    public Window(Screen screen) {

        add(screen);
        pack();

        setTitle("Minesweeper");
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

    }

}
