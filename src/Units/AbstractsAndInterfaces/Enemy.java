package Units.AbstractsAndInterfaces;

import Patterns.Visitor.*;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Wall;

import java.util.Vector;

public abstract class Enemy extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    protected int experienceValue, visionRange;

    public Enemy(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points,int visionRange, int experienceValue) {
        super(name, Health_pool, Attack_points, Defense_points, Char);
        this.experienceValue = experienceValue;
        this.visionRange = visionRange;
    }

    public int giveExperience(){
        return experienceValue;
    }

    public String attack(Unit player){
        int damage = this.roleAD();
        damage = Math.max(damage - player.defensePoints, 0);
        player.setHealthAmount(player.getHealthCurrent() - damage);
        System.out.println(this.name + " attacked " + player.name + " for " + damage + " damage!, " + player.name + " health is now " + player.getHealthCurrent() + "!" );
        if (player.getHealthCurrent() == 0){
            unitMessageController.deathMessage(player);
            player.setChar('X');
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

    public void visitSA(Player player, Vector<Unit> units) {}

    @Override
    public void visitSA(Enemy enemy, Vector<Unit> units) {}
}
