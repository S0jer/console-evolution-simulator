package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class MapBoundary implements PositionChangeObserver {

    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int maxY = Integer.MIN_VALUE;
    private HashMap<Vector2d, Animal> animalMap = new HashMap<>();

    public MapBoundary() {
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.animalMap.put(newPosition, this.animalMap.get(oldPosition));
        this.animalMap.remove(oldPosition);

        if (oldPosition.x == minX || oldPosition.x == maxX || oldPosition.y == minY || oldPosition.y == maxY) {
            changeBorders(newPosition);
        }
    }

    public void grassborder(Map<Vector2d, Grass> grassMap) {
        for (Vector2d q : grassMap.keySet()) {
            changeBorders(q);
        }
    }

    protected void changeBorders(Vector2d n) {
        if (n.x > maxX) maxX = n.x;
        else if (n.x < minX) minX = n.x;
        if (n.y > maxY) maxY = n.y;
        else if (n.y < minY) minY = n.y;
    }

    public Vector2d botLeft() {
        return new Vector2d(minX, minY);
    }

    public Vector2d topRight() {
        return new Vector2d(maxX, maxY);
    }
}
