package org.example.BFS;

import org.example.BFS.entities.Rock;

import java.util.*;

public class FindPath {

    private int GRID_ROW = 5;
    private int GRID_COL = 8;
    private Coordinates startPoint;
    private List<Coordinates> targetCoordinates;
    private Map map;


    Queue<Coordinates> queue = new LinkedList<>();
    HashMap<Coordinates, Coordinates> visited = new HashMap<>();

    public FindPath(Map map, Coordinates startPoint, List<Coordinates> targetCoordinates) {
        this.startPoint = startPoint;
        this.targetCoordinates = targetCoordinates;
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

    private List<Coordinates> findFood() {
        queue.add(startPoint);
        visited.put(startPoint, null);
        List<Coordinates> foundedFood = new ArrayList<>();

        while (!queue.isEmpty()) {
            Coordinates checkCoordinate = queue.poll();
            if (targetCoordinates.contains(checkCoordinate)) {
                foundedFood.add(checkCoordinate);
            } else {
                findAvailable(checkCoordinate);
            }
        }

        return foundedFood;
    }

    public List<Coordinates> path() {
        List<Coordinates> foodCoordinates = findFood();
        List<Coordinates> neighbors = new ArrayList<>();
        for (Coordinates food : foodCoordinates) {
            Coordinates neighborKey = food;
            neighbors.add(food);
            while (true) {
                Coordinates neighbor = visited.get(neighborKey);
                if (neighbor == null) {
                    break;
                }
                neighbors.add(neighbor);
                neighborKey = neighbor;
            }
            // Добавить сюда реализацию того как заец строит путь из поседнего места
            // Я Думаю это можно реализовать даже чуть в другом месте
            // Когда получили список точек с марковкой ->
            // сделать создание нового пути может с помощью метода findAvailable
            // надо еще подумать
        }
        return neighbors;
    }


}

