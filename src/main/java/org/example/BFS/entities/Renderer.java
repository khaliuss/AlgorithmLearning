package org.example.BFS.entities;

import org.example.BFS.Coordinate;
import org.example.BFS.GameMap;

import static org.example.BFS.Constants.*;

public class Renderer {

    public void render(GameMap gameMap){
        for (int row = GRID_ROW; row >= 0; row--) {
            String line = "";
            for (int colum = 0; colum < GRID_COL ; colum++) {
                Coordinate coordinate = new Coordinate(row,colum);
                if (gameMap.isEmpty(coordinate)) {
                    line+="::";
                }else {
                    line+= gameMap.getEntity(coordinate);
                }
            }
            System.out.println(line);
        }
    }
}
