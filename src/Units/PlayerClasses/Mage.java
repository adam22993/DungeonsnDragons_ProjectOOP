package Units.PlayerClasses;

import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.ConsumablePoints.ConsumablePoints;
import Units.ADDITIONAL.ConsumablePoints.MP;
import Units.AbstractsAndInterfaces.*;

import java.util.Random;
import java.util.Vector;

public class Mage extends Player {
    private final String abilityName;
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
        this.abilityName = "Blizzard";
    }

    @Override
    public ConsumablePoints getConsumablePoints() {
        return this.mana;
    }

    public String getAbilityName() {
        return abilityName;
    }


    public void castAbility(Vector<Enemy> enemies) {
        Vector<Enemy> enemiesInRange = new Vector<>();
        for (Enemy unit : enemies) {
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
                Enemy curr = enemiesInRange.get(randomIndex);
                int def = curr.roleDEF();
                int dmg = this.spellPower - def;
                curr.setHealthAmount(curr.getHealthCurrent() - (dmg));
                this.unitMessageController.castAbility(this, curr, this.spellPower, def, dmg);
                if (curr.isDead()) {
                    this.unitMessageController.deathMessage(curr);
                    this.gainExperience(curr.giveExperience());
                    if (this.checkLevelUp()) {
                        this.levelUp();
                    }
                }
                enemiesInRange.remove(randomIndex);
            }
        } else {
            this.unitMessageController.update(String.format("%s tried to cast ability, but didn't have enough mana", this.getName()));
        }


    }

    protected void levelUp() {
        super.levelUp();
        this.levelUpManaGain();
        this.spellPower += 10 * this.getLevel();
        this.unitMessageController.update(String.format("Mage %s reached level %d: +%d Health, +%d Attack, +%d Defense, +%d Spell Power, +%d Mana Pool",
                this.getName(), this.getLevel(), 10 * this.getLevel(), 5 * this.getLevel(), 2 * this.getLevel(), 10 * this.getLevel(), this.getLevel() * 25));
        if (!this.experience.checkLevelUp()) {
            return;
        }
        this.levelUp();
    }

    private void levelUpManaGain() {
        this.mana.levelUpManaGain(getLevel());
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
