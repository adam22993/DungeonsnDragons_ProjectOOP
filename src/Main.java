import GameBoard.GameBoard;
import Units.PlayerClasses.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameBoard gameBoard = new GameBoard();
        Warrior warrior = new Warrior("Warrior");
//        warrior.accept();
        System.out.println("hello");
        gameBoard.printBoard();
    }
}