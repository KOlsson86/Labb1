package othello;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-18.
 */
class AI {

    private final Controller controller;
    private int nodeCounter;

    public AI(Controller controller) {
        this.controller = controller;
    }


    public int alphaBeta(int[][] playField, int depth) {
        nodeCounter = 0;
        int v = maxVal(playField, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        System.out.println("V = " + v);
        System.out.println("The search was " + depth + " unit deep");
        System.out.println(nodeCounter + " nodes visited.");
        return v;

    }

    public int maxVal(int[][] playField, int alpha, int beta, int depth) {
        if (terminalTest(playField) || depth == 0) {
            return utility(playField);
        }
        int v = Integer.MIN_VALUE;
        ArrayList<Coordinate> coordinates = controller.actions(playField);
        for (Coordinate coordinate : coordinates) {
            nodeCounter++;
            v = Integer.max(v, minVal(ResultMax(playField, coordinate), alpha, beta, depth - 1));
            if (v >= beta) {
                return v;
            }
            alpha = Integer.max(alpha, v);
        }
        return v;
    }

    private int minVal(int[][] playField, int alpha, int beta, int depth) {
        if (terminalTest(playField) || depth == 0) {
            return utility(playField);
        }
        int v = Integer.MAX_VALUE;
        ArrayList<Coordinate> coordinates = controller.actions(playField);
        for (Coordinate coordinate : coordinates) {
            nodeCounter++;
            v = Integer.min(v, maxVal(ResultMin(playField, coordinate), alpha, beta, depth - 1));
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
}

