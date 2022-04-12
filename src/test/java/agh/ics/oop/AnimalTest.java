package agh.ics.oop;

import agh.ics.oop.world.RectangularMap;
import agh.ics.oop.world.WorldMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AnimalTest {

    static Stream<Arguments> orientationRightProviderFalse() {
        return Stream.of(
                arguments(AnimalCreator(2, 2, MapDirection.NORTH), MapDirection.NORTH, false),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), MapDirection.NORTH, false),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), MapDirection.EAST, false),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), MapDirection.EAST, false)
        );
    }

    @ParameterizedTest(name = "should return {3}")
    @MethodSource("orientationRightProviderFalse")
    void shouldTestOrientationRightFalse(Animal animal, MapDirection expected, boolean check) {
        animal.move(MoveDirection.RIGHT);
        boolean result = expected.equals(animal.getOrientation());
        assertEquals(check, result);
    }

    static Stream<Arguments> orientationRightProviderTrue() {
        return Stream.of(
                arguments(AnimalCreator(2, 2, MapDirection.NORTH), MapDirection.EAST, true),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), MapDirection.SOUTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), MapDirection.WEST, true),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), MapDirection.NORTH, true)
        );
    }

    @ParameterizedTest(name = "should return {3}")
    @MethodSource("orientationRightProviderTrue")
    void shouldTestOrientationRightTrue(Animal animal, MapDirection expected, boolean check) {
        animal.move(MoveDirection.RIGHT);

        boolean result = expected.equals(animal.getOrientation());

        assertEquals(check, result);
    }


    static Stream<Arguments> orientationLeftProviderTrue() {
        return Stream.of(
                arguments(AnimalCreator(2, 2, MapDirection.NORTH), MapDirection.WEST, true),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), MapDirection.NORTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), MapDirection.EAST, true),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), MapDirection.SOUTH, true)
        );
    }

    @ParameterizedTest(name = "should return {3}")
    @MethodSource("orientationLeftProviderTrue")
    void shouldTestOrientationLeftTrue(Animal animal, MapDirection expected, boolean check) {
        animal.move(MoveDirection.LEFT);

        boolean result = expected.equals(animal.getOrientation());

        assertEquals(check, result);
    }


    static Stream<Arguments> orientationLeftProviderFalse() {
        return Stream.of(
                arguments(AnimalCreator(2, 2, MapDirection.NORTH), MapDirection.EAST, false),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), MapDirection.SOUTH, false),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), MapDirection.WEST, false),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), MapDirection.NORTH, false)
        );
    }

    @ParameterizedTest(name = "should return {3}")
    @MethodSource("orientationLeftProviderFalse")
    void shouldTestOrientationLeftFalse(Animal animal, MapDirection expected, boolean check) {
        animal.move(MoveDirection.LEFT);

        boolean result = expected.equals(animal.getOrientation());

        assertEquals(check, result);
    }


    static Stream<Arguments> borderAndMovesArgumentsProvider() {
        String[] movesForward = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        String[] movesBackward = {"b", "b", "b", "b", "b", "b", "b", "b", "b", "b"};
        String[] movesAround = {"r", "r", "f", "l", "f", "f", "f", "l", "f", "f", "r", "b"};
        return Stream.of(
                arguments(AnimalCreator(2, 2, MapDirection.NORTH), OptionsParser.parse(movesForward), new Vector2d(2, 4), MapDirection.NORTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), OptionsParser.parse(movesForward), new Vector2d(4, 2), MapDirection.EAST, true),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), OptionsParser.parse(movesForward), new Vector2d(2, 0), MapDirection.SOUTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), OptionsParser.parse(movesForward), new Vector2d(0, 2), MapDirection.WEST, true),

                arguments(AnimalCreator(2, 2, MapDirection.NORTH), OptionsParser.parse(movesBackward), new Vector2d(2, 0), MapDirection.NORTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.EAST), OptionsParser.parse(movesBackward), new Vector2d(0, 2), MapDirection.EAST, true),
                arguments(AnimalCreator(2, 2, MapDirection.SOUTH), OptionsParser.parse(movesBackward), new Vector2d(2, 4), MapDirection.SOUTH, true),
                arguments(AnimalCreator(2, 2, MapDirection.WEST), OptionsParser.parse(movesBackward), new Vector2d(4, 2), MapDirection.WEST, true),

                arguments(AnimalCreator(1, 1, MapDirection.NORTH), OptionsParser.parse(movesAround), new Vector2d(3, 2), MapDirection.EAST, true),
                arguments(AnimalCreator(1, 3, MapDirection.EAST), OptionsParser.parse(movesAround), new Vector2d(2, 1), MapDirection.SOUTH, true),
                arguments(AnimalCreator(4, 4, MapDirection.WEST), OptionsParser.parse(movesAround), new Vector2d(2, 3), MapDirection.NORTH, true),
                arguments(AnimalCreator(0, 0, MapDirection.WEST), OptionsParser.parse(movesAround), new Vector2d(0, 2), MapDirection.NORTH, true),
                arguments(AnimalCreator(0, 4, MapDirection.WEST), OptionsParser.parse(movesAround), new Vector2d(0, 3), MapDirection.NORTH, true),
                arguments(AnimalCreator(4, 0, MapDirection.SOUTH), OptionsParser.parse(movesAround), new Vector2d(2, 0), MapDirection.WEST, true)
        );
    }

    @ParameterizedTest(name = "should return {3}")
    @MethodSource("borderAndMovesArgumentsProvider")
    void shouldTestBorderAndMoves(Animal animal, List<MoveDirection> path, Vector2d endPosition, MapDirection endOrientation, boolean check) {
        Animal afterMovesAnimal = doPath(animal, path);
        System.out.println(afterMovesAnimal.getPosition());

        boolean result = (afterMovesAnimal.isAt(endPosition) && afterMovesAnimal.getOrientation().equals(endOrientation));

        assertEquals(check, result);
    }


    static Animal doPath(Animal animal, List<MoveDirection> path) {
        for (MoveDirection doMove : path) {
            animal.move(doMove);
        }
        return animal;
    }

    static Animal AnimalCreator(int x, int y, MapDirection direction) {
        WorldMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(map);
        animal.setAnimalPosition(new Vector2d(x, y));
        animal.setOrientation(direction);

        return animal;
    }
}