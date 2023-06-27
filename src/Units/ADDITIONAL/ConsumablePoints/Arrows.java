package Units.ADDITIONAL.ConsumablePoints;

public class Arrows extends ConsumablePoints{
    public Arrows(int curr) {
        super(Integer.MAX_VALUE);
        this.current = curr;
    }

    public boolean use(){
        if (this.current > 0){
            this.current--;
            return true;
        }
        return false;
    }

    public void add(int amount) {
        current += amount;
    }
}
