package Tests;
import Controller.Messages.MessageCallback;
import GameBoard.GameBoard;
import GameBoard.Levels.LevelLoader;
import Patterns.Factory.TileFactory;
import Units.ADDITIONAL.Position;
import Units.AbstractsAndInterfaces.*;
import Units.EnemyTypes.Monster;
import Units.EnemyTypes.Trap;
import Units.PlayerClasses.Mage;
import Units.PlayerClasses.Rogue;
import Units.PlayerClasses.Warrior;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.util.Vector;

import static org.junit.Assert.*;

public class UnitTests {
    // first we are going to initialize a gameboard to test on and create a tick counter to follow the game ticks
    private LevelLoader testingLevelLoader = new LevelLoader("src/Tests/Levels");
    private GameBoard gameBoard;
    private int tickCounter = 0;
    private Player player;
    private Vector<Enemy> enemies;
    private TileFactory tileFactory = new TileFactory(getMessageCallbackTest());

    public MessageCallback getMessageCallbackTest(){
        return this::displayMessage;
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }


    /* we are going to test the features by creating levels that test each feature
     * we are going to test the following features:
     * 1. the attack interaction and walk interaction + wall interaction of both the player and the enemies
     * 2. each character's ability
     * 3. the level up of the classes
     *
     * tests also check the ability of the tile factory to produce the correct tiles
     */



    @Test
    public void testWallInteraction() {
        // first we are going to create the level, the player and the enemy
        player = tileFactory.producePlayer(0, new Position(0, 0));
        Unit unit = tileFactory.produceEnemy('s', new Position(0, 2));
        Unit wall = tileFactory.produceWall(new Position(0, 1));
        player.accept(wall);
        unit.accept(wall);
        // now we are going to test the interaction - position should not change
        assertEquals(0, player.getPosition().getX());
        assertEquals(0, player.getPosition().getY());
        assertEquals(0, unit.getPosition().getX());
        assertEquals(2, unit.getPosition().getY());
        assertEquals(0, wall.getPosition().getX());
        assertEquals(1, wall.getPosition().getY());
    }

    @Test
    public void testEmptyTiles(){
        player = tileFactory.producePlayer(0, new Position(0, 0));
        Unit empty = tileFactory.produceEmpty(new Position(0, 1));
        Unit unit = tileFactory.produceEnemy('s', new Position(0, 2));
        Unit empty2 = tileFactory.produceEmpty(new Position(0, 3));
        player.accept(empty);
        unit.accept(empty2);
        // now we are going to test the interaction - position should change
        assertEquals(0, player.getPosition().getX());
        assertEquals(1, player.getPosition().getY());
        assertEquals(0, empty.getPosition().getX());
        assertEquals(0, empty.getPosition().getY());
        assertEquals(0, unit.getPosition().getX());
        assertEquals(3, unit.getPosition().getY());
        assertEquals(0, empty2.getPosition().getX());
        assertEquals(2, empty2.getPosition().getY());
    }

    @Test
    public void testAttackInteraction() {
        // first we are going to create the level, the player and the enemy
        player = tileFactory.producePlayer(0, new Position(0, 0));
        Unit unit = tileFactory.produceEnemy('B', new Position(0, 1));
        while (!unit.isDead()) {
            player.accept(unit);
        }

        // the player should have killed the enemy trap and moved to its position
        assertEquals(0, player.getPosition().getX());
        assertEquals(1, player.getPosition().getY());
        assertFalse(player.isDead()); // the player should not be dead
        assertTrue(unit.isDead()); // the enemy should be dead
        assertEquals(0, unit.getPosition().getX());
        assertEquals(0, unit.getPosition().getY());
        // dead enemies get removed from the gameboard by the gameboard - not tested here
    }

