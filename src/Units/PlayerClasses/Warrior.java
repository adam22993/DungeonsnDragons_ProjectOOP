package Units.PlayerClasses;
import Controller.Messages.UnitMessageController;
import Units.Abstracts.*;

import java.util.Random;

public class Warrior extends Player {
    protected int abilityCD; // this is supposed to be 0 initially and count down from the number set as the abilityCD
    protected int currentAbilityCD;
    UnitMessageController unitMessageController;
    Random Random = new Random();
    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int abilityCD, UnitMessageController UMC) {
        super(name, healthPool, attackPoints, defensePoints, UMC);
        this.abilityCD = abilityCD;
        this.currentAbilityCD = 0;
        this.unitMessageController = UMC;
    }

    @Override
    protected void castSpecialAbility(){
        // TODO: implement special ability
    }

    protected void levelUp(){
        super.levelUp();
        this.resetCD();
        this.setHealthPool(this.getHealthPool() + 5 * this.getLevel());
        this.setHealthAmount(this.getHealthPool());
        this.setAttackPoints(this.getAttackPoints() + 2 * this.getLevel());
        this.setDefensePoints(this.getDefensePoints() + this.getLevel());
        this.unitMessageController.update("Warrior " + this.getName() + " leveled up to level " + this.getLevel() + "!");
        if (!this.experience.checkLevelUp()){
            return;
        }
        this.levelUp();
    }
    //################### Accepts Visitor ###################

    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }

    private void resetCD(){
        this.currentAbilityCD = 0;
    }
}
