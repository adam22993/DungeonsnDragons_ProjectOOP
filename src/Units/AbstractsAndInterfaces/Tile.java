package Units.AbstractsAndInterfaces;

import Units.ADDITIONAL.Position;

public class Tile {
    private char Char;
    private Position pos;

    public Tile(char Char, Position position){
        this.Char = Char;
        this.pos = position;
    }

    public Tile(char Char){
        this.Char = Char;
        this.pos = new Position(0,0);
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
        Char = Character;
    }


    public boolean equals(Tile other){
        if (other == null)
            return false;
        if (other == this)
            return true;
        return this.Char == other.Char && this.pos.equals(other.pos);
    }

    public String toString(){
        return String.format("%s", this.Char);
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
