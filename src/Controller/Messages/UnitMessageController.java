package Controller.Messages;

import GameBoard.MessagesController;
import Units.Abstracts.Unit;

public class UnitMessageController implements MessagesController {
    @Override
    public String update(String message) {
        System.out.println(message);
        return message;
    }

    @Override
    public String attackUpdate(Unit unitAttacking, Unit unitDefending, int damageDealt) {
        return String.format("%s attacked %s for %d damage", unitAttacking.getName(), unitDefending.getName(), damageDealt);
    }

    public String deathMessage(Unit unit) {
        System.out.printf("%s has been slain!%n", unit.getName());
        return String.format("%s has been slain!", unit.getName());
    }
}
