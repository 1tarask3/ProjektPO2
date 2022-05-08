package pl.po.projekt;

import java.util.Random;

public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public Point shift(Direction direction, int range) {
        if (direction == null) return new Point(x, y);
        Point p;
        switch (direction) {
            case UP:
                p = new Point(x, y - range);
                break;
            case RIGHT:
                p = new Point(x + range, y);
                break;
            case LEFT:
                p = new Point(x - range, y);
                break;
            case DOWN:
                p = new Point(x, y + range);
                break;
            default:
                p = new Point(x, y);
                break;
        }
        return p;
    }

    public boolean isOnTheBoard(World world) {
        return (x >= 0 && x < world.getX()) && (y >= 0 && y < world.getY());
    }

    public Point nextNotOccupiedPosition(World world) {
        Point[] availableMoves = new Point[4];
        int count = 0;
        int rand;
        for (Direction direction : Direction.values()) {
            Point move = this.shift(direction, 1);
            if (move.isOnTheBoard(world)) {
                if (world.getOrganismFromBoard(move) == null) {
                    availableMoves[count] = move;
                    count++;
                }
            }
        }
        if (count > 0) {
            rand = new Random().nextInt(count);
            return availableMoves[rand];
        }
        else return null;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
