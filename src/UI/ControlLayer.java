package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlLayer {
    JFrame window = new JFrame();
    ActionListener mouseButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            System.out.println(actionCommand + " button activated");
            window.requestFocus();
        }
    };
    KeyListener keyboardButtonListener = new KeyListener() {
        final boolean[] keys = new boolean[14]; // used to check if a key is pressed

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
            if (e.getKeyCode() == KeyEvent.VK_1 && !keys[6]) {
                System.out.println("1 pressed");
                keys[6] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_2 && !keys[7]) {
                System.out.println("2 pressed");
                keys[7] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_3 && !keys[8]) {
                System.out.println("3 pressed");
                keys[8] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_4 && !keys[9]) {
                System.out.println("4 pressed");
                keys[9] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_5 && !keys[10]) {
                System.out.println("5 pressed");
                keys[10] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_6 && !keys[11]) {
                System.out.println("6 pressed");
                keys[11] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_7 && !keys[12]) {
                System.out.println("7 pressed");
                keys[12] = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_8 && !keys[13]) {
                System.out.println("8 pressed");
                keys[13] = true;
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
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            switch (action){
                case "Q":
                    System.out.println("Q pressed");
                    break;
                case "1":
                    System.out.println("1 pressed");
                    break;
                case "2":
                    System.out.println("2 pressed");
                    break;
                case "3":
                    System.out.println("3 pressed");
                    break;
                case "4":
                    System.out.println("4 pressed");
                    break;
                case "5":
                    System.out.println("5 pressed");
                    break;
                case "6":
                    System.out.println("6 pressed");
                    break;
                case "7":
                    System.out.println("7 pressed");
                    break;
                case "8":
                    System.out.println("8 pressed");
                    break;
            }
        }

    };

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

    Container con;

    public ControlLayer() {
    }

    public JPanel playerControls() {
        // create player panel
        JPanel playerChoicesPanel = new JPanel();
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
        playerChoicesPanel.addKeyListener(keyboardButtonListener);
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

        // add them to panel
        playerInfoPanel.add(playerInfoLabel);
        playerInfoPanel.add(playerHealthLabel);
        playerInfoPanel.add(playerAttackLabel);
        playerInfoPanel.add(playerDefenseLabel);
        playerInfoPanel.add(playerSpecialAbilityLabel);
        playerInfoPanel.setVisible(true);
        con.add(playerInfoPanel);
        return playerInfoPanel;
    }

    public JPanel characterChoice(){
        JPanel characterSelectOptions = new JPanel();
        characterSelectOptions.setBounds(400, 380, 450, 250);
        characterSelectOptions.setBackground(Color.black);
        characterSelectOptions.setLayout(new GridLayout(4, 1));

//        Mage Melisandre = new Mage("Melisandre", 100, 5, 5, 1, 75, 300, 15);
        JButton choice1 = new JButton("Jon Snow");
        choice1.setSize(50, 50);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        characterSelectOptions.add(choice1);
        choice1.addActionListener(e -> {
//            handleButtonSelection(1);
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
        JButton choice8 = new JButton("Custom character");
        choice8.setBackground(Color.black);
        choice8.setForeground(Color.white);
        choice8.setFont(normalFont);
        characterSelectOptions.add(choice8);
        choice8.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "I'm crazy... but not that crazy!", "Custom character", JOptionPane.ERROR_MESSAGE);
        });
        return characterSelectOptions;
    }
}
