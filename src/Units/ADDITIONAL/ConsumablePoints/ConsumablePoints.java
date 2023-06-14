package Units.ADDITIONAL.ConsumablePoints;

public abstract class ConsumablePoints {
    /**
     * Mainly creates the methods all classes should have and setting the max value.
     * methods:
     * getCurrent() - returns current value
     * getMax() - returns max value
     * setCurrent(int) - sets current value
     * setMax(int) - sets max value
     * add(int) - adds to current value
     * subtract(int) - subtracts from current value
     * setCurrentInBounds(int) - sets current value
     */
    int current;
    int max;
    public ConsumablePoints(int max) {
        this.max = max;
        this.current = max;
        // children must set current - HP needs to be full and EXP needs to be empty for example.
    }
    public int getCurrent() {
        return current;
    }
    public int getMax() {
        return max;
    }
    public void setCurrent(int current) {
        this.current = current;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public void add(int amount) {
        current += amount;
    }
    public void subtract(int amount) {
        current -= amount;
        if (current < 0) {
            current = 0;
        }
    }
    public void setCurrentInBounds(int amount) {
        current = amount;
        if (current > max) {
            current = max;
        }
        if (current < 0) {
            current = 0;
        }
    }

}
