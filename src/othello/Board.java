package othello;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Board {
    private int[][] playField;

    /**
     * The constructor for the board. It initiates a board that is 4x4.
     */
    public Board() {
        playField = new int[4][4];
    }

    /**
     * @return The current state of the board. Where all the pieces are etc.
     */
    public int[][] getPlayField() {
        return playField;
    }

    /**
     * A method used to place a piece on the board.
     *
     * @param down   how far down the y-axis the piece is placed.
     * @param right  how far to the right on the x-axis the piece is placed.
     * @param colour if it is a player or cpu that made the move.
     * @return true if the move was successful, otherwise false.
     */
    public boolean play(int down, int right, int colour) {
        //int colour is who played. 0 means its empty, 1 is black(Player), 2 is white.
        if (playField[down][right] == 0) {
            playField[down][right] = colour;
            //Return true if the move was made.
            return true;
        }
        return false;
    }

    /**
     * Method used to print the current board.
     *
     * @return the current board as a string.
     */
    public String playFieldToString() {
        String s = "";
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                s += playField[i][j];
            }
            s += "\n";
        }
        return s;
    }

    public void flip(int down, int right, int colour) {
        if (colour == 1) {

            playField[down][right] = 1;
        } else
            playField[down][right] = 2;
    }

    public void flipControlPlayer(int down, int right) {
        //----->
        if (right + 1 < 3 && playField[down][right + 1] == 2 && playField[down][right + 2] == 1) {
            flip(down, right + 1, 1);
        } else
            //----->
            if (right == 0 && playField[down][right + 2] == 2 && playField[down][right + 1] == 2 && playField[down][right + 3] == 1) {
                flip(down, right + 1, 1);
                flip(down, right + 2, 1);
            }
        //<-----
        if (right - 1 > 0 && playField[down][right - 1] == 2 && playField[down][right - 2] == 1) {
            flip(down, right - 1, 1);
        } else
            //<-----
            if (right == 3 && playField[down][right - 1] == 2 && playField[down][right - 2] == 2 && playField[down][right - 3] == 1) {
                flip(down, right - 1, 1);
                flip(down, right - 2, 1);
            }
        //down
        if (down + 1 < 3 && playField[down + 1][right] == 2 && playField[down + 2][right] == 1) {
            flip(down + 1, right, 1);
        } else
            //down
            if (down == 0 && playField[down + 2][right] == 2 && playField[down + 1][right] == 2 && playField[down + 3][right] == 1) {
                flip(down + 1, right, 1);
                flip(down + 2, right, 1);
            }
        //up
        if (down - 1 > 0 && playField[down - 1][right] == 2 && playField[down - 2][right] == 1) {
            flip(down - 1, right, 1);
        } else
            //up
            if (down == 3 && playField[down - 1][right] == 2 && playField[down - 2][right] == 2 && playField[down - 3][right] == 1) {
                flip(down - 1, right, 1);
                flip(down - 2, right, 1);

            }

    }


    public void flipControlCPU(int down, int right) {
        //----->
        if (right + 1 < 3 && playField[down][right + 1] == 1 && playField[down][right + 2] == 2) {
            flip(down, right + 1, 2);
        } else
            //----->
            if (right == 0 && playField[down][right + 2] == 1 && playField[down][right + 1] == 1 && playField[down][right + 3] == 2) {
                flip(down, right + 1, 2);
                flip(down, right + 2, 2);
            }
        //<-----
        if (right - 1 > 0 && playField[down][right - 1] == 1 && playField[down][right - 2] == 2) {
            flip(down, right - 1, 2);
        } else
            //<-----
            if (right == 3 && playField[down][right - 1] == 1 && playField[down][right - 2] == 1 && playField[down][right - 3] == 2) {
                flip(down, right - 1, 2);
                flip(down, right - 2, 2);
            }
        //down
        if (down + 1 < 3 && playField[down + 1][right] == 1 && playField[down + 2][right] == 2) {
            flip(down + 1, right, 2);
        } else
            //down
            if (down == 0 && playField[down + 2][right] == 1 && playField[down + 1][right] == 1 && playField[down + 3][right] == 2) {
                flip(down + 1, right, 2);
                flip(down + 2, right, 2);
            }
        //up
        if (down - 1 > 0 && playField[down - 1][right] == 1 && playField[down - 2][right] == 2) {
            flip(down - 1, right, 2);
        } else
            //up
            if (down == 3 && playField[down - 1][right] == 1 && playField[down - 2][right] == 1 && playField[down - 3][right] == 2) {
                flip(down - 1, right, 2);
                flip(down - 2, right, 2);

            }
    }

}