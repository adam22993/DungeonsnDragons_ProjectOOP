package Units;
import Position.Position;

public class Tile {
    private char Char;
    private Position pos;

    public Tile(char Character, Position position){
        this.Char = Character;
        this.pos = position;
    }

    public char getChar(){
        return this.Char;
    }

    public Position getPosition(){
        return this.pos;
    }

    public void setPosition(Position position){
        this.pos = position;
    }

    public void setChar(char Character){
        this.Char = Character;
    }

    public boolean equals(Tile other){
        if (other == null)
            return false;
        if (other == this)
            return true;
        return this.Char == other.Char && this.pos.equals(other.pos);
    }

    public String toString(){
        return this.Char + " " + this.pos.toString();
    }

    public double Range(Tile other){
        return this.pos.Range(other.pos);
    }

    public void moveUp(){
        this.pos.moveUp();
    }

    public void moveDown(){
        this.pos.moveDown();
    }

    public void moveLeft(){
        this.pos.moveLeft();
    }

    public void moveRight(){
        this.pos.moveRight();
    }


}
