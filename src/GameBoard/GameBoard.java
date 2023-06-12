package GameBoard;
import GameBoard.Levels.Level;
import Patterns.Factory.TileFactory;
import Units.ADDITIONAL.Position;
import Units.Abstracts.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class GameBoard {
    /**
     * This class represents the game board. It holds the board itself, and the turn sequence.
     * It also holds the current level.
     */
    private Vector<Unit> turnSequence = new Vector<Unit>();
    private Vector<Unit> tilesOfBoard;
    private TileFactory tileFactory;
    private int current_level = 0;

    private int countLines;
    private int countColumns;
    private Unit[][] board;
    private String playerName;
    private Vector<Integer> playerChoice = new Vector<Integer>();
    private Vector<Player> players = new Vector<Player>(); // implement more than one player

    private Vector<Level> levels = new Vector<Level>();

    private int gameLoadingStage = 0; // 0 - Welcome Screen, 1 - Character Creating , 2 - Game Started

    private int gameTickCounter = 1;


    public GameBoard() {}
    public void setPlayerName(String player){
        this.playerName = player;
    }

    public void setPlayerChoice(int playerChoice){
        this.playerChoice.add(playerChoice);
    }


//    public Tile[][] loadCurrentLevelBoard(int current_level) {
//        /**
//         * This function loads the board from the file. Using the current_level variable, it loads the file of
//         * the current level. It then counts the number of lines and columns in the file, and creates a 2D array
//         * of chars with the size of the board. It then loads the board from the file into the 2D array.
//         * @return 2D array of chars representing the board.
//         */
//
//        //TODO: load all levels as files, load all of the levels into a level class and then load the levels as indexes
//        //TODO: this allows us to receive and not receive levels from the tester.
//
//        String levelFile = "/src/GameBoard/Levels/level" + (current_level + 1) + ".txt";
//        //String levelFile = "\\src\\GameBoard\\Levels\\level" + (current_level + 1) + ".txt"; // for some reason this works on windows only, mac needs the /src/ instead of \\src\\ and windows still works with /src/.
//        countLines = 0;
//        countColumns = 0;
//        String currentDir = System.getProperty("user.dir");
//        System.out.println("Current working directory: " + currentDir + levelFile);
//        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
//            // TODO: Look For the longest line in the file and set the countColumns to that number
//            while (br.readLine() != null) {
//                countLines++;
//            }
//        } catch (IOException e) {
//            System.err.format("IOException: %s%n", e);
//        }
//
//        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
//            String line;
//            if ((line = br.readLine()) != null) {
//                countColumns = line.length();
//            }
//        } catch (IOException e) {
//            System.err.format("IOException: %s%n", e);
//        }
//
//        Unit[][] tiles = new Unit[countLines][countColumns];
//        tileFactory = new TileFactory(1);
//        try (BufferedReader br = new BufferedReader(new
//                FileReader(currentDir + levelFile))) {
//            String line;
//            int i = 0;
//            while ((line = br.readLine()) != null) {
//                for (int j = 0; j < countColumns; j++) {
//                    char Char = line.charAt(j);
//                    if(Char == '.'){
//                        tiles[i][j] = tileFactory.produceEmpty(new Position(i, j));
//                    } else if(Char == '#'){
//                        tiles[i][j] = tileFactory.produceWall(new Position(i, j));
//                    } else if (Char == '@'){
//                        tiles[i][j] = tileFactory.producePlayer(playerChoice.remove(0), new Position(i, j));  // TODO: change to playerSelected
//                        turnSequence.insertElementAt(tiles[i][j], 0); // add player to the beginning of the turn sequence
//                    } else if (tileFactory.getEnemiesMap().containsKey(Char)){
//                        tiles[i][j] = tileFactory.produceEnemy(Char, new Position(i, j));
//                        turnSequence.add(tiles[i][j]);
//                    } else {
//                        tiles[i][j] = tileFactory.produceEmpty(new Position(i, j)); // nothing found to create, so create an empty tile. - this should never happen - this is for damage control.
//                    }
//
//                }
//                i++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        current_level++;
//        System.out.println(turnSequence);
//        System.out.println("Level " + current_level + " loaded successfully!");
//        this.board = tiles;
////        printBoard();
//        return tiles;
//    }

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
        for (int i = 0; i < countLines; i++) {
            for (int j = 0; j < countColumns; j++) {
                boardString.append(tilesOfBoard.get(i * countColumns + j));
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    public Tile getTile(Position position) {
        /**
         * This function returns the tile in the given position.
         * @param position Position of the tile to return.
         * @return Tile in the given position.
         */
        return board[position.getX()][position.getY()];
    }


    //        private Vector<Unit> scanForUnitsBoard() {
/*TODO: This is used to scan for the player by the enemies and helps the player select the enemy to attack with the special ability.
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
    public boolean playerIsDead(){
        return turnSequence.get(0).getHealthPool() <= 0;
    }

    public boolean playerIsAloneInTurnSequence(){
        return turnSequence.size() == 1 && turnSequence.get(0).getChar() == '@';
    }

//    public void gameTick(char playerInput){
//        System.out.println("Game tick " + gameTickCounter++ + " started!");
//        char temp;
//        Position posBeforeAction;
//        for (Unit unit : turnSequence) {
//            if (unit.getChar() == '@') {
//                temp = playerInput;
//            } else {
//                temp = unit.onGameTick(turnSequence.get(0).getPosition(), getBoardArray());
//            }
//            posBeforeAction = unit.getPosition();
//            switch (temp) {
//                case 'w':
//                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving up"); // debugging use
//                    unit.accept(board[unit.getPosition().getY() - 1][unit.getPosition().getX()]);
//                    break;
//                case 'a':
//                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving left");
//                    unit.accept(board[unit.getPosition().getY()][unit.getPosition().getX() - 1]);
//                    break;
//                case 's':
//                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving down");
//                    unit.accept(board[unit.getPosition().getY() + 1][unit.getPosition().getX()]);
//                    break;
//                case 'd':
//                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving right");
//                    unit.accept(board[unit.getPosition().getY()][unit.getPosition().getX() + 1]);
//                    break;
//                case 'e':
//                    continue;
//                case 'q':
//                    continue;
//                case 'v':
//                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing whatever a trap does");
//                case '\u0000':
//                    continue;
//            }
//            try{
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            updateBoard(new Position(unit.getPosition().getY(),unit.getPosition().getX()), posBeforeAction);
//            checkForDeathsInTurnSequence();
//        }
//        setDeadUnitsFromTurnSequenceAsEmpty();
//    }
//
//
//
//
//
//
    public void vectorGameTick(char playerInput){
        System.out.println("Game tick " + gameTickCounter++ + " started!");
        char currUnitAction;
        Position posBeforeAction;
        for (Unit unit : turnSequence) {
            if (unit.getChar() == '@') {
                currUnitAction = playerInput;
                System.out.println("Player health: " + unit.getHealthAmount());
            } else {
                currUnitAction = unit.onGameTick(turnSequence.get(0).getPosition(), getBoard());
            }
            posBeforeAction = unit.getPosition();
            switch (currUnitAction) {
                case 'w':
                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving up"); // debugging use
                    unit.accept(tilesOfBoard.get(getBoard().indexOf(unit) - countColumns));
                    System.out.println("Unit " + unit.getChar() + " is on position " + unit.getPosition() + " after moving up");
                    break;
                case 'a':
                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving left");
                    unit.accept(tilesOfBoard.get(getBoard().indexOf(unit) - 1));
                    System.out.println("Unit " + unit.getChar() + " is on position " + unit.getPosition() + " after moving left");
                    break;
                case 's':
                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving down");
                    unit.accept(tilesOfBoard.get(getBoard().indexOf(unit) + countColumns));
                    System.out.println("Unit " + unit.getChar() + " is on position " + unit.getPosition() + " after moving down");
                    break;
                case 'd':
                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is moving right");
                    unit.accept(tilesOfBoard.get(getBoard().indexOf(unit) + 1));
                    System.out.println("Unit " + unit.getChar() + " is on position " + unit.getPosition() + " after moving right");
                    break;
                case 'e':
                    continue;
                case 'q':
                    continue;
                case 'v':
                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing whatever a trap does");
                case '\u0000':
                    continue;
                default:
                    System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing nothing" + " because the input was " + currUnitAction);
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tilesOfBoard.sort((s,t) -> s.getPosition().compareTo(t.getPosition()));
            setDeadUnitsFromTurnSequenceAsEmpty();
        }
//        setDeadUnitsFromTurnSequenceAsEmpty();
    }











    private void loadLevel(int level) {
        String levelFile = "/src/GameBoard/Levels/level" + (current_level) + ".txt";
        //String levelFile = "\\src\\GameBoard\\Levels\\level" + (current_level + 1) + ".txt"; // for some reason this works on windows only, mac needs the /src/ instead of \\src\\ and windows still works with /src/.
        countLines = 0;
        countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir + levelFile);
        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            // TODO: Look For the longets line in the file and set the countColumns to that number
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
        tileFactory = new TileFactory(1);
        tilesOfBoard = new Vector<Unit>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(currentDir + levelFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sentence;
        int lineCounter = 0;
        try {
            while ((sentence = br.readLine()) != null) {
                for (int i = 0; i < countColumns; i++) {
                    tilesOfBoard.add(tileFactory.produceTile(sentence.charAt(i), new Position(i, lineCounter)));
                    char Char = sentence.charAt(i);
                    if (Char == '@') {
                        turnSequence.add(0, tilesOfBoard.get(tilesOfBoard.size() - 1));
                    } else if (Char != '.' && Char != '#') {
                        turnSequence.add(tilesOfBoard.get(tilesOfBoard.size() - 1));
                    }
                }
                lineCounter++;
            }
            br.close(); // practice of good manners and hygiene while coding - close the file after you're done with it.
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    public Vector<Unit> getBoard() {
        if (tilesOfBoard == null) {
            loadLevel(current_level);
        }
        return tilesOfBoard;
    }

    public void incrementLevel() {
        loadLevel(++current_level);
    }

    private Unit getTileByPosition(Position position) {
        return board[position.getY()][position.getX()];
    }
    private void updateBoard(Position posAfterAction, Position posBeforeAction) {
        if (posAfterAction.getX() != posBeforeAction.getX() || posAfterAction.getY() != posBeforeAction.getY()) {
            Unit temp = getTileByPosition(posAfterAction);
            board[posAfterAction.getY()][posAfterAction.getX()] = getTileByPosition(posBeforeAction);
            board[posBeforeAction.getY()][posBeforeAction.getX()] = temp;
        }
    }

    private void checkForDeathsInTurnSequence() {
        for (Unit unit : this.turnSequence)
            if (unit.getHealthAmount() <= 0) {
                board[unit.getPosition().getY()][unit.getPosition().getX()] = tileFactory.produceEmpty(unit.getPosition()); //TODO: change to tilesOfBoard

            }
    }

    private void setDeadUnitsFromTurnSequenceAsEmpty() {
        for (int i = 0; i < turnSequence.size() - 1; i++) {
            if (turnSequence.get(i).getHealthAmount() <= 0) {
                Position pos = turnSequence.get(i).getPosition();
                int x = pos.getX();
                int y = pos.getY();
                tilesOfBoard.setElementAt(tileFactory.produceEmpty(turnSequence.get(i).getPosition()), x + y * countColumns);
                turnSequence.setElementAt(tileFactory.produceEmpty(turnSequence.get(i).getPosition()), i);

            }
        }
    }

    public int getCurrentLevelCounter(){
        return this.current_level;
    }


    public void incrementCurrentLevelCounter() {
        this.current_level++;
    }
    public String toString(){
        return "current level " + current_level;
    }

    public void incrementGameLoadingStage(){
        this.gameLoadingStage++;
    }
    public int getGameLoadingStage(){
        return this.gameLoadingStage;
    }

    public Vector<Level> getLevels() {
        return levels;
    }

    public Vector<Player> getPlayers() {
        return players;
    }

    private Tile[][] getBoardArray() {
        return board;
    }

    public List<Unit> getTurnSequence() {
        return turnSequence;
    }
}