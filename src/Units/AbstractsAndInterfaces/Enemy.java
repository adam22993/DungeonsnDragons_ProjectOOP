package Units.AbstractsAndInterfaces;

import Controller.Messages.MessageCallback;
import Patterns.Visitor.*;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Wall;

public abstract class Enemy extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    protected int experienceValue, visionRange;

    public Enemy(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points,int visionRange, int experienceValue, MessageCallback m) {
        super(name, Health_pool, Attack_points, Defense_points, Char, m);
        this.experienceValue = experienceValue;
        this.visionRange = visionRange;
    }

    public int giveExperience(){
        return experienceValue;
    }

    public void attack(Unit player){
        int attPoints = this.roleAD();
        int defPoints = player.roleDEF();
        int damage = Math.max(attPoints - defPoints, 0);
        player.setHealthAmount(player.getHealthCurrent() - damage);
        m.update(String.format("%s attacked %s for %d damage (%s rolled %d attack points, %s rolled %d defense points), setting %s health to %d", this.getName(), player.getName(), damage, this.getName(), attPoints, player.getName(), defPoints, player.getName(), player.getHealthCurrent()));
        if (player.getHealthCurrent() == 0){
            m.update("You were killed by " + this.getName());
        }
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
