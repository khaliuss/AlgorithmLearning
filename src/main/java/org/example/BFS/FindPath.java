package org.example.BFS;

import org.example.BFS.entities.Rock;

import java.util.*;

public class FindPath {

    private int GRID_ROW = 5;
    private int GRID_COL = 8;
    private Coordinates startPoint;
    private Coordinates targetCoordinate;
    private Map map;


    Queue<Coordinates> queue = new LinkedList<>();
    HashMap<Coordinates, Coordinates> visited = new HashMap<>();

    public FindPath(Map map, Coordinates startPoint, Coordinates targetCoordinate) {
        this.startPoint = startPoint;
        this.targetCoordinate = targetCoordinate;
        this.map = map;
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

    public List<Coordinates> path() {
        queue.add(startPoint);
        visited.put(startPoint, null);
        List<Coordinates> neighbors = new ArrayList<>();

        while (!queue.isEmpty()) {
            Coordinates checkCoordinate = queue.poll();
            if (checkCoordinate.equals(targetCoordinate)) {
                neighbors.add(checkCoordinate);
                Coordinates neighborKey = checkCoordinate;

                while (true) {
                    if (visited.containsKey(neighborKey)) {
                        Coordinates neighbor = visited.get(neighborKey);
                        if (neighbor == null) {
                            Collections.reverse(neighbors);
                            return neighbors;
                        }
                        neighbors.add(neighbor);
                        neighborKey = neighbor;
                    } else {
                        return neighbors;
                    }
                }
            } else {
                findAvailable(checkCoordinate);
            }
        }
        return neighbors;
    }


}
