package agh.ics.oop;

import agh.ics.oop.MapDirection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static agh.ics.oop.MapDirection.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MapDirectionTest {

    static Stream<Arguments> nextVerificationArgumentsProvider() {
        return Stream.of(
                arguments(NORTH, EAST),
                arguments(EAST, SOUTH),
                arguments(SOUTH, WEST),
                arguments(WEST, NORTH)
        );
    }

    @ParameterizedTest(name = "should return {2} for direction {1}")
    @MethodSource("nextVerificationArgumentsProvider")
    void shouldVerifyNext(MapDirection start, MapDirection check) {
        MapDirection result = start.next();
        assertEquals(check, result);
    }

    static Stream<Arguments> previousVerificationArgumentsProvider() {
        return Stream.of(
                arguments(NORTH, WEST),
                arguments(EAST, NORTH),
                arguments(SOUTH, EAST),
                arguments(WEST, SOUTH)
        );
    }

    @ParameterizedTest(name = "should return {2} for direction {1}")
    @MethodSource("previousVerificationArgumentsProvider")
    void shouldVerifyPrevious(MapDirection start, MapDirection check) {
        MapDirection result = start.previous();
        assertEquals(check, result);
    }
}