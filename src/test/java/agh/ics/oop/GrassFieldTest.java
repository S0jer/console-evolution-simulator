package agh.ics.oop;

import agh.ics.oop.world.GrassField;
import agh.ics.oop.world.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GrassFieldTest {

    @Test
    void shouldBeAbleToMoveAnimalInEmptyMap() {
        WorldMap map = new GrassField(10);

        boolean result = map.canMoveTo(new Vector2d(1, 1));

        assertThat(result).isTrue();
    }

    @Test
    void shouldBeAbleToMoveAnimalInEmptyMapFalse() {
        WorldMap map = new GrassField(15);
        String[] args = {"l", "l"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2, 2));
        positions.add(new Vector2d(3, 4));
        Engine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        boolean result = map.canMoveTo(new Vector2d(2, 2));

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfPlaceIsOccupiedFalse() {
        WorldMap map = new GrassField(0);

        boolean result = map.isOccupied(new Vector2d(2, 2));

        assertThat(result).isFalse();
    }

    @Test
    void shouldCheckIfPlaceIsOccupiedTrue() {
        WorldMap map = new GrassField(15);
        String[] args = {"l", "l"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2, 2));
        positions.add(new Vector2d(3, 4));

        Engine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        boolean result = map.isOccupied(new Vector2d(2, 2));

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenIdexOutsideOfMap() {
        WorldMap map = new GrassField(0);

        boolean result = map.isOccupied(new Vector2d(-1, -1));

        assertThat(result).isFalse();
    }


    @Test
    void shouldCheckWhichObjectIsOnTheXYInstanceOfMap() {
        WorldMap map = new GrassField(0);
        assertThat(map.objectAt(new Vector2d(2, 2))).isNotEqualTo(new Animal(map));
    }

    @Test
    void shouldBeAbleToPlaceAnimaInEmptyMap() {
        WorldMap map = new GrassField(1);
        Animal animal = new Animal(new Vector2d(2, 2), map);

        map.place(animal);

        assertThat(map.objectAt(new Vector2d(2, 2))).isEqualToComparingFieldByField(animal);
    }

    @Test
    void shouldntBeAbleToPlaceAnimaInMap() {
        WorldMap map = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2), map);

        map.place(animal);
        boolean result = map.place(animal);

        assertThat(result).isFalse();
    }
}
