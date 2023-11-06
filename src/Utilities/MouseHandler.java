package Utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

    private int mouseX, mouseY;
    public int getMouseY() {
        return mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

    private boolean flagClicked = false;

    public boolean isFlagClicked() {
        return flagClicked;
    }

    public void setFlagClicked(boolean flagClicked) {
        this.flagClicked = flagClicked;
    }

    private int button;

    public int getButton() {
        return button;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        flagClicked = true;
        System.out.println("clicked");
        button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
