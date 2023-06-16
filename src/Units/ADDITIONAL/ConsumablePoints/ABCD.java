package Units.ADDITIONAL.ConsumablePoints;

public class ABCD extends ConsumablePoints{
    public ABCD(int max) {
        super(max);
        current = 0; // the initial cd for all abilities is 0 - allowing players to use them immediately.
    } // AbilityCD

    public boolean use() {
        if (current == 0) {
            current = max;
        } else {
            return false; // needs to be changed to a callbackmessage.
        }
        return true;
    }

    public void tick() {
        if (current > 0) {
            current--;
        }
    }

    public void reset() {
        current = 0;
    }

    public int getCD() {
        return current;
    }
}
