package Patterns.Factory;

class Unit {
    private DeathCallback dc;

    public void battle(Unit u) {
        int attack = this.roll(attackPoints);
        int defense = u.roll(defensePoints);
        int damage = attack - defense;
        if (damage > 0) {
            u.takeDamage(damage);
            if !u.alive() {
                u.onDeath();
            }
        }
    }

    public void onDeath() {
        dc.call();
    }
}
