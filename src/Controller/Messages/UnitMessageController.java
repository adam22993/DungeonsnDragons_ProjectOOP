package Controller.Messages;

import GameBoard.MessagesController;
import Units.AbstractsAndInterfaces.Player;
import Units.AbstractsAndInterfaces.Unit;

public class UnitMessageController implements MessagesController {
    @Override
    public String update(String message) {
        System.out.println(message);
        return message;
    }

    @Override
    public String attackUpdate(Unit unitAttacking, Unit unitDefending, int attackerDamage, int defenderDefense, int damageDealt) {
        System.out.printf("%s attacked %s for %d damage (%s rolled %d attack points, %s rolled %d defense points), setting %s health to %d%n", unitAttacking.getName(), unitDefending.getName(), damageDealt, unitAttacking.getName(), attackerDamage, unitDefending.getName(), defenderDefense, unitDefending.getName(), unitDefending.getHealthCurrent());
        return String.format("%s attacked %s for %d damage (%s rolled %d attack points, %s rolled %d defense points), setting %s health to %d", unitAttacking.getName(), unitDefending.getName(), damageDealt, unitAttacking.getName(), attackerDamage, unitDefending.getName(), defenderDefense, unitDefending.getName(), unitDefending.getHealthCurrent());
    }

    @Override
    public String castAbility(Unit caster, Unit opponent, int attackerDamage, int defenderDefense, int damageDealt) {
        System.out.printf("%s casted %s on %s for %d damage (%s attacked for %d attack points, %s rolled %d defense points), setting %s health to %d%n", caster.getName(), caster.getAbilityName(), opponent.getName(), damageDealt, caster.getName(), attackerDamage, opponent.getName(), defenderDefense, opponent.getName(), opponent.getHealthCurrent());
        return String.format("%s casted %s on %s for %d damage (%s attacked for %d attack points, %s rolled %d defense points), setting %s health to %d%n", caster.getName(), caster.getAbilityName(), opponent.getName(), damageDealt, caster.getName(), attackerDamage, opponent.getName(), defenderDefense, opponent.getName(), opponent.getHealthCurrent());
    }


    public String deathMessage(Unit unit) {
        System.out.printf("%s has been slain!%n", unit.getName());
        return String.format("%s has been slain!", unit.getName());
    }

    @Override
    public String levelUpMessage(Player player) {
        System.out.printf("%s leveled up to level %d!%n", player.getName(), player.getLevel());
        return String.format("%s leveled up to level %d!", player.getName(), player.getLevel());
    }
}
