package UI;

import GameBoard.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlLayer implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    final boolean[] keys = new boolean[14]; // used to check if a key is pressed
    int counter = 1;
    ActionListener mouseButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            System.out.println(actionCommand + " button activated");
            window.requestFocus();
        }
    };


    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

    private Container con;
    private JFrame window;
    private final GameBoard gameBoard;
    private final GameUI gameUI;

    public ControlLayer() {              // TODO: Think of a way to bypass game loading moves while player still pressing buttons.
        // Check the interface changes in the UI package.
        window = createWindow();
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            System.out.println(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println("Error setting native LAF: " + e);
//        }
        gameBoard = new GameBoard();
        gameUI = new GameUI();
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameUI.openWelcomeScreen(welcomeScreenControls(), window);
    }

    public JFrame createWindow(){
        window = new JFrame();
        con = new Container();
        window.setSize(1200, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        JPanel titleNamePanel = new JPanel();
        titleNamePanel.setBounds(290, 130, 600, 70);

        JLabel titleNameLabel = new JLabel("DUNGEONS & DRAGONS");
        titleNameLabel.setBounds(290, 130, 600, 70);
        titleNamePanel.setBackground(Color.pink); // we cant see the label during the game because of the background color, to see it we need to change the background color to something else.
        titleNameLabel.setForeground(Color.green);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);
        con.add(titleNamePanel);
        con.add(titleNameLabel);
        titleNamePanel.setVisible(true);
        titleNameLabel.setVisible(true);
        con = window.getContentPane();
        return window;
    }

    public JPanel welcomeScreenControls(){
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(500, 570, 200, 100);
        startButtonPanel.setBackground(Color.black);
        JButton startButton = new JButton("START");
        startButton.setFont(normalFont);
        startButton.setBackground(Color.black);
        startButton.addActionListener(e -> {
            gameUI.characterCreationScreen(characterChoice());
        });
        JButton debugAccessGameButton = new JButton("DEBUG");
        debugAccessGameButton.setFont(normalFont);
        debugAccessGameButton.setBackground(Color.black);
        debugAccessGameButton.addActionListener(e -> {
            gameBoard.setPlayerChoice(1);
            gameBoard.loadCurrentLevelBoard(gameBoard.getCurrentLevelCounter());
            gameBoard.incrementCurrentLevelCounter(); // increment for next level
            gameUI.createGameScreen(gameBoard.getBoardString(), playerControls());
        });
        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(normalFont);
        quitButton.setBackground(Color.white);
        quitButton.addActionListener(e -> {
            window.dispose();
            System.exit(0);
        });
        quitButton.addKeyListener(this);
        startButtonPanel.add(startButton);
        startButtonPanel.add(quitButton);
        con.add(startButtonPanel);
        window.setContentPane(con);
        window.setVisible(true);
        return startButtonPanel;
    }
    public JPanel characterChoice(){
        String playerName = JOptionPane.showInputDialog("Choose your character: ");
        gameBoard.setPlayerName(playerName);
        JPanel characterSelectOptions = new JPanel();
        characterSelectOptions.setBounds(300, 380, 600, 250);
        characterSelectOptions.setBackground(Color.white);
        characterSelectOptions.setLayout(new GridLayout(4, 1));

//        Mage Melisandre = new Mage("Melisandre", 100, 5, 5, 1, 75, 300, 15);
        JButton choice1 = new JButton("Jon Snow");
        choice1.setSize(50, 50);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        characterSelectOptions.add(choice1);
        choice1.addActionListener(e -> {
            gameBoard.setPlayerChoice(1);
            gameBoard.loadCurrentLevelBoard(gameBoard.getCurrentLevelCounter());
            gameBoard.incrementCurrentLevelCounter(); // increment for next level
            gameUI.createGameScreen(gameBoard.getBoardString(), playerControls());
        });

        JButton choice2 = new JButton("The Hound");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        characterSelectOptions.add(choice2);
        choice2.addActionListener(e -> {
//            handleButtonSelection(2);
        });
        JButton choice3 = new JButton("Melisandre");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        characterSelectOptions.add(choice3);
        choice3.addActionListener(e -> {
//            createGameScreen(new Melisandre());
        });
        JButton choice4 = new JButton("Thoros of Myr");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        characterSelectOptions.add(choice4);
        choice4.addActionListener(e -> {
//            createGameScreen(new ThorosofMyr());
        });
        JButton choice5 = new JButton("Arya Stark");
        choice5.setBackground(Color.black);
        choice5.setForeground(Color.white);
        choice5.setFont(normalFont);
        characterSelectOptions.add(choice5);
        choice5.addActionListener(e -> {
//            createGameScreen(new AryaStark());
        });
        JButton choice6 = new JButton("Bronn");
        choice6.setBackground(Color.black);
        choice6.setForeground(Color.white);
        choice6.setFont(normalFont);
        characterSelectOptions.add(choice6);
        choice6.addActionListener(e -> {
//            createGameScreen(new Warrior("Bronn", 250, 35, 3, 0));
        });
        JButton choice7 = new JButton("Ygritte");
        choice7.setBackground(Color.black);
        choice7.setForeground(Color.white);
        choice7.setFont(normalFont);
        characterSelectOptions.add(choice7);
        choice7.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Bruh! finish the assignment first", "Bonus Class", JOptionPane.ERROR_MESSAGE);
//            createGameScreen(new Ygritte());
        });
        JButton choice8 = new JButton(playerName + " (Custom)");
        choice8.setBackground(Color.black);
        choice8.setForeground(Color.white);
        choice8.setFont(normalFont);
        characterSelectOptions.add(choice8);
        choice8.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "I'm crazy... but not that crazy!", "Custom character", JOptionPane.ERROR_MESSAGE);
        });
        characterSelectOptions.addKeyListener(this);
        characterSelectOptions.addMouseListener(this);
        return characterSelectOptions;
    }
    public JPanel playerControls() {
        // create player panel
        JPanel playerChoicesPanel = new JPanel();
        playerChoicesPanel.setLayout(new GridLayout(2, 3));
        playerChoicesPanel.setBounds(750, 400, 300, 150);
        playerChoicesPanel.setBackground(Color.black);
        playerChoicesPanel.setForeground(Color.white);
        playerChoicesPanel.addKeyListener(this);

        // create player control buttons
        JButton qButton = new JButton("Q");
        qButton.setFont(normalFont);
        qButton.setBackground(Color.black);
        qButton.setForeground(Color.white);
        qButton.addActionListener(this);
        qButton.addKeyListener(this);
        JButton wButton = new JButton("W");
        wButton.setFont(normalFont);
        wButton.setBackground(Color.black);
        wButton.setForeground(Color.white);
        wButton.addActionListener(this);
        JButton eButton = new JButton("E");
        eButton.setFont(normalFont);
        eButton.setBackground(Color.black);
        eButton.setForeground(Color.white);
        eButton.addActionListener(this);
        JButton aButton = new JButton("A");
        aButton.setFont(normalFont);
        aButton.setBackground(Color.black);
        aButton.setForeground(Color.white);
        aButton.addActionListener(this);
        JButton sButton = new JButton("S");
        sButton.setFont(normalFont);
        sButton.setBackground(Color.black);
        sButton.setForeground(Color.white);
        sButton.addActionListener(this);
        JButton dButton = new JButton("D");
        dButton.setFont(normalFont);
        dButton.setBackground(Color.black);
        dButton.setForeground(Color.white);
        dButton.addActionListener(this);

        // add them to the panel
        playerChoicesPanel.add(qButton);
        playerChoicesPanel.add(wButton);
        playerChoicesPanel.add(eButton);
        playerChoicesPanel.add(aButton);
        playerChoicesPanel.add(sButton);
        playerChoicesPanel.add(dButton);
        playerChoicesPanel.setVisible(true);
        con.add(playerChoicesPanel);
        playerChoicesPanel.addKeyListener(this);
        playerChoicesPanel.addMouseListener(this);
        playerChoicesPanel.setFocusable(true);
        playerChoicesPanel.requestFocusInWindow();
        return playerChoicesPanel;
    }

    public JPanel playerInfo() { // TODO: Check moving this to UI layer
        // create player info panel
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(2, 1));
        playerInfoPanel.setBounds(750, 100, 300, 300);
        playerInfoPanel.setBackground(Color.black);
        playerInfoPanel.setForeground(Color.white);

        // create player info labels
        JLabel playerInfoLabel = new JLabel("Player Info");
        playerInfoLabel.setFont(titleFont);
        playerInfoLabel.setForeground(Color.white);
        JLabel playerHealthLabel = new JLabel("Health: "/* + player.getHealth()*/);
        playerHealthLabel.setFont(normalFont);
        playerHealthLabel.setForeground(Color.white);
        JLabel playerAttackLabel = new JLabel("Attack: "/* + player.getAttack()*/);
        playerAttackLabel.setFont(normalFont);
        playerAttackLabel.setForeground(Color.white);
        JLabel playerDefenseLabel = new JLabel("Defense: "/* + player.getDefense()*/);
        playerDefenseLabel.setFont(normalFont);
        playerDefenseLabel.setForeground(Color.white);
        JLabel playerSpecialAbilityLabel = new JLabel("Special Ability: "/* + player.getSpecialAbility()*/);
        playerSpecialAbilityLabel.setFont(normalFont);
        playerSpecialAbilityLabel.setForeground(Color.white);

        // add them to the panel
        playerInfoPanel.add(playerInfoLabel);
        playerInfoPanel.add(playerHealthLabel);
        playerInfoPanel.add(playerAttackLabel);
        playerInfoPanel.add(playerDefenseLabel);
        playerInfoPanel.add(playerSpecialAbilityLabel);
        playerInfoPanel.setVisible(true);
        con.add(playerInfoPanel);
        return playerInfoPanel;
    }

    public void handleButtonSelection(char Char){
        if (Char == 'Q') {
            System.out.println("Q");
        } else if (Char == 'W') {
            System.out.println("W");
        } else if (Char == 'E') {
            System.out.println("E");
        } else if (Char == 'A') {
            System.out.println("A");
        } else if (Char == 'S') {
            System.out.println("S");
        } else if (Char == 'D') {
            System.out.println("D");
        }
    }

