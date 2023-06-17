package Units.PlayerClasses;

import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.ConsumablePoints.ConsumablePoints;
import Units.ADDITIONAL.ConsumablePoints.MP;
import Units.AbstractsAndInterfaces.*;

import java.util.Random;
import java.util.Vector;

public class Mage extends Player {
    private final int manaCost;
    private int abilityRange;
    private int hitCount;
    private int spellPower;
    private MP mana;
    UnitMessageController unitMessageController;

    public Mage(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int manaPool,
                int manaCost, int spellPower, int hitCount, int abilityRange, UnitMessageController UMC) {
        super(name, Health_pool, Attack_points, Defense_points, UMC);
        this.mana = new MP(manaPool);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.abilityRange = abilityRange;
        this.unitMessageController = UMC;
    }

    @Override
    public ConsumablePoints getConsumablePoints() {
        return this.mana;
    }


    public void castHeroicAbility(Vector<Unit> units) {
        Vector<Unit> enemiesInRange = new Vector<>();
        for (Unit unit : units) {
            if (unit.getPosition().Range(this.getPosition()) <= abilityRange) {
                enemiesInRange.add(unit);
            }
        }
        Random Random = new Random();
        if (canCastAbility()) { // this uses mana if able to cast
            if (enemiesInRange.size() == 0) { // if there are no enemies in range, waste cast
                this.unitMessageController.update(String.format("%s tried to cast ability, but there were no enemies in range", this.getName()));
                return;
            }
            for (int currHitCount = 0; currHitCount < this.hitCount; currHitCount++) {
                if (enemiesInRange.size() == 0) {
                    break;
                }
                int randomIndex = Random.nextInt(enemiesInRange.size());
                Unit curr = enemiesInRange.get(randomIndex);
                curr.setHealthAmount(curr.getHealthCurrent() - (this.spellPower));
                this.unitMessageController.castAbility(this, curr, this.spellPower);
//                if (curr.isDead()){
//                    this.unitMessageController.deathMessage(curr);
//                    this.gainExperience(curr.giveExperience());
//                }
                enemiesInRange.remove(randomIndex);
            }
        } else {
            this.unitMessageController.update(String.format("%s tried to cast ability, but didn't have enough mana", this.getName()));
        }


    }

    public boolean canCastAbility() {
        return this.mana.useMP(this.manaCost);
    }

    public int getAbilityRange() {
        return abilityRange;
    }

    public int getSpellPower() {
        return spellPower;
    }


    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        this.mana.tick(getLevel());
        return 0;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCurrMana() {
        return mana.getCurrent();
    }




    public int getMaxMana() {
        return mana.getMax();
    }
}
