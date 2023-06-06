package Units.PlayerClasses;

import Units.ADDITIONAL.Position;
import Units.Abstracts.Player;

public class Rogue extends Player {
    protected int currEnergy, energyMax, castCost;

    public Rogue(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int castCost) {
        super(name, Health_pool, Attack_points, Defense_points);
        this.currEnergy = 100;
        this.energyMax = 100;
        this.castCost = castCost;
    }


    @Override
    protected void levelUp(){
        super.levelUp();
        this.energyMax += 10;
        this.currEnergy = this.energyMax;
    }
    @Override
    protected void castSpecialAbility() {

    }

    //################### Accepts Visitor ###################



}
