package Units.Abstracts;

import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Position;

public class Tile {
    private char Char;
    private Position pos;

    private Unit unit;

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

    public Unit getUnit(){
        if (this.unit == null)
            return null;
        return this.unit;
    }
    public void setUnit(Unit unit){
        this.unit = unit;
    }

    public boolean equals(Tile other){
        if (other == null)
            return false;
        if (other == this)
            return true;
        return this.Char == other.Char && this.pos.equals(other.pos);
    }

    public String toString(){
        return String.format("%c", this.Char);
    }

    public double Range(Tile other){ // uses floor to round down
        return this.pos.Range(other.pos);
    }

    public void swapPosition(Tile other){
        Position temp = this.pos;
        this.setPosition(other.getPosition());
        other.setPosition(temp);
    }
}
