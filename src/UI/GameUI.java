
package UI;

import Units.Abstracts.Player;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class GameUI {

    private JFrame window;
    private Container con;
    private JPanel startButtonPanel, mainTextPanel, characterSelectOptions, playerPanel, boardPanel, playerChoicesPanel, WSImagesPanel;
    private JLabel backgroundLabel, titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, weaponLabelNumber, armorLabel, armorLabelName, armorLabelNumber, playerLabel, playerLabelName, playerLabelNumber;
    private JTextArea boardTextArea;
    private JLayeredPane gameScreenPanel, CCSBGPanel;
    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private final Font smallFont = new Font("Times New Roman", Font.PLAIN, 18);
    private final Font boardFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
    private final String currentDir = System.getProperty("user.dir");
    private Clip clip;
    private Vector<Clip> clips = new Vector<Clip>();
    private JPanel MusicPanel;
    boolean musicPlaying = false;

    public GameUI() {
        loadMusicFolderToVector(currentDir + "/src/UI/Assets/Audio/");
    }

    public void openWelcomeScreen(JPanel startButtonsPanel, JFrame currWindow) {
        window = currWindow;
//        con = window.getContentPane();
        clip = clips.get(0);
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-10.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        window.setVisible(true);
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

//
//
//
//        con.add(WSImagesPanel);
        //TODO: check resizing of the window and how to fix the images being not in the right place.
    }

    public void characterCreationScreen(JPanel characterSelectPanel){
        startButtonPanel.setVisible(false);
        WSImagesPanel.setVisible(false);
        CCSBGPanel = new JLayeredPane();

        CCSBGPanel.setLayout(null);
        CCSBGPanel.setBounds(0, 0, window.getWidth(), window.getHeight());
        ImageIcon backgroundImage = new ImageIcon(currentDir + "/src/UI/Assets/Images/WelcomeScreenImage.jpg");
        Image bgImage = backgroundImage.getImage();
        bgImage = bgImage.getScaledInstance(window.getWidth(), window.getHeight(), Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(bgImage);
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, window.getWidth(), window.getHeight());

        CCSBGPanel.add(backgroundLabel);
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
            JLabel label = new JLabel(line);
            label.setFont(boardFont);
            label.setBounds(0, 20, 1100, 500);
            label.setForeground(Color.white);
            label.setHorizontalAlignment(JLabel.LEFT);
            mainTextPanel.add(label);
        }

        characterSelectOptions = characterSelectPanel;
        window.add(characterSelectOptions);
        playerPanel = new JPanel();
        playerPanel.setBounds(450, 35, 300, 50);
        playerLabel = new JLabel("Choose your character:");
        playerLabel.setHorizontalAlignment(JLabel.CENTER);
        playerLabel.setForeground(Color.white);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        playerLabel.setFont(normalFont);
        playerPanel.add(playerLabel);
        window.add(playerPanel);
        window.add(CCSBGPanel);
        window.revalidate();
        window.repaint();
    }

    public void createGameScreen(String board, JFrame window) {
        clip.stop();
        clip = loadMusic(currentDir + "/src/UI/Assets/Audio/2xDeviruchi - And The Journey Begins (Loop).wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(-10.0f); // inplace change of volume - change is done in decibels
//        playRandomClip();
        mainTextPanel.setVisible(false);
        characterSelectOptions.setVisible(false);
        CCSBGPanel.setVisible(false);
        // create player panel
//        playerPanel = playerControlsPanel;///////////////////////
//        gameScreenPanel = new JLayeredPane();
//        gameScreenPanel.setLayout(null);
//        gameScreenPanel.setBounds(50, 75, 700, 500);
//        gameScreenPanel.setBackground(Color.pink);
//        gameScreenPanel.setForeground(Color.white);
//        gameScreenPanel.setOpaque(true);
//        gameScreenPanel.setVisible(true);
//        window.add(playerPanel); ////////////////////////////

        boardTextArea = new JTextArea();
        boardTextArea = new JTextArea(board/*"\t\t\t\t\t###\n\n\n\n\n\n\n\n\n#\t\t\t\t\tthis is a test\n\n\n\n\n\n\n\n\n\t\t\t\t\t###"*/);
        boardTextArea.setBounds(50, 100, 600, 600);
        boardTextArea.setFont(boardFont);
        boardTextArea.setForeground(Color.white);
        boardTextArea.setBackground(Color.black);
        boardTextArea.setVisible(true);
        boardTextArea.setLineWrap(true);
        boardTextArea.setWrapStyleWord(true);
        boardTextArea.setEditable(false);
        window.add(boardTextArea);

//        window.add(con);
        window.revalidate();
        window.repaint();

    }

    public void updateBoard(String board) {
        System.out.println(board);
        boardTextArea.setText(board);
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
        try{
            clip = clips.iterator().next();
        } catch (Exception e) {
            clip = clips.stream().iterator().next();
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
