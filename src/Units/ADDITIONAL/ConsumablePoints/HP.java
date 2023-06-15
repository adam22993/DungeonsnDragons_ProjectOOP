package Units.ADDITIONAL.ConsumablePoints;

public class HP extends ConsumablePoints {
    public HP(int max) {
        super(max);
    }

    public boolean isDead() {
        return this.getCurrent() == 0;
    }
}
