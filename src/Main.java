import GameBoard.GameBoard;
import UI.GameUI;
import Units.PlayerClasses.Warrior;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        GameUI gameUI = new GameUI();
        Warrior warrior = new Warrior("Warrior");
        while (true) {
            gameUI.playerTurn(warrior);
        }
    }
}