package Units.ADDITIONAL;

import Units.AbstractsAndInterfaces.*;

import java.util.Vector;

public class Wall extends NonIntractable {

    public Wall(char Char, String name) {
        super(Char, name);
    }

    @Override
    public char onGameTick(Unit player, Vector<Unit> units) {
        return 0;
    }


    @Override
    public void accept(Unit visitor) {
        visitor.visit(this);
    }



    // Each unit that interacts with a wall will lose its turn.
    @Override
    public void visit(Enemy enemy) {
        return;
    }

    @Override
    public void visit(Player player) {
        System.out.println("You hit a wall! You lose your turn.");
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
