package Units.Abstracts;


import Units.ADDITIONAL.Position;

public abstract class Player extends Unit {
    //###################### Class related ######################
    protected Integer maxEXP; // TODO change to consumablePoints one day
    protected Integer currEXP;
    protected Integer level;

    public Player(char Char, String name, Integer Health_pool, Integer Attack_points, Integer Defense_points, Position position) {
        // TODO Random choose stats constructor stub
        super(Char, name, Health_pool, Attack_points, Defense_points, position);
        this.maxEXP = 50;
        this.currEXP = 0;
        this.level = 1;
    }


    protected void levelUp(){
        this.currEXP -= this.maxEXP;
        this.level += 1;
        this.maxEXP = 50 * this.level;
        this.attackPoints += 5 * this.level;
        this.defensePoints += 2 * this.level;
        this.healthPool += 10 * this.level;
        this.healthAmount = this.healthPool;
    }

    abstract protected void castSpecialAbility(); // TODO: implement special ability in subclasses - think of way to add more attacks

    public void kill(Enemy e){
        gainExperience(e.giveExperience());
        this.swapPositions(e);
        e = new Empty(e.getPosition());
        if (this.currEXP >= this.maxEXP){
            this.levelUp();
        }
    }
    public void gainExperience(int experience){
        this.currEXP += experience;
    }
    protected void onGameTick(){
        //TODO: implement player logic
    }

    //###################### Name related ######################
    private void renamePlayer(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public int getCurrEXP(){
        return this.currEXP;
    }

    public int getMaxEXP(){
        return this.maxEXP;
    }

    public int getLevel(){
        return this.level;
    }

    //###################### Stats related ######################
    public int getHealth_pool(){
        return this.healthPool;
    }

    public int getHealth_amount(){
        return this.healthAmount;
    }

    public int getAttack_points(){
        return this.attackPoints;
    }

    public int getDefense_points(){
        return this.defensePoints;
    }

    //######################### Visitors ##########################






}
