package Units;


public abstract class Unit extends Tile {
    String name;
    Integer Health_pool;
    Integer Health_amount;
    Integer Attack_points;
    Integer Defense_points;

    public Unit(char Char ,String name, Integer Health_pool, Integer Health_amount, Integer Attack_points, Integer Defense_points) {
        super(Char);
        this.name = name;
        this.Health_pool = Health_pool;
        this.Health_amount = Health_amount;
        this.Attack_points = Attack_points;
        this.Defense_points = Defense_points;

    }

    //###################### Actions related ######################

    abstract protected void onGameTick();
    public void attack(Unit enemy){
        int damage = this.Attack_points - enemy.Defense_points;
        if (damage < 0){
            damage = 0;
        }
        enemy.Health_amount -= damage;
        if (enemy.Health_amount < 0){
            enemy.Health_amount = 0;
        }
    }


}
//public abstract class Unit extends Tile {
//	...
//
//    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
//        super(tile);
//        ...
//    }
//
//    protected void initialize(Position position, MessageCallback messageCallback){
//        ...
//    }
//
//    protected int attack(){
//		...
//    }
//
//    public int defend(){
//        ...
//    }
//
//    // Should be automatically called once the unit finishes its turn
//    public abstract void processStep();
//
//    // What happens when the unit dies
//    public abstract void onDeath();
//
//    // This unit attempts to interact with another tile.
//    public void interact(Tile tile){
//		...
//    }
//
//    public void visit(Empty e){
//		...
//    }
//
//    public abstract void visit(Player p);
//    public abstract void visit(Enemy e);
//
//    // Combat against another unit.
//    protected void battle(Unit u){
//        ...
//    }
//
//
//    public String describe() {
//        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
//    }
//}

