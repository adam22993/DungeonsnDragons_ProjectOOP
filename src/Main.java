import GameBoard.GameBoard;
import UI.GameUI;
import Units.ADDITIONAL.Position;
import Units.PlayerClasses.*;

public class Main {
    public static void main(String[] args) {
//        GameBoard gameBoard = new GameBoard();
//        Warrior warrior = new Warrior("Warrior", 100, 100, 100, 100);
//        Warrior Bronn = new Warrior("Bronn", 100, 100, 100, 100);
//        warrior.performBasicAttack();
//        Bronn.performBasicAttack();
        GameBoard gameBoard = new GameBoard();
        GameUI gameUI = new GameUI();
        System.out.println(gameBoard.getBoardString());
        System.out.println();
        gameBoard.printBoard();
        }
    }
