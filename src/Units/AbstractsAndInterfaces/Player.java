package Units.AbstractsAndInterfaces;


import Controller.Messages.UnitMessageController;
import Patterns.Visitor.UnitInteractionVisited;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.ADDITIONAL.ConsumablePoints.EXP;
import Units.ADDITIONAL.*;
import java.util.Vector;

public abstract class Player extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    //###################### Class related ######################
    protected EXP experience;
    protected Integer level;

    protected UnitMessageController unitMessageController;

    public Player(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, UnitMessageController UMC) {
        // TODO Random choose stats constructor stub
        super(name, Health_pool, Attack_points, Defense_points, '@');
        this.experience = new EXP(50);
        this.level = 1;
        this.unitMessageController = UMC;
    }


    protected void levelUp() {
        this.experience.subtract(this.getMaxEXP());
        this.incrementLevel();
        this.setMaxEXP(50 * this.getLevel());
        this.attackPoints += 5 * this.getLevel();
        this.defensePoints += 2 * this.getLevel();
        this.setHealthPool(health.getMax() + 10 * this.getLevel());
        this.health.setCurrentInBounds(getHealthPool());
    }

    private void incrementLevel() {
        this.level++;
    }

    protected abstract void castAbility(Unit opponent); // TODO: implement special ability in subclasses - think of way to add more attacks

    protected void gainExperience(int experience) {
        this.setCurrEXP(experience);
    }

    abstract public char onGameTick(Unit playerPosition, Vector<Unit> units) ;

    protected String attack(Enemy enemy) {
//        Clip clip = soundController.getClip("attack");  // an idea for sound effects - will be implemented later
        int damage = this.roleAD();
        damage = Math.max(damage - enemy.defensePoints, 0);
        enemy.setHealthAmount(enemy.getHealthCurrent() - damage);
        System.out.println(this.name + " attacked " + enemy.name + " for " + damage + " damage!, " + enemy.name + " health is now " + enemy.getHealthCurrent() + "!");
        if (enemy.getHealthCurrent() == 0) {
            unitMessageController.deathMessage(enemy);
            this.kill(enemy);
        }
        return unitMessageController.attackUpdate(this, enemy, damage);
    }

    protected void kill(Enemy e) {
        gainExperience(e.giveExperience());
        this.swapPosition(e);
        if (this.checkLevelUp()) {
            this.levelUp();
        }
    }

    //###################### Getters ######################

    protected boolean checkLevelUp() {
        return this.experience.checkLevelUp();
    }

    protected int getMaxEXP() {
        return this.experience.getMax();
    }

    protected int getLevel() {
        return this.level;
    }

    //###################### Setters ######################
    private void setMaxEXP(int maxEXP) {
        this.experience.setMax(maxEXP);
    }

    private void setCurrEXP(int currEXP) {
        this.experience.add(currEXP);
    }


    //######################### Visitors ##########################

    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy enemy) {
        enemy.attack(this);
    }

    @Override
    public void visit(Player player) {
        return;
    }

    @Override
    public void visit(Empty empty) {
        this.swapPosition(empty);
    }

    @Override
    public void visit(Wall wall) {
        return;
    }



}


