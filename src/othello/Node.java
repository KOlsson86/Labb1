package othello;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-29.
 */
public class Node {

    private ArrayList<Node> children = new ArrayList<>();
    private int value;
    private int[][] state;
    private int X;
    private int Y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Node() {

    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getValue() {
        return value;
    }

    public int[][] getState() {
        return state;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setState(int[][] state) {
        this.state = state;
    }


}
