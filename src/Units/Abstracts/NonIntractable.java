package Units.Abstracts;

public class NonIntractable extends Unit {
    // TODO: OVERWRITE ANY METHOD THAT A NON-INTRACTABLE UNIT SHOULD NOT HAVE
    public NonIntractable(String name) {
        super(name, Integer.MAX_VALUE, 0, Integer.MAX_VALUE); // a non-intractable unit has infinite health and defense but no attack... a wall can't attack you... but maybe you can attack it? could be funny
    }

    @Override
    protected void onGameTick() {
        return;
    }

    @Override
    public void attack(Unit enemy){
        return;
    }
}
