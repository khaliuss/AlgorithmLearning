package org.example.BFS;

import org.example.BFS.entities.Carrot;
import org.example.BFS.entities.Entity;
import org.example.BFS.entities.Rabbit;

import java.util.HashMap;

public class Map {

    HashMap<Coordinates, Entity> entities = new HashMap();

    public void putEntity(Coordinates coordinates,Entity entity){
        entities.put(coordinates,entity);
    }

    public void deleteEntity(Coordinates coordinates){
        entities.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates){
        return entities.get(coordinates);
    }

    public boolean isEmpty(Coordinates coordinates){
        return !entities.containsKey(coordinates);
    }
}
