package GameBoard;
import Units.Unit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class GameBoard {

    int current_level = 0;

    char[][] board;
    public GameBoard(){
        this.board = loadBoard();
    }

    private char[][] loadBoard() {
        /*re
         * This function loads the board from the file. Using the current_level variable, it loads the file of
         * the current level. It then counts the number of lines and columns in the file, and creates a 2D array
         * of chars with the size of the board. It then loads the board from the file into the 2D array.
         * @return 2D array of chars representing the board.
         */
        String levelFile = "DungeonsnDragons_ProjectOOP\\src\\GameBoard\\Levels\\level" + (current_level + 1) + ".txt";
        int countLines = 0;
        int countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);
        try (BufferedReader br = new BufferedReader(new FileReader(levelFile))) {
            while (br.readLine() != null) {
                countLines++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(levelFile))) {
            String line;
            if ((line = br.readLine()) != null) {
                countColumns = line.length();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        char[][] board = new char[countLines][countColumns];

        try (BufferedReader br = new BufferedReader(new FileReader(levelFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                char[] values = line.toCharArray();
                System.arraycopy(values, 0, board[i], 0, values.length);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        current_level++;
        return board;
    }

    public void printBoard() {
        /**
         * This function prints the board to the console. It iterates through the 2D array of chars representing
         * the board, and prints each char to the console.
         */
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }


    private void scanForUnitsBoard() {
        /**DungeonsnDragons_ProjectOOP\src\GameBoard\GameBoard.java
         * This function scans the board for units. It creates a 2D array of Units with the same size as the board.
         * It then scans the board for units, and creates a new unit for each unit found. It then returns the 2D array
         * of units.
         * @return 2D array of Units representing the units on the board.
         */

        Vector<Unit> units = new Vector<Unit>();
        for (char[] chars : board) {
            for (int j = 0; j < board.length; j++) {
                Object current = chars[j]; // could be anything
                }
            }
        }


    public double calculateRange(Point p1, Point p2) {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();

        int xRange = Math.abs(x1 - x2);
        int yRange = Math.abs(y1 - y2);

        return Math.sqrt(Math.pow(xRange, 2) + Math.pow(yRange, 2));
    }

    private void playerTurn() {

    }

}