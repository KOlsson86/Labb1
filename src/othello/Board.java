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
     *
     * @return The current state of the board. Where all the pieces are etc.
     */
    public int[][] getPlayField() {
        return playField;
    }

    /**
     * A method to check if the board is full. If it isn't the game is still ongoing.
     * @return false if we find an empty spot, otherwise true.
     */
    public boolean isFull(){
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if(playField[i][j] ==0)
                    return false;
            }
        }
        return true;
    }

    /**
     * A method used to place a piece on the board.
     * @param down how far down the y-axis the piece is placed.
     * @param right how far to the right on the x-axis the piece is placed.
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
}