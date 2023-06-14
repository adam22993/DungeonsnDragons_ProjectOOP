package Units.PlayerClasses;

import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.Position;
import Units.Abstracts.*;

public class Mage extends Player {
    private int manaPool, manaCost, spellPower, hitCount, range;
    UnitMessageController unitMessageController;

    public Mage(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int manaPool,
                int manaCost, int spellPower, int hitCount, int range, UnitMessageController UMC) {
        super(name, Health_pool, Attack_points, Defense_points, UMC);
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
        this.unitMessageController = UMC;
    }

    @Override
    protected void castSpecialAbility() {

    }


}
