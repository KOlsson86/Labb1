package othello;

import javax.swing.*;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Controller {

    private Console console;
    private Board board;

    public Controller(Board board, Console console) {
        this.board = board;
        this.console = console;
        printBoard();
        playerPlay();
    }

    private void playerPlay() {
        try {
            String s = JOptionPane.showInputDialog("Where do you want to place your piece? (3,3 for example)");
            String[] split = s.split(",");
            board.play(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1);
            printBoard();
            //Activate computer opponent here then call upon this method again.
        } catch (Exception ignored) {
            playerPlay();
        }
    }

    private void printBoard() {
        console.appendText(board.playFieldToString());
    }
}
