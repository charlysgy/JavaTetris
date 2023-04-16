package Tetris;

import java.awt.*;
import java.util.Random;

public class Piece {
    public static int Pieces[][][][] = {
        {
                {
                        {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                },
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                },
        },
        {
                {
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
        },
        {
                {
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                }
        },
        {
                {
                        {0, 1, 1, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                }
        },
        {
                {
                        {1, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                }
        },
        {
                {
                        {0, 0, 1, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 0, 0},
                        {1, 1, 1, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                }
        },
        {
                {
                        {1, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                },
                {
                        {0, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                }
        }
    };

    public static Color[] colors = {Color.CYAN, Color.YELLOW, new Color(255, 0, 255), Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE};

    private int pieceId;
    private int[][] currentPiece;
    private int angle = 0;

    public Color color;
    public boolean isLocked = false;
    public int coordx = 7;
    public int coordy = 0;


    public Piece(int[] usedPieces){
        Random rand = new Random();
        while (true){
            this.pieceId = rand.nextInt(7);
            if (usedPieces[this.pieceId] == 0){
                usedPieces[this.pieceId] = 1;
                break;
            }
        }
        this.color = colors[this.pieceId];
        this.currentPiece = Pieces[this.pieceId][this.angle];
    }

    public void moveDown(Window winmain){
        // Check if the piece can move down, if not, lock it
        for (int i = 0; i < 4 && this.coordy + i < 24; i++) {
            for (int j = 0; j < 4 && this.coordx + j < 16; j++) {
                // If the piece has reached the bottom or touches another piece
                if (this.currentPiece[j][i] == 1 && this.coordy + i + 1 == 24){
                    this.isLocked = true;
                }
                if (this.currentPiece[j][i] == 1 && this.coordy + i + 1 < 24 &&
                    winmain.gameField[this.coordx + j][this.coordy + i + 1] != null &&
                    winmain.gameField[this.coordx + j][this.coordy + i + 1].isLocked == true){
                        this.isLocked = true;
                }
                
            }
        }

        // If it is locked (it can't move down), lock all the blocks in the game field
        if (this.isLocked){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (0 <= this.coordx + j && this.coordx + j < 16 && this.coordy + i < 24 &&
                        winmain.gameField[this.coordx + j][this.coordy + i] != null &&
                        winmain.gameField[this.coordx + j][this.coordy + i].isLocked == false) {
                            winmain.gameField[this.coordx + j][this.coordy + i].isLocked = true;
                    }
                    
                }
            }
            return;
        }
        
        // If it is not locked, remove the old positioned blocks
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (winmain.gameField[j][i] != null && winmain.gameField[j][i].isLocked == false) {
                    winmain.gameField[j][i] = null;
                }
            }
        }

        this.coordy += 1;
        for (int i = 0; i < 4 && this.coordy + i < 24; i++) {
            for (int j = 0; j < 4 && this.coordx + j < 16; j++) {
                // Add a block to draw the piece
                if (this.currentPiece[j][i] == 1) {
                    winmain.gameField[this.coordx + j][this.coordy + i] = new Square(this.coordx, this.coordy, this.color);
                }
            }
        }
    }

    public void moveRight(Window winmain){
        // Check if the piece can move left, if not, don't move it
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // If the piece has reached the left border or touches another piece
                if (this.currentPiece[j][i] == 1 && this.coordx + j + 1 >= 16){
                    return;
                }
                if (this.currentPiece[j][i] == 1 && this.coordx + j + 1 < 16 &&
                    winmain.gameField[this.coordx + j + 1][this.coordy + i] != null &&
                    winmain.gameField[this.coordx + j + 1][this.coordy + i].isLocked == true){
                        return;
                }
            }
        }

        // If this code is reached it means that the piece can move left
        // Remove the old positioned blocks
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (winmain.gameField[j][i] != null && winmain.gameField[j][i].isLocked == false) {
                    winmain.gameField[j][i] = null;
                }
            }
        }

        this.coordx += 1;
        for (int i = 0; i < 4 && this.coordy + i < 24; i++) {
            for (int j = 0; j < 4 && this.coordx + j < 16; j++) {
                // Add a block to draw the piece
                if (this.currentPiece[j][i] == 1) {
                    winmain.gameField[this.coordx + j][this.coordy + i] = new Square(this.coordx, this.coordy, this.color);
                }
            }
        }
    }

    public void moveLeft(Window winmain){
        // Check if the piece can move left, if not, don't move it
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // If the piece has reached the left border or touches another piece
                if (this.currentPiece[j][i] == 1 && this.coordx + j - 1 < 0){
                    return;
                }
                if (this.currentPiece[j][i] == 1 && this.coordx + j - 1 >= 0 &&
                    winmain.gameField[this.coordx + j - 1][this.coordy + i] != null &&
                    winmain.gameField[this.coordx + j - 1][this.coordy + i].isLocked == true){
                        return;
                }
            }
        }

        // If this code is reached it means that the piece can move left
        // Remove the old positioned blocks
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (winmain.gameField[j][i] != null && winmain.gameField[j][i].isLocked == false) {
                    winmain.gameField[j][i] = null;
                }
            }
        }

        this.coordx -= 1;
        for (int i = 0; i < 4 && this.coordy + i < 24; i++) {
            for (int j = 0; j < 4 && this.coordx + j < 16; j++) {
                // Add a block to draw the piece
                if (this.currentPiece[j][i] == 1) {
                    winmain.gameField[this.coordx + j][this.coordy + i] = new Square(this.coordx, this.coordy, this.color);
                }
            }
        }
    }

    public void turnLeft(Window winmain){
        angle = (angle - 1) % 4 >= 0 ? (angle - 1) % 4 : 3;
        this.currentPiece = Pieces[this.pieceId][this.angle];

        // Check if the piece can turn left, apply changes in certain conditions
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.currentPiece[j][i] == 1 && this.coordx + j < 0){
                    this.coordx += Math.abs(this.coordx + j);
                }
                else if (this.currentPiece[j][i] == 1 && this.coordx + j >= 16){
                    this.coordx -= Math.abs(this.coordx + j - 15);
                }
                else if (this.currentPiece[j][i] == 1 && 
                    winmain.gameField[this.coordx + j][this.coordy + i] != null &&
                    winmain.gameField[this.coordx + j][this.coordy + i].isLocked == true){
                    angle = (angle + 1) % 4;
                    this.currentPiece = Pieces[this.pieceId][this.angle];
                    return;
                }
            }
        }

        // Remove the old positioned blocks
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (winmain.gameField[j][i] != null && winmain.gameField[j][i].isLocked == false) {
                    winmain.gameField[j][i] = null;
                }
            }
        }

        for (int i = 0; i < 4 && this.coordy + i < 24; i++) {
            for (int j = 0; j < 4 && this.coordx + j < 16; j++) {
                // Add a block to draw the piece
                if (this.currentPiece[j][i] == 1) {
                    winmain.gameField[this.coordx + j][this.coordy + i] = new Square(this.coordx, this.coordy, this.color);
                }
            }
        }
        winmain.drawGameField();
    }

    public void turnRight(Window winmain){
        angle = (angle + 1) % 4;
        this.currentPiece = Pieces[this.pieceId][this.angle];

        // Check if the piece can turn right, apply changes in certain conditions
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.currentPiece[j][i] == 1 && this.coordx + j < 0){
                    this.coordx += Math.abs(this.coordx + j);
                }
                else if (this.currentPiece[j][i] == 1 && this.coordx + j >= 16){
                    this.coordx -= Math.abs(this.coordx + j - 15);
                }
                else if (this.currentPiece[j][i] == 1 && 
                    winmain.gameField[this.coordx + j][this.coordy + i] != null &&
                    winmain.gameField[this.coordx + j][this.coordy + i].isLocked == true){
                    angle = (angle - 1) % 4 >= 0 ? (angle - 1) % 4 : 3;
                    this.currentPiece = Pieces[this.pieceId][this.angle];
                    return;
                }
            }
        }

        // Remove the old positioned blocks
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 16; j++) {
                if (winmain.gameField[j][i] != null && winmain.gameField[j][i].isLocked == false) {
                    winmain.gameField[j][i] = null;
                }
            }
        }

        for (int i = 0; i < 4 && this.coordy + i < 24; i++) {
            for (int j = 0; j < 4 && this.coordx + j < 16; j++) {
                // Add a block to draw the piece
                if (this.currentPiece[j][i] == 1) {
                    winmain.gameField[this.coordx + j][this.coordy + i] = new Square(this.coordx, this.coordy, this.color);
                }
            }
        }
        winmain.drawGameField();
    }

    public int getAngle() {
        return this.angle;
    }

    public int[][] getPiece() {
        return this.currentPiece;
    }

    public Color getColor() {
        return this.color;
    }
}