package Units.ADDITIONAL.ConsumablePoints;

public class Arrows extends ConsumablePoints{
    public Arrows(int max) {
        super(max);
    }

    public boolean use(){
        if (this.current > 0){
            this.current--;
            return true;
        }
        return false;
    }
}
