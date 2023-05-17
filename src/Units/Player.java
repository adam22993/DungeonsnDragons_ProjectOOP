package Units;

import java.util.Random;

public abstract class Player extends Unit {
    //###################### Class related ######################
    private Integer Experience_cap;
    private Integer Experience;
    private Integer Level;
    private Random rand = new Random();
    public Player(String name) {
        // TODO Random choose stats constructor stub
        super(name, 140, 140, 10, 10);
        this.Experience_cap = 50;
        this.Experience = 0;
        this.Level = 1;
    }


    protected void levelUp(){
        this.Level += 1;
        this.Experience_cap += 50;
        this.Attack_points += 5 * this.Level;
        this.Defense_points += 2 * this.Level;
        this.Health_pool += 10 * this.Level;
        this.Health_amount = this.Health_pool;
    }

    abstract protected void castSpecialAbility();

    public void gainExperience(Integer exp){
        this.Experience += exp;
        if (this.Experience >= this.Experience_cap){
            this.levelUp();
        }
    }

    protected void onGameTick(){
        if (this.Health_amount < this.Health_pool){
            this.Health_amount += 10;
        }
    }

    //###################### Name related ######################
    private void renamePlayer(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public int getExperience(){
        return this.Experience;
    }

    public int getExperience_cap(){
        return this.Experience_cap;
    }

    public int getLevel(){
        return this.Level;
    }

    //###################### Stats related ######################
    public int getHealth_pool(){
        return this.Health_pool;
    }

    public int getHealth_amount(){
        return this.Health_amount;
    }

    public int getAttack_points(){
        return this.Attack_points;
    }

    public int getDefense_points(){
        return this.Defense_points;
    }





}
