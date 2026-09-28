package org.example.BFS;

import org.example.BFS.entities.Carrot;
import org.example.BFS.entities.Rabbit;
import org.example.BFS.entities.Renderer;
import org.example.BFS.entities.Rock;

import java.util.*;

public class FindPath {

    private int GRID_ROW = 5;
    private int GRID_COL = 8;
    private Coordinates startPoint;
    private List<Coordinates> targetCoordinates;
    private Map map;
    private Renderer renderer;


    Queue<Coordinates> queue = new LinkedList<>();
    HashMap<Coordinates, Coordinates> visited = new HashMap<>();

    public FindPath(Map map, Coordinates startPoint, List<Coordinates> targetCoordinates,Renderer renderer) {
        this.startPoint = startPoint;
        this.targetCoordinates = targetCoordinates;
        this.map = map;
        this.renderer = renderer;
    }

    int[] dRow = new int[]{0, -1, -1, -1, 0, +1, +1, +1};
    int[] dCol = new int[]{+1, +1, 0, -1, -1, -1, 0, +1};

    private void findAvailable(Coordinates cameFrom) {
        for (int i = 0; i < 8; i++) {
            int moveRow = dRow[i] + cameFrom.row;
            int moveCol = dCol[i] + cameFrom.col;
            if ((moveRow >= 0 && moveRow < GRID_ROW) && (moveCol >= 0 && moveCol < GRID_COL)) {
                Coordinates coordinate = new Coordinates(moveRow, moveCol);
                if (map.getEntity(coordinate) instanceof Rock) {
                    continue;
                }
                if (!visited.containsKey(coordinate)) {
                    queue.add(coordinate);
                    visited.put(coordinate, cameFrom);
                }
            }
        }
    }

    public void findFood() {
        queue.add(startPoint);
        visited.put(startPoint, null);

        while (map.isFoodExist(Carrot.class)) {
            Coordinates checkCoordinate = queue.poll();
            if (map.getEntity(checkCoordinate) instanceof Carrot){
                List<Coordinates> neighbors = new ArrayList<>();
                Coordinates neighborKey = checkCoordinate;
                neighbors.add(neighborKey);
                while (true) {
                    Coordinates neighbor = visited.get(neighborKey);
                    if (neighbor == null) {
                        break;
                    }
                    neighbors.add(neighbor);
                    neighborKey = neighbor;
                }
                Collections.reverse(neighbors);

                //Render
                for (int i = 0; i < neighbors.size(); i++) {
                    clearConsole();
                    renderer.render(map);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (i >= neighbors.size() - 1) continue;
                    map.deleteEntity(neighbors.get(i));
                    map.putEntity(neighbors.get(i + 1), new Rabbit());
                    clearConsole();
                }
                startPoint = neighbors.getLast();
                queue.clear();
                visited.clear();
                queue.add(startPoint);
                visited.put(startPoint, null);

            } else {
                if (checkCoordinate != null) {
                    findAvailable(checkCoordinate);
                }
            }
        }


    }

    private static void clearConsole() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}

