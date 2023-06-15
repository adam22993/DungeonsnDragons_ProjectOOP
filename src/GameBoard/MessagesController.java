package GameBoard;

import Units.AbstractsAndInterfaces.*;

public interface MessagesController { // suppose to be observer of some kind
    String update(String message);
    String attackUpdate(Unit player, Unit enemy, int damageDealt);
}

