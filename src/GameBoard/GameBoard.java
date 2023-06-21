package GameBoard;
import GameBoard.Levels.Level;
import GameBoard.Levels.LevelLoader;
import Patterns.Factory.TileFactory;
import Units.ADDITIONAL.Position;
import Units.AbstractsAndInterfaces.*;

import java.util.List;
import java.util.Vector;

public class GameBoard {
    /**
     * This class represents the game board. It holds the board itself, and the turn sequence.
     * It also holds the current level.
     */
    private Vector<Enemy> turnSequence = new Vector<Enemy>();
    private Vector<Unit> tilesOfBoard;
    private TileFactory tileFactory;
    private int currentLevel = 0;
    private int countLines;
    private int countColumns;
    private Vector<Integer> playerChoice = new Vector<Integer>(); //implement more than one player
    private Vector<Player> players = new Vector<Player>(); // implement more than one player

    private Vector<Level> levels = new Vector<Level>();

    private int gameLoadingStage = 0; // 0 - Welcome Screen, 1 - Character Creating , 2 - Game Started

    private int gameTickCounter = 0;

    private LevelLoader levelLoader;

    private Vector<String> unitsCallbacks = new Vector<String>();

    public GameBoard(LevelLoader loader) {
        this.levelLoader = loader;
        this.tileFactory = tileFactory;
        this.tilesOfBoard = new Vector<Unit>();
        this.turnSequence = new Vector<Enemy>();
        this.levels = new Vector<Level>();
        this.players = new Vector<Player>();
        this.playerChoice = new Vector<Integer>();
    }

    public void setTileFactory(TileFactory tileFactory){
        this.tileFactory = tileFactory;
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



    public void vectorGameTick(char playerInput){
        System.out.println("Game tick " + ++gameTickCounter + " started!"); // debugging use
        char currUnitAction;

        // loop for players
        for (Player player : players) {
            if (!player.isDead()) {
                currUnitAction = playerInput;
                System.out.println("Player health: " + player.getHealthCurrent());
                unitActionPerformed(currUnitAction, player);
                player.onGameTick(players.get(0), getBoard());
            }
            tilesOfBoard.sort((s,t) -> s.getPosition().compareTo(t.getPosition()));
        }

        // loop for enemies
        for (Unit unit : turnSequence) {
            if (!unit.isDead()) { // check if the unit is playable, if so, play its turn
                // take unit action input and play the unit's tick
                currUnitAction = unit.onGameTick(players.get(0), getBoard()); // sending the player for everyone to seek
                unitActionPerformed(currUnitAction, unit);
            }
            tilesOfBoard.sort((s,t) -> s.getPosition().compareTo(t.getPosition()));
            tilesOfBoard.replaceAll(unitToCheck -> unitToCheck.isDead() && unitToCheck.getChar() != '@' ? tileFactory.produceEmpty(unitToCheck.getPosition()) : unitToCheck); // replace dead units with empty tiles on board
        }
        System.out.println("Game tick " + gameTickCounter + " ended!"); // debugging use

        removeDeadUnitsFromTurnSequence();

        if (turnSequence.isEmpty() && !levelLoader.isEmpty()) {
            loadNextLevel();
        }
    }


    private void unitActionPerformed(char unitInput, Unit unit){
        Position posBeforeAction = unit.getPosition();
        switch (unitInput) {
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
                unit.castAbility(turnSequence);
                System.out.println("Unit " + unit.getChar() + " is on position " + unit.getPosition() + " after attacking");
                break;
            case 'q': // skip basically...
            case 'v':
                System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing whatever a trap does");
            default:
                System.out.println("Unit " + unit.getChar() + " is on position " + posBeforeAction + " and is doing nothing because the input was " + unitInput);
        }
    }


    private void removeDeadUnitsFromTurnSequence() {
        turnSequence.removeIf(Unit::isDead);
    }

    public void loadNextLevel(){
        loadNextLevel(levelLoader.loadNextLevel());
        incrementCurrentLevelCounter();
    }

    private void loadNextLevel(String[] levelText) {
        tilesOfBoard.clear();
        createNextLevel(levelText);
    }

    private void createNextLevel(String[] levelText) {
        tilesOfBoard.clear(); // this makes sure that the board is empty before loading the next level
        countColumns = levelText[0].length();
        countLines = levelText.length;
        if (gameTickCounter == 0) {
            players.add(tileFactory.producePlayer(playerChoice.get(0), new Position(0, 0)));
        }
        // to add more players, make a variable that increments with each player that needs to be added
        turnSequence.clear();
        for (int y = 0; y < levelText.length; y++) { // the number of lines
            for (int x = 0; x < levelText[y].length(); x++) { // the number of columns
                if (levelText[y].charAt(x) == '@') {
                    players.get(0).setPosition(new Position(x, y));
                    tilesOfBoard.add(players.get(0));
                } else if (levelText[y].charAt(x) != '#' && levelText[y].charAt(x) != '.') {
                    Enemy enemy = tileFactory.produceEnemy(levelText[y].charAt(x), new Position(x, y));
                    tilesOfBoard.add(enemy);
                    turnSequence.add(enemy);
                } else
                tilesOfBoard.add(tileFactory.produceTile(levelText[y].charAt(x), new Position(x, y)));
                }
            }
        }



    public Vector<Unit> getBoard() {
        return tilesOfBoard;
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
        return levelLoader.getLevels();
    }

    public Vector<Player> getPlayers() {
        return players;
    }


    public List<Enemy> getTurnSequence() {
        return turnSequence;
    }

    public int getGameTickCounter() {
        return gameTickCounter;
    }



}