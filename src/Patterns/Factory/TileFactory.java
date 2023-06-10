package Patterns.Factory;

import Units.ADDITIONAL.*;
import Units.Abstracts.*;
import Units.EnemyTypes.*;
import Units.PlayerClasses.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class TileFactory {
    private final List<Supplier<Player>> playersList;
    private final Map<Character, Supplier<Enemy>> enemiesMap;
    private final Player selected;

    public TileFactory(int playerChoice){
        this.playersList = initPlayers();
        this.enemiesMap = initEnemies();
        this.selected = playersList.get(playerChoice).get();
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3, 25, 15),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5),
//                () -> new Boss('M', "The Mountain", 1000, 60, 25, 500, 6, 5),
//                () -> new Boss('C', "Queen Cersei", 100, 10, 10,1000, 1, 8),
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10),

                () -> new Monster('z', "Wright", 600, 30, 15,100, 3),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250,  4),
                () -> new Monster('g', "Giant-Wright",1500, 100, 40,500,   5),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),
//                () -> new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3),
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getChar(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4, 3, '@', new Position(0,0)),
                () -> new Warrior("The Hound", 400, 20, 6, 5, '@', new Position(0,0)),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6, '@', new Position(0,0)),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4, '@', new Position(0,0)),
                () -> new Rogue("Arya Stark", 150, 40, 2, 20, '@', new Position(0,0)),
                () -> new Rogue("Bronn", 250, 35, 3, 50, '@', new Position(0,0))
//                () -> new Hunter("Ygritte", 220, 30, 2, 6) // TODO: Implement for bonus
        );
    }
//    private Map<String, Supplier<Player>> initPlayers() {
//        List<Supplier<Player>> players = Arrays.asList(
//                () -> new Warrior("Jon Snow", 300, 30, 4, 3),
//                () -> new Warrior("The Hound", 400, 20, 6, 5),
//                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
//                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
//                () -> new Rogue("Arya Stark", 150, 40, 2,20),
//                () -> new Rogue("Bronn", 250, 35, 3, 50)
////                () -> new Hunter("Ygritte", 220, 30, 2, 6)
//        );
//        return players.stream().collect(Collectors.toMap(s -> s.get().getName(), Function.identity()));
//    }

    public List<Enemy> listEnemies(){
        return enemiesMap.values().stream().map(Supplier::get).collect(Collectors.toList());
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }
    public Map<Character, Supplier<Enemy>> getEnemiesMap() {
        return enemiesMap;
    }

    public Tile produceEnemy(char tile, Position position/*, MessageCallback messageCallback, EnemyDeathCallback enemyDeathCallback*/) {
        Enemy e = enemiesMap.get(tile).get();
        e.setPosition(position);
        e.setUnit(e);
        return e;
    }

    public Player producePlayer(int idx, Position position/*, MessageCallback messageCallback, PlayerDeathCallback playerDeathCallback*/){
		Player p = playersList.get(idx).get();
        p.setPosition(position);
        p.setUnit(p);
        return p;
    }

    public Tile produceEmpty(Position position){
        Unit e = new Empty('.', "An empty tile");
        e.setPosition(position);
        e.setUnit(e);
        return e;
    }

    public Tile produceWall(Position position){
        Wall w = new Wall('#', "A wall");
        w.setPosition(position);
        w.setUnit(w);
        return w;
    }
}