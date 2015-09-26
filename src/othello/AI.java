package othello;

/**
 * Created by Sebastian on 2015-09-18.
 */
class AI {

    private final Controller controller;
 //   private int v;

    public AI(Controller controller) {
        this.controller = controller;
    }


 //   public String play(int[][] playField) {
 //       myTest(playField);
 //       return "1,1";
 //   }

    public Coordinate myTest(int[][] playField){
        Coordinate optimalCoordinate = null;
        int bestOutcome=Integer.MIN_VALUE;
        int outcome;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (playField[i][j] == 0){
                        outcome= controller.calcOutcome(i,j);
                        if(outcome>bestOutcome){
                            optimalCoordinate = new Coordinate(i,j);
                            bestOutcome=outcome;
                        }
                    }
                }
            }
        return optimalCoordinate;
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

