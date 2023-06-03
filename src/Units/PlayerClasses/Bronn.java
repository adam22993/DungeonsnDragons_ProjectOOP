package Units.PlayerClasses;

import Patterns.Visitor.UnitVisitor;
import Units.Player;

public class Bronn extends Warrior {
    int energyCost = 50;
    public Bronn() {
        super("Bronn", 300, 300, 40, 3, 3);

    }

    @Override
    protected void castSpecialAbility() {
//        Unit target = this.getTarget();
//        target.setHealth(target.getHealth() - 10);
    }

    @Override
    public void accept(UnitVisitor visitor) {
        visitor.visit(this);
    }
}
