package Units.Abstracts;

import Units.ADDITIONAL.Position;

import java.util.function.Supplier;

public abstract class Enemy extends Unit {
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
}
