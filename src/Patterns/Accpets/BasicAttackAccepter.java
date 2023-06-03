package Patterns.Accpets;

import Patterns.Visitor.BasicAttackVisitor;
import Patterns.Visitor.Character;

public class BasicAttackAccepter implements BasicAttackVisitor {
    @Override
    public void visitBA(Character character) {
        this.performBasicAttack(this);
    }


    public void performBasicAttack(BasicAttackAccepter warrior){
        warrior.performBasicAttack(this);
    }
}
