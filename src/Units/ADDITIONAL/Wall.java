package Units.ADDITIONAL;

import Patterns.Visitor.UnitInteractionVisitor;
import Units.Abstracts.*;

import java.util.Vector;

public class Wall extends NonIntractable {

    public Wall(char Char, String name) {
        super(Char, name);
    }

    @Override
    public char onGameTick(Position playerPosition, Vector<Unit> units) {
        return 0;
    }


    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy enemy) {
        return;
    }

    @Override
    public void visit(Player player) {
        return;
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
