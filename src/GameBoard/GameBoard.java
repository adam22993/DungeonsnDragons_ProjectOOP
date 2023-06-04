package GameBoard;
import Patterns.Visitor.UnitVisited;
import Patterns.Visitor.UnitVisitor;
import Position.Position;
import UI.GameUI;
import Units.Player;
import Units.PlayerClasses.*;
import Units.Tile;
import Units.Unit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class GameBoard {
    JFrame window;
    Container con;
    KeyListener keyboardListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            // This method is invoked when a key is typed (pressed and released)
            char keyPressed = e.getKeyChar();
            JLabel label = new JLabel("Press a key...");
            label.setText("Key typed: " + keyPressed);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.out.println("Escape pressed");
                System.exit(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                System.out.println("W pressed");
//                gameUI.moveUp();
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                System.out.println("S pressed");
//                gameUI.moveDown();
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                System.out.println("A pressed");
//                gameUI.moveLeft();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                System.out.println("D pressed");
//                gameUI.moveRight();
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                System.out.println("E pressed");
//                gameUI.useSpecialAbility();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // This method is invoked when a key is released
            char keyReleased = e.getKeyChar();
            JLabel label = new JLabel("Press a key...");
            label.setText("Key released: " + keyReleased);
            if (e.getKeyCode() == KeyEvent.VK_W) {
                System.out.println("W released");
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                System.out.println("S released");
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                System.out.println("A released");
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                System.out.println("D released");
            }
        }
    };
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, boardPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, weaponLabelNumber, armorLabel, armorLabelName, armorLabelNumber, playerLabel, playerLabelName, playerLabelNumber;
    JTextArea boardTextArea;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    GameUI gameUI;
    int current_level = 0;

    char[][] board;

    public GameBoard() {
        window = new JFrame();
        window.setSize(1200, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(290, 130, 600, 70);

        titleNameLabel = new JLabel("DUNGEONS & DRAGONS");
        titleNamePanel.setBackground(Color.BLACK);
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(500, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        JButton startButton = new JButton("START");
        startButton.setFont(normalFont);
        startButton.setBackground(Color.white);
        startButton.addActionListener(e -> {
            characterCreationScreen();
        });
        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(normalFont);
        quitButton.setBackground(Color.white);
        quitButton.addActionListener(e -> {
            window.dispose();
            System.exit(0);
        });
        startButtonPanel.add(startButton);
        startButtonPanel.add(quitButton);
        con.add(titleNamePanel);
        con.add(startButtonPanel);
        window.setContentPane(con);
        window.setVisible(true);
    }

    public void characterCreationScreen() {// to take input from user using jframe and jpanel, use the method JOptionPane.showInputDialog
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        //JOptionPane.showInputDialog("Enter your name: ");
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 75, 1100, 300);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        JTextArea mainTextArea = new JTextArea("   name\t\t          HP\t\t     ATT\t     DEF\tCAST SOURCE\t ADD STATS\n" +
                "1. Jon Snow\t\tHealth: 300/300\tAttack: 30\tDefense: 4\tCooldown: 0/3\n" +
                "2. The Hound\t\tHealth: 400/400\tAttack: 20\tDefense: 6\tCooldown: 0/5\n" +
                "3. Melisandre\t\tHealth: 100/100\tAttack: 5\tDefense: 1\tMana: 75/300\t\tSpell Power: 15\n" +
                "4. Thoros of Myr\tHealth: 250/250\tAttack: 25\tDefense: 4\tMana: 37/150\t\tSpell Power: 20\n" +
                "5. Arya Stark\t\tHealth: 150/150\tAttack: 40\tDefense: 2\tEnergy: 100/100\n" +
                "6. Bronn\t\tHealth: 250/250\tAttack: 35\tDefense: 3\tEnergy: 100/100\n" +
                "7. Ygritte\t\tHealth: 220/220\tAttack: 30\tDefense: 2\tArrows: 10\t\tRange: 6" +
                "\n8. Custom character");
        mainTextArea.setBounds(100, 100, 1100, 300);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(smallFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(400, 380, 450, 250);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);

//        Mage Melisandre = new Mage("Melisandre", 100, 5, 5, 1, 75, 300, 15);
        JButton choice1 = new JButton("Jon Snow");
        choice1.setSize(50, 50);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choiceButtonPanel.add(choice1);
        choice1.addActionListener(e -> {
            playerLabel.setText("Jon Snow");
            createGameScreen(new JohnSnow());
        });
        JButton choice2 = new JButton("The Hound");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choiceButtonPanel.add(choice2);
        choice2.addActionListener(e -> {
            createGameScreen(new TheHound());
        });
        JButton choice3 = new JButton("Melisandre");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choiceButtonPanel.add(choice3);
        choice3.addActionListener(e -> {
//            createGameScreen(new Melisandre());
        });
        JButton choice4 = new JButton("Thoros of Myr");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choiceButtonPanel.add(choice4);
        choice4.addActionListener(e -> {
//            createGameScreen(new ThorosofMyr());
        });
        JButton choice5 = new JButton("Arya Stark");
        choice5.setBackground(Color.black);
        choice5.setForeground(Color.white);
        choice5.setFont(normalFont);
        choiceButtonPanel.add(choice5);
        choice5.addActionListener(e -> {
//            createGameScreen(new AryaStark());
        });
        JButton choice6 = new JButton("Bronn");
        choice6.setBackground(Color.black);
        choice6.setForeground(Color.white);
        choice6.setFont(normalFont);
        choiceButtonPanel.add(choice6);
        choice6.addActionListener(e -> {
            createGameScreen(new Bronn());
        });
        JButton choice7 = new JButton("Ygritte");
        choice7.setBackground(Color.black);
        choice7.setForeground(Color.white);
        choice7.setFont(normalFont);
        choiceButtonPanel.add(choice7);
        choice7.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Bruh! finish the assignment first", "Bonus Class", JOptionPane.ERROR_MESSAGE);
//            createGameScreen(new Ygritte());
        });
        JButton choice8 = new JButton("Custom character");
        choice8.setBackground(Color.black);
        choice8.setForeground(Color.white);
        choice8.setFont(normalFont);
        choiceButtonPanel.add(choice8);
        choice8.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "I'm crazy... but not that crazy!", "Custom character", JOptionPane.ERROR_MESSAGE);
        });
        playerPanel = new JPanel();
        playerPanel.setBounds(50, 15, 600, 50);
        playerLabel = new JLabel("Choose your character:");
        playerLabel.setForeground(Color.white);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        playerLabel.setFont(normalFont);
        playerPanel.add(playerLabel);
        con.add(playerPanel);

    }

    public void createGameUI() {
        boardPanel = new JPanel();
        boardPanel.setBounds(100, 100, 600, 250);
        boardPanel.setBackground(Color.black);
        con.add(boardPanel);
    }

    public void createGameScreen(Player playerUnit) {
        mainTextPanel.setVisible(false);
        choiceButtonPanel.setVisible(false);
        boardPanel = new JPanel();
        boardTextArea = new JTextArea();
        boardPanel.setSize(300, 300);
        boardPanel.setVisible(true);
        boardPanel.setLayout(null);
        boardPanel.setBackground(Color.black);
        boardPanel.setForeground(Color.white);
        this.board = loadBoard();
        boardTextArea = new JTextArea(returnBoard());
        boardTextArea.setBounds(50, 100, 600, 600);
        boardTextArea.setFont(boardFont);
        boardTextArea.setForeground(Color.white);
        boardTextArea.setBackground(Color.black);
        boardTextArea.setVisible(true);
        printBoard();
        boardPanel.setBounds(0, 0, 600, 600);
        boardTextArea.setLineWrap(true);
        boardPanel.add(boardTextArea);
        con.add(boardPanel);

    }

    private char[][] loadBoard() {
        /*re
         * This function loads the board from the file. Using the current_level variable, it loads the file of
         * the current level. It then counts the number of lines and columns in the file, and creates a 2D array
         * of chars with the size of the board. It then loads the board from the file into the 2D array.
         * @return 2D array of chars representing the board.
         */

        //TODO: change to fit tiles and not print the board

        String levelFile = "/src/GameBoard/Levels/level" + (current_level + 1) + ".txt";
        //String levelFile = "\\src\\GameBoard\\Levels\\level" + (current_level + 1) + ".txt";
        int countLines = 0;
        int countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir + levelFile);
        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            while (br.readLine() != null) {
                countLines++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            String line;
            if ((line = br.readLine()) != null) {
                countColumns = line.length();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        char[][] board = new char[countLines][countColumns];

        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < countColumns; j++) {
                    board[i][j] = line.charAt(j);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Unit[][] units = new Unit[countLines][countColumns];
        for (char[] row: board){
            for (char c: row){

            }
        }
        current_level++;
        return board;
    }

    public void printBoard() {
        /**
         * This function prints the board to the console. It iterates through the 2D array of chars representing
         * the board, and prints each char to the console.
         */
        //TODO: change to fit tiles and not print the board
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public String returnBoard() {
        /**
         * This function prints the board to the console. It iterates through the 2D array of chars representing
         * the board, and prints each char to the console.
         */
        StringBuilder boardString = new StringBuilder();
        for (char[] row : board) {
            for (char c : row) {
                boardString.append(c);
            }
            boardString.append("\n");
        }
        return boardString.toString();


//        private Vector<Unit> scanForUnitsBoard() {
//            /**DungeonsnDragons_ProjectOOP\src\GameBoard\GameBoard.java
//             * This function scans the board for units. It creates a 2D array of Units with the same size as the board.
//             * It then scans the board for units, and creates a new unit for each unit found. It then returns the 2D array
//             * of units.
//             * @return 2D array of Units representing the units on the board.
//             */
//            //Vector so we can know which enemies are close to the player and are accessible for attack using special ability
//            ArrayList<Unit> units = new ArrayList<Unit>();
//            for (int i = 0; i < board.length; i++){
//                for (int j = 0; j < board.length; j++) {
//                    Tile current = new Tile(board[i][j], new Position(i, j));
//                    if (current.getChar() != '.' || current.getChar() != '#') {
////                        Unit unit = UnitVisitor.visit(current);
////                        units.add(unit);
//                    }
//                }
//            }
////            return units;
//        }


//        private void playerTurn () {
//            //TODO: implement
//        }

    }
}