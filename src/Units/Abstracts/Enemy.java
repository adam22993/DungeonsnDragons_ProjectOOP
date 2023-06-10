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
        this.Char = Char;
    }

    public int giveExperience(){
        return experienceValue;
    }

    public char getChar() {
        return Char;
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
        this.swapPosition(empty);
    }

    @Override
    public void visit(Wall wall) {
        return;
    }
}
