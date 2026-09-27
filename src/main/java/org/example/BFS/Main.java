package org.example.BFS;

import org.example.BFS.entities.Carrot;
import org.example.BFS.entities.Rabbit;
import org.example.BFS.entities.Renderer;
import org.example.BFS.entities.Rock;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Map map = new Map();
        Renderer renderer = new Renderer();
        Coordinates rabbitCoordinate = new Coordinates(4,1);
        Coordinates carrotCoordinate = new Coordinates(1,6);
        Coordinates carrotCoordinate2 = new Coordinates(0,0);
        Coordinates rockCoordinate = new Coordinates(1,5);
        Coordinates rockCoordinate2 = new Coordinates(2,5);

        map.putEntity(rabbitCoordinate,new Rabbit());
        map.putEntity(carrotCoordinate,new Carrot());
        map.putEntity(carrotCoordinate2,new Carrot());
        map.putEntity(rockCoordinate,new Rock());
        map.putEntity(rockCoordinate2,new Rock());

        FindPath findPath = new FindPath(map,rabbitCoordinate,carrotCoordinate);
        List<Coordinates> paths = findPath.path();

        for (Coordinates path : paths){
            map.putEntity(path,new Rabbit());
            renderer.render(map);
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();

            map.deleteEntity(path);
            clearConsole();

        }
    }

    private static void clearConsole() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
