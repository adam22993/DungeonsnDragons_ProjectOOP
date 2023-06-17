package Units.PlayerClasses;

import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.ConsumablePoints.*;
import Units.AbstractsAndInterfaces.Enemy;
import Units.AbstractsAndInterfaces.HeroicUnit;
import Units.AbstractsAndInterfaces.Player;
import Units.AbstractsAndInterfaces.Unit;

import java.util.Vector;

public class Rogue extends Player implements HeroicUnit {
    protected int castCost;
    private ENERGY energy;
    UnitMessageController unitMessageController;

    public Rogue(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int castCost, UnitMessageController UMC) {
        super(name, Health_pool, Attack_points, Defense_points, UMC);
        this.energy = new ENERGY(100);
        this.castCost = castCost;
        this.unitMessageController = UMC;
    }
    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        // gain things rogues have... maybe... you know... like... energy and stuff.............
        return 0;
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


    //################### Accepts Visitor ###################



}
