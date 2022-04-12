package agh.ics.oop.world;


import agh.ics.oop.Animal;
import agh.ics.oop.Vector2d;

public interface WorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

}

