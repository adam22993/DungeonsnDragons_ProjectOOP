package GameBoard.Levels;

import Units.ADDITIONAL.*;
import Units.Abstracts.*;

public record Level(String name, Tile[][] tiles) {
    public Level(String name, Tile[][] tiles) {
        if (name == null || tiles == null) {
            throw new IllegalArgumentException("Level name and tiles cannot be null.");
        }
        this.name = name;
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public String getName() {
        return this.name;
    }

    public void printLevel() {
        for (Tile[] tile : tiles) {
            for (Tile value : tile) {
                if (value.getUnit() instanceof Player) {
                    System.out.print("P");
                } else if (value.getUnit() instanceof Enemy) {
                    System.out.print("E");
                } else if (value.getUnit() instanceof Wall) {
                    System.out.print("W");
                } else if (value.getUnit() instanceof Empty) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
