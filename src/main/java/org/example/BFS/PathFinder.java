package org.example.BFS;

import org.example.BFS.entities.*;

import java.util.*;

import static org.example.BFS.Constants.*;

public class PathFinder {

    private Coordinate currentPosition;
    private GameMap gameMap;
    private Renderer renderer;


    Queue<Coordinate> queue = new LinkedList<>();
    HashMap<Coordinate, Coordinate> visited = new HashMap<>();

    public PathFinder(GameMap gameMap, Coordinate currentPosition, Renderer renderer) {
        this.currentPosition = currentPosition;
        this.gameMap = gameMap;
        this.renderer = renderer;
    }

    int[] dRow = new int[]{0, -1, -1, -1, 0, +1, +1, +1};
    int[] dCol = new int[]{+1, +1, 0, -1, -1, -1, 0, +1};

    private void findAvailable(Coordinate cameFrom) {
        for (int i = 0; i < 8; i++) {
            int moveRow = dRow[i] + cameFrom.row;
            int moveCol = dCol[i] + cameFrom.col;
            if ((moveRow >= 0 && moveRow < GRID_ROW) && (moveCol >= 0 && moveCol < GRID_COL)) {
                Coordinate coordinate = new Coordinate(moveRow, moveCol);
                if (gameMap.getEntity(coordinate) instanceof Rock) {
                    continue;
                }
                if (!visited.containsKey(coordinate)) {
                    queue.add(coordinate);
                    visited.put(coordinate, cameFrom);
                }
            }
        }
    }

    public void huntTargets(Entity targetType) {
        queue.add(currentPosition);
        visited.put(currentPosition, null);

        while (gameMap.isFoodExist(targetType.getClass())) {

            Coordinate current = queue.poll();
            Entity currentEntity = gameMap.getEntity(current);

            findTarget(targetType, currentEntity, current);
        }


    }

    private void findTarget(Entity targetType, Entity currentEntity, Coordinate current) {

        if (targetType.getClass().isInstance(currentEntity)){
            List<Coordinate> path = new ArrayList<>();
            Coordinate neighborKey = current;
            path.add(neighborKey);
            while (true) {
                Coordinate neighbor = visited.get(neighborKey);
                if (neighbor == null) {
                    break;
                }
                path.add(neighbor);
                neighborKey = neighbor;
            }
            Collections.reverse(path);

            //Render
            for (int i = 0; i < path.size(); i++) {
                clearConsole();
                renderer.render(gameMap);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (i >= path.size() - 1) continue;
                gameMap.deleteEntity(path.get(i));
                gameMap.putEntity(path.get(i + 1), new Rabbit());
                clearConsole();
            }
            currentPosition = path.getLast();
            queue.clear();
            visited.clear();
            queue.add(currentPosition);
            visited.put(currentPosition, null);

        } else {
            if (current != null) {
                findAvailable(current);
            }
        }
    }

    private static void clearConsole() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}