    @Test
    public void testSpecialAbilities() {
        // first we are going to create the level, the player and the enemy
        player = tileFactory.producePlayer(0, new Position(0, 0));
        Enemy unit = tileFactory.produceEnemy('B', new Position(0, 1));
        Vector<Enemy> enemies = new Vector<>();
        enemies.add(unit);

        assertEquals(player.getLevel(), 1); // the player should be level 1
        player.castAbility(enemies);
        // the player should have killed the enemy trap and not moved to its position
        assertEquals(0, player.getPosition().getX());
        assertEquals(0, player.getPosition().getY());
        assertFalse(player.isDead()); // the player should not be dead
        assertTrue(unit.isDead()); // the enemy should be dead
        assertEquals(0, unit.getPosition().getX());
        assertEquals(1, unit.getPosition().getY());

        // now we are going to test the mage's ability
        player = tileFactory.producePlayer(2, new Position(7, 0));
        unit = tileFactory.produceEnemy('B', new Position(7, 1));
        enemies = new Vector<>();
        enemies.add(unit);
        player.castAbility(enemies);
        // the player should have killed the enemy trap and not moved to its position
        assertEquals(7, player.getPosition().getX());
        assertEquals(0, player.getPosition().getY());
        assertFalse(player.isDead()); // the player should not be dead
        assertTrue(unit.isDead()); // the enemy should be dead
        assertEquals(7, unit.getPosition().getX());
        assertEquals(1, unit.getPosition().getY());


        // now we are going to test the rogue's ability
        player = tileFactory.producePlayer(4, new Position(15, 0));
        unit = tileFactory.produceEnemy('B', new Position(15, 1));
        enemies = new Vector<>();
        enemies.add(unit);
        player.castAbility(enemies);
        // the player should have killed the enemy trap and not moved to its position
        assertEquals(15, player.getPosition().getX());
        assertEquals(0, player.getPosition().getY());
        assertFalse(player.isDead()); // the player should not be dead
        assertTrue(unit.isDead()); // the enemy should be dead
        assertEquals(15, unit.getPosition().getX());
        assertEquals(1, unit.getPosition().getY());

    }

    @Test
    public void testLevelUpAndStatsGainingANDResourceAfterTickAllClasses(){
        // first we are going to create the level, the player and the enemy
        Mage melisandre = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6, getMessageCallbackTest());
        Enemy unit = tileFactory.produceEnemy('B', new Position(0, 1));
        Vector<Enemy> enemies = new Vector<>();
        Vector<Unit> units = new Vector<>(); // for testing purposes
        enemies.add(unit);

        assertEquals(1, melisandre.getLevel()); // the player should be level 1
        assertEquals(5, melisandre.getAttackPoints()); // the player should have 5 attack
        assertEquals(1, melisandre.getDefensePoints()); // the player should have 1 defense
        assertEquals(100, melisandre.getHealthPool()); // the player should have 300 health
        assertEquals(100, melisandre.getHealthCurrent()); // the player should have 300 health
        assertEquals(0, melisandre.getCurrEXP()); // the player should have 0 experience
        assertEquals(75, melisandre.getCurrMana());
        assertEquals(300, melisandre.getMaxMana());
        assertEquals(15, melisandre.getSpellPower());
        assertEquals(30, melisandre.getManaCost());
        assertEquals(6, melisandre.getAbilityRange());
        assertEquals(5, melisandre.getHitCount());

        melisandre.castAbility(enemies);

        assertEquals(3, melisandre.getLevel()); // the player should be level 3 after leveling up twice
        assertEquals(25, melisandre.getAttackPoints()); // the player should have 10 attack
        assertEquals(6, melisandre.getDefensePoints()); // the player should have 3 defense
        assertEquals(150, melisandre.getHealthPool()); // the player should have 300 health
        assertEquals(150, melisandre.getHealthCurrent()); // the player should have 300 health
        assertEquals(100, melisandre.getCurrEXP()); // the player should have 100 experience, 50 more for levelup
        assertEquals(238, melisandre.getCurrMana());
        assertEquals(425, melisandre.getMaxMana());
        assertEquals(65, melisandre.getSpellPower());
        assertEquals(30, melisandre.getManaCost());
        assertEquals(6, melisandre.getAbilityRange());
        assertEquals(5, melisandre.getHitCount());

