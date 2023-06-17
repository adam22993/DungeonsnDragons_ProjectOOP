package Units.ADDITIONAL.ConsumablePoints;

public class MP extends ConsumablePoints {
    public MP(int max) {
        super(max);
        current = max/4;
    }

    public boolean useMP(int amount) {
        if (current >= amount) {
            current -= amount;
            return true;
        }
        return false;
    }

    public void tick(int level) {
        current = Math.min(current + level, max);
    }
}
