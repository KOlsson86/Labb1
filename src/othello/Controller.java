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
        cpu = new AI(this);
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

        if (!board.play(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1)){
            playerPlay();}
        else
            flipControlPlayer(Integer.parseInt(split[0]),Integer.parseInt(split[1]));

    }

    /**
     * A method representing the computers move.
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
                if (!board.isFull())
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

    /**
     * Method used by AI to check outcome of a play
     *
     * @param down
     * @param right
     * @return
     */
    public void flipControlPlayer(int down, int right) {
        if (board.getPlayField()[down][right] != 0) {
        }
        //----->
        if (right + 1 < 3 && board.getPlayField()[down][right + 1] == 2 && board.getPlayField()[down][right + 2] == 1) {
            board.flip(down,right+1,1);
        } else
        //----->
        if (right == 0 && board.getPlayField()[down][right + 2] == 2 && board.getPlayField()[down][right + 1] == 2 && board.getPlayField()[down][right + 3] == 1) {
            board.flip(down,right+1,1);
            board.flip(down,right+2,1);
        }
        //<-----
        if (right - 1 > 0 && board.getPlayField()[down][right - 1] == 2 && board.getPlayField()[down][right - 2] == 1) {
            board.flip(down,right-1,1);
        } else
        //<-----
        if (right == 3 && board.getPlayField()[down][right - 1] == 2 && board.getPlayField()[down][right - 2] == 2 && board.getPlayField()[down][right - 3] == 1) {
            board.flip(down,right-1,1);
            board.flip(down,right-2,1);
        }
        //down
        if (down + 1 < 3 && board.getPlayField()[down + 1][right] == 2 && board.getPlayField()[down + 2][right] == 1) {
            board.flip(down+1,right,1);
        } else
        //down
        if (down == 0 && board.getPlayField()[down + 2][right] == 2 && board.getPlayField()[down + 1][right] == 2 && board.getPlayField()[down + 3][right] == 1) {
            board.flip(down+1,right,1);
            board.flip(down+2,right,1);
        }
        //up
        if (down - 1 > 0 && board.getPlayField()[down - 1][right] == 2 && board.getPlayField()[down - 2][right] == 1) {
            board.flip(down-1,right,1);
        } else
        //up
        if (down == 3 && board.getPlayField()[down - 1][right] == 2 && board.getPlayField()[down - 2][right] == 2 && board.getPlayField()[down - 3][right] == 1) {
            board.flip(down-1,right,1);
            board.flip(down-2,right,1);

        }

    }

    public void flipControlCPU(int down, int right) {
        if (board.getPlayField()[down][right] != 0) {
        }
        //----->
        if (right + 1 < 3 && board.getPlayField()[down][right + 1] == 1 && board.getPlayField()[down][right + 2] == 2) {
            board.flip(down,right+1,2);
        } else
            //----->
            if (right == 0 && board.getPlayField()[down][right + 2] == 1 && board.getPlayField()[down][right + 1] == 1 && board.getPlayField()[down][right + 3] == 2) {
                board.flip(down,right+1,2);
                board.flip(down,right+2,2);
            }
        //<-----
        if (right - 1 > 0 && board.getPlayField()[down][right - 1] == 1 && board.getPlayField()[down][right - 2] == 2) {
            board.flip(down,right-1,2);
        } else
            //<-----
            if (right == 3 && board.getPlayField()[down][right - 1] == 1 && board.getPlayField()[down][right - 2] == 1 && board.getPlayField()[down][right - 3] == 2) {
                board.flip(down,right-1,2);
                board.flip(down,right-2,2);
            }
        //down
        if (down + 1 < 3 && board.getPlayField()[down + 1][right] == 1 && board.getPlayField()[down + 2][right] == 2) {
            board.flip(down+1,right,2);
        } else
            //down
            if (down == 0 && board.getPlayField()[down + 2][right] == 1 && board.getPlayField()[down + 1][right] == 1 && board.getPlayField()[down + 3][right] == 2) {
                board.flip(down+1,right,2);
                board.flip(down+2,right,2);
            }
        //up
        if (down - 1 > 0 && board.getPlayField()[down - 1][right] == 1 && board.getPlayField()[down - 2][right] == 2) {
            board.flip(down-1,right,2);
        } else
            //up
            if (down == 3 && board.getPlayField()[down - 1][right] == 1 && board.getPlayField()[down - 2][right] == 1 && board.getPlayField()[down - 3][right] == 2) {
                board.flip(down-1,right,2);
                board.flip(down-2,right,2);

            }
    }

    public int calcCurrentCPUScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if (board.getPlayField()[i][j] == 2)
                    score++;
            }
        }
        System.out.println(score);
        return score;
    }

    public int calcOutcome(int down, int right) {
        int outcome = 0;
        if (board.getPlayField()[down][right] != 0) {
            //If the position is occupied the outcome is irrelevant.
            return -1;
        }
        //----->
        if (right + 1 < 3 && board.getPlayField()[down][right + 1] == 1 && board.getPlayField()[down][right + 2] == 2) {
            outcome++;
        } else
            //----->
            if (right == 0 && board.getPlayField()[down][right + 2] == 1 && board.getPlayField()[down][right + 1] == 1 && board.getPlayField()[down][right + 3] == 2) {
                outcome += 2;
            }
        //<-----
        if (right - 1 > 0 && board.getPlayField()[down][right - 1] == 1 && board.getPlayField()[down][right - 2] == 2) {
            outcome++;
        } else
            //<-----
            if (right == 3 && board.getPlayField()[down][right - 1] == 1 && board.getPlayField()[down][right - 2] == 1 && board.getPlayField()[down][right - 3] == 2) {
                outcome += 2;
            }
        //down
        if (down + 1 < 3 && board.getPlayField()[down + 1][right] == 1 && board.getPlayField()[down + 2][right] == 2) {
            outcome++;
        } else
            //down
            if (down == 0 && board.getPlayField()[down + 2][right] == 1 && board.getPlayField()[down + 1][right] == 1 && board.getPlayField()[down + 3][right] == 2) {
                outcome += 2;
            }
        //up
        if (down - 1 > 0 && board.getPlayField()[down - 1][right] == 1 && board.getPlayField()[down - 2][right] == 2) {
            outcome++;
        } else
            //up
            if (down == 3 && board.getPlayField()[down - 1][right] == 1 && board.getPlayField()[down - 2][right] == 1 && board.getPlayField()[down - 3][right] == 2) {
                outcome += 2;
            }
        return outcome;
    }

}
