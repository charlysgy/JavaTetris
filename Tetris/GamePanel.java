package Tetris;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public Square[][] gameField; // an array of Square objects

    public GamePanel(Square[][] gameField){
        this.gameField = gameField;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the game field
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 24; j++) {
                if (this.gameField[i][j] != null) {
                    g.setColor(gameField[i][j].color);
                    g.fillRect(i*30, j*30, 30, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(i*30, j*30, 30, 30);
                }
            }
        }
    }
}