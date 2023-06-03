package Units.PlayerClasses;
import Patterns.Visitor.BasicAttackVisitor;
import Patterns.Visitor.Character;
import Patterns.Visitor.UnitVisitor;
import Units.Player;
import Speaches_Screens.Quotes;
import java.util.Random;

public class Warrior extends Player implements Character {

    Integer Health_pool;
    Integer Health_amount;
    Integer Attack_points;
    Integer Defense_points;
    protected int abilityCD;
    Random Random = new Random();
    public Warrior(String name, Integer Health_pool, Integer Health_amount, Integer Attack_points, Integer Defense_points, int abilityCD) {
        super(name, Health_pool, Health_amount, Attack_points, Defense_points);
        this.abilityCD = abilityCD;
    }

    @Override
    protected void castSpecialAbility(){
        if (this.abilityCD == 0){
            this.abilityCD = 3;
            // TODO: add special ability
            System.out.printf("%s", Quotes.avengersShieldQuotes[Random.nextInt(Quotes.avengersShieldQuotes.length)]);
        }
        else{
            System.out.println("Special ability is not ready yet.");
        }
    }

    protected void levelUp(){
        super.levelUp();
        abilityCD = 0;
        Health_amount = Health_pool + 5 * Level;
        Attack_points = Attack_points + 2 * Level;
        Defense_points = Defense_points + Level;
    }

    @Override
    protected void onGameTick() {
        super.onGameTick();
        if (this.abilityCD > 0){
            this.Health_amount += 5 + this.Level;
            this.abilityCD -= 1;
        }
    }

    @Override
    public void accept(UnitVisitor visitor) {
        visitor.visit(this);
    }
    //################### Accepts Visitor ###################

    @Override
    public void accept(BasicAttackVisitor visitor) {
        visitor.visitBA(this);
    }

    public void performBasicAttack(){
        System.out.println(this.getName() + " performed a basic attack!");
    }




}
