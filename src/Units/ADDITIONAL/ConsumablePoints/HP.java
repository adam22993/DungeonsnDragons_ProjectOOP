package Units.ADDITIONAL.ConsumablePoints;

public class HP extends ConsumablePoints {
    public HP(int max) {
        super(max);
    }

    public void isDead() {
        if (current <= 0) {
            System.out.println("Unit is dead.");
        }
    }
}
