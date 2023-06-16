package GameBoard.Levels;

import Units.AbstractsAndInterfaces.Unit;

import java.io.File;
import java.util.Random;
import java.util.Vector;

public class LevelLoader {
    private Vector<Level> levels;
    public LevelLoader(String directory) {
        levels = new Vector<>();
        loadLevels(directory);
    }

    private void loadLevels(String directory) { // receives a directory to a folder with .txt files
        File folder = new File(directory);
        File[] files = folder.listFiles();

        if (files != null && files.length != 0) {
            Vector<String> fileNames = new Vector<>();
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    fileNames.add(file.getName());
                    levels.add(new Level(file.getName(), directory + "\\" + file.getName()));
                }
            }

            // Print the file names
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }
    }

    public boolean isEmpty() {
        return levels.isEmpty();
    }

    public Level getLevel() {
        return levels.remove(new Random().nextInt(levels.size()));
    }

    public int getLevelsCount() {
        return levels.size();
    }

    public String[] loadNextLevel() {
        if (!isEmpty()) {
            Level level = getLevel();
            System.out.println("Loading level " + level.getName() + "...");
            return level.getLevelText();
        } else {
            System.out.println("No more levels to load.");
        }
        return null;
    }

}
