import GameBoard.GameBoard;
import Units.PlayerClasses.Warrior;

public class Main {
    public static void main(String[] args) {
//        GameBoard gameBoard = new GameBoard();
        Warrior warrior = new Warrior("Warrior");
        warrior.performBasicAttack();
    }
}