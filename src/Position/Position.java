package Position;

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

    public void moveUp(){
        this.y -= 1;
    }

    public void moveDown(){
        this.y += 1;
    }

    public void moveLeft(){
        this.x -= 1;
    }

    public void moveRight(){
        this.x += 1;
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
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}
