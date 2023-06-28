package Units.PlayerClasses;

import Controller.Messages.MessageCallback;
import Units.ADDITIONAL.ConsumablePoints.*;
import Units.AbstractsAndInterfaces.Enemy;
import Units.AbstractsAndInterfaces.Player;
import Units.AbstractsAndInterfaces.Unit;

import java.util.Vector;

public class Rogue extends Player {

    private final String abilityName;
    protected int castCost;
    private ENERGY energy;

    public Rogue(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int castCost, MessageCallback m) {
        super(name, Health_pool, Attack_points, Defense_points, m);
        this.energy = new ENERGY(100);
        this.castCost = castCost;
        this.abilityName = "Fan of Knifes";
    }
    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        energy.setCurrent(Math.min(energy.getCurrent() + 10, energy.getMax()));
        return 0;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public int getCastCost() {
        return castCost;
    }

    public int getCurrEnergy() {
        return energy.getCurrent();
    }

    public int getMaxEnergy() {
        return energy.getMax();
    }


    @Override
    protected void levelUp(){
        super.levelUp();
        this.energy.setCurrent(this.energy.getMax());
        this.attackPoints += 3 * this.getLevel();
        this.m.update(String.format("Rogue %s reached level %d: +%d Health, +%d Attack, +%d Defense, energy restored", this.getName(), this.getLevel(), 10 * this.getLevel(), 7 * this.getLevel(), this.getLevel()));
        if (!this.experience.checkLevelUp()){
            return;
        }
        this.levelUp();
    }


    @Override
    public ConsumablePoints getConsumablePoints() {
        return this.energy;
    }


    public void castAbility(Vector<Enemy> enemies) {
        if (!this.canCastAbility()) {
            this.m.update(String.format("%s tried to cast %s, but there was not enough energy: %d/%d", this.getName(), this.getAbilityName(), this.energy.getCurrent(), this.energy.getMax()));
            return;
        }
        Vector<Enemy> enemiesInRange = new Vector<>();
        for (Enemy unit : enemies) {
            if (this.getPosition().Range(unit.getPosition()) <= 2) {
                enemiesInRange.add(unit);
            }
        }
        if (enemiesInRange.isEmpty()) {
            this.m.update(String.format("%s tried to cast %s, but there were no enemies in range", this.getName(), this.getAbilityName()));
            return;
        }
        for (Enemy unit : enemiesInRange) {
            if (unit.isDead()) {
                continue;
            }
            int def = unit.roleDEF();
            int atk = this.getAttackPoints();
            int dmg = Math.max(0, atk - def);
            unit.setHealthAmount(unit.getHealthCurrent() - dmg);
            this.m.update(String.format("%s casted %s on %s for %d damage (%s attacked for %d attack points, %s rolled %d defense points), setting %s health to %d%n", this.getName(), this.getAbilityName(), unit.getName(), dmg, this.getName(), this.getAttackPoints(), unit.getName(), def, unit.getName(), unit.getHealthCurrent()));
            if (unit.isDead()) {
                this.m.update(String.format("%s killed %s", this.getName(), unit.getName()));
                this.gainExperience(unit.giveExperience());
                if (this.checkLevelUp()){
                    this.levelUp();
                }
            }
        }
    }

    private boolean canCastAbility() {
        return this.energy.useEnergy(this.castCost);
    }


    //################### Accepts Visitor ###################



}
