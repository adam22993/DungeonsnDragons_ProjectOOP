package Units;

import Patterns.Visitor.*;
import Units.Monsters.*;
import Units.PlayerClasses.*;
import Units.Traps.Trap;

public class UnitCreator implements UnitVisitor, UnitVisited{
    @Override
    public Unit accept(char visitor) {
        return null;
    }

    @Override
    public void visit(Warrior unit) {

    }

    @Override
    public void visit(Mage unit) {

    }

    @Override
    public void visit(Rogue unit) {

    }

    @Override
    public void visit(Bronn unit) {

    }

    @Override
    public void visit(JohnSnow unit) {

    }

    @Override
    public void visit(TheHound unit) {

    }

    @Override
    public void visit(Melisandre unit) {

    }

    @Override
    public void visit(AryaStark unit) {

    }

    @Override
    public void visit(ThorosofMyr unit) {

    }

    @Override
    public void visit(QueenCersei unit) {

    }

    @Override
    public void visit(TheMountain unit) {

    }

    @Override
    public void visit(NightsKing unit) {

    }

    @Override
    public void visit(WhiteWalker unit) {

    }

    @Override
    public void visit(Wright unit) {

    }

    @Override
    public void visit(Trap unit) {

    }

}
