package Units.Abstracts;

import Units.ADDITIONAL.Position;

import java.util.Random;

public abstract class Unit extends Tile{
    protected String name;
    protected int healthPool; //TODO: implement ConsumablePoints
    protected int healthAmount;
    protected int attackPoints;
    protected int defensePoints;
    Random Random = new Random();

    public Unit(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, char Char, Position position) {
        super(Char, position);
        this.name = name;
        this.healthPool = Health_pool;
        this.healthAmount = Health_pool; // Start with full health
        this.attackPoints = Attack_points;
        this.defensePoints = Defense_points;

    }

    //###################### Actions related ######################

    abstract public void onGameTick();
    public void attack(Unit enemy){
        int damage = Random.nextInt(0, this.attackPoints - enemy.defensePoints);
        if (damage - enemy.defensePoints < 0) {
            damage = 0;
        } else {
            damage = damage - enemy.defensePoints;
        }
        enemy.healthAmount = enemy.getHealthAmount() - damage;
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


}
