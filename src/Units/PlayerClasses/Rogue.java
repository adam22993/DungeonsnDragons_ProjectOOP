package Units.PlayerClasses;

import Controller.Messages.UnitMessageController;
import Units.AbstractsAndInterfaces.Enemy;
import Units.AbstractsAndInterfaces.HeroicUnit;
import Units.AbstractsAndInterfaces.Player;
import Units.AbstractsAndInterfaces.Unit;

import java.util.Vector;

public class Rogue extends Player implements HeroicUnit {
    protected int currEnergy, energyMax, castCost;
    UnitMessageController unitMessageController;

    public Rogue(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int castCost, UnitMessageController UMC) {
        super(name, Health_pool, Attack_points, Defense_points, UMC);
        this.currEnergy = 100;
        this.energyMax = 100;
        this.castCost = castCost;
        this.unitMessageController = UMC;
    }
    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        // gain things rogues have... maybe... you know... like... energy and stuff.............
        return 0;
    }


    @Override
    protected void levelUp(){
        super.levelUp();
        this.energyMax += 10;
        this.currEnergy = this.energyMax;
    }
    @Override
    public void castAbility(Unit opponent) {

    }


    @Override
    public void acceptSA(Unit visitor, Vector<Unit> units) {

    }

    @Override
    public void visitSA(Player player, Vector<Unit> units) {

    }

    @Override
    public void visitSA(Enemy enemy, Vector<Unit> units) {

    }


    //################### Accepts Visitor ###################



}
