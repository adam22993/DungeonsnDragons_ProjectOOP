package Units.PlayerClasses;
import Units.ADDITIONAL.ConsumablePoints.ABCD;
import Units.ADDITIONAL.ConsumablePoints.ConsumablePoints;
import Units.AbstractsAndInterfaces.*;

import java.util.Random;
import java.util.Vector;

public class Warrior extends Player {

    private final String abilityName;
    protected ABCD abilityCD; // Ability Cooldown
    Random Random = new Random();
    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int abilityCD) {
        super(name, healthPool, attackPoints, defensePoints);
        this.abilityCD = new ABCD(abilityCD);
        this.abilityName = "Avenger's Shield";
    }

    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        this.tickCD();
        return 0;
    }

    public String getAbilityName(){
        return this.abilityName;
    }


    private void tickCD() {
        this.abilityCD.tick();
    }


    protected void levelUp(){
        super.levelUp();
        this.resetCD();
        this.setHealthPool(this.getHealthPool() + 5 * this.getLevel());
        this.setHealthAmount(this.getHealthPool());
        this.setAttackPoints(this.getAttackPoints() + 2 * this.getLevel());
        this.setDefensePoints(this.getDefensePoints() + this.getLevel());
        this.m.update(String.format("Warrior %s reached level %d: +%d Health, +%d Attack, +%d Defense", this.getName(), this.getLevel(), 15 * this.getLevel(), 6 * this.getLevel(), 2 * this.getLevel()));
        if (!this.experience.checkLevelUp()){
            return;
        }
        this.levelUp();
    }

    private boolean canCastAbility(){
        return this.abilityCD.use();
    }

    public ConsumablePoints getConsumablePoints(){
        return this.abilityCD;
    }

    public int getCurrCD(){
        return this.abilityCD.getCD();
    }

    public int getMaxCD(){
        return this.abilityCD.getMax();
    }

    //################### Accepts Visitor ###################

    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }

    private void resetCD(){
        this.abilityCD.reset();
    }

    public void castAbility(Vector<Enemy> enemies) {
        Vector<Enemy> closest = new Vector<Enemy>();
        Enemy chosenTarget;
        for (Enemy unit : enemies){
            if (this.getPosition().Range(unit.getPosition()) < 3) {
                if (closest.isEmpty() && unit.getChar() != '@') {
                    closest.add(unit);
                }
                // if the unit is not the player and the unit is closer than the current closest units
                else if (!closest.isEmpty() && unit.getChar() != '@' && this.getPosition().Range(unit.getPosition()) < this.getPosition().Range(closest.get(0).getPosition())) {
                    closest.clear();
                    closest.add(unit);
                } else if (!closest.isEmpty() && unit.getChar() != '@' && this.getPosition().Range(unit.getPosition()) == this.getPosition().Range(closest.get(0).getPosition())) {
                    closest.add(unit);
                }
            }
        }
        if (closest.isEmpty() && canCastAbility()) {
            m.update("Warrior " + this.getName() + " tried to cast ability, but failed! (no targets in range!)");
            return; // no targets in range to cast ability on - do nothing and be sad
        } else if (!this.canCastAbility()){
            this.m.update("Warrior " + this.getName() + " tried to cast ability, but failed! " + this.abilityCD.getCD() + " turns left until he can cast ability again!");
            return;
        }
        chosenTarget = closest.get(Random.nextInt(closest.size()));
        int def = chosenTarget.roleDEF();
        int hit = this.getHealthPool() / 10;
        int dmg = hit - def ; // should the defender defend against the ability? -
        // not written in the instructions but the game does it
        chosenTarget.setHealthAmount(chosenTarget.getHealthCurrent() - dmg);
        this.setHealthAmount(this.getHealthCurrent() + this.getDefensePoints() * 10);
        this.m.update(String.format("%s casted %s on %s for %d damage (%s attacked for %d attack points, %s rolled %d defense points), setting %s health to %d", this.getName(), this.getAbilityName(), chosenTarget.getName(), dmg, this.getName(), hit, chosenTarget.getName(), def, chosenTarget.getName(), chosenTarget.getHealthCurrent()));
        if (chosenTarget.isDead()){
            this.m.update("Warrior " + this.getName() + " killed " + chosenTarget.getName() + "!");
            this.gainExperience(chosenTarget.giveExperience());
            if (this.checkLevelUp()){
                this.levelUp();
            }
        }
    }


    @Override
    public void update(String message) {
        this.m.update(message);
    }
}
