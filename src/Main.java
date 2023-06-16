import Controller.ControlLayer;
import GameBoard.GameBoard;
import GameBoard.Levels.Level;
import GameBoard.Levels.LevelLoader;

public class Main {
    public static void main(String[] args) {

        ControlLayer controlLayer = new ControlLayer(new GameBoard(new LevelLoader("src\\GameBoard\\Levels")));
//        LevelLoader levelLoader = new LevelLoader("src\\GameBoard\\Levels");
    }
}
