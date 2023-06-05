package Units.PlayerClasses;

import Units.Abstracts.Player;

public class Rogue extends Player {
    protected Integer energy;
    protected Integer energyMax;

    public Rogue(String name, Integer Health_pool, Integer Health_amount, Integer Attack_points, Integer Defense_points) {
        super(name, Health_pool, Health_amount, Attack_points, Defense_points);
        this.energy = 100;
        this.energyMax = 100;

    }

    @Override
    public void accept(UnitVisitor visitor) {
        visitor.visit(this);
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
