package othello;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-18.
 */
class AI {

    private final Controller controller;
    private int nodeCounter;
    private int id=0;
    private Node bestNode;

    public Node getRoot() {
        return root;
    }

    private Node root;

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
        return controller.calcCurrentCPUScore(playField) - controller.calcCurrentPlayerScore(playField);
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


    public void startTree(){
        generateTree(root, 5, true, true);
    }

    private void generateTree(Node node, int depth, boolean maximizingPlayer, boolean rootReset) {
        if (rootReset) {
            root = new Node();
            node = root;
            node.setState(controller.getPlayField());
        }
        if (depth != 0) {
            if (maximizingPlayer) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (node.getState()[i][j] == 0) {
                            Node tempNode = new Node();
                            int[][] tempArray = new int[4][4];
                            controller.copyArray(node.getState(), tempArray);
                            tempArray[i][j] = 2;
                            tempArray = controller.emulateCPUFlip(tempArray, i, j);
                            tempNode.setValue(controller.calcCurrentCPUScore(tempArray) - controller.calcCurrentPlayerScore(tempArray));
                            tempNode.setState(tempArray);
                            tempNode.setX(j);
                            tempNode.setY(i);
                            tempNode.setId(id++);
                            node.addChild(tempNode);
                            generateTree(tempNode, depth - 1, false, false);
                        }

                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (node.getState()[i][j] == 0) {
                            Node tempNode = new Node();
                            int[][] tempArray = new int[4][4];
                            controller.copyArray(node.getState(), tempArray);
                            tempArray[i][j] = 1;
                            tempArray = controller.emulatePlayerFlip(tempArray, i, j);
                            tempNode.setValue(controller.calcCurrentCPUScore(tempArray) - controller.calcCurrentPlayerScore(tempArray));
                            tempNode.setState(tempArray);
                            tempNode.setX(j);
                            tempNode.setY(i);
                            tempNode.setId(id++);
                            node.addChild(tempNode);
                            generateTree(tempNode, depth - 1, true, false);
                        }

                    }
                }

            }
        }
    }

    public int alphaBeta(Node node, int depth, int alpha, int beta, boolean maximizingPlayer){
        int v;
        if(depth==0 || node.getChildren().isEmpty()){
            return -1;
        }
        if(maximizingPlayer){
             v = Integer.MIN_VALUE;
            for (Node child : node.getChildren()){
                v = Integer.max(v, alphaBeta(child,depth-1,alpha,beta,false));
                alpha = Integer.max(alpha,v);
                if(beta<=alpha){
                    break;
                }
            }
        } else {
             v = Integer.MAX_VALUE;
            for(Node child : node.getChildren()){
                v = Integer.min(v,alphaBeta(child,depth-1,alpha,beta,true));
                beta = Integer.min(beta,v);
                if(beta<=alpha){
                    break;
                }
            }

        }
        return -1;
    }


}

