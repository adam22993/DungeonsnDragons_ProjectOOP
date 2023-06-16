package GameBoard.Levels;

import Patterns.Factory.TileFactory;
import Units.ADDITIONAL.*;
import Units.AbstractsAndInterfaces.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Level {
    private static int countLevels = 0; // a counter for the number of levels created - also sets the level number

    private final String name;
    private Vector<Unit> tilesOfBoard;
    private int levelNumber;

    private int countLines;

    private int countColumns;

    private String[] levelText;

    public Level(String name, Vector<Unit> tiles) { // a constructor that could be useful for testing,
                                                    // but not used currently
        if (name == null || tiles == null) {
            throw new IllegalArgumentException("Level name and tiles cannot be null.");
        }
        this.name = name;
        this.levelNumber = ++countLevels;
        this.tilesOfBoard = tiles;
    }

    public Level(String name, String directory) {
        if (name == null) {
            throw new IllegalArgumentException("Level name cannot be null.");
        }
        this.name = name;
        this.levelNumber = ++countLevels;
        loadLevel(directory); // loading the level from the file as an array of strings
    }

    public static int getCountLevels() {
        return countLevels;
    }

    public Vector<Unit> getTiles() {
        return this.tilesOfBoard;
    }

    public String getName() {
        return this.name;
    }

    private void loadLevel(String directory) {  // the correct version that loads a vector of units to the tilesOfBoard vector
        countLines = 0;
        countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current file location: " + directory); // debugging use
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            String line = br.readLine();
            // TODO: Look For the longest line in the file and set the countColumns to that number
            while (line != null) {
                if (countLines == 0) {
                    countColumns = line.length();
                }
                countLines++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        levelText = new String[countLines];
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(directory));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sentence;
        int lineCounter = 0;
        try {
            while ((sentence = br.readLine()) != null) {
                levelText[lineCounter] = sentence;
                lineCounter++;
            }
            br.close(); // practice of good manners and hygiene while coding - close the file after you're done with it.
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        // debugging use - printing the level
        System.out.println("The level is " + countColumns + " columns wide and " + countLines + " lines long, level " + countLevels + " loaded successfully.");
        for (String s : levelText) {
            System.out.println(s);
        }
        System.out.println();
    }

    public String[] getLevelText() {
        return levelText;
    }
}
