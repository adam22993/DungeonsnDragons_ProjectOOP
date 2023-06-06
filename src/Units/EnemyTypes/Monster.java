package Units.EnemyTypes;

import Units.ADDITIONAL.Position;
import Units.Abstracts.Enemy;
import Units.Abstracts.Unit;

public class Monster extends Enemy {
    int experienceValue;
    public Monster(char Char, String name, Integer healthPool, Integer attackPoints, Integer defensePoints,int experienceValue, int visionRange) {
        super(Char, name, healthPool, attackPoints, defensePoints ,visionRange , experienceValue);
        this.experienceValue = experienceValue;
    }

    @Override
    protected void onGameTick() {

    }
}
