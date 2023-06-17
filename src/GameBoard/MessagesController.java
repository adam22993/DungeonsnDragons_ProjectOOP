package GameBoard;

import Units.AbstractsAndInterfaces.*;

public interface MessagesController { // suppose to be observer of some kind

    String update(String message);

    String attackUpdate(Unit attacker, Unit defender, int attackerDamage, int defenderDefense, int damageDealt);

    String castAbility(Unit caster, Unit opponent, int damageDealt);

    String deathMessage(Unit unit);

    String levelUpMessage(Player player);
}

