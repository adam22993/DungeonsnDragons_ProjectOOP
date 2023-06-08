package Units.ADDITIONAL;

import Patterns.Visitor.UnitInteractionVisitor;
import Units.Abstracts.Enemy;
import Units.Abstracts.NonIntractable;
import Units.Abstracts.Player;
import Units.Abstracts.Tile;

public class Wall extends NonIntractable {

        public Wall(char Char, String name) {
            super(Char, name);
        }

    @Override
    public void onGameTick() {
        return;
    }

    @Override
    public void accept(UnitInteractionVisitor visitor) {
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
