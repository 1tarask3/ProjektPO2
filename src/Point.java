public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setX(int x) {
        this.x = x;
    }

    int getX() {
        return x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getY() {
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
        if ((x >= 0 && x < world.getX()) && (y >= 0 && y < world.getY())) return true;
        else return false;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
