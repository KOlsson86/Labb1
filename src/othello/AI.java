package othello;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-18.
 */
class AI {

    private final Controller controller;
    private Node bestNode = null;
    private Coordinate bestCoordinate;

    public AI(Controller controller) {
        this.controller = controller;
    }


    //   public String play(int[][] playField) {
    //       myTest(playField);
    //       return "1,1";
    //   }


    public int myTest(int[][] playField) {
        Coordinate optimalCoordinate = null;
        int bestOutcome = Integer.MIN_VALUE;
        int outcome;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (playField[i][j] == 0) {
                    outcome = controller.calcOutcome(controller.getBoard(), i, j);
                    if (outcome > bestOutcome) {
                        optimalCoordinate = new Coordinate(i, j);
                        bestOutcome = outcome;
                    }
                }
            }
        }
        return bestOutcome;
    }


    public Node alphaBeta(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {

        if (depth == 0 || node.getChildren().isEmpty()) {
            return bestNode;
        }
        if (maximizingPlayer) {
            Node tempNode = new Node();
            tempNode.setValue(Integer.MIN_VALUE);
            for (Node child : node.getChildren()) {
                if (tempNode.getValue() < alphaBeta(child, depth - 1, alpha, beta, false).getValue()) {
                    tempNode = child;
                    bestNode = child;
                }
                alpha = Integer.max(alpha, tempNode.getValue());
                if (beta <= alpha) {
                    return bestNode;
                }
            }
        } else {
            Node tempNode = new Node();
            tempNode.setValue(Integer.MAX_VALUE);
            for (Node child : node.getChildren()) {
                if (tempNode.getValue() > alphaBeta(child, depth - 1, alpha, beta, true).getValue()) {
                    tempNode = child;
                    bestNode = child;
                }
                beta = Integer.min(beta, tempNode.getValue());
                if (beta <= alpha) {
                    return bestNode;
                }
            }
        }
        return bestNode;
    }


//public int alphaBeta(int[][] playfield){
    //   int v = MaxValue(playfield,Integer.MIN_VALUE,Integer.MAX_VALUE);
    //  return 0;
//}

    //  private int MaxValue(int[][] playfield, int alpha, int beta) {
    //    if(terminalTest(playfield)){
    //      utility(playfield);
    // }
    //int v = Integer.MIN_VALUE;

    // return 0;
    //}


    public int alphaBeta(int[][] playField) {
        int v = maxVal(playField, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return v;

    }

    public int maxVal(int[][] playField, int alpha, int beta) {
        if (terminalTest(playField)) {
            return utility(playField);
        }
        int v = Integer.MIN_VALUE;
        ArrayList<Coordinate> coordinates = controller.actions(playField);
        for (Coordinate coordinate : coordinates) {
            v = Integer.max(bestCoordinate.getValue(), minVal(Result(playField, coordinate), alpha, beta));
            if (v >= beta) {
                return v;
            }
            alpha = Integer.max(alpha, v);
        }
        return v;
    }

    private int minVal(int[][] playField, int alpha, int beta) {
        if (terminalTest(playField)) {
            return utility(playField);
        }
        int v = Integer.MAX_VALUE;
        ArrayList<Coordinate> coordinates = controller.actions(playField);
        for (Coordinate coordinate : coordinates) {
            v = Integer.min(v, maxVal(Result(playField, coordinate), alpha, beta));
            if (v <= alpha) {
                return v;
            }
            beta = Integer.min(beta, v);
        }
        return v;
    }

    private int[][] Result(int[][] playField, Coordinate coordinate) {
        return controller.emulateCPUFlip(playField, coordinate.getY(), coordinate.getX());
    }

    private int utility(int[][] playField) {
        return myTest(playField);
    }

    public boolean terminalTest(int[][] playField) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (playField[i][j] == 0)
                    return false;
            }
        }
        return true;
    }







/*
    public int alphaBeta(Node node, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || node.isTerminal) {
            return node.value;
        }
        if (maximizingPlayer) {
            v = Integer.MIN_VALUE;
            for (Node child : node.children) {
                v = Integer.max(v, alphaBeta(child, depth - 1, alpha, beta, false));
                alpha = Integer.max(alpha, v);
                if (beta <= alpha) {
                    return v;
                }
            }
        } else {
            v = Integer.MAX_VALUE;
            for (Node child : node.children) {
                v = Integer.min(v, alphaBeta(child, depth - 1, alpha, beta, true));
                beta = Integer.min(beta, v);
                if (beta <= alpha) {
                    return v;
                }
            }
        }
        return -1;
    }
    */
}

