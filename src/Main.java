import Controller.ControlLayer;
import GameBoard.GameBoard;
import GameBoard.Levels.Level;
import GameBoard.Levels.LevelLoader;

public class Main {
    public static void main(String[] args) {
//        GameBoard gameBoard = new GameBoard();
//        if (args.length == 1){
//            gameBoard.loadAdditions(args[0]);
//        }
        ControlLayer controlLayer = new ControlLayer(new GameBoard());
//        LevelLoader levelLoader = new LevelLoader("src\\GameBoard\\Levels");
    }
}
