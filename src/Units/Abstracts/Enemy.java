package Units.Abstracts;

import Patterns.Visitor.*;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Wall;

public abstract class Enemy extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    protected int experienceValue, visionRange;
    protected char Char;

    public Enemy(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points,int visionRange, int experienceValue) {
        super(name, Health_pool, Attack_points, Defense_points);
        this.experienceValue = experienceValue;
        this.visionRange = visionRange;
        this.Char = Char;
    }

    @Override
    protected void onGameTick() {
        // TODO create enemy logic
    }

    public int giveExperience(){
        return experienceValue;
    }

    public char getChar() {
        return Char;
    }

    @Override
    public void accept(UnitInteractionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy enemy) {
        return;
    }

    @Override
    public void visit(Player player) {
        this.attack(player);
    }

    @Override
    public void visit(Empty empty) {
        return;
    }

    @Override
    public void visit(Wall wall) {
        return;
    }
}
