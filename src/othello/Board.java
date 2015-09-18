package othello;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Board {
    private int[][] playField;

    public Board(){
        playField = new int[3][3];
    }

    public int[][] getPlayField(){
        return playField;
    }

    public void play(short val1, short val2, int colour){
        //int colour is who played. 0 means its empty, 1 is black, 2 is white.
        playField[val1][val2] = colour;
    }

    public String playFieldToString(){
        String s ="";

        return s;
    }
}
