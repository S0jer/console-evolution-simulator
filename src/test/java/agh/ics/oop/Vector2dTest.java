package agh.ics.oop;

import agh.ics.oop.Vector2d;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Vector2dTest {

    static Stream<Arguments> equalsVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(1, 1, new Vector2d(1, 1), true),
                arguments(-2, -2, new Vector2d(-2, -2), true),
                arguments(-10, 21, new Vector2d(-10, 21), true),
                arguments(12, -2, new Vector2d(12, -2), true)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("equalsVerificationArgumentsProviderTrue")
    void shouldVerifyEqualsTrue(int a, int b, Object check, boolean result) {
        Vector2d vector2dTest = new Vector2d(a, b);
        assertEquals(vector2dTest.equals(check), result);
    }

    static Stream<Arguments> equalsVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(1, 1, new Vector2d(0, 1), false),
                arguments(1, 2, new Vector2d(-1, -1), false),
                arguments(-10, 21, new Vector2d(-21, 21), false),
                arguments(12, -2, new Vector2d(-12, 2), false)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("equalsVerificationArgumentsProviderFalse")
    void shouldVerifyEqualsFalse(int a, int b, Object check, boolean result) {
        Vector2d vector2dTest = new Vector2d(a, b);
        assertEquals(vector2dTest.equals(check), result);
    }

    static Stream<Arguments> toStringVerificationArgumentsProvider() {
        return Stream.of(
                arguments(1, 1, "(" + 1 + ", " + 1 + ")"),
                arguments(1, 2, "(" + 1 + ", " + 2 + ")"),
                arguments(-10, 21, "(" + -10 + ", " + 21 + ")"),
                arguments(12, -2, "(" + 12 + ", " + -2 + ")")
        );
    }

    @ParameterizedTest(name = "should return {3}")
    @MethodSource("toStringVerificationArgumentsProvider")
    void shouldVerifyToString(int a, int b, String check) {
        Vector2d vector2dTest = new Vector2d(a, b);
        String result = vector2dTest.toString();
        assertEquals(result, check);
    }


    static Stream<Arguments> precedesVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(1, 1, 1, 2, true),
                arguments(1, 2, 10, 21, true),
                arguments(-10, 21, -10, 21, true),
                arguments(12, -2, 15, 2, true)
        );
    }

    @ParameterizedTest(name = "should return {5}")
    @MethodSource("precedesVerificationArgumentsProviderTrue")
    void shouldVerifyPrecedesTrue(int a, int b, int c, int d, boolean check) {
        Vector2d vector2d = new Vector2d(a, b);
        Vector2d vector2dPrecedes = new Vector2d(c, d);
        boolean result = vector2dPrecedes.precedes(vector2d);
        assertEquals(result, check);
    }

    static Stream<Arguments> precedesVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(1, 1, 1, 0, false),
                arguments(1, 2, -10, 21, false),
                arguments(-10, 21, 1, 20, false),
                arguments(12, -2, -15, -2, false)
        );
    }

    @ParameterizedTest(name = "should return {5}")
    @MethodSource("precedesVerificationArgumentsProviderFalse")
    void shouldVerifyPrecedesFalse(int a, int b, int c, int d, boolean check) {
        Vector2d vector2d = new Vector2d(a, b);
        Vector2d vector2dPrecedes = new Vector2d(c, d);
        boolean result = vector2dPrecedes.precedes(vector2d);
        assertEquals(result, check);
    }

    static Stream<Arguments> followsVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(1, 1, 10, 2, true),
                arguments(1, 2, 1, 2, true),
                arguments(-10, 21, 1, 30, true),
                arguments(12, -2, 15, 2, true)
        );
    }

    @ParameterizedTest(name = "should return {5}")
    @MethodSource("followsVerificationArgumentsProviderTrue")
    void shouldVerifyFollowsTrue(int a, int b, int c, int d, boolean check) {
        Vector2d vector2dFollows = new Vector2d(a, b);
        Vector2d vector2d = new Vector2d(c, d);
        boolean result = vector2dFollows.follows(vector2d);
        assertEquals(result, check);
    }


    static Stream<Arguments> followsVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(1, 1, 1, 0, false),
                arguments(1, 2, -10, 21, false),
                arguments(-10, 21, -111, 30, false),
                arguments(12, -2, 11, -2, false)
        );
    }

    @ParameterizedTest(name = "should return {5}")
    @MethodSource("followsVerificationArgumentsProviderFalse")
    void shouldVerifyFollowsFalse(int a, int b, int c, int d, boolean check) {
        Vector2d vector2dFollows = new Vector2d(a, b);
        Vector2d vector2d = new Vector2d(c, d);
        boolean result = vector2dFollows.follows(vector2d);
        assertEquals(result, check);
    }


    static Stream<Arguments> upperRightVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(1, 1, 1, 2, 1, 2, true),
                arguments(1, 2, -10, 21, 1, 21, true),
                arguments(-10, 21, 1, 30, 1, 30, true),
                arguments(12, -2, 15, 2, 15, 2, true)
        );
    }

    @ParameterizedTest(name = "should return {7}")
    @MethodSource("upperRightVerificationArgumentsProviderTrue")
    void shouldVerifyUpperRightTrue(int a, int b, int c, int d, int check_a, int check_b, boolean check) {
        Vector2d vector2d_1 = new Vector2d(a, b);
        Vector2d vector2d_2 = new Vector2d(c, d);
        Vector2d vector2d_check = new Vector2d(check_a, check_b);
        Vector2d vector2d_connected = vector2d_1.upperRight(vector2d_2);
        boolean result = vector2d_check.equals(vector2d_connected);
        assertEquals(result, check);
    }

    static Stream<Arguments> upperRightVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(1, 1, 1, 2, 1, 0, false),
                arguments(1, 2, -10, 21, -10, 2, false),
                arguments(-10, 21, 1, 30, -10, 30, false),
                arguments(12, -2, 15, 2, 12, 2, false)
        );
    }

    @ParameterizedTest(name = "should return {7}")
    @MethodSource("upperRightVerificationArgumentsProviderFalse")
    void shouldVerifyUpperRightFalse(int a, int b, int c, int d, int check_a, int check_b, boolean check) {
        Vector2d vector2d_1 = new Vector2d(a, b);
        Vector2d vector2d_2 = new Vector2d(c, d);
        Vector2d vector2d_check = new Vector2d(check_a, check_b);
        Vector2d vector2d_connected = vector2d_1.upperRight(vector2d_2);
        boolean result = vector2d_check.equals(vector2d_connected);
        assertEquals(result, check);
    }

    static Stream<Arguments> lowerLeftVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 1), true),
                arguments(new Vector2d(1, 2), new Vector2d(-10, 21), new Vector2d(-10, 2), true),
                arguments(new Vector2d(-10, 21), new Vector2d(1, 30), new Vector2d(-10, 21), true),
                arguments(new Vector2d(12, -2), new Vector2d(15, 2), new Vector2d(12, -2), true)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("lowerLeftVerificationArgumentsProviderTrue")
    void shouldVerifyLowerLeftTrue(Vector2d vector2d_1, Vector2d vector2d_2, Vector2d vector2d_check, boolean check) {
        Vector2d vector2d_connected = vector2d_1.lowerLeft(vector2d_2);
        boolean result = vector2d_check.equals(vector2d_connected);
        assertEquals(result, check);
    }

    static Stream<Arguments> lowerLeftVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(1, 2), false),
                arguments(new Vector2d(1, 2), new Vector2d(-10, 21), new Vector2d(1, 21), false),
                arguments(new Vector2d(-10, 21), new Vector2d(1, 30), new Vector2d(1, 24), false),
                arguments(new Vector2d(12, -2), new Vector2d(15, 2), new Vector2d(15, -2), false)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("lowerLeftVerificationArgumentsProviderFalse")
    void shouldVerifyLowerLeftFalse(Vector2d vector2d_1, Vector2d vector2d_2, Vector2d vector2d_check, boolean check) {
        Vector2d vector2d_connected = vector2d_1.lowerLeft(vector2d_2);
        boolean result = vector2d_check.equals(vector2d_connected);
        assertEquals(result, check);
    }

    static Stream<Arguments> addVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(2, 3), true),
                arguments(new Vector2d(1, 2), new Vector2d(-10, 21), new Vector2d(-9, 23), true),
                arguments(new Vector2d(-10, 21), new Vector2d(1, 30), new Vector2d(-9, 51), true),
                arguments(new Vector2d(12, -2), new Vector2d(15, 2), new Vector2d(27, 0), true)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("addVerificationArgumentsProviderTrue")
    void shouldVerifyAddTrue(Vector2d vector2d_1, Vector2d vector2d_2, Vector2d vector2d_check, boolean check) {

        Vector2d vector2d_connected = vector2d_1.add(vector2d_2);
        boolean result = vector2d_check.equals(vector2d_connected);
        assertEquals(result, check);
    }

    static Stream<Arguments> addVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(2, -3), false),
                arguments(new Vector2d(1, 2), new Vector2d(-10, 21), new Vector2d(-9, 3), false),
                arguments(new Vector2d(-10, 21), new Vector2d(1, 30), new Vector2d(9, 15), false),
                arguments(new Vector2d(12, -2), new Vector2d(15, 2), new Vector2d(-27, 0), false)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("addVerificationArgumentsProviderFalse")
    void shouldVerifyAddFalse(Vector2d vector2d_1, Vector2d vector2d_2, Vector2d vector2d_check, boolean check) {

        Vector2d vector2d_connected = vector2d_1.add(vector2d_2);

        boolean result = vector2d_check.equals(vector2d_connected);

        assertEquals(result, check);
    }

    static Stream<Arguments> subtractVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(0, -1), true),
                arguments(new Vector2d(1, 2), new Vector2d(-10, 21), new Vector2d(11, -19), true),
                arguments(new Vector2d(-10, 21), new Vector2d(1, 30), new Vector2d(-11, -9), true),
                arguments(new Vector2d(12, -2), new Vector2d(15, 2), new Vector2d(-3, -4), true)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("subtractVerificationArgumentsProviderTrue")
    void shouldVerifySubtractTrue(Vector2d vector2d_1, Vector2d vector2d_2, Vector2d vector2d_check, boolean check) {
        Vector2d vector2d_connected = vector2d_1.subtract(vector2d_2);

        boolean result = vector2d_check.equals(vector2d_connected);

        assertEquals(result, check);
    }

    static Stream<Arguments> subtractVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(1, 2), new Vector2d(0, 3), false),
                arguments(new Vector2d(1, 2), new Vector2d(-10, 21), new Vector2d(-9, 19), false),
                arguments(new Vector2d(-10, 21), new Vector2d(1, 30), new Vector2d(-9, 51), false),
                arguments(new Vector2d(12, -2), new Vector2d(15, 2), new Vector2d(3, 0), false)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("subtractVerificationArgumentsProviderFalse")
    void shouldVerifySubtractFalse(Vector2d vector2d_1, Vector2d vector2d_2, Vector2d vector2d_check, boolean check) {

        Vector2d vector2d_connected = vector2d_1.subtract(vector2d_2);

        boolean result = vector2d_check.equals(vector2d_connected);

        assertEquals(result, check);
    }


    static Stream<Arguments> oppositeVerificationArgumentsProviderTrue() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(-1, -1), true),
                arguments(new Vector2d(1, 2), new Vector2d(-1, -2), true),
                arguments(new Vector2d(-10, 21), new Vector2d(10, -21), true),
                arguments(new Vector2d(12, -2), new Vector2d(-12, 2), true)
        );
    }


    @ParameterizedTest(name = "should return {4}")
    @MethodSource("oppositeVerificationArgumentsProviderTrue")
    void shouldVerifyOppositeTrue(Vector2d vector2d_1, Vector2d vector2d_check, boolean check) {
        Vector2d vector2d_op = vector2d_1.opposite();

        boolean result = vector2d_check.equals(vector2d_op);

        assertEquals(result, check);
    }


    static Stream<Arguments> oppositeVerificationArgumentsProviderFalse() {
        return Stream.of(
                arguments(new Vector2d(1, 1), new Vector2d(-2, -2), false),
                arguments(new Vector2d(1, 2), new Vector2d(1, -2), false),
                arguments(new Vector2d(-10, 21), new Vector2d(-10, 21), false),
                arguments(new Vector2d(12, -2), new Vector2d(-12, -2), false)
        );
    }

    @ParameterizedTest(name = "should return {4}")
    @MethodSource("oppositeVerificationArgumentsProviderFalse")
    void shouldVerifyOppositeFalse(Vector2d vector2d_1, Vector2d vector2d_check, boolean check) {

        Vector2d vector2d_op = vector2d_1.opposite();

        boolean result = vector2d_check.equals(vector2d_op);

        assertEquals(result, check);
    }
}
