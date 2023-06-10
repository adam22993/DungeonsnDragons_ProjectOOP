package Units.EnemyTypes;

import Units.ADDITIONAL.Position;
import Units.Abstracts.Enemy;
import Units.Abstracts.Unit;

import java.util.Random;

public class Monster extends Enemy  {
    int experienceValue;
    public Monster(char Char, String name, Integer healthPool, Integer attackPoints, Integer defensePoints,int experienceValue, int visionRange) {
        super(Char, name, healthPool, attackPoints, defensePoints ,visionRange , experienceValue , new Position(0,0));
        this.experienceValue = experienceValue;
    }

    @Override
    public char onGameTick() {
        switch (new Random().nextInt(4)) {
            case 0:
                return 's';
            case 1:
                return 'd';
            case 2:
                return 'a';
            case 3:
                return 'w';
        }
        return 's';
    }

    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }
}
