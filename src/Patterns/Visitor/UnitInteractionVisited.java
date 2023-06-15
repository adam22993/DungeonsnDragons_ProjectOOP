package Patterns.Visitor;

import Units.AbstractsAndInterfaces.Unit;

public interface UnitInteractionVisited {
    public void accept(Unit visitor);
}
