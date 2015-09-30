package othello;

/**
 * Created by Sebastian on 2015-09-26.
 * A data structure representing a coordinate.
 */
public class Coordinate {
    private final int Y;
    private final int X;

    public Coordinate(int Y, int X) {
        this.Y = Y;
        this.X = X;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
