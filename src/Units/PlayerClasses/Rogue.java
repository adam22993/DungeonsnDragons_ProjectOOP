package Units.PlayerClasses;

import Units.ADDITIONAL.ConsumablePoints.*;
import Units.AbstractsAndInterfaces.Enemy;
import Units.AbstractsAndInterfaces.HeroicUnit;
import Units.AbstractsAndInterfaces.Player;
import Units.AbstractsAndInterfaces.Unit;

import java.util.Vector;

public class Rogue extends Player {

    private final String abilityName;
    protected int castCost;
    private ENERGY energy;

    public Rogue(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int castCost) {
        super(name, Health_pool, Attack_points, Defense_points);
        this.energy = new ENERGY(100);
        this.castCost = castCost;
        this.abilityName = "Fan of Blades";
    }
    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        // gain things rogues have... maybe... you know... like... energy and stuff.............
        return 0;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public int getCastCost() {
        return castCost;
    }

    public int getCurrEnergy() {
        return energy.getCurrent();
    }

    public int getMaxEnergy() {
        return energy.getMax();
    }


    @Override
    protected void levelUp(){
        super.levelUp();
    }


    @Override
    public ConsumablePoints getConsumablePoints() {
        return this.energy;
    }


    public void castAbility(Enemy opponent, Vector<Enemy> units) {
//        if (canCastAbility()) { // this uses mana if able to cast
//            opponent.setHealthAmount(opponent.getHealthCurrent() - (this.getAttackPoints() + this.castCost));
//            this.unitMessageController.castAbility(this, opponent, this.getAttackPoints() + this.castCost);
//            if (opponent.isDead()){
//                this.unitMessageController.deathMessage(opponent);
//                this.gainExperience(opponent.giveExperience());
//            }
//        }
    }

    @Override
    public void update(String message) {
        this.m.update(message);
    }


    //################### Accepts Visitor ###################



}
