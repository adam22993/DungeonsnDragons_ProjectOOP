package Units.PlayerClasses;

import Controller.Messages.MessageCallback;
import Units.ADDITIONAL.ConsumablePoints.Arrows;
import Units.ADDITIONAL.ConsumablePoints.ConsumablePoints;
import Units.AbstractsAndInterfaces.*;

import java.util.Vector;

public class Hunter extends Player {

    protected final String abilityName = "Shoot";
    protected int range;
    protected Arrows arrows;
    protected int ticksCount = 0;

    public Hunter(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int range, int arrowCount, MessageCallback m) {
        super(name, Health_pool, Attack_points, Defense_points, m);
        this.range = range;
        this.arrows = new Arrows(arrowCount);
    }

    public int getRange() {
        return range;
    }

    public int getArrows() {
        return arrows.getCurrent();
    }

    public int getMaxArrows() {
        return arrows.getMax();
    }

    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        if (ticksCount == 10) {
            ticksCount = 0;
            arrows.add(this.getLevel());
            if (arrows.getCurrent() > arrows.getMax()) {
                arrows.setCurrent(arrows.getMax());
            }
        } else {
            ticksCount++;
        }
        return 0;
    }

    protected void levelUp() {
        super.levelUp();
        this.arrows.add(10 * this.getLevel());
        attackPoints += 2 * this.getLevel();
        defensePoints += this.getLevel();
        this.m.update(String.format("Hunter %s reached level %d: +%d Health, +%d Attack, +%d Defense, +%d Arrows",
                this.getName(), this.getLevel(), 10 * this.getLevel(), 6 * this.getLevel(), 2 * this.getLevel(), this.getLevel() * 10));
        if (!this.experience.checkLevelUp()) {
            return;
        }
        this.levelUp();
    }

    @Override
    public String getAbilityName() {
        return abilityName;
    }

    @Override
    public ConsumablePoints getConsumablePoints() {
        return arrows;
    }

    @Override
    public void castAbility(Vector<Enemy> enemies) {
        Vector<Enemy> closest = new Vector<Enemy>();
        Enemy chosenTarget;
        for (Enemy unit : enemies) {
            if (this.getPosition().Range(unit.getPosition()) <= this.getRange() && !unit.isDead()) {
                if (closest.isEmpty() && unit.getChar() != '@' && unit.toString() != "." ) {
                    closest.add(unit);
                }
                // if the unit is not the player and the unit is closer than the current closest units
                else if (!closest.isEmpty() && unit.getChar() != '@' && unit.toString() != "." && this.getPosition().Range(unit.getPosition()) < this.getPosition().Range(closest.get(0).getPosition())) {
                    closest.clear();
                    closest.add(unit);
                } else if (!closest.isEmpty() && unit.getChar() != '@' && unit.toString() != "." && this.getPosition().Range(unit.getPosition()) == this.getPosition().Range(closest.get(0).getPosition())) {
                    closest.add(unit);
                }
            }
        }
        if (closest.isEmpty() && arrows.use()) {
            m.update("Warrior " + this.getName() + " tried to cast ability, but failed! (no targets in range!)");
            return; // no targets in range to cast ability on - do nothing and be sad
        } else if (!this.arrows.use()) {
            this.m.update("Hunter " + this.getName() + " tried to cast " + this.getAbilityName() + ", but failed! no arrows left!");
            return;
        }
        chosenTarget = closest.get(Random.nextInt(closest.size()));
        int def = chosenTarget.roleDEF();
        int hit = this.getAttackPoints();
        int dmg = Math.max(0, hit - def);
        chosenTarget.setHealthAmount(chosenTarget.getHealthCurrent() - dmg);
        this.m.update(String.format("%s casted %s on %s for %d damage (%s attacked for %d attack points, %s rolled %d defense points), setting %s health to %d", this.getName(), this.getAbilityName(), chosenTarget.getName(), dmg, this.getName(), hit, chosenTarget.getName(), def, chosenTarget.getName(), chosenTarget.getHealthCurrent()));
        if (chosenTarget.isDead()){
            this.gainExperience(chosenTarget.giveExperience());
            this.m.update(String.format("%s has slain %s", this.getName(), chosenTarget.getName()));
            if (checkLevelUp()){
                this.levelUp();
            }
        }
    }
}

