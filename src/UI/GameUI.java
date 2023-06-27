
package UI;

import Controller.Messages.MessageCallback;
import Units.AbstractsAndInterfaces.Player;
import Units.ADDITIONAL.ConsumablePoints.*;
import Units.PlayerClasses.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.logging.Logger;

public class GameUI {

    private JFrame window;
    private JPanel labelsPanel, startButtonPanel, mainTextPanel, characterSelectOptions, headerLabelCS, WSImagesPanel, gameTickPanel, numberOfMonstersLeftPanel;
    private JLabel numberOfMonstersLeft, backgroundLabel, chooseACharacter, playerAttackLabel, playerDefenseLabel, playerSpecialAbilityLabel, playerLevel, gameTickLabel;
    private JTextArea boardTextArea;
    private JProgressBar hpBar, expBar, resourceBar;
    private JLayeredPane gameScreenPanel, CCSBGPanel;
    private JScrollPane scrollPane;
    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private final Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    private final Font monospacedSmallFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
    private final String currentDir = System.getProperty("user.dir");
    private Clip clip;
    private final Vector<Clip> clips = new Vector<Clip>();
    private JPanel MusicPanel;
    private boolean musicPlaying = false;
    private Player player;
    private int currentSongIndex = 0;
    private int numberOfMonstersLeftInt = 0;
    private int messageCount = 0;

    public GameUI(JFrame controlLayerWindow) {
        loadMusicFolderToVector("\\resources\\Sound\\music");
        window = controlLayerWindow;
    }

    public MessageCallback getMessageCallback() {
        return this::displayMessage;
    }

