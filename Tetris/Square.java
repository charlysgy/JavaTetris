package Tetris;

import java.awt.*;

public class Square {
    public int coordx;
    public int coordy;
    public Color color;
    public boolean isLocked;
    public boolean isBeingDeleted;

    public Square(int x, int y, Color color){
        this.coordx = x;
        this.coordy = y;
        this.color = color;
        this.isLocked = false;
        this.isBeingDeleted = false;
    }
}