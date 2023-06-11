package GameBoard;

import Units.Abstracts.*;

public interface MessagesController { // suppose to be observer of some kind
    String update(String message);
    String attackUpdate(Unit player, Unit enemy, int damageDealt);
}

