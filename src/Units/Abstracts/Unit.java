package Units.Abstracts;

import Controller.UnitMessageController;
import Patterns.Visitor.UnitInteractionVisited;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Position;
import Units.ADDITIONAL.Wall;

import java.util.Random;

public abstract class Unit extends Tile implements UnitInteractionVisited, UnitInteractionVisitor {
    protected String name;
    protected int healthPool; //TODO: implement ConsumablePoints
    protected int healthAmount;
    protected int attackPoints;
    protected int defensePoints;
    Random Random = new Random();
    UnitMessageController unitMessageController = new UnitMessageController();

    public Unit(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, char Char, Position position) {
        super(Char, position);
        this.name = name;
        this.healthPool = Health_pool;
        this.healthAmount = Health_pool; // Start with full health
        this.attackPoints = Attack_points;
        this.defensePoints = Defense_points;

    }

    //###################### Actions related ######################

    abstract public char onGameTick(Position playerPosition, Tile[][] Surroundings);
    public String attack(Unit enemy){
        int damage = Random.nextInt(0, this.attackPoints - enemy.defensePoints);
        if (damage - enemy.defensePoints < 0) {
            damage = 0;
        } else {
            damage = damage - enemy.defensePoints;
        }
        enemy.healthAmount = enemy.getHealthAmount() - damage;
        return unitMessageController.attackUpdate(this, enemy, damage);
    }
//    public abstract void accept(UnitVisitor visitor); //TODO: implement interact visitor

    public String getName() {
        return name;
    }
    public int getHealthPool() {
        return healthPool;
    }
    public int getHealthAmount() {
        return healthAmount;
    }
    public int getAttackPoints() {
        return attackPoints;
    }
    public int getDefensePoints() {
        return defensePoints;
    }
    protected void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }
    protected void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }
    protected void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    protected void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }
    protected void setName(String name) {
        this.name = name;
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
