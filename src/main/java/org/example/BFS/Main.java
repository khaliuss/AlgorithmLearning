package org.example.BFS;

import org.example.BFS.entities.Carrot;
import org.example.BFS.entities.Rabbit;
import org.example.BFS.entities.Renderer;
import org.example.BFS.entities.Rock;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

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

        FindPath findPath = new FindPath(map,rabbitCoordinate,Arrays.asList(carrotCoordinate,carrotCoordinate2),new Renderer());
        findPath.findFood();

    }


}
