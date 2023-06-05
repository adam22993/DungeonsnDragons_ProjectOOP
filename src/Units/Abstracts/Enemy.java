package Units.Abstracts;

import Units.ADDITIONAL.Position;

public abstract class Enemy extends Unit {
    int experienceValue;
    int visionRange;

    public Enemy(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, Position position, int experienceValue, int visionRange) {
        super(Char, name, Health_pool, Attack_points, Defense_points, position);
        this.experienceValue = experienceValue;
        this.visionRange = visionRange;
    }

    @Override
    protected void onGameTick() {
        // TODO create enemy logic
    }

    public int giveExperience(){
        return experienceValue;
    }

}
