package Units.Abstracts;


import Patterns.Visitor.UnitInteractionVisited;
import Patterns.Visitor.UnitInteractionVisitor;
import Units.ADDITIONAL.Empty;
import Units.ADDITIONAL.Wall;

public abstract class Player extends Unit implements UnitInteractionVisited, UnitInteractionVisitor {
    //###################### Class related ######################
    protected Integer maxEXP; // TODO change to consumablePoints one day
    protected Integer currEXP;
    protected Integer level;

    public Player(String name, Integer Health_pool, Integer Attack_points, Integer Defense_points) {
        // TODO Random choose stats constructor stub
        super(name, Health_pool, Attack_points, Defense_points);
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


    public void gainExperience(int experience){
        this.currEXP += experience;
    }
    protected void onGameTick(){
        //TODO: implement player logic
    }

//    public void kill(Enemy e){
//        gainExperience(e.giveExperience());
//        this.swapPosition(e);
//        Empty em = new Empty('.', e.getPosition());
//        if (this.currEXP >= this.maxEXP){
//            this.levelUp();
//        }
//    }

    //###################### Name related ######################
    private void renamePlayer(String name){
        this.name = name;
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

    //######################### Visitors ##########################

    @Override
    public void accept(UnitInteractionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Enemy enemy) {

    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void visit(Empty empty) {

    }

    @Override
    public void visit(Wall wall) {

    }




}
