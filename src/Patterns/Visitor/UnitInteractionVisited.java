package Patterns.Visitor;

import Units.Abstracts.Unit;

public interface UnitInteractionVisited {
    public void accept(Unit visitor);
}
