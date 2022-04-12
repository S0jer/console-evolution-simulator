package agh.ics.oop;


import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean precedes(Vector2d other) {
        return other.x <= this.x && other.y <= this.y;
    }

    public boolean follows(Vector2d other) {
        return other.x >= this.x && other.y >= this.y;
    }

    public Vector2d upperRight(Vector2d other) {

        int a = Math.max(other.x, this.x);

        int b = Math.max(other.y, this.y);

        return new Vector2d(a, b);
    }

    public Vector2d lowerLeft(Vector2d other) {

        int a = Math.min(other.x, this.x);

        int b = Math.min(other.y, this.y);

        return new Vector2d(a, b);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d((this.x + other.x), (this.y + other.y));
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d((this.x - other.x), (this.y - other.y));
    }

    public Vector2d opposite() {
        return new Vector2d(this.x * (-1), this.y * (-1));
    }
}


