package Patterns.Visitor;

public interface Character {
    void accept(BasicAttackVisitor visitor);
}
