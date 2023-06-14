package Units.PlayerClasses;
import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.Position;
import Units.Abstracts.Player;
import Units.Abstracts.Unit;

import java.util.Random;

public class Warrior extends Player {
    protected int abilityCD;
    protected int currentAbilityCD;
    UnitMessageController unitMessageController;
    Random Random = new Random();
    public Warrior(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int abilityCD, UnitMessageController UMC) {
        super(name, Health_pool, Attack_points, Defense_points, UMC);
        this.abilityCD = abilityCD;
        this.currentAbilityCD = 0;
        this.unitMessageController = UMC;
    }

    @Override
    protected void castSpecialAbility(){
        // TODO: implement special ability
    }

    protected void levelUp(){
        if (this.currEXP < this.maxEXP){
            return;
        }
        super.levelUp();
        abilityCD = 0;
        this.healthAmount = this.healthAmount + 5 * this.level;
        this.attackPoints = this.attackPoints + 2 * this.level;
        this.defensePoints = this.defensePoints + this.level;
        unitMessageController.update("Warrior " + this.name + " leveled up to level " + this.level + "!");
        this.levelUp();
    }
    //################### Accepts Visitor ###################

    public void performBasicAttack(){
        System.out.println(this.getName() + " performed a basic attack!");
    }

    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }
}
