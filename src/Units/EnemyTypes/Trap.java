package Units.EnemyTypes;

import Units.ADDITIONAL.Position;
import Units.Abstracts.Unit;

public class Trap extends Unit {
    int visibilityTime;
    int invisibilityTime;
    int ticksCount;
    boolean visible;

    public Trap(char Char, String name, Integer Health_pool, Integer Health_amount, Integer Attack_points, Integer Defense_points, Position position, int visibilityTime, int invisibilityTime) {
        super(Char, name, Health_pool, Health_amount, Attack_points, Defense_points, position);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    protected void onGameTick() {
        // TODO create trap logic
    }
}
