package Units.ADDITIONAL;

public class EXP extends ConsumablePoints{

    public EXP(int max) {
        super(max);
    }

    public boolean checkLevelUp() {
        return current >= max;
    }
}
