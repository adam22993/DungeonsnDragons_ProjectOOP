package GameBoard;
import Patterns.Factory.TileFactory;
import UI.GameUI;
import UI.Inputs.ButtonSelectionListener;
import Units.ADDITIONAL.Position;
import Units.Abstracts.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class GameBoard {
    /**
     * This class represents the game board. It holds the board itself, and the turn sequence.
     * It also holds the current level.
     */
    Vector<Unit> turnSequence = new Vector<Unit>();
    GameUI gameUI = new GameUI();
    TileFactory tileFactory;
    int current_level = 0;

    Tile[][] board;

    public GameBoard() {
        this.board = loadCurrentLevelBoard(current_level);

        gameUI.openWelcomeScreen();
    }


//    public void createGameUI() {
//        boardPanel = new JPanel();
//        boardPanel.setBounds(100, 100, 600, 250);
//        boardPanel.setBackground(Color.black);
//        con.add(boardPanel);
//    }


    private Tile[][] loadCurrentLevelBoard(int current_level) {
        /*re
         * This function loads the board from the file. Using the current_level variable, it loads the file of
         * the current level. It then counts the number of lines and columns in the file, and creates a 2D array
         * of chars with the size of the board. It then loads the board from the file into the 2D array.
         * @return 2D array of chars representing the board.
         */

        //TODO: change to fit tiles and not print the board

        String levelFile = "/src/GameBoard/Levels/level" + (current_level + 1) + ".txt";
        //String levelFile = "\\src\\GameBoard\\Levels\\level" + (current_level + 1) + ".txt"; // for some reason this works on windows only, mac needs the /src/ instead of \\src\\ and windows still works with /src/.
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

        Tile[][] tiles = new Tile[countLines][countColumns];
        tileFactory = new TileFactory(1);
        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                for (int j = 0; j < countColumns; j++) {
                    char Char = line.charAt(j);
                    if(Char == '.'){
                        tiles[i][j] = tileFactory.produceEmpty(new Position(i, j));
                    } else if(Char == '#'){
                        tiles[i][j] = tileFactory.produceWall(new Position(i, j));
                    } else if (Char == '@'){
                        tiles[i][j] = tileFactory.producePlayer(1, new Position(i, j));  // TODO: change to playerSelected
                        turnSequence.insertElementAt(tiles[i][j].getUnit(), 0); // add player to the beginning of the turn sequence
                    } else if (tileFactory.getEnemiesMap().containsKey(Char)){
                        tiles[i][j] = tileFactory.produceEnemy(Char, new Position(i, j));
                        turnSequence.add(tiles[i][j].getUnit());
                    } else {
                        tiles[i][j] = tileFactory.produceEmpty(new Position(i, j)); // nothing found to create, so create an empty tile. - this should never happen - this is for damage control.
                    }

                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        current_level++;
        System.out.println(turnSequence);
        return tiles;
    }

    public void printBoard() {
        /**
         * This function prints the board to the console. It iterates through the 2D array of chars representing
         * the board, and prints each char to the console.
         */
        //TODO: change to fit tiles and not print the board
        for (Tile[] row : board) {
            for (Tile c : row) {
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
        for (Tile[] row : board) {
            for (Tile c : row) {
                boardString.append(c);
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }



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

        private void gameTick(){
            for (Unit unit : turnSequence) {
//                unit.onGameTick(); // this is an error because Unit is abstract - its 1:30 am and I'm tired, I'll fix it tomorrow - fuck you all
            }
        }


}