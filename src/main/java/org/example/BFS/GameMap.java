package org.example.BFS;

import org.example.BFS.entities.Entity;

import java.util.HashMap;

public class GameMap {

    HashMap<Coordinate, Entity> entities = new HashMap();

    public void putEntity(Coordinate coordinate, Entity entity){
        entities.put(coordinate,entity);
    }

    public void deleteEntity(Coordinate coordinate){
        entities.remove(coordinate);
    }

    public Entity getEntity(Coordinate coordinate){
        return entities.get(coordinate);
    }

    public boolean isEmpty(Coordinate coordinate){
        return !entities.containsKey(coordinate);
    }


    public boolean isFoodExist(Class<? extends Entity> type) {
        boolean exist = false;
        for (Entity entity : entities.values()){
            if (type.isInstance(entity)) {
                return true;
            }
        }
        return exist;
    }
}
