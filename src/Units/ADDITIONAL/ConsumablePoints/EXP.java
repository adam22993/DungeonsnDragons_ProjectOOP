package Units.ADDITIONAL.ConsumablePoints;

public class EXP extends ConsumablePoints{

    public EXP(int max) {
        super(max);
        this.current = 0;
    }

    public boolean checkLevelUp() {
        return current >= max;
    }
}
