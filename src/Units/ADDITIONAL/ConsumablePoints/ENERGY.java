package Units.ADDITIONAL.ConsumablePoints;

public class ENERGY extends ConsumablePoints {

    public ENERGY(int max) {
        super(max);
    }
    public void use(int cost) {
        if (current - cost > 0) {
            current -= cost;
        } else {
            System.out.println("Ability costs too much."); // needs to be changed to a callbackmessage.
        }
    }
}
