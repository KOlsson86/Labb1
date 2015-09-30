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
}