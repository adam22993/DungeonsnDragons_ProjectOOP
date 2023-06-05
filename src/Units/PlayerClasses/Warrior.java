package Units.PlayerClasses;
import Units.ADDITIONAL.Position;
import Units.Abstracts.Player;
import Speaches_Screens.Quotes;
import java.util.Random;

public class Warrior extends Player {

    Integer Health_pool;
    Integer Health_amount;
    Integer Attack_points;
    Integer Defense_points;
    protected int abilityCD;
    Random Random = new Random();
    public Warrior(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, int abilityCD, Position position) {
        super(Char, name, Health_pool, Attack_points, Defense_points, position);
        this.abilityCD = abilityCD;
    }

    @Override
    protected void castSpecialAbility(){
        // TODO: implement special ability
    }

    protected void levelUp(){
        if (this.currEXP < this.maxEXP){
            return;
        }
        super.levelUp();
        abilityCD = 0;
        Health_amount = Health_pool + 5 * level;
        Attack_points = Attack_points + 2 * level;
        Defense_points = Defense_points + level;
        this.levelUp();
    }
    //################### Accepts Visitor ###################

    public void performBasicAttack(){
        System.out.println(this.getName() + " performed a basic attack!");
    }




}
