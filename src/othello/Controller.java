package othello;

import javax.swing.*;

/**
 * Created by Sebastian on 2015-09-18.
 */
public class Controller {

    private Console console;
    private Board board;
    private AI cpu;

    public Controller(Board board, Console console) {
        this.board = board;
        this.console = console;
        cpu = new AI();
        play();
    }

    private void playerPlay() {
        String s = JOptionPane.showInputDialog("Where do you want to place your piece? (3,3 for example)");
        if(s==null)
            System.exit(0);
        String[] split = s.split(",");

       if(!board.play(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1))
           playerPlay();
    }

    private void cpuPlay(){
        String cpuPlay = cpu.play(board.getPlayField());
        String [] split2 = cpuPlay.split(",");
        board.play(Integer.parseInt(split2[0]),Integer.parseInt(split2[1]),2);
    }

    private void play() {
        while(!board.isFull()) {
            try {
                playerPlay();
                cpuPlay();
                printBoard();
            } catch (ArrayIndexOutOfBoundsException ignored) {
                play();
            }
        }
    }

    private void printBoard() {
        console.appendText(board.playFieldToString());
    }
}
