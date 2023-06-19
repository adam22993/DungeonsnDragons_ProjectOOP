import Controller.ControlLayer;
import GameBoard.GameBoard;
import GameBoard.Levels.Level;
import GameBoard.Levels.LevelLoader;
import Patterns.Factory.TileFactory;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No levels directory specified.");
            return;
        } else if (args.length > 1) {
            System.out.println("Too many arguments.");
            return;
        } else if (!checkIfFolderContainsLevelsTXT(args[0])) {
            System.out.println("The specified folder does not contain any .txt files.");
            return;
        } else {
            System.out.println("Loading levels from " + args[0] + "...");


            ControlLayer controlLayer = new ControlLayer(new GameBoard(new LevelLoader(args[0])));
        }
    }



    public static boolean checkIfFolderContainsLevelsTXT(String directory) {
        File folder = new File(directory);
        File[] files = folder.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    return true;
                }
            }
        }
        return false;
    }
}
