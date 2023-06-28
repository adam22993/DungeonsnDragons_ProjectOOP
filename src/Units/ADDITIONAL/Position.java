package Units.ADDITIONAL;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean equals(Position other){
        if (other == null)
            return false;
        if (other == this)
            return true;
        return this.x == other.x && this.y == other.y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    public double Range(Position other){
        return Math.floor(Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));
    }

    public static double Range(int first, int other) {
        	return Math.floor(Math.sqrt(Math.pow(first - other, 2)));
    }
    // create a compare function for sorting
    public int compareTo(Position other){
        if (this.x == other.x && this.y == other.y)
            return 0;
        if (this.y < other.y)
            return -1;
        if (this.y > other.y)
            return 1;
        if (this.x < other.x)
            return -1;
        return 1;
    }

}
