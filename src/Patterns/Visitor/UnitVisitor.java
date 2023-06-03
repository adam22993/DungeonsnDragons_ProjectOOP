package Patterns.Visitor;

import Units.PlayerClasses.*;
import Units.Monsters.*;
import Units.Unit;
import Units.Traps.*;

public interface UnitVisitor {
    void visit(Warrior unit);
    void visit(Mage unit);
    void visit(Rogue unit);
    void visit(Bronn unit);
    void visit(JohnSnow unit);
    void visit(TheHound unit);
    void visit(Melisandre unit);
    void visit(AryaStark unit);
    void visit(ThorosofMyr unit);
    void visit(QueenCersei unit);
    void visit(TheMountain unit);
    void visit(NightsKing unit);
    void visit(WhiteWalker unit);
    void visit(Wright unit);

    void visit(Trap unit);

}
