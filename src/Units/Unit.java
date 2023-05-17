package Units;
import GameBoard.Point;


public abstract class Unit {
    String name;
    Integer Health_pool;
    Integer Health_amount;
    Integer Attack_points;
    Integer Defense_points;

    public Unit(String name, Integer Health_pool, Integer Health_amount, Integer Attack_points, Integer Defense_points) {
        this.name = name;
        this.Health_pool = Health_pool;
        this.Health_amount = Health_amount;
        this.Attack_points = Attack_points;
        this.Defense_points = Defense_points;

    }

    //###################### Actions related ######################

    abstract protected void onGameTick();
    public void attack(Unit enemy){
        int damage = this.Attack_points - enemy.Defense_points;
        if (damage < 0){
            damage = 0;
        }
        enemy.Health_amount -= damage;
        if (enemy.Health_amount < 0){
            enemy.Health_amount = 0;
        }
    }

}
