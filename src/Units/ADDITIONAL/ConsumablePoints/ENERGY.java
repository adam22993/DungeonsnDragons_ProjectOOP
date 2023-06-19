package Units.ADDITIONAL.ConsumablePoints;

public class ENERGY extends ConsumablePoints {

    public ENERGY(int max) {
        super(max);
    }

    public boolean useEnergy(int cost){
    if (current - cost >= 0) {
            current -= cost;
            return true;
        } else {
            return false;
        }
    }
}
