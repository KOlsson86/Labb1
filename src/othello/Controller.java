package othello;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Controller {

    private Console console;
    private Board board;


    public Controller(Board board, Console console){
        this.board= board;
        this.console=console;
        printBoard();
    }

    private void printBoard(){
    console.appendText(board.playFieldToString());
    }
}
