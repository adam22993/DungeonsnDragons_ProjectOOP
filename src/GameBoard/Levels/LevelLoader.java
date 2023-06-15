package GameBoard.Levels;

import java.io.File;
import java.util.Vector;

public class LevelLoader {
    private Vector<Level> levels;
    public LevelLoader(String directory) {
        levels = new Vector<>();
        loadLevels(directory);
    }

    private void loadLevels(String directory) {
        File folder = new File(directory);
        File[] files = folder.listFiles();

        if (files != null) {
            Vector<String> fileNames = new Vector<>();
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".txt")) {
                    fileNames.add(files[i].getName());
                    levels.add(new Level(files[i].getName(), directory + "\\" + files[i].getName()));
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
}
