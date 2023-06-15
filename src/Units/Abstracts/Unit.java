package Units.Abstracts;

import Controller.Messages.UnitMessageController;
import Patterns.Visitor.UnitInteractionVisited;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.ADDITIONAL.ConsumablePoints.HP;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Position;
import Units.ADDITIONAL.Wall;

import java.util.Random;
import java.util.Vector;

public abstract class Unit extends Tile implements UnitInteractionVisited, UnitInteractionVisitor {
    protected String name;
    protected HP health;
    protected int attackPoints;
    protected int defensePoints;
    Random Random = new Random();
    UnitMessageController unitMessageController = new UnitMessageController();

    public Unit(String name, int healthPool, Integer attackPoints, Integer defensePoints, char Char) {
        super(Char);
        this.name = name;
        this.health = new HP(healthPool);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.setChar(Char);

    }

    //###################### Actions related ######################

    abstract public char onGameTick(Unit player, Vector<Unit> units);

    public String getName() {
        return name;
    }
    public int getHealthPool() {
        return health.getMax();
    }
    public int getHealthCurrent() {
        return health.getCurrent();
    }
    public int getAttackPoints() {
        return attackPoints;
    }
    public int getDefensePoints() {
        return defensePoints;
    }
    protected void setHealthAmount(int healthAmount) {
        this.health.setCurrentInBounds(Math.max(healthAmount, 0));
    }
    protected void setHealthPool(int healthPool) {
        this.health.setMax(healthPool);
    }
    protected void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    protected void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }
    public String toString(){
        return String.format("%s", this.getChar());
    }

    abstract public void accept(Unit visitor);
    abstract public void visit(Enemy enemy);
    abstract public void visit(Player player);
    abstract public void visit(Empty empty);
    abstract public void visit(Wall wall);



}
