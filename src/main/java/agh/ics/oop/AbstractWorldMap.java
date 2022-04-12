package agh.ics.oop;

import agh.ics.oop.world.WorldMap;

import java.util.*;

import static java.lang.Math.sqrt;

public abstract class AbstractWorldMap implements WorldMap, PositionChangeObserver {

    protected Map<Vector2d, Grass> grassMap = new HashMap<>();
    protected Map<Vector2d, Animal> animalMap = new HashMap<>();
    private List<PositionChangeObserver> observers = new ArrayList<>();
    protected MapBoundary borders;

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(borders.botLeft(), borders.topRight());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.animalMap.get(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPos = animal.getPosition();
        if (canMoveTo(animalPos)) {
            animal.addObserver(this);
            animal.addObserver(this.borders);
            this.animalMap.put(animalPos, animal);
            borders.changeBorders(animalPos);
            return true;
        }
        throw new IllegalArgumentException("Can not add " + animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (this.animalMap.get(position) instanceof Animal || this.grassMap.get(position) instanceof Grass);
    }

    public void setGrass(int grassFields) {
        Random random = new Random();
        int i = 0;
        while (i < grassFields) {
            int x = random.nextInt((int) sqrt(grassFields * 10));
            int y = random.nextInt((int) sqrt(grassFields * 10));
            Vector2d vector2d = new Vector2d(x, y);
            if (this.grassMap.get(vector2d) == null && this.animalMap.get(vector2d) == null) {
                this.grassMap.put(vector2d, new Grass(vector2d));
                i++;
            }
        }
        borders.grassborder(this.grassMap);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.animalMap.get(position) instanceof Animal) {
            return this.animalMap.get(position);
        }
        return this.grassMap.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.animalMap.put(newPosition, this.animalMap.get(oldPosition));
        this.animalMap.remove(oldPosition);
    }
}
