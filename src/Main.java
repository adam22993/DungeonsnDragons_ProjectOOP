import GameBoard.GameBoard;
import UI.GameUI;
import Units.PlayerClasses.*;

public class Main {
    public static void main(String[] args) {
//        GameBoard gameBoard = new GameBoard();
        Warrior warrior = new Warrior("Warrior", 100, 100, 100, 100, 100);
        Warrior warrior2 = new Bronn();
        warrior.performBasicAttack();
        warrior2.performBasicAttack();
        GameBoard gameBoard = new GameBoard();
        GameUI gameUI = new GameUI();
        }
    }
