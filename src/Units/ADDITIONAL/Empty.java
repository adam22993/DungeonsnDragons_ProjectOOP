package Units.ADDITIONAL;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.Abstracts.Enemy;
import Units.Abstracts.NonIntractable;
import Units.Abstracts.Player;
import Units.Abstracts.Tile;
public class Empty extends NonIntractable{

    public Empty(char Char, String name) {
        super(Char, name);
    }

    @Override
    public void onGameTick() {

    }

    @Override
    public void accept(UnitInteractionVisitor visitor) {
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
