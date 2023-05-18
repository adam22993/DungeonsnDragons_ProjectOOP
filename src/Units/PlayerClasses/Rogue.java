package Units.PlayerClasses;

import Units.Player;

public class Rogue extends Player {
    protected Integer energy;
    protected Integer energyMax;

    public Rogue(String name) {
        super(name);
        this.energy = 100;
        this.energyMax = 100;

    }
    @Override
    protected void levelUp(){
        super.levelUp();
        this.energyMax += 10;
        this.energy = this.energyMax;
    }
    @Override
    protected void castSpecialAbility() {

    }

    //################### Accepts Visitor ###################



}
