package Tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window {

    // Swing components needs to be defined here in order to be accessable
    // in all functions in this file
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel nextPiecePanel;
    private JLabel title;
    private JLabel helpMsg;
    private JLabel playerInfos;
    private Graphics2D nextPiecePanelG2d;
    

    // I'm aware that this is not the best way to do it, but I don't know how to do it better
    // I need to access the "this" Window object ref in KeyEvent class declaration
    private Window winref;

    // Frame state variables, public to be accessable from GameEngine class
    public boolean pause = false;
    public int score = 0;

    // Message are pre-written here to avoid multiple writing in the code
    private String messageFull = "<html>Press H to hide this message and start game<br>" +
                                    "Use arrows to move the piece when it's falling<br>" +
                                    "Press the spacebar to move the piece down<br>" + 
                                    "Press Q to turn the piece on left<br>" + 
                                    "Press D to turn the piece on right<br>" +
                                    "Press escape to quit the game</html>";
    private String messageTronc = "Press H to pause the game and show help message";
    private String scoreMsg = "Score = ";
    private String recordMsg = "Record = ";
    private String gamePLayedMsg = "Game played : ";
    
    // Player profile loaded at the beggining of the game with stats
    public Player player;

    // Game logic
    public boolean moved = false;

    // An array that represent the game field
    public Square[][] gameField;

    public Piece current;
    public Piece next;

    /**
     * Create the JFrame object that will contain all the game components
     * @param None
     * @return void
     */
    public void createWindow() {
        if (frame == null) {
            frame = new JFrame("Tetris");
        }
        frame.getContentPane().removeAll();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(150, 150, 255));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Handle the help message to write on the top left corner of the frame.
     * It depends on the pause variable.
     * @param None
     * @return void
     */
    public void handleHelpMessage(){
        if (helpMsg == null)
            helpMsg = new JLabel(messageTronc);
        
        // If the game is not currently paused, the pause it to let time
        // to the user to read the message. Displayed in it's full version
        // by default when window is created.
        pause = !pause;
        frame.add(helpMsg);
        helpMsg.setText(pause ? messageFull : messageTronc);
        helpMsg.setHorizontalAlignment(JLabel.CENTER);
        helpMsg.setVerticalAlignment(JLabel.TOP);
        helpMsg.setFont(new Font("Retro", Font.BOLD, 10));
        helpMsg.setForeground(new Color(0, 0, 0));
        Dimension size = helpMsg.getPreferredSize();
        helpMsg.setBounds(10, 10, size.width, size.height);

    }

    /**
     * Display informations about the player (score, record...)
     * @param None
     * @return void
     */
    public void displayPlayerInfos(){
        if (player != null) {
            player.checkRecord(score);
        }

        if (playerInfos == null)
            playerInfos = new JLabel(messageTronc);

        frame.add(playerInfos);
        playerInfos.setText("<html>" + player.name + "<br>" + 
                            scoreMsg + score + "<br>" + 
                            recordMsg + player.record + "<br>" + 
                            gamePLayedMsg + player.gamePLayed);
        playerInfos.setHorizontalAlignment(JLabel.CENTER);
        playerInfos.setVerticalAlignment(JLabel.TOP);
        playerInfos.setFont(new Font("Retro", Font.BOLD, 30));
        playerInfos.setForeground(new Color(0, 0, 0));
        Dimension size = playerInfos.getPreferredSize();
        playerInfos.setBounds(nextPiecePanel.getBounds().x,
                              nextPiecePanel.getBounds().y + 2*nextPiecePanel.getSize().height, 
                              size.width, size.height);
    }

    /**
     * Initialize the panel where the game will take place
     * @param None
     * @return void
     */
    public void initGamePanel() {
        // Setup the panel where the game will take place
        gamePanel = new GamePanel(this.gameField);
        frame.add(gamePanel);
        gamePanel.setBounds(100, title.getSize().height + 2*title.getBounds().y, 
                            480, 720);
        gamePanel.setBorder(BorderFactory.createMatteBorder(0, 2, 5, 2, Color.BLACK));
    }

    /**
     * Initialize the panel where the next piece will be displayed
     * @param None
     * @return void
     */
    public void initNextPiecePanel() {
        // Setup the panel where the next playable piece will be displayed
        if (nextPiecePanel == null)
            nextPiecePanel = new JPanel();
        frame.add(nextPiecePanel);
        
        nextPiecePanel.setBounds(700, gamePanel.getBounds().y, 
                                180, 180);
        nextPiecePanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 
                                                                4)
                                                            );

        nextPiecePanelG2d = (Graphics2D)nextPiecePanel.getGraphics();
    }

    /**
     * Initialize the window (JFrame) in which all the game graphics take place.
     * Creates different panels (JPanel) in which game pieces are displayed.
     * Call all method that handle message displaying
     * @param playerName : String, The name of the player
     * @return A reference to an instance of the Window class
     */
    public Window(String playerName) {
        winref = this;
        player = new Player(playerName);
        gameField = new Square[16][24];

        if (frame == null)
            createWindow();
        // Set layout to null for absolut positionning
        frame.setLayout(null);

        // Set the title of the frame
        title = new JLabel("TETRIS");
        frame.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        title.setFont(new Font("Retro", Font.BOLD, 100));
        title.setForeground(new Color(0, 0, 0));
        Dimension size = title.getPreferredSize();
        title.setBounds(500 - size.width/2, 
                        100 - size.height/2,
                        size.width, 
                        size.height);

        handleHelpMessage();
        initGamePanel();
        initNextPiecePanel();
        initEventListeners();
        displayPlayerInfos();
    }

    /**
     * Create the event listener for KeyEvent
     * @param None
     * @return void
     */
    public void initEventListeners() {
         // Create a KeyListener
         KeyListener listener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        player.checkRecord(score);
                        player.saveInfos();
                        frame.dispose();
                        break;
                    
                    case KeyEvent.VK_H:
                        handleHelpMessage();
                        break;

                    case KeyEvent.VK_SPACE:
                        if (!moved) {
                            moved = true;
                            current.moveDown(winref);
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if (!moved) {
                            moved = true;
                            current.moveLeft(winref);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if (!moved) {
                            moved = true;
                            current.moveRight(winref);
                        }
                        break;

                    case KeyEvent.VK_Q:
                        if (!moved) {
                            moved = true;
                            current.turnLeft(winref);
                        }
                        break;

                    case KeyEvent.VK_D:
                        if (!moved) {
                            moved = true;
                            current.turnRight(winref);
                        }
                        break;
                        
                    default:
                        break;
                }
            }
        };

        frame.addKeyListener(listener);
    }

    /**
     * Method that draw the next playable piece in it's panel
     * @param None
     * @return void
     */
    public void drawNextPiece() {
        nextPiecePanelG2d.clearRect(0, 0, nextPiecePanel.getWidth(), nextPiecePanel.getHeight());
        // Draw the game field
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.next.getPiece()[i][j] == 1) {
                    Rectangle rect = new Rectangle((int)(i+1.5)*30, (int)(j+1.5)*30, 30, 30);
                    nextPiecePanelG2d.setColor(next.color);
                    nextPiecePanelG2d.fill(rect);
                    nextPiecePanelG2d.setColor(Color.BLACK);
                    nextPiecePanelG2d.draw(rect);
                    gamePanel.paintComponents(nextPiecePanelG2d);
                }
            }
        }
    }


    /**
     * Method called when a new piece is used
     * @param None
     * @return void
     */
    public void addPieceToGameField() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.current.getPiece()[i][j] == 1) {
                    this.gameField[i + current.coordx][j + current.coordy] = new Square(current.coordx + j, current.coordy + i, current.color);
                }
            }
        }
    }

    public void drawGameField() {
        gamePanel.repaint();
    }

    public boolean isAlive(){
        return frame.isVisible();
    }

    /**
     * Method that turn all square in a line to white
     * Called only on full row for animation before deletion
     * @param line : int, the line to turn to white
     */
    public void blankLine(int line){
        for (int i = 0; i < 16; i++) {
            gameField[i][line].color = Color.WHITE;
        }
    }

    /**
     * Method that turn all square in a line to black
     * Called only on full row for animation before deletion
     * @param line : int, the line to turn to black
     */
    public void blackLine(int line){
        for (int i = 0; i < 16; i++) {
            gameField[i][line].color = Color.BLACK;
        }
    }

    /**
     * Method that delete a line, turn all square to null
     * @param line : int, the line to delete
     */
    public void deleteLine(int line){
        for (int i = 0; i < 16; i++) {
            gameField[i][line] = null;
        }
    }

    /**
     * Method that move all line above the deleted one down
     * Called in up to down order for multiple lines deletion to avoid overwriting
     * @param row
     */
    public void moveLines(int row){
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < 16; j++) {
                if (gameField[j][i] != null && gameField[j][i].isLocked == true) {
                    gameField[j][i].coordy += 1;
                    gameField[j][i + 1] = gameField[j][i];
                    gameField[j][i] = null;
                }
            }
        }
    }

    /**
     * Method that close the JFrame
     * @param None
     * @return void
     */
    public void kill(){
        frame.dispose();
    }
}
