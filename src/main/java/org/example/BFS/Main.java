package org.example.BFS;

import org.example.BFS.entities.Carrot;
import org.example.BFS.entities.Rabbit;
import org.example.BFS.entities.Renderer;
import org.example.BFS.entities.Rock;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        Renderer renderer = new Renderer();

        RandomInit randomInit = new RandomInit(gameMap);
        randomInit.create(new Carrot());

        Coordinate rabbitCoordinate = new Coordinate(5, 1);
        Coordinate rockCoordinate = new Coordinate(1, 5);
        Coordinate rockCoordinate2 = new Coordinate(2, 5);

        gameMap.putEntity(rabbitCoordinate, new Rabbit());
        gameMap.putEntity(rockCoordinate, new Rock());
        gameMap.putEntity(rockCoordinate2, new Rock());

        PathFinder pathFinder = new PathFinder(gameMap, rabbitCoordinate, renderer);
        pathFinder.huntTargets(new Carrot());

    }


}
