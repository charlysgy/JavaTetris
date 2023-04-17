package Tetris;

import javax.swing.*;

public class GameEngine {

    private static int[] fullRows = new int[4];
    private static int[] usedPieces = new int[7];
    private static Window winref;

    private static boolean checkForFullRow(Window winmain) {
        int index = 0;
        int nbFullRow = 0;
        for (int i = 0; i < 24; i++) {
            boolean isRowFull = true;
            for (int j = 0; j < 16; j++) {
                if (winmain.gameField[j][i] == null) {
                    isRowFull = false;
                    break;
                }
                else if (winmain.gameField[j][i] != null && !winmain.gameField[j][i].isLocked){
                    isRowFull = false;
                    break;
                }
                else if (winmain.gameField[j][i] != null && winmain.gameField[j][i].isLocked && winmain.gameField[j][i].isBeingDeleted){
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                nbFullRow++;
                for (int j = 0; j < 16; j++) {
                    winmain.gameField[j][i].isBeingDeleted = true;
                }
                fullRows[index] = i;
                index++;
                isRowFull = false;
            }
        }
        winmain.score += nbFullRow == 1 ? 40 : nbFullRow == 2 ? 100 : nbFullRow == 3 ? 300 : nbFullRow == 4 ? 1200 : 0;
        winmain.displayPlayerInfos();
        return fullRows[0] != -1;
    }

    public static void checkUsedPieces() {
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += usedPieces[i];
        }
        if (sum == 7) {
            for (int i = 0; i < 7; i++) {
                usedPieces[i] = 0;
            }
        }
    }

    public static boolean isGameOver(Window winmain){
        for (int i = 0; i < 16; i++) {
            if (winmain.gameField[i][0] != null && winmain.gameField[i][0].isLocked) {
                JOptionPane.showMessageDialog(null, "Game Over!");
                return true;
            }
        }
        return false;
    }

    /**
     * Run the game
     */
    public static void runGame() {
        for (int i = 0; i < 4; i++) {
            fullRows[i] = -1;
        }
        for (int i = 0; i < 7; i++) {
            usedPieces[i] = 0;
        }

        long delay = 1000;
        long lastSysTime = System.currentTimeMillis();
        long framerate = System.currentTimeMillis();

        String playerName = JOptionPane.showInputDialog("Enter a player name");
        if (playerName == null || playerName.equals(""))
            return;

        Window winmain = new Window(playerName);
        
        winref = winmain;
        winmain.current = new Piece(usedPieces);
        winmain.addPieceToGameField();
        winmain.drawGameField();

        winmain.next = new Piece(usedPieces);
        winmain.drawNextPiece();

        JOptionPane.showMessageDialog(null, "Read the message in the top left corner then press H to start the game", "Start Game", JOptionPane.INFORMATION_MESSAGE);


        while(winmain.isAlive() && !isGameOver(winmain)){

            // Doing so permits to not run the loop at 100% of the CPU
            try {
                if (winmain.pause) {
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Speed up the game as the score increases
            delay = (int)(1000.f / (1.f + 0.05 * Math.sqrt(winmain.score)));

            // Automatic down movement
            if (!winmain.pause && System.currentTimeMillis() - lastSysTime >= delay) {

                lastSysTime = System.currentTimeMillis();
                
                winmain.current.moveDown(winmain);
                winmain.drawGameField();

                // If full row(s) have been detected, delete them with animation
                // Uses a thread to not block the main thread and keep the game fluid
                if (checkForFullRow(winmain)) {
                    Thread lineBlinker = new Thread(new Runnable() {
                        public void run(){
                            long lastSysTime = System.currentTimeMillis();

                            for (int counter = 0; counter < 5; counter++) {
                                for (int index = 0; index < 4; index ++) {
                                    if (fullRows[index] != -1) {
                                        winmain.blankLine(fullRows[index]);
                                    }
                                }
                                winmain.drawGameField();
                                while (System.currentTimeMillis() - lastSysTime <= 100);
                                lastSysTime = System.currentTimeMillis();
                                for (int index = 0; index < 4; index ++) {
                                    if (fullRows[index] != -1) {
                                        winmain.blackLine(fullRows[index]);
                                    }
                                }
                                winmain.drawGameField();
                                while (System.currentTimeMillis() - lastSysTime <= 100);
                                lastSysTime = System.currentTimeMillis();
                            }

                            for (int index = 0; index < 4; index++) {
                                if (fullRows[index] != -1) {
                                    winmain.deleteLine(fullRows[index]);
                                    winmain.moveLines(fullRows[index]);
                                }
                                fullRows[index] = -1;
                            }
                        }
                    }); // end of thread
                    lineBlinker.start();
                }
            }

            // Refresh the screen with a framerate of 60 fps
            else if (!winmain.pause && System.currentTimeMillis() - framerate >= 1000/60){
                
                // If the piece is locked (has reached the bottom or another piece)
                if (winmain.current.isLocked){
                    winmain.current = winmain.next;
                    winmain.addPieceToGameField();
                    winmain.next = new Piece(usedPieces);
                    checkUsedPieces();
                    winmain.drawNextPiece();
                }
                winmain.drawGameField();
                framerate = System.currentTimeMillis();
                winmain.moved = false; // Allows the piece to move again
            }
        }

        // Save the score and player's infos
        winmain.player.score = winmain.score;
        winmain.player.saveInfos();
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        int answer = 0;
        while (answer == 0) {
            if (winref != null) {
                winref.kill();
            }
            runGame();
            answer = JOptionPane.showConfirmDialog(null, "Do you wanna play again ?", "PLay Again ?", 0);
        }
        if (winref != null) {
            winref.kill();
        }
        return;
    }
}