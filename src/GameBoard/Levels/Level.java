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
    private static int countLevels = 0;

    private final String name;
    private Vector<Unit> tilesOfBoard;
    private int levelNumber;

    private int countLines;

    private int countColumns;

    private TileFactory tileFactory;

    public Level(String name, Vector<Unit> tiles) {
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
        this.tilesOfBoard = loadLevel(directory);
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

    private Vector<Unit> loadLevel(String directory) {  // the correct version that loads a vector of units to the tilesOfBoard vector
        countLines = 0;
        countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current file location: " + directory); // debugging use
        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            String line = br.readLine();
            // TODO: Look For the longets line in the file and set the countColumns to that number
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

        tileFactory = new TileFactory(0);
        tilesOfBoard = new Vector<Unit>();
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

                for (int i = 0; i < countColumns; i++) {
                    Unit unit = tileFactory.produceTile(sentence.charAt(i), new Position(i, lineCounter));
                    tilesOfBoard.add(unit);
                }
                lineCounter++;
            }
            br.close(); // practice of good manners and hygiene while coding - close the file after you're done with it.
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("The level is " + countColumns + " columns wide and " + countLines + " lines long, level " + countLevels + " loaded successfully.");
        for (int i = 0; i < tilesOfBoard.size(); i++) {
            System.out.print(tilesOfBoard.get(i));
            if ((i + 1) % countColumns == 0) {
                System.out.println();
            }
        }
        System.out.println();
        return tilesOfBoard;
    }
}