    private void displayMessage(String m) {
//        JLabel message = new JLabel(++messageCount + ". " + m);
        JLabel message = new JLabel(String.format("<HTML><body style='width: %dpx'>%d.%s</body></HTML>", 300, ++messageCount, m));
        message.setFont(monospacedSmallFont);
        message.setBounds(0, 0, 100, 40);
        message.setForeground(Color.WHITE);
        message.setBackground(Color.BLACK);
        message.setOpaque(true);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);
        message.setVisible(true);
        JLabel seperator = new JLabel("------------------------------------------");
        seperator.setFont(monospacedSmallFont);
        seperator.setBounds(0, 0, 100, 40);
        seperator.setForeground(Color.WHITE);
        seperator.setBackground(Color.BLACK);
        seperator.setOpaque(true);
        seperator.setHorizontalAlignment(JLabel.CENTER);
        seperator.setVerticalAlignment(JLabel.CENTER);
        seperator.setVisible(true);
        labelsPanel.add(message, 0);
        labelsPanel.add(seperator, 0);
    }

    public void updateBoard(String board, Vector<Player> players) {
        System.out.println(board);
        boardTextArea.setText(board);
        accept(players.get(0));
    }

    public void updateGameTick(int gameTickCounter, int numberOfMonstersLeftInt) {
        gameTickLabel.setText("Game Ticks: " + gameTickCounter);
        numberOfMonstersLeft.setText("Monsters Left: " + numberOfMonstersLeftInt);
    }

    // ################# GameUI stuff from here on #################


    public void openWelcomeScreen(JPanel startButtonsPanel) {

//        clip = clips.get(0);  // mac for some reason loads differently than windows, sequence is different
//        if (clip != null) {
//            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            volumeControl.setValue(-30.0f);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        }
        playNextMusic();
        WSImagesPanel = new JPanel();
        WSImagesPanel.setLayout(null);
        WSImagesPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
        WSImagesPanel.add(startButtonsPanel);
        URL TNurl = getClass().getResource("/Images/titleName.png");
        ImageIcon titleImage = new ImageIcon(TNurl);
        JLabel titleImageLabel = new JLabel(titleImage);
        titleImageLabel.setBounds(340, 20, 500, 140);
        URL WSurl = getClass().getResource("/Images/WelcomeScreenImage.jpg");
        ImageIcon backgroundImage = new ImageIcon(WSurl);
        Image bgImage = backgroundImage.getImage();
        bgImage = bgImage.getScaledInstance(window.getWidth(), window.getHeight(), Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(bgImage);
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, window.getWidth(), window.getHeight());

        WSImagesPanel.add(titleImageLabel);
        WSImagesPanel.add(backgroundLabel);
        startButtonPanel = startButtonsPanel;
        startButtonPanel.setOpaque(false);
        startButtonPanel.setFocusable(false);
        window.add(startButtonPanel);
        window.add(WSImagesPanel);
        window.revalidate();
        window.repaint();

        //TODO: check resizing of the window and how to fix the images being not in the right place.
    }

    public void characterCreationScreen(JPanel characterSelectPanel) {

        startButtonPanel.setVisible(false);
        WSImagesPanel.setVisible(false);
        CCSBGPanel = new JLayeredPane();

        CCSBGPanel.setLayout(null);
        CCSBGPanel.setOpaque(false);
        CCSBGPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
        URL url = getClass().getResource("/Images/WelcomeScreenImage.jpg");
        ImageIcon backgroundImage = new ImageIcon(url);
        Image bgImage = backgroundImage.getImage();
        bgImage = bgImage.getScaledInstance(window.getWidth(), window.getHeight(), Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(bgImage);
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, window.getWidth(), window.getHeight());

        CCSBGPanel.add(backgroundLabel, 0);
        window.add(CCSBGPanel);
        characterSelectOptions = characterSelectPanel;
        characterSelectOptions.setOpaque(false);
        characterSelectOptions.setFocusable(false);
        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(new GridLayout(8, 1));
        mainTextPanel.setBounds(50, 75, 1100, 300);
        window.add(mainTextPanel);
        String infoText =

                " 1. Jon Snow      | Health: 300/300 | Attack: 30 | Defense: 4 | Level: 1 | Experience: 0/50 | Cooldown: 0/3    |\n" +
                        " 2. The Hound     | Health: 400/400 | Attack: 20 | Defense: 6 | Level: 1 | Experience: 0/50 | Cooldown: 0/5    |\n" +
                        " 3. Melisandre    | Health: 100/100 | Attack: 5  | Defense: 1 | Level: 1 | Experience: 0/50 | Mana: 75/300     | Spell Power: 15\n" +
                        " 4. Thoros of Myr | Health: 250/250 | Attack: 25 | Defense: 4 | Level: 1 | Experience: 0/50 | Mana: 37/150     | Spell Power: 20\n" +
                        " 5. Arya Stark    | Health: 150/150 | Attack: 40 | Defense: 2 | Level: 1 | Experience: 0/50 | Energy: 100/100  |\n" +
                        " 6. Bronn         | Health: 250/250 | Attack: 35 | Defense: 3 | Level: 1 | Experience: 0/50 | Energy: 100/100  |\n" +
                        " 7. Ygritte       | Health: 220/220 | Attack: 30 | Defense: 2 | Level: 1 | Experience: 0/50 | Arrows: 10       | Range: 6\n" +
                        " 8. Custom character                                 player creation screen TBI";

        String[] lines = infoText.split("\n");

        for (String line : lines) {
            JButton charButton = new JButton(line);
            charButton.setFont(monospacedSmallFont);
            charButton.setBounds(0, 20, 1100, 500);
            charButton.setForeground(Color.black);
            charButton.setBackground(Color.white);
            charButton.setHorizontalAlignment(JButton.LEFT);
            mainTextPanel.add(charButton);
        }

        characterSelectOptions = characterSelectPanel;
        window.add(characterSelectOptions);
        headerLabelCS = new JPanel();
        headerLabelCS.setBounds(450, 35, 300, 50);
        chooseACharacter = new JLabel("Choose your character:");
        chooseACharacter.setHorizontalAlignment(JLabel.CENTER);
        chooseACharacter.setForeground(Color.white);
        headerLabelCS.setBackground(Color.black);
        headerLabelCS.setLayout(new GridLayout(1, 4));
        chooseACharacter.setFont(boardFont);
        headerLabelCS.add(chooseACharacter);
        window.add(headerLabelCS);
        window.add(CCSBGPanel);
        window.revalidate();
        window.repaint();
    }

    public void createGameScreen(String board, Player player1, JPanel playerControlsPanel) {
        playNextMusic();
        CCSBGPanel.setVisible(false);

        characterSelectOptions.setVisible(false);
        headerLabelCS.setVisible(false);
        mainTextPanel.setVisible(false);

        gameScreenPanel = new JLayeredPane();
        gameScreenPanel.setLayout(new BoxLayout(gameScreenPanel, BoxLayout.Y_AXIS));
        gameScreenPanel.setBounds(0, 0, 750, window.getHeight());

        gameScreenPanel.setBackground(Color.lightGray);
        gameScreenPanel.setForeground(Color.lightGray);
        gameScreenPanel.setOpaque(false);
        gameScreenPanel.setVisible(true);

        boardTextArea = new JTextArea(board);
        boardTextArea.setBounds(0, 0, 750, 720);


        //doesnt actually work
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expand horizontally
        gbc.weighty = 1.0; // Expand vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically

        boardTextArea.setFont(boardFont);
        boardTextArea.setForeground(Color.white);
        boardTextArea.setBackground(Color.darkGray);
        boardTextArea.setVisible(true);
        boardTextArea.setLineWrap(true);
        boardTextArea.setWrapStyleWord(true);
        boardTextArea.setEditable(false);

        // forgive me senpai, for I have sinned - I have cast the player to a specific class (BAD PRACTICE)
        if (player1 instanceof Warrior) {
            JPanel playerInfoPanel = playerInfo((Warrior) player1);
            playerInfoPanel.setBackground(Color.black);
            playerInfoPanel.setForeground(Color.white);
            playerInfoPanel.setOpaque(true);
            window.add(playerInfoPanel);
        } else if (player1 instanceof Mage) {
            JPanel playerInfoPanel = playerInfo((Mage) player1);
            playerInfoPanel.setBackground(Color.black);
            playerInfoPanel.setForeground(Color.white);
            playerInfoPanel.setOpaque(true);
            window.add(playerInfoPanel);
        } else if (player1 instanceof Rogue) {
            JPanel playerInfoPanel = playerInfo((Rogue) player1);
            playerInfoPanel.setBackground(Color.black);
            playerInfoPanel.setForeground(Color.white);
            playerInfoPanel.setOpaque(true);
            window.add(playerInfoPanel);
        } else if (player1 instanceof Hunter) {
            JPanel playerInfoPanel = playerInfo((Hunter) player1);
            playerInfoPanel.setBackground(Color.black);
            playerInfoPanel.setForeground(Color.white);
            playerInfoPanel.setOpaque(true);
            window.add(playerInfoPanel);
        }

        JLabel controlLabelInfo = new JLabel("<HTML> Movement: W, A, S, D <br> Attack: Use movement to ram other units <br> Use Ability: E <br> Pass your turn: Q <br> Exit Game: ESC </HTML>");
        controlLabelInfo.setFont(smallFont);
        controlLabelInfo.setForeground(Color.white);
        controlLabelInfo.setBackground(Color.black);
        controlLabelInfo.setOpaque(false);
        controlLabelInfo.setVisible(true);
        controlLabelInfo.setBounds(980, 520, 200, 150);
        window.add(controlLabelInfo);


        // Create the scroll pane and add the labels panel to it
        labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBackground(Color.black);
        labelsPanel.setForeground(Color.black);
        labelsPanel.setOpaque(false);
        labelsPanel.setVisible(true);
        labelsPanel.setBounds(760, 250, 400, 250);

        scrollPane = new JScrollPane(labelsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(760, 250, 400, 250);
        scrollPane.setSize(400, 250);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setForeground(Color.black);
        scrollPane.setOpaque(false);
        scrollPane.setVisible(true);

        window.add(scrollPane, BorderLayout.CENTER);

        gameTickPanel = new JPanel();
        gameTickPanel.setLayout(new BoxLayout(gameTickPanel, BoxLayout.Y_AXIS));
        gameTickPanel.setBackground(Color.pink);
        gameTickPanel.setForeground(Color.pink);
        gameTickPanel.setOpaque(false);
        gameTickPanel.setVisible(true);
        gameTickPanel.setBounds(770, 10, 200, 50);
        gameTickLabel = new JLabel("Game Ticks: 0");
        gameTickLabel.setFont(smallFont);
        gameTickLabel.setForeground(Color.white);
        gameTickLabel.setBackground(Color.black);
        gameTickLabel.setOpaque(false);
        gameTickLabel.setVisible(true);
        gameTickPanel.add(gameTickLabel);
        window.add(gameTickPanel);

        numberOfMonstersLeftPanel = new JPanel();
        numberOfMonstersLeftPanel.setLayout(new BoxLayout(numberOfMonstersLeftPanel, BoxLayout.Y_AXIS));
        numberOfMonstersLeftPanel.setBackground(Color.black);
        numberOfMonstersLeftPanel.setForeground(Color.white);
        numberOfMonstersLeftPanel.setOpaque(false);
        numberOfMonstersLeftPanel.setVisible(true);
        numberOfMonstersLeftPanel.setBounds(850, 227, 400, 50);
        numberOfMonstersLeft = new JLabel("Monsters left in this round: " + numberOfMonstersLeftInt);
        numberOfMonstersLeft.setFont(smallFont);
        numberOfMonstersLeft.setForeground(Color.white);
        numberOfMonstersLeft.setBackground(Color.black);
        numberOfMonstersLeft.setOpaque(false);
        numberOfMonstersLeft.setVisible(true);
        numberOfMonstersLeftPanel.add(numberOfMonstersLeft);
        window.add(numberOfMonstersLeftPanel);

        gameScreenPanel.add(boardTextArea, gbc);
        window.add(gameScreenPanel, gbc);
        JPanel playerControls = playerControlsPanel;
        playerControls.setBounds(760, 520, 200, 150);
        playerControls.setBackground(Color.black);
        playerControls.setForeground(Color.white);
        playerControls.setOpaque(false);
        window.add(playerControls);

        window.revalidate();
        window.repaint();
    }

    // a temporary method to test the game screen and the loading of the board without changing the main method

    public void debugStart(String board, Player player1, JPanel playerControlsPanel) {

//        clip.stop();
//        clip = loadMusic(currentDir + "/src/UI/Assets/Audio/2xDeviruchi - And The Journey Begins (Loop).wav");
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//        volumeControl.setValue(-30.0f); // inplace change of volume - change is done in decibels
        playNextMusic();

        WSImagesPanel.setVisible(false);
        startButtonPanel.setVisible(false);
        gameScreenPanel = new JLayeredPane();
        gameScreenPanel.setLayout(new GridBagLayout());

        gameScreenPanel.setBackground(Color.black);
        gameScreenPanel.setForeground(Color.white);
        gameScreenPanel.setOpaque(false);
        gameScreenPanel.setVisible(true);

        boardTextArea = new JTextArea();
        boardTextArea = new JTextArea(board);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expand horizontally
        gbc.weighty = 1.0; // Expand vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically

        boardTextArea.setFont(boardFont);
        boardTextArea.setForeground(Color.white);
        boardTextArea.setBackground(Color.darkGray);
        boardTextArea.setVisible(true);
        boardTextArea.setLineWrap(true);
        boardTextArea.setWrapStyleWord(true);

        JPanel playerInfoPanel = playerInfo((Warrior) player1);
        playerInfoPanel.setBackground(Color.black);
        playerInfoPanel.setForeground(Color.white);
        playerInfoPanel.setOpaque(true);
        window.add(playerInfoPanel);

        gameScreenPanel.add(boardTextArea, gbc);
        window.add(gameScreenPanel);
        JPanel playerControls = playerControlsPanel;
        playerControls.setBounds(950, 500, 200, 150);
        playerControls.setBackground(Color.black);
        playerControls.setForeground(Color.white);
        playerControls.setOpaque(false);
        window.add(playerControls);
        window.revalidate();
        window.repaint();
    }



    // huge chunk of player updating info that is implemented poorly but with a lot of love QQ (,:

    public JPanel playerInfo(Player player) {
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(4, 2));
        playerInfoPanel.setBounds(770, 30, 400, 200);


        // create player info labels
        JLabel playerInfoLabel = new JLabel(player.getName() + " Info");
        playerInfoLabel.setFont(smallFont);
        playerInfoLabel.setForeground(Color.white);
        playerInfoPanel.add(playerInfoLabel);

        // hp progress bar
        hpBar = new JProgressBar(0, player.getHealthPool());
        hpBar.setValue(player.getHealthPool());
        hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
        hpBar.setStringPainted(true);
        hpBar.setFont(smallFont);
        hpBar.setBackground(Color.BLACK);
        hpBar.setForeground(new Color(200, 0, 0));
        hpBar.setBorderPainted(true);
        playerInfoPanel.add(hpBar);
        playerSpecialAbilityLabel = new JLabel();
        // player cd and ability label
        if (player instanceof Warrior) {
            Warrior warrior = (Warrior) player;
            playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Avenger's Shield <br>>dmg: %d heal: %d<HTML>", player.getHealthPool() / 10, player.getDefensePoints() * 10));
            playerSpecialAbilityLabel.setFont(smallFont);
            playerSpecialAbilityLabel.setForeground(Color.white);

            playerInfoPanel.add(playerSpecialAbilityLabel);
            resourceBar = new JProgressBar(0, warrior.getMaxCD());
            resourceBar.setStringPainted(true);
            resourceBar.setValue(warrior.getCurrCD());
            resourceBar.setString("CD: " + warrior.getCurrCD() + "/" + warrior.getMaxCD());
            resourceBar.setFont(smallFont);
            resourceBar.setBackground(Color.white);
            resourceBar.setForeground(new Color(128, 128, 128));
            playerInfoPanel.add(resourceBar);


            // i couldnt find a better way to implement the different player classes info panels except for casting
        } else if (player instanceof Mage) {
            Mage mage = (Mage) player;
            playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Blizzard <br>dmg: %d range: %d<HTML>", mage.getSpellPower(), mage.getAbilityRange()));
            playerSpecialAbilityLabel.setFont(smallFont);
            playerSpecialAbilityLabel.setForeground(Color.white);

            playerInfoPanel.add(playerSpecialAbilityLabel);
            resourceBar = new JProgressBar(0, mage.getMaxMana());
            resourceBar.setStringPainted(true);
            resourceBar.setValue(mage.getCurrMana());
            resourceBar.setString("Mana: " + mage.getCurrMana() + "/" + mage.getMaxMana());
            resourceBar.setFont(smallFont);
            resourceBar.setBackground(Color.black);
            resourceBar.setForeground(new Color(16, 90, 250)); // blue
            playerInfoPanel.add(resourceBar);

        } else if (player instanceof Rogue) {
            Rogue rogue = (Rogue) player;
            playerSpecialAbilityLabel = new JLabel(String.format("<HTML>Ability: Fan of Knives <br>dmg: %d range: %d<HTML>", rogue.getAttackPoints(), 2));
            playerSpecialAbilityLabel.setFont(smallFont);
            playerSpecialAbilityLabel.setForeground(Color.white);

            playerInfoPanel.add(playerSpecialAbilityLabel);
            resourceBar = new JProgressBar(0, rogue.getMaxEnergy());
            resourceBar.setStringPainted(true);
            resourceBar.setValue(rogue.getCurrEnergy());
            resourceBar.setString("Energy: " + rogue.getCurrEnergy() + "/" + rogue.getMaxEnergy());
            resourceBar.setFont(smallFont);
            resourceBar.setBackground(Color.white);
            resourceBar.setForeground(new Color(128, 128, 128));
            playerInfoPanel.add(resourceBar);
        } else if (player instanceof Hunter) {
            Hunter hunter = (Hunter) player;
            playerSpecialAbilityLabel = new JLabel(String.format("<HTML>Ability: Shoot <br>dmg: %d range: %d<HTML>", hunter.getAttackPoints(), hunter.getRange()));
            playerSpecialAbilityLabel.setFont(smallFont);
            playerSpecialAbilityLabel.setForeground(Color.white);

            playerInfoPanel.add(playerSpecialAbilityLabel);
            resourceBar = new JProgressBar(0, hunter.getMaxArrows());
            resourceBar.setStringPainted(true);
            resourceBar.setValue(hunter.getArrows());
            resourceBar.setString("Arrows: " + hunter.getArrows());
            resourceBar.setFont(smallFont);
            resourceBar.setBackground(Color.black);
            resourceBar.setForeground(new Color(150, 64, 0)); // brown rgb 150, 64, 0
            playerInfoPanel.add(resourceBar);
        }

        // player level label and exp bar
        playerLevel = new JLabel("Level: " + player.getLevel());
        playerLevel.setFont(smallFont);
        playerLevel.setForeground(Color.white);
        playerLevel.setVisible(true);
        playerInfoPanel.add(playerLevel);
        expBar = new JProgressBar(0, player.getMaxEXP());
        expBar.setValue(player.getCurrEXP());
        expBar.setStringPainted(true);
        expBar.setString(player.getCurrEXP() + "/" + player.getMaxEXP());
        expBar.setFont(smallFont);
        expBar.setForeground(Color.yellow);
        playerInfoPanel.add(expBar); //0058FB mana bar color

        // player stats labels
        playerAttackLabel = new JLabel("Attack: " + player.getAttackPoints());
        playerAttackLabel.setFont(smallFont);
        playerAttackLabel.setForeground(Color.white);
        playerAttackLabel.setVisible(true);
        playerInfoPanel.add(playerAttackLabel);

        playerDefenseLabel = new JLabel("Defense: " + player.getDefensePoints());
        playerDefenseLabel.setFont(smallFont);
        playerDefenseLabel.setForeground(Color.white);
        playerDefenseLabel.setVisible(true);
        playerInfoPanel.add(playerDefenseLabel);

        // add them to the panel
        playerInfoPanel.setVisible(true);
        window.add(playerInfoPanel);
        return playerInfoPanel;
    }

    private void accept(Player player) {
        if (player.getConsumablePoints() instanceof ABCD) {
            this.playerInfoUpdate((Warrior) player);
        } else if (player.getConsumablePoints() instanceof MP) {
            this.playerInfoUpdate((Mage) player);
        } else if (player.getConsumablePoints() instanceof ENERGY) {
            this.playerInfoUpdate((Rogue) player);
        } else if (player.getConsumablePoints() instanceof Arrows) {
            this.playerInfoUpdate((Hunter) player);
        }

    }

    private void playerInfoUpdate(Warrior player) {
        hpBar.setMaximum(player.getHealthPool());
        hpBar.setValue(player.getHealthCurrent());
        hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
        expBar.setValue(player.getCurrEXP());
        expBar.setMaximum(player.getMaxEXP());
        expBar.setString("XP: " + player.getCurrEXP() + "/" + player.getMaxEXP());
        if (player.getCurrCD() == 0) {
            resourceBar.setBackground(new Color(33, 217, 33)); // green
            resourceBar.setString("Ready");
        } else {
            resourceBar.setBackground(new Color(32, 32, 32)); // gray is rg
            resourceBar.setString("Available in: " + player.getCurrCD() + " turns");
        }
        playerLevel.setText("Level: " + player.getLevel());
        playerAttackLabel.setText("Attack: " + player.getAttackPoints());
        playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
        playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Avenger's Shield \ndmg: %d heal: %d<HTML>", player.getHealthPool() / 10, player.getDefensePoints() * 10));
    }

    private void playerInfoUpdate(Mage player) {
        hpBar.setMaximum(player.getHealthPool());
        hpBar.setValue(player.getHealthCurrent());
        hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
        expBar.setValue(player.getCurrEXP());
        expBar.setMaximum(player.getMaxEXP());
        expBar.setString("XP: " + player.getCurrEXP() + "/" + player.getMaxEXP());
        resourceBar.setMaximum(player.getMaxMana());
        resourceBar.setValue(player.getCurrMana());
        if (player.getCurrMana() >= 30) {
            resourceBar.setForeground(new Color(16, 90, 250)); // blue
            resourceBar.setString("Mana: " + player.getCurrMana() + "/" + player.getMaxMana());
        } else {
            resourceBar.setForeground(new Color(32, 32, 32)); // gray is rg
            resourceBar.setString("Mana: " + player.getCurrMana() + "/" + player.getMaxMana());
        }
        playerLevel.setText("Level: " + player.getLevel());
        playerAttackLabel.setText("Attack: " + player.getAttackPoints());
        playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
        playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Blizzard <br>dmg: %d range: %d<HTML>", player.getSpellPower(), player.getAbilityRange()));
    }

    private void playerInfoUpdate(Rogue player) {
        hpBar.setMaximum(player.getHealthPool());
        hpBar.setValue(player.getHealthCurrent());
        hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
        expBar.setValue(player.getCurrEXP());
        expBar.setMaximum(player.getMaxEXP());
        expBar.setString("XP: " + player.getCurrEXP() + "/" + player.getMaxEXP());
        resourceBar.setMaximum(player.getMaxEnergy());
        resourceBar.setValue(player.getCurrEnergy());
        if (player.getCurrEnergy() >= player.getCastCost()) {
            resourceBar.setForeground(new Color(255, 180, 0)); // yellow
            resourceBar.setString("Energy: " + player.getCurrEnergy() + "/" + player.getMaxEnergy());
        } else {
            resourceBar.setForeground(new Color(32, 32, 32)); // gray is rg
            resourceBar.setString("Energy: " + player.getCurrEnergy() + "/" + player.getMaxEnergy());
        }
        playerLevel.setText("Level: " + player.getLevel());
        playerAttackLabel.setText("Attack: " + player.getAttackPoints());
        playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
        playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Fan of Knives <br>dmg: %d range: %d<HTML>", player.getAttackPoints(), 2));
    }

    private void playerInfoUpdate(Hunter player) {
        hpBar.setMaximum(player.getHealthPool());
        hpBar.setValue(player.getHealthCurrent());
        hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
        expBar.setValue(player.getCurrEXP());
        expBar.setMaximum(player.getMaxEXP());
        expBar.setString("XP: " + player.getCurrEXP() + "/" + player.getMaxEXP());
        resourceBar.setMaximum(player.getArrows());
        resourceBar.setValue(player.getArrows());
        if (player.getArrows() >= 1) {
            resourceBar.setForeground(new Color(196, 100, 64)); // yellow
            resourceBar.setString("Arrows: " + player.getArrows());
        } else {
            resourceBar.setForeground(new Color(32, 32, 32)); // gray is rg
            resourceBar.setString("Arrows: " + player.getArrows());
        }
        playerLevel.setText("Level: " + player.getLevel());
        playerAttackLabel.setText("Attack: " + player.getAttackPoints());
        playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
        playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Shoot <br>dmg: %d range: %d<HTML>", player.getAttackPoints(), 3));
    }


    private Clip loadMusic(URL filePath) { // loads the music file
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadMusicFolderToVector(String folderPath) {
        try {
            // Get the resource folder as a URL
            JarFile jar = new JarFile("DungeonsnDragons_ProjectOOP.jar");
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".wav")) {
                    System.out.println("File: " + entry.getName());
                    URL url = getClass().getResource("/" + entry.getName());
                    clips.add(loadMusic(url));
                }
            }
            // the laziest way to load the songs i want
            clips.remove(clips.lastElement());
            clips.remove(clips.lastElement());
        } catch (IOException e) {
            System.out.println("Failed to load music files from folder: " + folderPath);
            System.out.println(e.getMessage());
        }
        System.out.println("Finished loading music files from folder: " + folderPath);
    }


    public void playNextMusic() {
        if (!clips.isEmpty()) {
            try{
                if(clip == null){
                    clip = clips.get(currentSongIndex++);
                } else {
                    clip.stop();
                    try {
                        clip = clips.get(currentSongIndex++);
                        if (clip == null) {
                            throw new IndexOutOfBoundsException("Restarting playlist");
                        }
                    } catch (IndexOutOfBoundsException e){
                        throw new IndexOutOfBoundsException("Restarting playlist");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                currentSongIndex = 0;
                clip = clips.get(++currentSongIndex);
            }
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-30.0f); // inplace change of volume - change is done in decibels
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } else {
            Logger logger = Logger.getLogger("no music loaded");
            logger.info("No music loaded");
        }
    }

    public void playVictorySong() {
        clip.stop();
        clips.clear();
        playSpecificMusicFile("Sound/music/VictorySong/6.8-bit RPG Music  Victory Theme.wav");
        clip = clips.get(0);
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(-30.0f); // inplace change of volume - change is done in decibels
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playDeathMusic() {
        clip.stop();
        clips.clear();
        JarFile jar;
        playSpecificMusicFile("Sound/music/DefeatSong/Sadness and Sorrow 8-bit (Remasterizado)-YoutubeConvert.cc.wav");
        clip = clips.get(0);
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(-15.0f); // inplace change of volume - change is done in decibels
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSpecificMusicFile(String filePath) {
        try {
            JarFile jar = new JarFile("DungeonsnDragons_ProjectOOP.jar");
            filePath = filePath.startsWith("/") ? filePath.substring(1) : filePath; // Remove leading slash if present
            JarEntry entry = jar.getJarEntry(filePath);
            if (entry != null && entry.getName().endsWith(".wav")) {
                System.out.println("File: " + entry.getName());
                URL url = new URL("jar:file:" + jar.getName() + "!/" + entry.getName());
                Clip clip = loadMusic(url);
                if (clip != null) {
                    clips.add(clip);
                }
            }
            jar.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger("no music loaded");
            logger.info("No music loaded");
        }
    }


    public void playRandomClip() {
        while (true) {
            clip.stop();
            Random random = new Random();
            System.out.println(clips.size());
            Clip clip = clips.get(random.nextInt(clips.size()));
            try {
                clip.open();
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-10.0f); // inplace change of volume - change is done in decibels
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);

                clip.stop();
                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            playRandomClip();
        }
    }

    public void clipsIterator() {
        Iterator<Clip> iterator = clips.iterator();
        while (iterator.hasNext()) {
            Clip clip = iterator.next();
            try {
                clip.open();
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-10.0f); // inplace change of volume - change is done in decibels
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);

                clip.stop();
                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getMusicStatus() {
        return musicPlaying;
    }

    public void setMusicStatus(boolean b) {
        musicPlaying = b;
    }

    public void stopMusic() {
        clip.stop();
    }

    public void playNextTrack() {
        try {
            Clip clip = clips.iterator().next();
            clip.start();
        } catch (Exception e) {
            clips.stream().iterator().next().start();
        }
    }


    public int getNumberOfMonstersLeftInt() {
        return numberOfMonstersLeftInt;
    }

    public void setNumberOfMonstersLeftInt(int numberOfMonstersLeftInt) {
        this.numberOfMonstersLeftInt = numberOfMonstersLeftInt;
    }
}
