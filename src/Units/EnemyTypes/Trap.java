package Units.EnemyTypes;

import Units.ADDITIONAL.Position;
import Units.Abstracts.Unit;

public class Trap extends Monster {
    int visibilityTime;
    int invisibilityTime;
    int ticksCount;
    boolean visible;

    public Trap(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(Char, name, Health_pool, Attack_points, Defense_points, experienceValue,0);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public void onGameTick() {
        this.ticksCount++;
        if (this.ticksCount % (this.visibilityTime + this.invisibilityTime) + 1 == (this.visibilityTime + this.invisibilityTime) ){ // TODO: check if this is the correct way to do it
            this.ticksCount = 0;
            this.visible = true;
        }
        if (this.ticksCount == this.visibilityTime){
            this.visible = false;
        }
    }
}
