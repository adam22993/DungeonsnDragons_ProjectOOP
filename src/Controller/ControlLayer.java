package Controller;

import GameBoard.GameBoard;
import Patterns.Factory.TileFactory;
import UI.GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlLayer implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    private boolean keysPressedWSADEQ12345678[] = new boolean[14];
    private int levelCounter;
    private boolean keyboardPressed = false;
    private boolean playerGamePlayInput = false;
    private char playerGamePlayInputVal = ' ';

    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private final Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

    private JFrame window;
    private final GameBoard gameBoard;
    private final GameUI gameUI;
    private JButton choice1, choice2, choice3, choice4, choice5, choice6, choice7, choice8, qButton, wButton, eButton, aButton, sButton, dButton;

    private TileFactory tileFactory;

    private MessageCallback m;

    public ControlLayer(GameBoard gameboard) {
        // Check the interface changes in the UI package.
        window = createWindow();
        gameUI = new GameUI(window);
        MessageCallback m = gameUI.getMessageCallback();
        tileFactory = new TileFactory(m);
        gameboard.setTileFactory(tileFactory);
        gameBoard = gameboard;
        this.levelCounter = gameboard.getLevels().size();
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.setFocusable(true);
        window.setFocusTraversalKeysEnabled(false); // this does not allow the TAB key to be used for focus traversal
        gameUI.openWelcomeScreen(welcomeScreenControls());
    }

    public JFrame createWindow(){
        window = new JFrame();
        window.setTitle("Dungeons & Dragons OOP Project");
        window.setVisible(true);
        window.setIconImage(new ImageIcon("resources/Images/DNDICON.png").getImage());
        window.setSize(1200, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.getContentPane().setBackground(Color.black);
        window.setResizable(false);
        return window;
    }


    public JPanel welcomeScreenControls(){
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(500, 546, 200, 150);
        startButtonPanel.setBackground(Color.black);
        startButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JButton startButton = new JButton("START");
        startButton.setFont(normalFont);
        startButton.setBackground(Color.white);
        startButton.setOpaque(true);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> {
            gameUI.characterCreationScreen(characterChoice());
        });

        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(normalFont);
        quitButton.setBackground(Color.white);
        quitButton.setFocusable(false);
        quitButton.setOpaque(true);
        quitButton.addActionListener(e -> {
            window.dispose();
            System.exit(0);
        });
        quitButton.addKeyListener(this);

        startButtonPanel.add(startButton);
        startButtonPanel.add(quitButton);
        startButtonPanel.add(createDebugStart());
        startButtonPanel.setBackground(new Color(0, 0, 0, 0));
        startButtonPanel.setOpaque(false);
        startButtonPanel.setVisible(true);
        return startButtonPanel;
    }

    private JButton createDebugStart(){
        JButton debugAccessGameButton = new JButton("DEBUG"); // TODO: Implement debug button
        debugAccessGameButton.setBounds(500, 560, 400, 100);
        debugAccessGameButton.setFont(normalFont);
        debugAccessGameButton.setBackground(Color.black);
        debugAccessGameButton.setForeground(Color.white);
        debugAccessGameButton.setFocusable(false);
        debugAccessGameButton.setVisible(true);
        debugAccessGameButton.setOpaque(true);
        debugAccessGameButton.addActionListener(e -> {
            handleDebugStart(0);
        });
        return debugAccessGameButton;
    }
    public JPanel characterChoice(){
//        String playerName = JOptionPane.showInputDialog("Enter your name: ");
//        int counter = 0;
//        while (playerName == null || !playerName.matches("[a-zA-Z0-9]+") && !playerName.matches("[a-zA-Z]+")) {
//            playerName = JOptionPane.showInputDialog("Enter alphanumeric name! ");
//            counter++;
//            if (counter == 2){
//                JOptionPane.showMessageDialog(null, "You have failed to enter a valid name 3 times. You will be named 'Player' by default.");
//                playerName = "Player";
//                break;
//            }
//        }
        String playerName = "Player";
        gameBoard.incrementGameLoadingStage();
        JPanel characterSelectOptions = new JPanel();
        characterSelectOptions.setBounds(300, 380, 600, 250);
        characterSelectOptions.setBackground(Color.black);
        characterSelectOptions.setLayout(new GridLayout(4, 1));

        choice1 = new JButton("Jon Snow");
        choice1.setBackground(Color.white);
        choice1.setForeground(Color.black);
        choice1.setFont(boardFont);
        choice1.setFocusable(false);
        characterSelectOptions.add(choice1);
        choice1.addActionListener(e -> {
            handleGameStart(0);
        });

        choice2 = new JButton("The Hound");
        choice2.setBackground(Color.white);
        choice2.setForeground(Color.black);
        choice2.setFont(boardFont);
        choice2.setFocusable(false);
        characterSelectOptions.add(choice2);
        choice2.addActionListener(e -> {
            handleGameStart(1);
        });

        choice3 = new JButton("Melisandre");
        choice3.setBackground(Color.white);
        choice3.setForeground(Color.black);
        choice3.setFont(boardFont);
        choice3.setFocusable(false);
        characterSelectOptions.add(choice3);
        choice3.addActionListener(e -> {
            handleGameStart(2);
        });

        choice4 = new JButton("Thoros of Myr");
        choice4.setBackground(Color.white);
        choice4.setForeground(Color.black);
        choice4.setFont(boardFont);
        choice4.setFocusable(false);
        characterSelectOptions.add(choice4);
        choice4.addActionListener(e -> {
            handleGameStart(3);
        });

        choice5 = new JButton("Arya Stark");
        choice5.setBackground(Color.white);
        choice5.setForeground(Color.black);
        choice5.setFont(boardFont);
        choice5.setFocusable(false);
        characterSelectOptions.add(choice5);
        choice5.addActionListener(e -> {
            handleGameStart(4);
        });

        choice6 = new JButton("Bronn");
        choice6.setBackground(Color.white);
        choice6.setForeground(Color.black);
        choice6.setFont(boardFont);
        choice6.setFocusable(false);
        characterSelectOptions.add(choice6);
        choice6.addActionListener(e -> {
            handleGameStart(5);
        });

        choice7 = new JButton("Ygritte");
        choice7.setBackground(Color.white);
        choice7.setForeground(Color.black);
        choice7.setFont(boardFont);
        choice7.setFocusable(false);
        characterSelectOptions.add(choice7);
        choice7.addActionListener(e -> {
            handleGameStart(6);
        });

        choice8 = new JButton(playerName + " (Custom)");
        choice8.setBackground(Color.white);
        choice8.setForeground(Color.black);
        choice8.setFont(boardFont);
        choice8.setFocusable(false);
        characterSelectOptions.add(choice8);
        choice8.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "I'm crazy... but not that crazy!", "Custom character", JOptionPane.ERROR_MESSAGE);
        });


        characterSelectOptions.addKeyListener(this);
        characterSelectOptions.addMouseListener(this);
        characterSelectOptions.setFocusable(false);
        return characterSelectOptions;
    }
    public JPanel playerControls() {
        gameBoard.incrementGameLoadingStage();
        // create player panel
        JPanel playerChoicesPanel = new JPanel();
        playerChoicesPanel.setLayout(new GridLayout(2, 3));
        playerChoicesPanel.setBounds(750, 400, 300, 150);
        playerChoicesPanel.setBackground(Color.black);
        playerChoicesPanel.setForeground(Color.white);
        playerChoicesPanel.addKeyListener(this);
        playerChoicesPanel.requestFocus();
        playerChoicesPanel.setOpaque(false);


        // create player control buttons
        qButton = new JButton("Q");
        qButton.setFont(normalFont);
        qButton.setBackground(Color.white);
        qButton.setForeground(Color.black);
        qButton.addActionListener(this);
        qButton.addActionListener(e -> {
            playerGamePlayInputVal = 'q';
            handlePlayerChoice();
            playerGamePlayInputVal = ' ';
        });
        qButton.setFocusable(false);

        wButton = new JButton("W");
        wButton.setFont(normalFont);
        wButton.setBackground(Color.white);
        wButton.setForeground(Color.black);
        wButton.addActionListener(this);
        wButton.addActionListener(e -> {
            playerGamePlayInputVal = 'w';
            handlePlayerChoice();
            playerGamePlayInputVal = ' ';
        });
        wButton.setFocusable(false);

        eButton = new JButton("E");
        eButton.setFont(normalFont);
        eButton.setBackground(Color.white);
        eButton.setForeground(Color.black);
        eButton.addActionListener(this);
        eButton.addActionListener(e -> {
            playerGamePlayInputVal = 'e';
            handlePlayerChoice();
            playerGamePlayInputVal = ' ';
        });
        eButton.setFocusable(false);

        aButton = new JButton("A");
        aButton.setFont(normalFont);
        aButton.setBackground(Color.white);
        aButton.setForeground(Color.black);
        aButton.addActionListener(this);
        aButton.addActionListener(e -> {
            playerGamePlayInputVal = 'a';
            handlePlayerChoice();
            playerGamePlayInputVal = ' ';
        });

        aButton.setFocusable(false);
        sButton = new JButton("S");
        sButton.setFont(normalFont);
        sButton.setBackground(Color.white);
        sButton.setForeground(Color.black);
        sButton.addActionListener(this);
        sButton.addActionListener(e -> {
            playerGamePlayInputVal = 's';
            handlePlayerChoice();
            playerGamePlayInputVal = ' ';
        });

        sButton.setFocusable(false);
        dButton = new JButton("D");
        dButton.setFont(normalFont);
        dButton.setBackground(Color.white);
        dButton.setForeground(Color.black);
        dButton.addActionListener(e -> {
            playerGamePlayInputVal = 'd';
            handlePlayerChoice();
            playerGamePlayInputVal = ' ';
        });

        dButton.setFocusable(false);

        // add them to the panel
        playerChoicesPanel.add(qButton);
        playerChoicesPanel.add(wButton);
        playerChoicesPanel.add(eButton);
        playerChoicesPanel.add(aButton);
        playerChoicesPanel.add(sButton);
        playerChoicesPanel.add(dButton);
        playerChoicesPanel.setVisible(true);
        playerChoicesPanel.addMouseListener(this);
        playerChoicesPanel.setFocusable(true);
        return playerChoicesPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is invoked when a key is typed
    }

    @Override
    public void keyPressed(KeyEvent e) { //TODO: after fixing game window not showing the board - handle this
        if (e.getKeyCode() == KeyEvent.VK_W && !keyboardPressed && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("W Pressed");
            keyboardPressed = true;
            playerGamePlayInput = true;
            playerGamePlayInputVal = 'w';
            keysPressedWSADEQ12345678[0] = true;
            wButton.getModel().setPressed(true);
            wButton.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S && !keyboardPressed && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("S Pressed");
            keyboardPressed = true;
            playerGamePlayInput = true;
            playerGamePlayInputVal = 's';
            keysPressedWSADEQ12345678[1] = true;
            sButton.getModel().setPressed(true);
            sButton.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_A && !keyboardPressed && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("A Pressed");
            keyboardPressed = true;
            playerGamePlayInput = true;
            playerGamePlayInputVal = 'a';
            keysPressedWSADEQ12345678[2] = true;
            aButton.getModel().setPressed(true);
            aButton.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D && !keyboardPressed && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("D Pressed");
            keyboardPressed = true;
            playerGamePlayInput = true;
            playerGamePlayInputVal = 'd';
            keysPressedWSADEQ12345678[3] = true;
            dButton.getModel().setPressed(true);
            dButton.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_E && !keyboardPressed && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("E Pressed");
            keyboardPressed = true;
            playerGamePlayInput = true;
            playerGamePlayInputVal = 'e';
            keysPressedWSADEQ12345678[4] = true;
            eButton.getModel().setPressed(true);
            eButton.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q && !keyboardPressed && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("Q Pressed");
            keyboardPressed = true;
            playerGamePlayInput = true;
            playerGamePlayInputVal = 'q';
            keysPressedWSADEQ12345678[5] = true;
            qButton.getModel().setPressed(true);
            qButton.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_1 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("1 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[6] = true;
            choice1.getModel().setPressed(true);
            choice1.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_2 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("2 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[7] = true;
            choice2.getModel().setPressed(true);
            choice2.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_3 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("3 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[8] = true;
            choice3.getModel().setPressed(true);
            choice3.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_4 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("4 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[9] = true;
            choice4.getModel().setPressed(true);
            choice4.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_5 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("5 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[10] = true;
            choice5.getModel().setPressed(true);
            choice5.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_6 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("6 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[11] = true;
            choice6.getModel().setPressed(true);
            choice6.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_7 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("7 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[12] = true;
            choice7.getModel().setPressed(true);
            choice7.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_8 && !keyboardPressed && gameBoard.getGameLoadingStage() == 1) {
            System.out.println("8 Pressed");
            keyboardPressed = true;
            keysPressedWSADEQ12345678[13] = true;
            choice8.getModel().setPressed(true);
            choice8.getModel().setArmed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Escape Pressed");
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game", JOptionPane.YES_NO_CANCEL_OPTION);

            // Handle the user's choice
            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("Quitting the game...");
                System.exit(0);
            } else if (choice == JOptionPane.NO_OPTION) {
                System.out.println("Continuing the game...");
                // Perform any necessary actions to continue the game
            } else if (choice == JOptionPane.CANCEL_OPTION) {
                System.out.println("Canceling the quit operation...");
                // Perform any necessary actions to cancel the quit operation
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // This method is invoked when a key is released
        char keyReleased = e.getKeyChar();
//        System.out.println("Key released: " + keyReleased);


        if (e.getKeyCode() == KeyEvent.VK_W && keyboardPressed && keysPressedWSADEQ12345678[0] && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("W released");
            keyboardPressed = false;
            playerGamePlayInput = false;
            playerGamePlayInputVal = ' ';
            wButton.getModel().setPressed(false);
            wButton.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S && keyboardPressed && keysPressedWSADEQ12345678[1] && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("S released");
            keyboardPressed = false;
            playerGamePlayInput = false;
            playerGamePlayInputVal = ' ';
            sButton.getModel().setPressed(false);
            sButton.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_A && keyboardPressed && keysPressedWSADEQ12345678[2] && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("A released");
            keyboardPressed = false;
            playerGamePlayInput = false;
            playerGamePlayInputVal = ' ';
            aButton.getModel().setPressed(false);
            aButton.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D && keyboardPressed && keysPressedWSADEQ12345678[3] && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("D released");
            keyboardPressed = false;
            playerGamePlayInput = false;
            playerGamePlayInputVal = ' ';
            dButton.getModel().setPressed(false);
            dButton.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_E && keyboardPressed && keysPressedWSADEQ12345678[4] && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("E released");
            keyboardPressed = false;
            playerGamePlayInput = false;
            playerGamePlayInputVal = ' ';
            eButton.getModel().setPressed(false);
            eButton.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q && keyboardPressed && keysPressedWSADEQ12345678[5] && gameBoard.getGameLoadingStage() >= 2) {
            System.out.println("Q released");
            keyboardPressed = false;
            playerGamePlayInput = false;
            playerGamePlayInputVal = ' ';
            qButton.getModel().setPressed(false);
            qButton.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_1 && keyboardPressed && keysPressedWSADEQ12345678[6] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("1 released");
            keyboardPressed = false;
            choice1.getModel().setPressed(false);
            choice1.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_2 && keyboardPressed && keysPressedWSADEQ12345678[7] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("2 released");
            keyboardPressed = false;
            choice2.getModel().setPressed(false);
            choice2.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_3 && keyboardPressed && keysPressedWSADEQ12345678[8] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("3 released");
            keyboardPressed = false;
            choice3.getModel().setPressed(false);
            choice3.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_4 && keyboardPressed && keysPressedWSADEQ12345678[9] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("4 released");
            keyboardPressed = false;
            choice4.getModel().setPressed(false);
            choice4.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_5 && keyboardPressed && keysPressedWSADEQ12345678[10] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("5 released");
            keyboardPressed = false;
            choice5.getModel().setPressed(false);
            choice5.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_6 && keyboardPressed && keysPressedWSADEQ12345678[11] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("6 released" );
            keyboardPressed = false;
            choice6.getModel().setPressed(false);
            choice6.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_7 && keyboardPressed && keysPressedWSADEQ12345678[12] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("7 released");
            keyboardPressed = false;
            choice7.getModel().setPressed(false);
            choice7.getModel().setArmed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_8 && keyboardPressed && keysPressedWSADEQ12345678[13] && gameBoard.getGameLoadingStage() <= 1) {
            System.out.println("8 released");
            keyboardPressed = false;
            choice8.getModel().setPressed(false);
            choice8.getModel().setArmed(false);
        }
    }

    private void handlePlayerChoice(){ // any other loading of the game
        gameBoard.vectorGameTick(playerGamePlayInputVal);
        if (gameBoard.getLevels().size() + 1 < this.levelCounter){
            --this.levelCounter;
            gameUI.playNextMusic();
        }
            gameUI.updateBoard(gameBoard.getBoardString(), gameBoard.getPlayers());
            gameUI.updateGameTick(gameBoard.getGameTickCounter());
        if (!gameBoard.getPlayers().get(0).isDead() && gameBoard.getLevels().isEmpty() && gameBoard.getTurnSequence().isEmpty()) {
            gameUI.playVictorySong();
            window.dispose();
            int pressed = JOptionPane.showConfirmDialog(null, "You have won the game!", "Congratulation", JOptionPane.DEFAULT_OPTION);
            if (pressed == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        } else if (gameBoard.getPlayers().get(0).isDead() && !gameBoard.getLevels().isEmpty()) {
            gameUI.stopMusic();
            window.dispose();
            int pressed = JOptionPane.showConfirmDialog(null, "You have lost the game!", "Game Over", JOptionPane.DEFAULT_OPTION);
            if (pressed == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

    private void handleGameStart(int choice){ // first loading of the game
        gameBoard.setPlayerChoice(choice);
        gameBoard.loadNextLevel();
        gameUI.createGameScreen(gameBoard.getBoardString(), gameBoard.getPlayers().get(0), playerControls());
        gameUI.updateBoard(gameBoard.getBoardString(), gameBoard.getPlayers());
    }

    private void handleDebugStart(int choice){ // first loading of the game
        gameBoard.setPlayerChoice(choice);
        gameBoard.loadNextLevel();
        gameUI.debugStart(gameBoard.getBoardString(), gameBoard.getPlayers().get(0), playerControls());
        gameUI.updateBoard(gameBoard.getBoardString(), gameBoard.getPlayers());
    }

    @Override
    public void mouseClicked(MouseEvent e) { // checks if mouse is clicked - after releasing press
//        System.out.println("Mouse clicked" + counter);
//        counter++;
        window.requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) { // checks if mouse is pressed - initial press
//        System.out.println("Mouse pressed" + counter);
//        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) { // checks if mouse is released - after pressing is released
//        System.out.println("Mouse released" + counter);
//        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseEntered(MouseEvent e) { // checks if mouse is entered - when mouse enters the window - not labels or panels
//        System.out.println("Mouse entered" + counter);
//        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) { // checks if mouse is exited - when mouse exits the window - not labels or panels
//        System.out.println("Mouse exited" + counter);
//        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        if (counter % 25 == 0)
//            System.out.println("Mouse dragged" + counter);
//        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        if (counter % 100 == 0)
//            System.out.println("Mouse moved" + counter + " (x: " + e.getX() + ", y: " + e.getY() + ")");
//        counter++;
        window.requestFocus();
    }
}
