package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    private OptionsParser() {
        //should be empty
    }

    public static List<MoveDirection> parse(String[] moves) {
        List<MoveDirection> directions = new ArrayList<>();

        for (String move : moves) {
            switch (move) {
                case "f", "forward" -> directions.add(MoveDirection.FORWARD);
                case "b", "backward" -> directions.add(MoveDirection.BACKWARD);
                case "r", "right" -> directions.add(MoveDirection.RIGHT);
                case "l", "left" -> directions.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(move + "is not legal move specification");
            }
        }
        return directions;
    }
}