//    public void handleCharacterSelectionScreenOptions(char Char){
//        switch (char){
//            if (Char)
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
//        char keyPressed = e.getKeyChar();
//        System.out.println("Key released: " + keyPressed);
//        window.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // This method is invoked when a key is released
        char keyPressed = Character.toUpperCase(e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_W && !keys[0]) {
            System.out.println("W Pressed");
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && !keys[1]) {
            System.out.println("S Pressed");
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A && !keys[2]) {
            System.out.println("A Pressed");
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D && !keys[3]) {
            System.out.println("D Pressed");
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_E && !keys[4]) {
            System.out.println("E Pressed");
            keys[4] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q && !keys[5]) {
            System.out.println("Q Pressed");
            keys[5] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_1 && !keys[6]) {
            System.out.println("1 Pressed");
            keys[6] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_2 && !keys[7]) {
            System.out.println("2 Pressed");
            keys[7] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_3 && !keys[8]) {
            System.out.println("3 Pressed");
            keys[8] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_4 && !keys[9]) {
            System.out.println("4 Pressed");
            keys[9] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_5 && !keys[10]) {
            System.out.println("5 Pressed");
            keys[10] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_6 && !keys[11]) {
            System.out.println("6 Pressed");
            keys[11] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_7 && !keys[12]) {
            System.out.println("7 Pressed");
            keys[12] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_8 && !keys[13]) {
            System.out.println("8 Pressed");
            keys[13] = true;
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


        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("W released");
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("S released");
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A released");
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("D released");
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            System.out.println("E released");
            keys[4] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.out.println("Q released");
            keys[5] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            System.out.println("1 released");
            keys[6] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            System.out.println("2 released");
            keys[7] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            System.out.println("3 released");
            keys[8] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            System.out.println("4 released");
            keys[9] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            System.out.println("5 released");
            keys[10] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_6) {
            System.out.println("6 released");
            keys[11] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_7) {
            System.out.println("7 released");
            keys[12] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_8) {
            System.out.println("8 released");
            keys[13] = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { // checks if mouse is clicked - after releasing press
        System.out.println("Mouse clicked" + counter);
        counter++;
        window.requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) { // checks if mouse is pressed - initial press
        System.out.println("Mouse pressed" + counter);
        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) { // checks if mouse is released - after pressing is released
        System.out.println("Mouse released" + counter);
        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseEntered(MouseEvent e) { // checks if mouse is entered - when mouse enters the window - not labels or panels
//        System.out.println("Mouse entered" + counter);
//        counter++;
//        window.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) { // checks if mouse is exited - when mouse exits the window - not labels or panels
//        System.out.println("Mouse exited" + counter);
//        counter++;
//        window.requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e) { // not sure what this does
        System.out.println("Mouse dragged" + counter);
        counter++;
        window.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {  // not sure what this does
        if (counter % 100 == 0)
            System.out.println("Mouse moved" + counter + " (x: " + e.getX() + ", y: " + e.getY() + ")");
        counter++;
        window.requestFocus();
    }
}
