package Units.ADDITIONAL;

public class ABCD extends ConsumablePoints{
    public ABCD(int max) {
        super(max);
        current = 0; // initial cd for all abilities is 0 - allowing players to use them immediately.
    } // AbilityCD

    public void use() {
        if (current == 0) {
            current = max;
        } else {
            System.out.println("Ability is on cooldown."); // needs to be changed to a callbackmessage.
        }
    }

}
