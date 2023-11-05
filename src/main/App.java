package main;

import java.awt.Graphics;

import Utilities.MouseHandler;

public class App {
    
    private Screen screen;
    private Game game;
    private Window window;

    private MouseHandler mouseHandler;

    public App() {

        this.mouseHandler = new MouseHandler();
        this.game = new Game(mouseHandler);
        
        screen = new Screen(this, mouseHandler);
        window = new Window(screen);
        
        run();

    }

    private void run() {
    
        boolean running = true;

        while (running) {

            if (!(game.isGameOver() || game.isGameWon())) game.update();
            screen.repaint();

            if (game.isGameOver()) {
                window.setTitle("GAME OVER! CLICK TO RESTART");
            }
            if (game.isGameWon()) {
                window.setTitle("YOU WON! CLICK TO RESTART");
            }

            if (game.isGameOver() || game.isGameWon()) {
                if (mouseHandler.isFlagClicked()) {game = new Game(mouseHandler);}
                mouseHandler.setFlagClicked(false);
            }
            System.out.println("running");

        }

    }

    public void render(Graphics g) {

        game.render(g);

    }

}
