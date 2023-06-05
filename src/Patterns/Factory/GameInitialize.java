package Patterns.Factory;

class GameInitialize {
    public void parseLevel() {
        MessageCallback m = (s) -> System.out.println(s);
        TileFactory factory = new TileFactory();
        for char c in chars {
            if c == '@' {

            }
			else if c == '#' {

            }
			else if c == '.' {

            } else{
                Enemy e = factory.produceEnemy(c);
                e.initialize(Position.at(i, j),
                        () -> {
                            player.kill(e)
                            board.remove(e)
                        },
                        m)
            }
        }
    }
}
