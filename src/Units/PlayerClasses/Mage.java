package Units.PlayerClasses;

import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.ConsumablePoints.ConsumablePoints;
import Units.ADDITIONAL.ConsumablePoints.MP;
import Units.AbstractsAndInterfaces.*;

import java.util.Vector;

public class Mage extends Player implements HeroicUnit {
    private final int manaCost;
    private int range;
    private int hitCount;
    private int spellPower;
    private MP mana;
    UnitMessageController unitMessageController;

    public Mage(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int manaPool,
                int manaCost, int spellPower, int hitCount, int range, UnitMessageController UMC) {
        super(name, Health_pool, Attack_points, Defense_points, UMC);
        this.mana = new MP(manaPool);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
        this.unitMessageController = UMC;
    }

    @Override
    public ConsumablePoints getConsumablePoints() {
        return this.mana;
    }

    @Override
    public void castAbility(Unit opponent) {

    }

    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        // gain mana and stuff things mages do... maybe... you know... like... casting spells and stuff.............
        return 0;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCurrMana() {
        return mana.getCurrent();
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




}
