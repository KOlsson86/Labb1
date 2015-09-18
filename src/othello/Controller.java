package othello;

import javax.swing.*;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Controller {

    private Console console;
    private Board board;
    private AI cpu;

    /**
     * The constructor for the controller.
     *
     * @param board   an object representing the board.
     * @param console an object representing the console.
     */
    public Controller(Board board, Console console) {
        this.board = board;
        this.console = console;
        cpu = new AI();
        play();
    }

    /**
     * A method that acts as the players turn. The player inputs a coordinate and the game tries to place a player piece there.
     * If there is already another piece there the player is prompted to input a different coordinate.
     * If the user press cancel the game will turn itself off.
     */
    private void playerPlay() {
        String s = JOptionPane.showInputDialog("Where do you want to place your piece? (3,3 for example)");
        if (s == null)
            System.exit(0);
        String[] split = s.split(",");

        if (!board.play(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1))
            playerPlay();
    }

    /**
     *A method representing the computers move.
     */
    private void cpuPlay() {
        String cpuPlay = cpu.play(board.getPlayField());
        String[] split2 = cpuPlay.split(",");
        board.play(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]), 2);
    }

    /**
     * A method representing the majority of the game.
     * While the board isnt full the game continues. If the player attempt to put a piece outside the board he is
     * prompted to play again.
     */
    private void play() {
        while (!board.isFull()) {
            try {
                playerPlay();
                if(!board.isFull())
                cpuPlay();
                printBoard();
            } catch (ArrayIndexOutOfBoundsException ignored) {
                play();
            }
        }
    }

    /**
     * A method that prints the current state of the board.
     */
    private void printBoard() {
        console.appendText(board.playFieldToString());
    }
}
