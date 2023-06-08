
package UI;

import Units.Abstracts.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameUI {

    private JFrame window;
    private Container con;
    private JPanel startButtonPanel, mainTextPanel, characterSelectOptions, playerPanel, boardPanel, playerChoicesPanel, WSImagesPanel;
    private JLabel backgroundLabel, titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, weaponLabelNumber, armorLabel, armorLabelName, armorLabelNumber, playerLabel, playerLabelName, playerLabelNumber;
    private JTextArea boardTextArea;
    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private final Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    private final String currentDir = System.getProperty("user.dir");

    public GameUI() {}

    public void openWelcomeScreen(JPanel startButtonsPanel, JFrame currWindow) {
        window = currWindow;
        con = window.getContentPane();

        //TODO: check resizing of the window and how to fix the images being not in the right place.
        // Create WSImagesPanel
        WSImagesPanel = new JPanel();
        WSImagesPanel.setBounds(0, 0, window.getWidth(), window.getHeight());


        ImageIcon titleImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/titleName.jpg");
        JLabel titleImageLabel = new JLabel(titleImage);
        titleImageLabel.setBounds(400, 50, 400, 300);

        ImageIcon backgroundImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/WelcomeScreenImage.jpg");
        Image bgImage = backgroundImage.getImage();
        bgImage = bgImage.getScaledInstance(window.getWidth(), window.getHeight(), Image.SCALE_SMOOTH);

        backgroundLabel = new JLabel(new ImageIcon(bgImage));
        backgroundLabel.setBounds(0, 0, window.getWidth(), window.getHeight());

        WSImagesPanel.add(backgroundLabel);
        WSImagesPanel.add(titleImageLabel);

        startButtonPanel = startButtonsPanel;
        startButtonPanel.setBackground(new Color(0, 0, 0, 0));
        startButtonPanel.setOpaque(false);
        con.add(WSImagesPanel);
        startButtonPanel.setVisible(true);
        con.add(startButtonPanel);
        window.setVisible(true);
    }

    public void characterCreationScreen(JPanel characterSelectPanel){
        startButtonPanel.setVisible(false);
        WSImagesPanel.setVisible(false);
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 75, 1100, 300);
        mainTextPanel.setForeground(Color.darkGray);
        con.add(mainTextPanel);
        JTextArea mainTextArea = new JTextArea("   name\t\t          HP\t\t     ATT\t     DEF\tCAST SOURCE\t ADD STATS\n" +
                "1. Jon Snow\t\tHealth: 300/300\tAttack: 30\tDefense: 4\tCooldown: 0/3\n" +
                "2. The Hound\t\tHealth: 400/400\tAttack: 20\tDefense: 6\tCooldown: 0/5\n" +
                "3. Melisandre\t\tHealth: 100/100\tAttack: 5\tDefense: 1\tMana: 75/300\t\tSpell Power: 15\n" +
                "4. Thoros of Myr\tHealth: 250/250\tAttack: 25\tDefense: 4\tMana: 37/150\t\tSpell Power: 20\n" +
                "5. Arya Stark\t\tHealth: 150/150\tAttack: 40\tDefense: 2\tEnergy: 100/100\n" +
                "6. Bronn\t\tHealth: 250/250\tAttack: 35\tDefense: 3\tEnergy: 100/100\n" +
                "7. Ygritte\t\tHealth: 220/220\tAttack: 30\tDefense: 2\tArrows: 10\t\tRange: 6" +
                "\n8. Custom character\t\t\tplayer creation screen TBI");
        mainTextArea.setBounds(100, 100, 1100, 300);
        mainTextArea.setBackground(Color.darkGray);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(smallFont);
        mainTextArea.setLineWrap(true);
//        JLabel mainTextLabel = new JLabel();            // the text area is better than the label because
//        mainTextLabel.setBounds(100, 100, 1100, 300);   // it allows wrapping and by that we can see the whole text
//        mainTextLabel.setBackground(Color.black);
//        mainTextLabel.setForeground(Color.white);
//        mainTextLabel.setFont(smallFont);
        mainTextPanel.add(mainTextArea);
        characterSelectOptions = characterSelectPanel;
//        characterSelectOptions.addKeyListener(controlLayer.keyboardButtonListener);
        con.add(characterSelectOptions);
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

    public void createGameScreen(String board, JPanel playerControlsPanel) {
        mainTextPanel.setVisible(false);
        characterSelectOptions.setVisible(false);

        // create player panel
        playerPanel = playerControlsPanel;

        // create board panel and text area - currently set to testing
//        boardPanel = new JPanel(); // defaulted to a white square and unnecessary
        boardTextArea = new JTextArea();
        boardTextArea = new JTextArea(board/*"\t\t\t\t\t###\n\n\n\n\n\n\n\n\n#\t\t\t\t\tthis is a test\n\n\n\n\n\n\n\n\n\t\t\t\t\t###"*/);
        boardTextArea.setBounds(50, 100, 600, 600);
        boardTextArea.setFont(boardFont);
        boardTextArea.setForeground(Color.white);
        boardTextArea.setBackground(Color.black);
        boardTextArea.setVisible(true);
        boardTextArea.setLineWrap(true);
    }
}
