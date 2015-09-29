package othello;

/**
 * Created by Sebastian on 2015-09-26.
 */
public class Coordinate {
    private int Y;
    private int X;


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
