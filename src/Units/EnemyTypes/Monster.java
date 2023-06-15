package Units.EnemyTypes;

import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.Position;
import Units.AbstractsAndInterfaces.Enemy;
import Units.AbstractsAndInterfaces.Tile;
import Units.AbstractsAndInterfaces.Unit;

import java.util.Random;
import java.util.Vector;

public class Monster extends Enemy  {
    int experienceValue;
    UnitMessageController unitMessageController;
    public Monster(char Char, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, int experienceValue, int visionRange, UnitMessageController UMC) {
        super(Char, name, healthPool, attackPoints, defensePoints ,visionRange , experienceValue);
        this.experienceValue = experienceValue;
        this.unitMessageController = UMC;
    }

    @Override
    public char onGameTick(Unit player, Vector<Unit> units) {
        if (this.getPosition().Range(player.getPosition()) <= this.visionRange && player.getChar() == '@') {
            return this.moveTowardsPlayer(player.getPosition(), units);
        }
        switch (new Random().nextInt(4)) {
            case 0:
                return 's';
            case 1:
                return 'd';
            case 2:
                return 'a';
            case 3:
                return 'w';
        }
        return 's';
    }
    public char moveTowardsPlayer(Position playerPosition, Vector<Unit> units) {
        int countCols = 0;
        for (Unit unit : units) {
            if (unit.getPosition().getY() == 0) {
                countCols++;
            } else {
            break;
            }
        }
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        int playerX = playerPosition.getX();
        int playerY = playerPosition.getY();

        if (playerX > x && units.get(y * countCols + x + 1).getChar() == '.' || units.get(y * countCols + x + 1).getChar() == '@'){
            return 'd';
        }
        if (playerX < x && units.get(y * countCols + x - 1).getChar() == '.' || units.get(y * countCols + x - 1).getChar() == '@'){
            return 'a';
        }
        if (playerY > y && units.get((y+1) * countCols + x).getChar() == '.' || units.get((y+1) * countCols + x).getChar() == '@'){
            return 's';
        }
        if (playerY < y && units.get((y-1) * countCols + x).getChar() == '.' || units.get((y-1) * countCols + x).getChar() == '@'){
            return 'w';
        }
        return 'o';   //findBestWay(playerPosition, Surroundings);
    }

    private char findBestWay(Position playerPosition, Tile[][] Surroundings){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        int playerX = playerPosition.getX();
        int playerY = playerPosition.getY();
        char firstStep = 0;
        if (playerX > x && Surroundings[y][x-1].getChar() == '.'){
            firstStep = 'd';
        }
        if (playerX < x && Surroundings[y][x-1].getChar() == '.'){
            firstStep = 'a';
        }
        if (playerY > y && Surroundings[y+1][x].getChar() == '.'){
            firstStep = 's';
        }
        if (playerY < y && Surroundings[y-1][x].getChar() == '.'){
            firstStep = 'w';
        }
        return findFirstStepToMonster(this.getPosition(), Surroundings, playerPosition, firstStep , 0, this.getChar());
    }

