package Units.Abstracts;

import Patterns.Visitor.*;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Position;
import Units.ADDITIONAL.Wall;

public abstract class Enemy extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    protected int experienceValue, visionRange;
    protected char Char;

    public Enemy(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points,int visionRange, int experienceValue, Position position) {
        super(name, Health_pool, Attack_points, Defense_points, Char, position);
        this.experienceValue = experienceValue;
        this.visionRange = visionRange;
    }

    public int giveExperience(){
        return experienceValue;
    }

    public String attack(Unit player){
        int damage = Random.nextInt(0, this.attackPoints);
        damage = Math.max(damage - player.defensePoints, 0);
        player.setHealthAmount(player.getHealthAmount() - damage);
        System.out.println(this.name + " attacked " + player.name + " for " + damage + " damage!, " + player.name + " health is now " + player.healthAmount + "!" );
        if (player.healthAmount == 0){
            unitMessageController.deathMessage(player);
            this.swapPosition(player);
        }
        return unitMessageController.attackUpdate(this, player, damage);
    }

    @Override
    public void visit(Enemy enemy) {
        return;
    }

    @Override
    public void visit(Player player) {
        player.attack(this);
    }

    @Override
    public void visit(Empty empty) {
        this.swapPosition(empty);
    }

    @Override
    public void visit(Wall wall) {
        return;
    }
}
