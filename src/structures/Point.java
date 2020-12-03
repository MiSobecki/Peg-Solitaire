package structures;

public class Point {

    private final int x, y;
    private boolean hasBall;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        placeBall();
    }

    public static void moveBall(Point p1, Point p2) throws IllegalArgumentException {
        if (!p1.hasBall) throw new IllegalArgumentException("Point p1 doesn't have ball");
        if (p2.hasBall) throw new IllegalArgumentException("Point p2 has already ball");
        p1.removeBall();
        p2.placeBall();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean includesBall() {
        return hasBall;
    }

    public void placeBall() {
        this.hasBall = true;
    }

    public void removeBall() {
        this.hasBall = false;
    }
}
