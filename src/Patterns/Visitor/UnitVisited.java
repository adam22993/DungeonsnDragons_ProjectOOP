package Patterns.Visitor;

import Units.Unit;

public interface UnitVisited {
    public Unit accept(UnitVisitor visitor);
}
