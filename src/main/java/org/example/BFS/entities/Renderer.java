package org.example.BFS.entities;

import org.example.BFS.Coordinates;
import org.example.BFS.Map;

public class Renderer {

    public void render(Map map){
        for (int row = 4; row >= 0; row--) {
            String line = "";
            for (int colum = 0; colum < 8 ; colum++) {
                Coordinates coordinates = new Coordinates(row,colum);
                if (map.isEmpty(coordinates)) {
                    line+="::";
                }else {
                    line+=map.getEntity(coordinates);
                }
            }
            System.out.println(line);
        }
    }
}
