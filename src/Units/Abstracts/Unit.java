package Units.Abstracts;

import Units.ADDITIONAL.Position;

public abstract class Unit {
    protected String name;
    protected int healthPool; //TODO: implement ConsumablePoints
    protected int healthAmount;
    protected int attackPoints;
    protected int defensePoints;

    public Unit(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points) {
        this.name = name;
        this.healthPool = Health_pool;
        this.healthAmount = Health_pool; // Start with full health
        this.attackPoints = Attack_points;
        this.defensePoints = Defense_points;

    }

    //###################### Actions related ######################

    abstract protected void onGameTick();
    public void attack(Unit enemy){

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


}
