
package UI;

import Units.Abstracts.Player;
import Units.PlayerClasses.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameUI {
    ActionListener mouseButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            System.out.println(actionCommand + " button activated");
            window.requestFocus();
        }
    };
    KeyListener keyboardButtonListener = new KeyListener() {
        boolean[] keys = new boolean[6]; // used to check if a key is pressed

        @Override
        public void keyTyped(KeyEvent e) {
            // This method is invoked when a key is typed (pressed and released)
            char keyPressed = e.getKeyChar();
            JLabel label = new JLabel("Press a key...");
            label.setText("Key typed: " + keyPressed);
            window.requestFocus();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.out.println("Escape pressed");
                System.exit(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_W && !keys[0]) {
                System.out.println("W pressed");
                keys[0] = true;
//                player.moveUp();
            }
            if (e.getKeyCode() == KeyEvent.VK_S && !keys[1]) {
                System.out.println("S pressed");
                keys[1] = true;
//                player.moveDown();
            }
            if (e.getKeyCode() == KeyEvent.VK_A && !keys[2]) {
                System.out.println("A pressed");
                keys[2] = true;
//                player.moveLeft();
            }
            if (e.getKeyCode() == KeyEvent.VK_D && !keys[3]) {
                System.out.println("D pressed");
                keys[3] = true;
//                player.moveRight();
            }
            if (e.getKeyCode() == KeyEvent.VK_E && !keys[4]) {
                System.out.println("E pressed");
                keys[4] = true;
//                player.useSpecialAbility();
            }
            if (e.getKeyCode() == KeyEvent.VK_Q && !keys[5]) {
                System.out.println("Q pressed");
                keys[5] = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // This method is invoked when a key is released
            char keyReleased = e.getKeyChar();
            JLabel label = new JLabel("Press a key..."); // what to do if i want to avoid pressed keys continue to be printed? (i.e. if i press 'a' and hold it, it will print 'a' many times) - maybe use a boolean array to check if a key is pressed? this can be done in the keyPressed method
            label.setText("Key released: " + keyReleased);
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
        }

    };
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, characterSelectOptions, playerPanel, boardPanel, playerChoicesPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, weaponLabelNumber, armorLabel, armorLabelName, armorLabelNumber, playerLabel, playerLabelName, playerLabelNumber;
    JTextArea boardTextArea;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

    public GameUI() {}

    public void openWelcomeScreen() {
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

    public void characterCreationScreen(){
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
        characterSelectOptions = new JPanel();
        characterSelectOptions.setBounds(400, 380, 450, 250);
        characterSelectOptions.setBackground(Color.black);
        characterSelectOptions.setLayout(new GridLayout(4, 1));
        con.add(characterSelectOptions);

//        Mage Melisandre = new Mage("Melisandre", 100, 5, 5, 1, 75, 300, 15);
        JButton choice1 = new JButton("Jon Snow");
        choice1.setSize(50, 50);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        characterSelectOptions.add(choice1);
        choice1.addActionListener(e -> {
            playerLabel.setText("Jon Snow");
            createGameScreen(new Warrior("Jon Snow", 300, 30, 4, 0, 3));
        });
        JButton choice2 = new JButton("The Hound");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        characterSelectOptions.add(choice2);
        choice2.addActionListener(e -> {
            createGameScreen(new Warrior("The Hound", 400, 20, 6, 0, 5));
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
            createGameScreen(new Warrior("Bronn", 250, 35, 3, 0, 5));
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
        JButton choice8 = new JButton("Custom character");
        choice8.setBackground(Color.black);
        choice8.setForeground(Color.white);
        choice8.setFont(normalFont);
        characterSelectOptions.add(choice8);
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

    public void createGameScreen(Player playerUnit) {
        mainTextPanel.setVisible(false);
        characterSelectOptions.setVisible(false);

        // create player panel
        playerChoicesPanel = new JPanel();
        playerChoicesPanel.setLayout(new GridLayout(2, 3));
        playerChoicesPanel.setBounds(750, 400, 300, 150);
        playerChoicesPanel.setBackground(Color.black);
        playerChoicesPanel.setForeground(Color.white);

        // create player control buttons
        JButton qButton = new JButton("Q");
        qButton.setFont(normalFont);
        qButton.setBackground(Color.black);
        qButton.setForeground(Color.white);
        qButton.addActionListener(mouseButtonListener);
        qButton.addKeyListener(keyboardButtonListener);
        JButton wButton = new JButton("W");
        wButton.setFont(normalFont);
        wButton.setBackground(Color.black);
        wButton.setForeground(Color.white);
        wButton.addActionListener(mouseButtonListener);
        JButton eButton = new JButton("E");
        eButton.setFont(normalFont);
        eButton.setBackground(Color.black);
        eButton.setForeground(Color.white);
        eButton.addActionListener(mouseButtonListener);
        JButton aButton = new JButton("A");
        aButton.setFont(normalFont);
        aButton.setBackground(Color.black);
        aButton.setForeground(Color.white);
        aButton.addActionListener(mouseButtonListener);
        JButton sButton = new JButton("S");
        sButton.setFont(normalFont);
        sButton.setBackground(Color.black);
        sButton.setForeground(Color.white);
        sButton.addActionListener(mouseButtonListener);
        JButton dButton = new JButton("D");
        dButton.setFont(normalFont);
        dButton.setBackground(Color.black);
        dButton.setForeground(Color.white);
        dButton.addActionListener(mouseButtonListener);

        // add them to panel
        playerChoicesPanel.add(qButton);
        playerChoicesPanel.add(wButton);
        playerChoicesPanel.add(eButton);
        playerChoicesPanel.add(aButton);
        playerChoicesPanel.add(sButton);
        playerChoicesPanel.add(dButton);
        playerChoicesPanel.setVisible(true);
        con.add(playerChoicesPanel);

        // create board panel and text area - currently set to testing
//        boardPanel = new JPanel(); // defaulted to a white square and unnecessary
        boardTextArea = new JTextArea();
        boardTextArea = new JTextArea("\t\t\t\t\t###\n\n\n\n\n\n\n\n\n#\t\t\t\t\tthis is a test\n\n\n\n\n\n\n\n\n\t\t\t\t\t###");
        boardTextArea.setBounds(50, 100, 600, 600);
        boardTextArea.setFont(boardFont);
        boardTextArea.setForeground(Color.white);
        boardTextArea.setBackground(Color.black);
        boardTextArea.setVisible(true);
        boardTextArea.setLineWrap(true);
        playerChoicesPanel.addKeyListener(keyboardButtonListener);
        playerChoicesPanel.setFocusable(true);
        playerChoicesPanel.requestFocusInWindow();
    }

}
