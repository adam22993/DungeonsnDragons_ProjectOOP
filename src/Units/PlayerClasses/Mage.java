package Units.PlayerClasses;

import Units.ADDITIONAL.Position;
import Units.Abstracts.Player;

public class Mage extends Player {
    private int manaPool, manaCost, spellPower, hitCount, range;

    public Mage(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int manaPool, int manaCost, int spellPower, int hitCount, int range, Position position) {
        super(Char, name, Health_pool, Attack_points, Defense_points, position);
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
    }

    @Override
    protected void castSpecialAbility() {

    }
}
