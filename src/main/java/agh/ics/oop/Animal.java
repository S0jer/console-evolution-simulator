package agh.ics.oop;

import agh.ics.oop.world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Animal implements MapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d animalPosition;
    private List<PositionChangeObserver> observers = new ArrayList<>();
    private final WorldMap map;


    public Animal(WorldMap map) {
        this.map = map;
    }

    public Animal(Vector2d animalPosition, WorldMap map) {
        this.animalPosition = animalPosition;
        this.map = map;
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case SOUTH -> "S";
            case NORTH -> "N";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public boolean isAt(Vector2d position) {
        return (position.x == animalPosition.x && position.y == animalPosition.y);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                if (map.canMoveTo(this.animalPosition.add(this.orientation.toUnitVector()))) {
                    positionChanged(this.animalPosition, this.animalPosition.add(this.orientation.toUnitVector()));
                    this.animalPosition = this.animalPosition.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(this.animalPosition.subtract(this.orientation.toUnitVector()))) {
                    positionChanged(this.animalPosition, this.animalPosition.subtract(this.orientation.toUnitVector()));
                    this.animalPosition = this.animalPosition.subtract(this.orientation.toUnitVector());
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public Vector2d getPosition() {
        return animalPosition;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public void setAnimalPosition(Vector2d animalPosition) {
        this.animalPosition = animalPosition;
    }

    public void positionChanged(Vector2d oldV, Vector2d newV) {
        for (PositionChangeObserver o : this.observers) {
            o.positionChanged(oldV, newV);
        }
    }

    public void addObserver(PositionChangeObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(PositionChangeObserver observer) {
        this.observers.remove(observer);
    }
}