package Units.AbstractsAndInterfaces;

import java.util.Vector;

public interface HeroicUnit {
    private void castAbility(Unit opponent) {}

    public void acceptSA(Unit visitor, Vector<Unit> units);
    public void visitSA(Player player, Vector<Unit> units);
    public void visitSA(Enemy enemy, Vector<Unit> units);
    
}
