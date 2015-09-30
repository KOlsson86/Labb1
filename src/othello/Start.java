package othello;

/**
 * Created by Sebastian on 2015-09-18.
 * The class that initiates the program.
 */
class Start {

    public static void main(String[] args) {
        Console console = new Console();
        Board board = new Board();
        new Controller(board, console);
    }
}
