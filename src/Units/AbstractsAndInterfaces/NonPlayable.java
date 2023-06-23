package Units.AbstractsAndInterfaces;

import Patterns.Visitor.UnitInteractionVisited;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Wall;

public abstract class NonPlayable extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    // TODO: OVERWRITE ANY METHOD THAT A NON-INTRACTABLE UNIT SHOULD NOT HAVE
    public NonPlayable(char Char, String name) {
        super(name, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Char, message -> {}); // a non-intractable unit has infinite health and defense but no attack... a wall can't attack you... but maybe you can attack it? could be funny
    }


    @Override
    abstract public void visit(Enemy enemy);

    @Override
    abstract public void visit(Player player);

    @Override
    abstract public void visit(Empty empty);

    @Override
    abstract public void visit(Wall wall);
}