        melisandre.onGameTick(melisandre, units);
        assertEquals(241, melisandre.getCurrMana());


        Warrior theHound = new Warrior("The Hound", 400, 20, 6, 5, getMessageCallbackTest());
        unit.setHealthAmount(1); // reset units health
        assertEquals(1, theHound.getLevel()); // the player should be level 1
        assertEquals(20, theHound.getAttackPoints()); // the player should have 20 attack
        assertEquals(6, theHound.getDefensePoints()); // the player should have 6 defense
        assertEquals(400, theHound.getHealthPool()); // the player should have 400 health
        assertEquals(400, theHound.getHealthCurrent()); // the player should have 400 health
        assertEquals(0, theHound.getCurrEXP()); // the player should have 0 experience
        assertEquals(0, theHound.getCurrCD());
        assertEquals(6, theHound.getMaxCD()); // initialized as max + 1 in ABCD constructor
        theHound.castAbility(enemies);

        assertEquals(3, theHound.getLevel()); // the player should be level 3
        assertEquals(50, theHound.getAttackPoints()); // the player should have 40 attack
        assertEquals(16, theHound.getDefensePoints()); // the player should have 12 defense
        assertEquals(475, theHound.getHealthPool()); // the player should have 600 health
        assertEquals(475, theHound.getHealthCurrent()); // the player should have 600 health
        assertEquals(100, theHound.getCurrEXP()); // the player should have 100 experience, 50 more for levelup
        assertEquals(0, theHound.getCurrCD());
        assertEquals(6, theHound.getMaxCD());
        theHound.castAbility(enemies);
        theHound.onGameTick(theHound, units);
        assertEquals(5, theHound.getCurrCD());


        Rogue arya = new Rogue("Arya", 100, 20, 2, 20, getMessageCallbackTest());
        unit.setHealthAmount(1); // reset units health
        assertEquals(1, arya.getLevel()); // the player should be level 1
        assertEquals(20, arya.getAttackPoints()); // the player should have 20 attack
        assertEquals(2, arya.getDefensePoints()); // the player should have 2 defense
        assertEquals(100, arya.getHealthPool()); // the player should have 100 health
        assertEquals(100, arya.getHealthCurrent()); // the player should have 100 health
        assertEquals(0, arya.getCurrEXP()); // the player should have 0 experience
        assertEquals(100, arya.getCurrEnergy());
        assertEquals(100, arya.getMaxEnergy());
        assertEquals(20, arya.getCastCost());
        arya.castAbility(enemies);

        assertEquals(3, arya.getLevel()); // the player should be level 3
        assertEquals(55, arya.getAttackPoints()); // the player should have 40 attack
        assertEquals(7, arya.getDefensePoints()); // the player should have 4 defense
        assertEquals(150, arya.getHealthPool()); // the player should have 200 health
        assertEquals(150, arya.getHealthCurrent()); // the player should have 200 health
        assertEquals(100, arya.getCurrEXP()); // the player should have 100 experience, 50 more for levelup
        assertEquals(100, arya.getCurrEnergy());
        assertEquals(100, arya.getMaxEnergy());
        assertEquals(20, arya.getCastCost()); // no change in cast cost

        arya.castAbility(enemies);
        arya.onGameTick(arya, units);
        assertEquals(90, arya.getCurrEnergy());
    }

    @Test
    public void testTrapDisappearing(){
        // we will create a trap and let it play its tick, it should disappear after the tick and reappear after 5 ticks
        Enemy trap = tileFactory.produceEnemy('B', new Position(0, 1));
        assertEquals("B", trap.toString());
        assertEquals('B', trap.getChar());
        trap.onGameTick(trap, new Vector<>());
        assertEquals(".", trap.toString());
        assertEquals('B', trap.getChar());
        for (int i = 0; i < 5; i++) {
            trap.onGameTick(trap, new Vector<>());
        }
        assertEquals("B", trap.toString());
        assertEquals('B', trap.getChar());
    }

}
