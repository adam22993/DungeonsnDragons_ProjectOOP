package GameBoard;
import Units.*;
import Units.PlayerClasses.*;
import Units.Monsters.*;
import Units.Traps.*;
import Units.Bosses.*;

public class UnitCreator {
    public static Unit createChar(char tile){
        switch (tile){
            case '@':
                return new Warrior("Warrior");
            case 's':
                return new Mage("Mage");
            case 'p':
                return new LannisterKnight("LannisterKnight");
            case 'w':
                return new Wall("Wall");
            case 'b':
                return new Empty("Empty");
            default:
                return null;
        }
    }
}
