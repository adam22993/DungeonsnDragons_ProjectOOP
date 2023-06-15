package Units.EnemyTypes;

import Controller.Messages.UnitMessageController;
import Units.Abstracts.*;

import java.util.Vector;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;
    UnitMessageController unitMessageController;

    public Trap(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int experienceValue, int visibilityTime, int invisibilityTime, UnitMessageController UMC) {
        super(Char, name, Health_pool, Attack_points, Defense_points, 0, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
        this.unitMessageController = UMC;
    }

    @Override
    public char onGameTick(Unit player, Vector<Unit> units) {
        this.ticksCount++;
        if (this.ticksCount % (this.visibilityTime + this.invisibilityTime) == 0 ){ // TODO: check if this is the correct way to do it
            this.ticksCount = 0;
            this.visible = true;
//            this.setChar('B');
//            this.setUnitChar('B');
//            setTrapChar('B');
        }
        if (this.ticksCount == this.visibilityTime){
            this.visible = false;
//            this.setChar('.');
//            this.setUnitChar('.');
//            setTrapChar('.');
        }
        return 'v';
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
