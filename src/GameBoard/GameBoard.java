package GameBoard;
import Controller.Messages.UnitMessageController;
import GameBoard.Levels.Level;
import GameBoard.Levels.LevelLoader;
import Patterns.Factory.TileFactory;
import Units.ADDITIONAL.Position;
import Units.AbstractsAndInterfaces.*;

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
    private int currentLevel = 0;
    private int countLines;
    private int countColumns;
    private Vector<Integer> playerChoice = new Vector<Integer>(); //implement more than one player
    private Vector<Player> players = new Vector<Player>(); // implement more than one player

    private Vector<Level> levels = new Vector<Level>();

    private int gameLoadingStage = 0; // 0 - Welcome Screen, 1 - Character Creating , 2 - Game Started

    private int gameTickCounter = 1;

    public UnitMessageController UMC;

    private LevelLoader levelLoader;


    public GameBoard(LevelLoader loader) {
        this.levelLoader = loader;
        this.UMC = new UnitMessageController();
        this.tilesOfBoard = new Vector<Unit>();
        this.turnSequence = new Vector<Unit>();
        this.levels = new Vector<Level>();
        this.players = new Vector<Player>();
        this.playerChoice = new Vector<Integer>();
    }

    public void setPlayerChoice(int playerChoice){
        this.playerChoice.add(playerChoice);
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


    public boolean playerIsDead(){
        return turnSequence.get(0).isDead();
    }

    public boolean playerIsAloneInTurnSequence(){
        return turnSequence.size() == 1 && turnSequence.get(0).getChar() == '@';
    }


    public void vectorGameTick(char playerInput){
        System.out.println("Game tick " + gameTickCounter + " started!"); // debugging use
        char currUnitAction;
        Position posBeforeAction;
        for (Unit unit : turnSequence) {
            if (!unit.isDead()) { // check if the unit is playable
                if (unit.getChar() == '@') {
                    currUnitAction = playerInput;
                    unit.onGameTick(turnSequence.get(0), getBoard());
                    System.out.println("Player health: " + unit.getHealthCurrent()); // debugging use
                } else {
                    currUnitAction = unit.onGameTick(players.get(0), getBoard()); // sending the player for everyone to seek
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
                        System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is attacking");
                        unit.acceptSA(tilesOfBoard.get(getBoard().indexOf(unit)), turnSequence);
                        System.out.println("Unit " + unit.getChar() + " is on position " + unit.getPosition() + " after attacking");
                        break;
                    case 'q':
                        continue;
                    case 'v':
                        System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing whatever a trap does");
                    case '\u0000':
                        continue;
                    default:
                        System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing nothing" + " because the input was " + currUnitAction);
                }
            }
            tilesOfBoard.sort((s,t) -> s.getPosition().compareTo(t.getPosition()));
            setDeadUnitsFromTurnSequenceAsEmptyOnBoard(); //
        }
        System.out.println("Game tick " + gameTickCounter++ + " ended!"); // debugging use
        removeDeadUnitsFromTurnSequence();
        if (playerIsAloneInTurnSequence() && levelLoader.isEmpty()) {
            System.out.println("You won!");
            System.exit(0);
        } else if (playerIsAloneInTurnSequence() && !levelLoader.isEmpty()) {
            getNextLevel();
        }
    }


    private void removeDeadUnitsFromTurnSequence() {
        turnSequence.removeIf(Unit::isDead);
    }

    private void getNextLevel(){
        loadNextLevel(levelLoader.loadNextLevel());
        incrementCurrentLevelCounter();
    }

    private void loadNextLevel(String[] levelText) {
        tilesOfBoard.clear();
        createNextLevel(levelText);
//        turnSequence.sort((s,t) -> s.getPosition().compareTo(t.getPosition()));
    }

    private void createNextLevel(String[] levelText) {
        tilesOfBoard.clear(); // this makes sure that the board is empty before loading the next level
        countColumns = levelText[0].length();
        countLines = levelText.length;
        for (int y = 0; y < levelText.length; y++) { // the number of lines
            for (int x = 0; x < levelText[y].length(); x++) { // the number of columns
                if (levelText[y].charAt(x) == '@') {
                    players.get(0).setPosition(new Position(x, y));
                    tilesOfBoard.add(players.get(0));
                } else {
                tilesOfBoard.add(tileFactory.produceTile(levelText[y].charAt(x), new Position(x, y)));
                }
            }
        }
        // recreate the turn sequence with the new units and the same player
        turnSequence.clear();
        for (Unit unit : tilesOfBoard) {
            if (unit.getChar() == '@') {
                turnSequence.add(0, players.get(0));
            } else if (unit.getChar() != '.' && unit.getChar() != '#') {
                turnSequence.add(unit);
            }
        }
    }

    public Vector<Unit> getBoard() {
        if (tilesOfBoard == null) {
            loadInitialLevel();
        }
        return tilesOfBoard;
    }

    public void incrementLevel() { // loads the next level to the vector on the board, creates turnSequence and sets the current level to ++1
        loadInitialLevel();
    }

    private void setDeadUnitsFromTurnSequenceAsEmptyOnBoard() {
        for (int i = 0; i < turnSequence.size(); i++) {
            if (turnSequence.get(i).isDead()) {
                if (turnSequence.get(i).getChar() == 'X') {
                    System.out.println("You died, game over");
                    // disable the ability of the player to play in the turn sequence -
                    // keep him in the game-board for visual purposes
                    turnSequence.setElementAt(tileFactory.produceEmpty(turnSequence.get(i).getPosition()), i);
                    break;
                }
                Position pos = turnSequence.get(i).getPosition();
                int x = pos.getX();
                int y = pos.getY();
                tilesOfBoard.setElementAt(tileFactory.produceEmpty(turnSequence.get(i).getPosition()), x + y * countColumns);
            }
        }
    }


    private void incrementCurrentLevelCounter() {
        this.currentLevel++;
    }

    public String toString(){
        return "current level " + currentLevel;
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


    public List<Unit> getTurnSequence() {
        return turnSequence;
    }

    private void loadInitialLevel() {  // the correct version that loads a vector of units to the tilesOfBoard vector
        String levelFile = "/src/GameBoard/Levels/level1Debug.txt";
        countLines = 0;
        countColumns = 0;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir + levelFile); // debugging use
        try (BufferedReader br = new BufferedReader(new FileReader(currentDir + levelFile))) {
            String line = br.readLine();
            // TODO: Look For the longets line in the file and set the countColumns to that number
            while (line != null) {
                if (countLines == 0) {
                    countColumns = line.length();
                }
                countLines++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        tileFactory = new TileFactory(playerChoice.get(0));
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
                Player player;
                for (int i = 0; i < countColumns; i++) {
                    if (sentence.charAt(i) == '@') {
                        player = tileFactory.producePlayer(playerChoice.get(0), new Position(i, lineCounter));
                        tilesOfBoard.add(player);
                        turnSequence.add(0, player); // the last player to pick a character is the first to play
                        players.add(player);
                    } else {
                        Unit unit = tileFactory.produceTile(sentence.charAt(i), new Position(i, lineCounter));
                        tilesOfBoard.add(unit);
                        if (unit.getChar() != '.' && unit.getChar() != '#') {
                            turnSequence.add(unit);
                        }
                    }
                }
                lineCounter++;
            }
            br.close(); // practice of good manners and hygiene while coding - close the file after you're done with it.
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("The level is " + countColumns + " columns wide and " + countLines + " lines long, level " + currentLevel);
    }
}