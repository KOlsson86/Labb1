package othello;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Board {
    private int[][] playField;

    public Board() {
        playField = new int[4][4];
    }

    public int[][] getPlayField() {
        return playField;
    }

    public void play(short val1, short val2, int colour) {
        //int colour is who played. 0 means its empty, 1 is black, 2 is white.
        if (playField[val1][val2] == 0) {
            playField[val1][val2] = colour;
        }
    }

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