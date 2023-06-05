package GameBoard;
import UI.GameUI;
import Units.Abstracts.Unit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameBoard {
    GameUI gameUI = new GameUI();
    int current_level = 0;

    char[][] board;

    public GameBoard() {
        this.board = loadBoard(current_level);
        gameUI.openWelcomeScreen();
    }


//    public void createGameUI() {
//        boardPanel = new JPanel();
//        boardPanel.setBounds(100, 100, 600, 250);
//        boardPanel.setBackground(Color.black);
//        con.add(boardPanel);
//    }


    private char[][] loadBoard(int current_level) {
        /*re
         * This function loads the board from the file. Using the current_level variable, it loads the file of
         * the current level. It then counts the number of lines and columns in the file, and creates a 2D array
         * of chars with the size of the board. It then loads the board from the file into the 2D array.
         * @return 2D array of chars representing the board.
         */

        //TODO: change to fit tiles and not print the board

        String levelFile = "/src/GameBoard/Levels/level" + (current_level + 1) + ".txt";
        //String levelFile = "\\src\\GameBoard\\Levels\\level" + (current_level + 1) + ".txt";
        int countLines = 0;
        int countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir + levelFile);
        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            while (br.readLine() != null) {
                countLines++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            String line;
            if ((line = br.readLine()) != null) {
                countColumns = line.length();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        char[][] board = new char[countLines][countColumns];

        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < countColumns; j++) {
                    board[i][j] = line.charAt(j);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Unit[][] units = new Unit[countLines][countColumns];
        for (char[] row: board){
            for (char c: row){

            }
        }
        current_level++;
        return board;
    }

    public void printBoard() {
        /**
         * This function prints the board to the console. It iterates through the 2D array of chars representing
         * the board, and prints each char to the console.
         */
        //TODO: change to fit tiles and not print the board
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public String getBoardString() {
        /**
         * This function prints the board to the console. It iterates through the 2D array of chars representing
         * the board, and prints each char to the console.
         */
        StringBuilder boardString = new StringBuilder();
        for (char[] row : board) {
            for (char c : row) {
                boardString.append(c);
            }
            boardString.append("\n");
        }
        return boardString.toString();


//        private Vector<Unit> scanForUnitsBoard() {
//            /**DungeonsnDragons_ProjectOOP\src\GameBoard\GameBoard.java
//             * This function scans the board for units. It creates a 2D array of Units with the same size as the board.
//             * It then scans the board for units, and creates a new unit for each unit found. It then returns the 2D array
//             * of units.
//             * @return 2D array of Units representing the units on the board.
//             */
//            //Vector so we can know which enemies are close to the player and are accessible for attack using special ability
//            ArrayList<Unit> units = new ArrayList<Unit>();
//            for (int i = 0; i < board.length; i++){
//                for (int j = 0; j < board.length; j++) {
//                    Tile current = new Tile(board[i][j], new Position(i, j));
//                    if (current.getChar() != '.' || current.getChar() != '#') {
////                        Unit unit = UnitVisitor.visit(current);
////                        units.add(unit);
//                    }
//                }
//            }
////            return units;
//        }

//        private void gameTick(){
//            // TODO: implement
//        }
//        private void playerTurn () {
//            //TODO: implement
//        }
//        private void monsterTurn(){
//            // TODO: implement
//        }

    }
}