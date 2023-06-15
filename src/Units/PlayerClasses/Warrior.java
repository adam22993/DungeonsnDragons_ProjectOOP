package Units.PlayerClasses;
import Controller.Messages.UnitMessageController;
import Units.ADDITIONAL.ConsumablePoints.ABCD;
import Units.AbstractsAndInterfaces.*;

import java.util.Random;
import java.util.Vector;

public class Warrior extends Player implements HeroicUnit {
    protected ABCD abilityCD; // Ability Cooldown
    UnitMessageController unitMessageController;
    Random Random = new Random();
    public Warrior(String name, int healthPool, int attackPoints, int defensePoints, int abilityCD, UnitMessageController UMC) {
        super(name, healthPool, attackPoints, defensePoints, UMC);
        this.abilityCD = new ABCD(abilityCD);
        this.unitMessageController = UMC;
    }

    @Override
    public char onGameTick(Unit playerPosition, Vector<Unit> units) {
        this.tickCD();
        return 0;
    }
    @Override
    public void castAbility(Unit opponent){
        if (!this.canCastAbility()){
            this.unitMessageController.update("Warrior " + this.getName() + " tried to cast ability, but failed!");
            this.unitMessageController.update("Warrior " + this.getName() + " has " + this.abilityCD.getCD() + " turns left until he can cast ability again!");
            return;
        }
        this.unitMessageController.update("Warrior " + this.getName() + " casted Avenger's Shield!");
        int dmg = this.getHealthPool() / 10 - opponent.getDefensePoints();
        opponent.setHealthAmount(opponent.getHealthCurrent() - dmg);
        this.unitMessageController.update("Warrior " + this.getName() + " dealt " + dmg + " damage to " + opponent.getName() + "!");
        this.setHealthAmount(this.getHealthCurrent() + this.getDefensePoints() * 10);
        this.unitMessageController.update("Warrior " + this.getName() + " healed himself for " + this.getDefensePoints() * 10 + "!");
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
        this.unitMessageController.update("Warrior " + this.getName() + " leveled up to level " + this.getLevel() + "!");
        if (!this.experience.checkLevelUp()){
            return;
        }
        this.levelUp();
    }

    private boolean canCastAbility(){
        return this.abilityCD.use();
    }

    //################### Accepts Visitor ###################

    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }

    private void resetCD(){
        this.abilityCD.reset();
    }
    @Override
    public void acceptSA(Unit visitor, Vector<Unit> units) {
        visitor.visitSA(this, units);
    }

    @Override
    public void visitSA(Player player, Vector<Unit> units) {
        Vector<Unit> closest = new Vector<Unit>();
        Unit chosenTarget;
//        units.sort((s,t) -> s.getPosition().compareTo(t.getPosition()));
        int i = 0;
        for (Unit unit : units){
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
            i++;
        }
        if (closest.isEmpty()){
            unitMessageController.update("Warrior " + this.getName() + " tried to cast ability, but failed!");
            unitMessageController.update("Warrior " + this.getName() + " has no targets in range!");
            return; // no targets in range to cast ability on - do nothing and be sad
        }
        chosenTarget = closest.get(Random.nextInt(closest.size()));
        this.castAbility(chosenTarget);

    }

    @Override
    public void visitSA(Enemy enemy, Vector<Unit> units) {

    }
}
