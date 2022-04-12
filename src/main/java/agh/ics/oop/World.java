package agh.ics.oop;


import agh.ics.oop.world.GrassField;
import agh.ics.oop.world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            String[] arguments = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            List<MoveDirection> directions = OptionsParser.parse(arguments);
            ArrayList<Vector2d> animalsPos = new ArrayList<>();
            animalsPos.add(new Vector2d(1, 2));
            WorldMap map = new GrassField(10);
            Engine engine = new SimulationEngine(directions, map, animalsPos);
            System.out.println(map);
            engine.run();
            System.out.println(map);
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong code");
        }
    }
}
