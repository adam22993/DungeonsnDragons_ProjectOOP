package Units.EnemyTypes;

import Controller.Messages.UnitMessageController;
import Units.AbstractsAndInterfaces.Enemy;
import Units.AbstractsAndInterfaces.HeroicUnit;
import Units.AbstractsAndInterfaces.Player;
import Units.AbstractsAndInterfaces.Unit;

import java.util.Vector;

public class Boss extends Monster {

    private int abilityFrequency;
    private int combatTicks;
    public Boss(char Char, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, int experienceValue, int visionRange, int abilityFrequency, UnitMessageController UMC) {
        super(Char, name, healthPool, attackPoints, defensePoints, experienceValue, visionRange, UMC);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
        // not going to make it because of time constraints
    }



}
