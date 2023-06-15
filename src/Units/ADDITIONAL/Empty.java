package Units.ADDITIONAL;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.Abstracts.*;

import java.util.Vector;

public class Empty extends NonIntractable{

    public Empty(char Char, String name) {
        super(Char, name);
    }

    @Override
    public char onGameTick(Unit player, Vector<Unit> units) {
        return '\u0000';
    }


    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }


    @Override
    public void visit(Enemy enemy) {
        this.swapPosition(enemy);
    }

    @Override
    public void visit(Player player) {
        this.swapPosition(player);
    }

    @Override
    public void visit(Empty empty) {
        return;
    }

    @Override
    public void visit(Wall wall) {
        return;
    }
}
