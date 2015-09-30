package othello;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-18.
 */
class AI {

    private final Controller controller;
    private int nodeCounter;
    private Coordinate bestCoordinate;

    public AI(Controller controller) {
        this.controller = controller;
    }

    public Coordinate alphaBeta(int[][] playField) {
        bestCoordinate = null;
        if (obviousOptimal(playField)) {
            System.out.println("A corner is free. Let's grab it!");

        } else {
            nodeCounter = 0;
            int v = maxVal(playField, Integer.MIN_VALUE, Integer.MAX_VALUE);
            System.out.println("V = " + v);
            int depth = controller.actions(playField).size()+1;
            System.out.println("The search was " + depth + " units deep");
            System.out.println(nodeCounter + " nodes visited.");
            if (bestCoordinate == null) {
                playAnything(playField);
            }
        }
        return bestCoordinate;
    }

    public int maxVal(int[][] playField, int alpha, int beta) {
        if (terminalTest(playField)) {
            return utility(playField);
        }
        int v = Integer.MIN_VALUE;
        ArrayList<Coordinate> coordinates = controller.actions(playField);
        for (Coordinate coordinate : coordinates) {
            nodeCounter++;
            v = Integer.max(v, minVal(ResultMax(playField, coordinate), alpha, beta));
            if (v >= beta) {
                bestCoordinate = coordinate;
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
            nodeCounter++;
            v = Integer.min(v, maxVal(ResultMin(playField, coordinate), alpha, beta));
            if (v <= alpha) {
                return v;
            }
            beta = Integer.min(beta, v);
        }
        return v;
    }

    private int[][] ResultMax(int[][] playField, Coordinate coordinate) {
        int[][] tempField = new int[4][4];
        return controller.emulateCPUFlip(controller.copyArray(playField, tempField), coordinate.getY(), coordinate.getX());
    }

    private int[][] ResultMin(int[][] playField, Coordinate coordinate) {
        int[][] tempField = new int[4][4];
        return controller.emulatePlayerFlip(controller.copyArray(playField, tempField), coordinate.getY(), coordinate.getX());
    }

    private int utility(int[][] playField) {
        return controller.calcCurrentCPUScore(playField);
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

    public void playAnything(int[][] playField) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (playField[i][j] == 0) {
                    bestCoordinate = new Coordinate(i, j);
                }
            }
        }
    }

    public boolean obviousOptimal(int[][] playField) {

        if (playField[0][0] == 0) {
            bestCoordinate = new Coordinate(0, 0);
            return true;
        } else if (playField[0][3] == 0) {
            bestCoordinate = new Coordinate(0, 3);
            return true;
        } else if (playField[3][0] == 0) {
            bestCoordinate = new Coordinate(3, 0);
            return true;
        } else if (playField[3][3] == 0) {
            bestCoordinate = new Coordinate(3, 3);
            return true;
        }
        return false;
    }
}

