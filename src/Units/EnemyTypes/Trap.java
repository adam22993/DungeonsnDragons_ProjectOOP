package Units.EnemyTypes;

import Units.AbstractsAndInterfaces.*;

import java.util.Vector;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char Char, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(Char, name, healthPool, attackPoints, defensePoints, 0, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public char onGameTick(Unit player, Vector<Unit> units) {
        this.ticksCount++;
        if (this.ticksCount % (this.visibilityTime + this.invisibilityTime) == 0 ){ // TODO: check if this is the correct way to do it
            this.ticksCount = 0;
            this.visible = true;
        }
        if (this.ticksCount == this.visibilityTime){
            this.visible = false;
        }
        if (player.getChar() == '@'){
            if (player.getPosition().Range(this.getPosition()) < 2){
                this.attack(player);
            }
        }
        return 'v';
    }

    @Override
    public String getAbilityName() {
        return "Surprise!";
    }


    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (this.visible){
            return String.format("%c", this.getChar());
        } else {
            return ".";
        }
    }
}
