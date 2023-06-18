package Units.EnemyTypes;


public class Boss extends Monster {

    private int abilityFrequency;
    private int combatTicks;
    public Boss(char Char, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, int experienceValue, int visionRange, int abilityFrequency) {
        super(Char, name, healthPool, attackPoints, defensePoints, experienceValue, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
        // not going to make it because of time constraints
    }



}
