package org.example.BFS;

import org.example.BFS.entities.Entity;

import java.util.Random;

import static org.example.BFS.Constants.*;

public class RandomInit {

    private Random random = new Random();
    private GameMap map;

    public RandomInit(GameMap map) {
        this.map = map;
    }

    public void create(Entity entity) {
        int count = 0;
        while (count < 6) {
            Coordinate coordinate = new Coordinate(random.nextInt(GRID_ROW), random.nextInt(GRID_COL));
            map.putEntity(coordinate,entity);
            count++;
        }
    }
}
