
package UI;

import Controller.Messages.MessageCallback;
import Units.ADDITIONAL.ConsumablePoints.*;
import Units.AbstractsAndInterfaces.HeroicUnit;
import Units.AbstractsAndInterfaces.Player;
import Units.PlayerClasses.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class GameUI {

    private JFrame window;
    private Container con;
    private JPanel startButtonPanel, mainTextPanel, characterSelectOptions, headerLabelCS, boardPanel, playerControlPanel, WSImagesPanel;
    private JLabel backgroundLabel, chooseACharacter, playerAttackLabel, playerDefenseLabel, playerSpecialAbilityLabel, playerLevel;
    private JTextArea boardTextArea;
    private JProgressBar hpBar, expBar, resourceBar;
    private JLayeredPane gameScreenPanel, CCSBGPanel;
    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private final Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    private final String currentDir = System.getProperty("user.dir");
    private Clip clip;
    private final Vector<Clip> clips = new Vector<Clip>();
    private JPanel MusicPanel;
    private boolean musicPlaying = false;
    private Player player;

    private MessageCallback m;


    public GameUI(JFrame controlLayerWindow) {
        loadMusicFolderToVector(currentDir + "/src/UI/Assets/Audio/");
        window = controlLayerWindow;
        m = (m) -> displayMessage(m);
    }

    private void displayMessage(String m) {
        // create an if statement to delete the oldest message if there are more than 5 messages
        JLabel message = new JLabel(m);
        message.setFont(smallFont);
        message.setBounds(0, 0, 100, 40);
        message.setForeground(Color.WHITE);
        message.setBackground(Color.BLACK);
        message.setOpaque(true);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);
        message.setVisible(true);
    }

    public void openWelcomeScreen(JPanel startButtonsPanel) {
//        window = controlLayerWindow;
//        con = window.getContentPane();

        clip = clips.get(0);  // mac for some reason loads differently than windows, sequence is different
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-30.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        WSImagesPanel = new JPanel();
        WSImagesPanel.setLayout(null);
        WSImagesPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
        WSImagesPanel.add(startButtonsPanel);
        ImageIcon titleImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/titleName.png");
        JLabel titleImageLabel = new JLabel(titleImage);
        titleImageLabel.setBounds(340, 20, 500, 140);

        ImageIcon backgroundImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/WelcomeScreenImage.jpg");
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

    public void characterCreationScreen(JPanel characterSelectPanel){

        startButtonPanel.setVisible(false);
        WSImagesPanel.setVisible(false);
        CCSBGPanel = new JLayeredPane();

        CCSBGPanel.setLayout(null);
        CCSBGPanel.setOpaque(false);
        CCSBGPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
        ImageIcon backgroundImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/WelcomeScreenImage.jpg");
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
        mainTextPanel.setLayout(new GridLayout(9,1));
        mainTextPanel.setBounds(50, 75, 1100, 300);
        window.add(mainTextPanel);
        String infoText =
                "      name        |       HP        |     ATT    |     DEF    |    CAST SOURCE   |    ADD STATS\n" +
                        " 1. Jon Snow      | Health: 300/300 | Attack: 30 | Defense: 4 | Cooldown: 0/3    |\n" +
                        " 2. The Hound     | Health: 400/400 | Attack: 20 | Defense: 6 | Cooldown: 0/5    |\n" +
                        " 3. Melisandre    | Health: 100/100 | Attack: 5  | Defense: 1 | Mana: 75/300     | Spell Power: 15\n" +
                        " 4. Thoros of Myr | Health: 250/250 | Attack: 25 | Defense: 4 | Mana: 37/150     | Spell Power: 20\n" +
                        " 5. Arya Stark    | Health: 150/150 | Attack: 40 | Defense: 2 | Energy: 100/100  |\n" +
                        " 6. Bronn         | Health: 250/250 | Attack: 35 | Defense: 3 | Energy: 100/100  |\n" +
                        " 7. Ygritte       | Health: 220/220 | Attack: 30 | Defense: 2 | Arrows: 10       | Range: 6\n" +
                        " 8. Custom character                  player creation screen TBI";

        String[] lines = infoText.split("\n");

        for (String line : lines) {
            JButton charButton = new JButton(line);
            charButton.setFont(boardFont);
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

        clip.stop();
        clip = loadMusic(currentDir + "/src/UI/Assets/Audio/2xDeviruchi - And The Journey Begins (Loop).wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(-30.0f); // inplace change of volume - change is done in decibels
        CCSBGPanel.setVisible(false);
//        CCSBGPanel = new JLayeredPane();
//
//
//        ImageIcon backgroundImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/WelcomeScreenImage.jpg");
//        Image bgImage = backgroundImage.getImage();
//        bgImage = bgImage.getScaledInstance(window.getWidth(), window.getHeight(), Image.SCALE_DEFAULT);
//        backgroundImage = new ImageIcon(bgImage);
//        backgroundLabel = new JLabel(backgroundImage);
//        backgroundLabel.setBounds(0, 0, window.getWidth(), window.getHeight());
//        backgroundLabel.setVisible(true);
//        backgroundLabel.setOpaque(false);
//        window.add(backgroundLabel, 0);

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

        JPanel playerInfoPanel = warriorInfo((Warrior) player1);
        playerInfoPanel.setBackground(Color.black);
        playerInfoPanel.setForeground(Color.white);
        playerInfoPanel.setOpaque(true);
        window.add(playerInfoPanel);

        JLabel controlLabelInfo = new JLabel("<HTML> Movement: W, A, S, D <br> Attack: Use movement to ram other units <br> Use Ability: E <br> Pass your turn: Q <br> Exit Game: ESC </HTML>");
        controlLabelInfo.setFont(smallFont);
        controlLabelInfo.setForeground(Color.white);
        controlLabelInfo.setBackground(Color.black);
        controlLabelInfo.setOpaque(false);
        controlLabelInfo.setVisible(true);
        controlLabelInfo.setBounds(980, 520, 200, 150);
        window.add(controlLabelInfo);

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

    public void debugStart(String board, Player player1, JPanel playerControlsPanel) {

        clip.stop();
        clip = loadMusic(currentDir + "/src/UI/Assets/Audio/2xDeviruchi - And The Journey Begins (Loop).wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(-30.0f); // inplace change of volume - change is done in decibels
//        CCSBGPanel.setVisible(false);

//        characterSelectOptions.setVisible(false);
//        headerLabelCS.setVisible(false);
//        mainTextPanel.setVisible(false);
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

        JPanel playerInfoPanel = warriorInfo((Warrior) player1);
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

    public void updateBoard(String board, Vector<Player> players) {
        System.out.println(board);
        boardTextArea.setText(board);
        accept(players.get(0));

    }

    private void accept(Player player){
        if (player.getConsumablePoints() instanceof ABCD) {
            this.playerInfoUpdate((Warrior) player);
        }
        else if (player.getConsumablePoints() instanceof MP) {
            this.playerInfoUpdate((Mage) player);
        }
//        else if (player.getConsumablePoints() instanceof ENERGY) {
//            this.playerInfoUpdate((Rogue) player);
//        }

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
            resourceBar.setBackground(new Color(32,32,32)); // gray is rg
            resourceBar.setString("Available in: " + player.getCurrCD() + " turns");
        }
        playerLevel.setText("Level: " + player.getLevel());
        playerAttackLabel.setText("Attack: " + player.getAttackPoints());
        playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
        playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Avenger's Shield \ndmg: %d heal: %d<HTML>", player.getHealthPool()/10, player.getDefensePoints() * 10));
    }

    private void playerInfoUpdate(Mage player) {
        hpBar.setValue(player.getHealthCurrent());
        hpBar.setMaximum(player.getHealthPool());
        hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
        expBar.setValue(player.getCurrEXP());
        expBar.setMaximum(player.getMaxEXP());
        expBar.setString("XP: " + player.getCurrEXP() + "/" + player.getMaxEXP());
        if (player.getCurrMana() >= 30) {
            resourceBar.setBackground(new Color(33, 217, 33)); // green
            resourceBar.setString("CD: " + player.getCurrMana() + "/" + player.getMaxMana());
        } else {
            resourceBar.setBackground(new Color(32, 32, 32)); // gray is rg
            resourceBar.setString("CD: " + player.getCurrMana() + "/" + player.getMaxMana());
        }
        playerLevel.setText("Level: " + player.getLevel());
        playerAttackLabel.setText("Attack: " + player.getAttackPoints());
        playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
        playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Blizzard \ndmg: %d heal: %d<HTML>", player.getHealthPool() / 10, player.getDefensePoints() * 10));
    }

//    private void playerInfoUpdate(Rogue player) {
//            hpBar.setValue(player.getHealthCurrent());
//            hpBar.setMaximum(player.getHealthPool());
//            hpBar.setString("HP: " + player.getHealthCurrent() + "/" + player.getHealthPool());
//            expBar.setValue(player.getCurrEXP());
//            expBar.setMaximum(player.getMaxEXP());
//            expBar.setString("XP: " + player.getCurrEXP() + "/" + player.getMaxEXP());
//            resourceBar.setString(player.getCurrCD() + "/" + player.getMaxCD());
//            if (player.getCurrCD() == 0) {
//                resourceBar.setBackground(new Color(33, 217, 33)); // green
//                resourceBar.setString("Ready");
//            } else {
//                resourceBar.setBackground(new Color(32,32,32)); // gray is rg
//                resourceBar.setString("CD: " + player.getCurrCD() + "/" + player.getMaxCD());
//            }
//            playerLevel.setText("Level: " + player.getLevel());
//            playerAttackLabel.setText("Attack: " + player.getAttackPoints());
//            playerDefenseLabel.setText("Defense: " + player.getDefensePoints());
//            playerSpecialAbilityLabel.setText(String.format("<HTML>Ability: Avenger's Shield \ndmg: %d heal: %d<HTML>", player.getHealthPool()/10, player.getDefensePoints() * 10));
//    }



    public JPanel warriorInfo(Warrior player) {
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(7, 2));
        playerInfoPanel.setBounds(770, 30, 400, 400);

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

        // player cd and ability label
        playerSpecialAbilityLabel = new JLabel(String.format("<HTML>Ability: Avenger's Shield \ndmg: %d heal: %d<HTML>", player.getHealthPool()/10, player.getDefensePoints() * 10));
        playerSpecialAbilityLabel.setFont(smallFont);
        playerSpecialAbilityLabel.setForeground(Color.white);

        playerInfoPanel.add(playerSpecialAbilityLabel);
        resourceBar = new JProgressBar(0, player.getMaxCD());
        resourceBar.setStringPainted(true);
        resourceBar.setValue(player.getCurrCD());
        resourceBar.setString("CD: " + player.getCurrCD() + "/" + player.getMaxCD());
        resourceBar.setFont(smallFont);
        resourceBar.setBackground(Color.white);
        resourceBar.setForeground(new Color(128, 128, 128));
        playerInfoPanel.add(resourceBar);

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
        expBar.setForeground(Color.decode("#FCCD12"));
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

    private Clip loadMusic(String filePath) { // loads the music file
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void loadMusicFolderToVector(String folderPath) { // loads the music folder to the vector
        File folder = new File(folderPath);
        try {
            if (folder.exists() && folder.isDirectory()) {
                // Get all files in the folder
                File[] files = folder.listFiles();

                // Iterate over the files
                if (files != null) {
                    System.out.println("Loading music files from folder: " + folder.getName());
                    for (File file : files) {
                        // Process each file
                        if (file.isFile()) {
                            System.out.println("File: " + file.getName());
                            clips.add(loadMusic(file.getAbsolutePath()));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error loading music files from folder: " + folder.getName());
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Finished loading music files from folder: " + folder.getName());
    }

    public void playRandomClip() {
        while (true) {
            // Get a random clip from the vector
            clip.stop();
            Random random = new Random();
            System.out.println(clips.size());
            Clip clip = clips.get(random.nextInt(1, clips.size() - 1));
            try {
                // Open and play the clip
                clip.open();
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-10.0f); // inplace change of volume - change is done in decibels
                clip.start();

                // Wait for the clip to finish playing
                Thread.sleep(clip.getMicrosecondLength() / 1000);

                // Stop and close the clip
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
                // Open and play the clip
                clip.open();
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(-10.0f); // inplace change of volume - change is done in decibels
                clip.start();

                // Wait for the clip to finish playing
                Thread.sleep(clip.getMicrosecondLength() / 1000);

                // Stop and close the clip
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

    }

    public void playMusic() {
        try{
            clips.iterator().next().start();
        } catch (Exception e) {
            clips.stream().iterator().next().start();
        }
    }
}
