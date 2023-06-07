package Patterns.Visitor;

import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Wall;
import Units.Abstracts.*;

public interface UnitInteractionVisitor { // We are visiting the units on the map - each unit is placed on a tile, so we are visiting the tiles for their units
    public void visit(Enemy enemy);
    public void visit(Player player);
    public void visit(Empty empty);
    public void visit(Wall wall);

}
