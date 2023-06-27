package Units.PlayerClasses;

import Controller.Messages.MessageCallback;
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

    public Mage(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int manaPool,
                int manaCost, int spellPower, int hitCount, int abilityRange, MessageCallback m) {
        super(name, Health_pool, Attack_points, Defense_points, m);
        this.mana = new MP(manaPool);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.abilityRange = abilityRange;
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
                this.m.update(String.format("%s tried to cast ability, but there were no enemies in range", this.getName()));
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
                this.m.update(String.format("%s casted %s on %s for %d damage (%s attacked for %d attack points, %s rolled %d defense points), setting %s health to %d%n", this.getName(), this.getAbilityName(), curr.getName(), dmg, this.getName(), this.spellPower, curr.getName(), def, curr.getName(), curr.getHealthCurrent()));
                if (curr.isDead()) {
                    this.m.update(String.format("%s has slain %s", this.getName(), curr.getName()));
                    this.gainExperience(curr.giveExperience());
                    if (this.checkLevelUp()) {
                        this.levelUp();
                    }
                }
                enemiesInRange.remove(randomIndex);
            }
        } else {
            this.m.update(String.format("%s tried to cast ability, but didn't have enough mana", this.getName()));
        }


    }

    protected void levelUp() {
        super.levelUp();
        this.levelUpManaGain();
        this.spellPower += 10 * this.getLevel();
        this.m.update(String.format("Mage %s reached level %d: +%d Health, +%d Attack, +%d Defense, +%d Spell Power, +%d Mana Pool",
                this.getName(), this.getLevel(), 10 * this.getLevel(), 4 * this.getLevel(), this.getLevel(), 10 * this.getLevel(), this.getLevel() * 25));
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

    public int getCurrMana() {
        return mana.getCurrent();
    }


    public int getMaxMana() {
        return mana.getMax();
    }

}
