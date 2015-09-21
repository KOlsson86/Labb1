/**
 * Created by Blyerts on 18/09/2015.
 */
package othello;


import java.util.ArrayList;

class Node {

    boolean isMin;
    boolean isMax;
    boolean isTerminal;
    int value;
    int depth;
    ArrayList <Node> children = new ArrayList <Node>();

}
class AlphaBeta {


    static Node alphaBetaSearch(Node state){

        state.value = max_value(state,-99999,99999);

        System.out.println(state.value);

        return null;
    }


    static int max_value(Node state, int alpha, int beta){


        if (state.isTerminal){
            System.out.println("visited leaf with value " + state.value);
            return state.value;
        }

        state.value = -99999;

        for (Node a: state.children){

            state.value = Math.max(state.value , min_value(a, alpha, beta));
            if (state.value >= beta){

                return state.value;
            }
            alpha = Math.max(alpha, state.value);
        }
        return state.value;
    }

    static int min_value(Node state, int alpha, int beta){


        if (state.isTerminal)
            return state.value;

        state.value = 99999;

        for (Node a: state.children){

            state.value = Math.min(state.value, max_value(a, alpha, beta));
            if (state.value >= beta){

                return state.value;

            }
            beta = Math.min(beta, state.value);
        }
        return state.value;
    }


    public static void main(String[] args) {

        Node t1 = new Node();
//            t1.value = 4;
        t1.value = 3;
        t1.depth =2;
        t1.isTerminal = true;

        Node t2 = new Node();
//             t2.value = 8;
        t2.value = 12;
        t2.depth=2;
        t2.isTerminal= true;

        Node t3 = new Node();
        // t3.value = 7;
        t3.value = 8;
        t3.depth=2;
        t3.isTerminal= true;

        Node min1 = new Node();
        min1.isTerminal=false;
        min1.depth=1;
        min1.children.add(t1);
        min1.children.add(t2);
        min1.children.add(t3);

        Node t4 = new Node();
//            t4.value = 5;
        t4.value = 2;
        t4.depth =2;
        t4.isTerminal = true;

        Node t5 = new Node();
        //t5.value = 2;
        t5.value =3;
        t5.depth=2;
        t5.isTerminal= true;

        Node t6 = new Node();
//             t6.value = 1;
        t6.value=9;
        t6.depth=2;
        t6.isTerminal= true;

        Node min2 = new Node();
        min2.isMin=true;
        min2.isTerminal=false;
        min2.depth=1;
        min2.children.add(t4);
        min2.children.add(t5);
        min2.children.add(t6);


        Node t7 = new Node();
//             t7.value = 1;
        t7.value=14;
        t7.depth =2;
        t7.isTerminal = true;

        Node t8 = new Node();
//             t8.value = 6;
        t8.value=1;
        t8.depth=2;
        t8.isTerminal= true;

        Node t9 = new Node();
//             t9.value = 0;
        t9.value=8;
        t9.depth=2;
        t9.isTerminal= true;

        Node min3 = new Node();
        min3.isMin=true;
        min3.isTerminal=false;
        min3.depth=1;
        min3.children.add(t7);
        min3.children.add(t8);
        min3.children.add(t9);

        Node max1 = new Node();
        max1.isMax=true;
        max1.isTerminal=false;
        max1.depth=0;
        max1.children.add(min1);
        max1.children.add(min2);
        max1.children.add(min3);



        alphaBetaSearch (max1);



    }
}