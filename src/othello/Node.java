package othello;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-26.
 */
public class Node {

    private int value;
    private int[][] state;
    private ArrayList<Node> children = new ArrayList<>();
    private Coordinate coordinate;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }



    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[][] getState() {
        return state;
    }

    public int getValue() {
        return value;
    }
}