    private char findFirstStepToPlayer(Position playerPosition, Tile[][] surroundings, Position currentPosition, char firstStep, int counter) {
        if (checkIfNextStepEncountersPlayer(playerPosition, surroundings)) {
            return firstStep;
        }
        // check if stepping right is closer to player and if possible - if so, delve deeper
        if (playerPosition.Range(new Position(currentPosition.getY(), currentPosition.getX() + 1)) < playerPosition.Range(new Position(currentPosition.getY(), currentPosition.getX())) && surroundings[currentPosition.getY()][currentPosition.getX() + 1].getChar() == '.') {
            return findFirstStepToPlayer(playerPosition, surroundings, new Position(currentPosition.getY(), currentPosition.getX() + 1), 'd', counter + 1);
        }
        // check if stepping left is closer to player and if possible - if so, delve deeper
        if (playerPosition.Range(new Position(currentPosition.getY(), currentPosition.getX() - 1)) < playerPosition.Range(new Position(currentPosition.getY(), currentPosition.getX())) && surroundings[currentPosition.getY()][currentPosition.getX() - 1].getChar() == '.') {
            return findFirstStepToPlayer(playerPosition, surroundings, new Position(currentPosition.getY(), currentPosition.getX() - 1), 'a', counter + 1);
        }
        // check if stepping down is closer to player and if possible - if so, delve deeper
        if (playerPosition.Range(new Position(currentPosition.getY() + 1, currentPosition.getX())) < playerPosition.Range(new Position(currentPosition.getY(), currentPosition.getX())) && surroundings[currentPosition.getY() + 1][currentPosition.getX()].getChar() == '.') {
            return findFirstStepToPlayer(playerPosition, surroundings, new Position(currentPosition.getY() + 1, currentPosition.getX()), 's', counter + 1);
        }
        // check if stepping up is closer to player and if possible - if so, delve deeper
        if (playerPosition.Range(new Position(currentPosition.getY() - 1, currentPosition.getX())) < playerPosition.Range(new Position(currentPosition.getY(), currentPosition.getX())) && surroundings[currentPosition.getY() - 1][currentPosition.getX()].getChar() == '.') {
            return findFirstStepToPlayer(playerPosition, surroundings, new Position(currentPosition.getY() - 1, currentPosition.getX()), 'w', counter + 1);
        }
        return 0;
    }

    private char findFirstStepToMonster(Position monsterPosition, Tile[][] surroundings, Position currentPosition, char lastStep, int counter, char monsterChar) {
        if (checkIfNextStepEncountersMonster(monsterPosition, surroundings, monsterChar)) {
            return lastStep;
        }

        Position[] possibleMoves = {
                new Position(currentPosition.getY(), currentPosition.getX() + 1),  // Right
                new Position(currentPosition.getY(), currentPosition.getX() - 1),  // Left
                new Position(currentPosition.getY() + 1, currentPosition.getX()),  // Down
                new Position(currentPosition.getY() - 1, currentPosition.getX())   // Up
        };

        char[] moveDirections = {'d', 'a', 's', 'w'};
        double minDistance = Integer.MAX_VALUE;
        char bestMove = lastStep;

        for (int i = 0; i < possibleMoves.length; i++) {
            Position move = possibleMoves[i];
            if (isValidMove(move, surroundings)) {
                double distance = monsterPosition.Range(move);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestMove = moveDirections[i];
                }
            }
        }
        return findFirstStepToMonster(monsterPosition, surroundings, possibleMoves[getIndex(moveDirections, bestMove)], bestMove, counter + 1, monsterChar);
    }

    private int getIndex(char[] array, char value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1; // Return a default value indicating not found
    }

    private boolean isValidMove(Position position, Tile[][] surroundings) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < surroundings[0].length && y >= 0 && y < surroundings.length && surroundings[y][x].getChar() == '.';
    }

    private boolean checkIfNextStepEncountersMonster(Position monsterPosition, Tile[][] surroundings, char monsterChar) {
        int x = monsterPosition.getX();
        int y = monsterPosition.getY();
        return (surroundings[y][x + 1].getChar() == monsterChar && x + 1 < surroundings[0].length)
                || (surroundings[y][x - 1].getChar() == monsterChar && x - 1 >= 0)
                || (surroundings[y + 1][x].getChar() == monsterChar && y + 1 < surroundings.length)
                || (surroundings[y - 1][x].getChar() == monsterChar && y - 1 >= 0);
    }
    private boolean checkIfNextStepEncountersPlayer(Position playerPosition, Tile[][] surroundings){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        int playerX = playerPosition.getX();
        int playerY = playerPosition.getY();
        return playerX > x && surroundings[y][x + 1].getChar() == '@' || playerY < y && surroundings[y - 1][x].getChar() == '@' || playerY > y && surroundings[y + 1][x].getChar() == '@' || playerX < x && surroundings[y][x - 1].getChar() == '@';
    }


    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }
}
