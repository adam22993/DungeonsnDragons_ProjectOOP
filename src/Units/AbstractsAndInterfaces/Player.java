package Units.AbstractsAndInterfaces;


import GameBoard.MessagesController;
import Patterns.Visitor.UnitInteractionVisited;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.ADDITIONAL.ConsumablePoints.ConsumablePoints;
import Units.ADDITIONAL.ConsumablePoints.EXP;
import Units.ADDITIONAL.*;
import java.util.Vector;

public abstract class Player extends Unit implements UnitInteractionVisited, UnitInteractionVisitor, MessagesController {
    //###################### Class related ######################

    protected EXP experience;
    protected Integer level;

    public Player(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points) {
        // TODO Random choose stats constructor stub
        super(name, Health_pool, Attack_points, Defense_points, '@', message -> {});
        this.experience = new EXP(50);
        this.level = 1;
    }


    protected void levelUp() {
        this.experience.subtract(this.getMaxEXP());
        this.incrementLevel();
        this.setMaxEXP(50 * this.getLevel());
        this.attackPoints += 4 * this.getLevel();
        this.defensePoints += this.getLevel();
        this.setHealthPool(health.getMax() + 10 * this.getLevel());
        this.health.setCurrentInBounds(getHealthPool());
    }

    private void incrementLevel() {
        this.level++;
    }

    protected void gainExperience(int experience) {
        this.setCurrEXP(experience);
    }

    abstract public char onGameTick(Unit playerPosition, Vector<Unit> units) ;

    protected void attack(Enemy enemy) {
        int attPoints = this.roleAD();
        int defPoints = enemy.roleAD();
        int damage = Math.max(attPoints - defPoints, 0);
        enemy.setHealthAmount(enemy.getHealthCurrent() - damage);
        m.update(String.format("%s attacked %s for %d damage (%s rolled %d attack points, %s rolled %d defense points), setting %s health to %d", this.getName(), enemy.getName(), damage, this.getName(), attPoints, enemy.getName(), defPoints, enemy.getName(), enemy.getHealthCurrent()));
        if (enemy.getHealthCurrent() == 0) {
            m.update(String.format("%s has slain %s", this.getName(), enemy.getName()));
            this.kill(enemy);
        }
    }

    protected void kill(Enemy e) {
        gainExperience(e.giveExperience());
        this.swapPosition(e);
        if (this.checkLevelUp()) {
            this.levelUp();
        }
    }
    public abstract ConsumablePoints getConsumablePoints();

    //###################### Getters ######################


    public boolean checkLevelUp() {
        return this.experience.checkLevelUp();
    }

    public int getMaxEXP() {
        return this.experience.getMax();
    }

    public int getCurrEXP() {
        return this.experience.getCurrent();
    }

    public int getLevel() {
        return this.level;
    }

    //###################### Setters ######################
    private void setMaxEXP(int maxEXP) {
        this.experience.setMax(maxEXP);
    }

    private void setCurrEXP(int currEXP) {
        this.experience.add(currEXP);
    }

    public String toString(){
        if (this.isDead()){
            return "X";
        }
        return String.format("%c", this.getChar());
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


