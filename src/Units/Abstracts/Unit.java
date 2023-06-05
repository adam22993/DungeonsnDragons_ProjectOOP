package Units.Abstracts;

import Units.ADDITIONAL.Position;

public abstract class Unit extends Tile {
    String name;
    Integer healthPool; //TODO: implement ConsumablePoints
    Integer healthAmount;
    Integer attackPoints;
    Integer defensePoints;

    public Unit(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, Position position) {
        super(Char, position);
        this.name = name;
        this.healthPool = Health_pool;
        this.healthAmount = Health_pool;
        this.attackPoints = Attack_points;
        this.defensePoints = Defense_points;

    }

    //###################### Actions related ######################

    abstract protected void onGameTick();
    public void attack(Unit enemy){

    }
//    public abstract void accept(UnitVisitor visitor); //TODO: implement interact visitor


}
