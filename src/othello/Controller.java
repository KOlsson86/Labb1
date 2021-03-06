package othello;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-09-18.
 * The controller class containing most logic operations.
 */
class Controller {

    private final Console console;
    private final Board board;
    private final AI cpu;

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
        try {
            String s = JOptionPane.showInputDialog("Where do you want to place your piece? (3,3 for example)");
            if (s == null)
                System.exit(0);
            String[] split = s.split(",");
            int Y = Integer.parseInt(split[0]);
            int X = Integer.parseInt(split[1]);

            if (board.getPlayField()[Y][X] == 0) {
                emulatePlayerFlip(board.getPlayField(), Y, X);
            } else {
                playerPlay();
            }
            //In case the player types in text, a number that's too high etc.
        } catch (Exception ignored) {
            playerPlay();
        }
    }

    /**
     * A method representing the computers move.
     */
    private void cpuPlay() {
        Coordinate coordinate = cpu.alphaBeta(board.getPlayField());
        emulateCPUFlip(board.getPlayField(), coordinate.getY(), coordinate.getX());
    }

    public ArrayList<Coordinate> actions(int[][] playField) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (playField[i][j] == 0) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        return coordinates;
    }

    /**
     * A method representing the majority of the game.
     * While the board isn't full the game continues. If the player attempt to put a piece outside the board he is
     * prompted to play again.
     */
    private void play() {
        while (notFull()) {
            playerPlay();
            if (notFull())
                cpuPlay();
            printBoard();
        }
        int cpuScore = calcCurrentCPUScore(board.getPlayField());
        int playerScore = calcCurrentPlayerScore(board.getPlayField());
        if (cpuScore > playerScore) {
            System.out.println("The computer won. Try again...");
        } else if (cpuScore < playerScore) {
            System.out.println("Holy shit, you actually won!");
        } else
            System.out.println("Draw. Nobody is happy.");
    }

    /**
     * A method to check if the board is full. If it isn't the game is still ongoing.
     *
     * @return false if we find an empty spot, otherwise true.
     */
    private boolean notFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getPlayField()[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    public int[][] copyArray(int[][] oldArray, int[][] newArray) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(oldArray[i], 0, newArray[i], 0, 4);
        }
        return newArray;
    }

    /**
     * A method that prints the current state of the board.
     */
    private void printBoard() {
        console.appendText(board.playFieldToString());
    }

    private int[][] emulateFlip(int[][] playField, int down, int right, int colour) {
        if (colour == 1) {
            playField[down][right] = 1;
        } else
            playField[down][right] = 2;
        return playField;
    }

    public int[][] emulateCPUFlip(int[][] playField, int down, int right) {
        //----->
        if (right + 1 < 3 && playField[down][right + 1] == 1 && playField[down][right + 2] == 2) {
            playField = emulateFlip(playField, down, right + 1, 2);

        } else
            //----->
            if (right == 0 && playField[down][right + 2] == 1 && playField[down][right + 1] == 1 && playField[down][right + 3] == 2) {
                playField = emulateFlip(playField, down, right + 1, 2);
                playField = emulateFlip(playField, down, right + 2, 2);
            }
        //<-----
        if (right - 1 > 0 && playField[down][right - 1] == 1 && playField[down][right - 2] == 2) {
            playField = emulateFlip(playField, down, right - 1, 2);
        } else
            //<-----
            if (right == 3 && playField[down][right - 1] == 1 && playField[down][right - 2] == 1 && playField[down][right - 3] == 2) {
                playField = emulateFlip(playField, down, right - 1, 2);
                playField = emulateFlip(playField, down, right - 2, 2);
            }
        //down
        if (down + 1 < 3 && playField[down + 1][right] == 1 && playField[down + 2][right] == 2) {
            playField = emulateFlip(playField, down + 1, right, 2);
        } else
            //down
            if (down == 0 && playField[down + 2][right] == 1 && playField[down + 1][right] == 1 && playField[down + 3][right] == 2) {
                playField = emulateFlip(playField, down + 1, right, 2);
                playField = emulateFlip(playField, down + 2, right, 2);
            }
        //up
        if (down - 1 > 0 && playField[down - 1][right] == 1 && playField[down - 2][right] == 2) {
            playField = emulateFlip(playField, down - 1, right, 2);
        } else
            //up
            if (down == 3 && playField[down - 1][right] == 1 && playField[down - 2][right] == 1 && playField[down - 3][right] == 2) {
                playField = emulateFlip(playField, down - 1, right, 2);
                playField = emulateFlip(playField, down - 2, right, 2);
            }

        //Diagonal

        if (right + 1 < 3 && down - 1 > 0 && playField[down - 1][right + 1] == 1 && playField[down - 2][right + 2] == 2) {
            playField = emulateFlip(playField, down - 1, right + 1, 2);
        }

        if (right == 0 && down == 3 && playField[down - 2][right + 2] == 1 && playField[down - 1][right + 1] == 1 && playField[down - 3][right + 3] == 2) {
            playField = emulateFlip(playField, down - 1, right + 1, 2);
            playField = emulateFlip(playField, down - 2, right + 2, 2);
        }

        if (right - 1 > 0 && down + 1 < 3 && playField[down + 1][right - 1] == 1 && playField[down + 2][right - 2] == 2) {
            playField = emulateFlip(playField, down + 1, right - 1, 2);
        }

        if (right == 3 && down == 0 && playField[down + 1][right - 1] == 1 && playField[down + 2][right - 2] == 1 && playField[down + 3][right - 3] == 2) {
            playField = emulateFlip(playField, down + 1, right - 1, 2);
            playField = emulateFlip(playField, down + 2, right - 2, 2);
        }

        if (down + 1 < 3 && right + 1 < 3 && playField[down + 1][right + 1] == 1 && playField[down + 2][right + 2] == 2) {
            playField = emulateFlip(playField, down + 1, right + 1, 2);
        }
        //down
        if (down == 0 && right == 0 && playField[down + 2][right + 2] == 1 && playField[down + 1][right + 1] == 1 && playField[down + 3][right + 3] == 2) {
            playField = emulateFlip(playField, down + 1, right + 1, 2);
            playField = emulateFlip(playField, down + 2, right + 2, 2);
        }

        if (down - 1 > 0 && right - 1 > 0 && playField[down - 1][right - 1] == 1 && playField[down - 2][right - 2] == 2) {
            playField = emulateFlip(playField, down - 1, right - 1, 2);
        }
        //up
        if (down == 3 && right == 3 && playField[down - 1][right - 1] == 1 && playField[down - 2][right - 2] == 1 && playField[down - 3][right - 3] == 2) {
            playField = emulateFlip(playField, down - 1, right - 1, 2);
            playField = emulateFlip(playField, down - 2, right - 2, 2);
        }

        playField[down][right] = 2;
        return playField;
    }

    public int[][] emulatePlayerFlip(int[][] playField, int down, int right) {
        //----->
        if (right + 1 < 3 && playField[down][right + 1] == 2 && playField[down][right + 2] == 1) {
            playField = emulateFlip(playField, down, right + 1, 1);

        } else
            //----->
            if (right == 0 && playField[down][right + 2] == 2 && playField[down][right + 1] == 2 && playField[down][right + 3] == 1) {
                playField = emulateFlip(playField, down, right + 1, 1);
                playField = emulateFlip(playField, down, right + 2, 1);
            }
        //<-----
        if (right - 1 > 0 && playField[down][right - 1] == 2 && playField[down][right - 2] == 1) {
            playField = emulateFlip(playField, down, right - 1, 1);
        } else
            //<-----
            if (right == 3 && playField[down][right - 1] == 2 && playField[down][right - 2] == 2 && playField[down][right - 3] == 1) {
                playField = emulateFlip(playField, down, right - 1, 1);
                playField = emulateFlip(playField, down, right - 2, 1);
            }
        //down
        if (down + 1 < 3 && playField[down + 1][right] == 2 && playField[down + 2][right] == 1) {
            playField = emulateFlip(playField, down + 1, right, 1);
        } else
            //down
            if (down == 0 && playField[down + 2][right] == 2 && playField[down + 1][right] == 2 && playField[down + 3][right] == 1) {
                playField = emulateFlip(playField, down + 1, right, 1);
                playField = emulateFlip(playField, down + 2, right, 1);
            }
        //up
        if (down - 1 > 0 && playField[down - 1][right] == 2 && playField[down - 2][right] == 1) {
            playField = emulateFlip(playField, down - 1, right, 1);
        } else
            //up
            if (down == 3 && playField[down - 1][right] == 2 && playField[down - 2][right] == 2 && playField[down - 3][right] == 1) {
                playField = emulateFlip(playField, down - 1, right, 1);
                playField = emulateFlip(playField, down - 2, right, 1);

            }

        //Diagonal

        if (right + 1 < 3 && down - 1 > 0 && playField[down - 1][right + 1] == 2 && playField[down - 2][right + 2] == 1) {
            playField = emulateFlip(playField, down - 1, right + 1, 1);
        }

        if (right == 0 && down == 3 && playField[down - 2][right + 2] == 2 && playField[down - 1][right + 1] == 2 && playField[down - 3][right + 3] == 1) {
            playField = emulateFlip(playField, down - 1, right + 1, 1);
            playField = emulateFlip(playField, down - 2, right + 2, 1);
        }

        if (right - 1 > 0 && down + 1 < 3 && playField[down + 1][right - 1] == 2 && playField[down + 2][right - 2] == 1) {
            playField = emulateFlip(playField, down + 1, right - 1, 1);
        }

        if (right == 3 && down == 0 && playField[down + 1][right - 1] == 2 && playField[down + 2][right - 2] == 2 && playField[down + 3][right - 3] == 1) {
            playField = emulateFlip(playField, down + 1, right - 1, 1);
            playField = emulateFlip(playField, down + 2, right - 2, 1);
        }

        if (down + 1 < 3 && right + 1 < 3 && playField[down + 1][right + 1] == 2 && playField[down + 2][right + 2] == 1) {
            playField = emulateFlip(playField, down + 1, right + 1, 1);
        }
        //down
        if (down == 0 && right == 0 && playField[down + 2][right + 2] == 2 && playField[down + 1][right + 1] == 2 && playField[down + 3][right + 3] == 1) {
            playField = emulateFlip(playField, down + 1, right + 1, 1);
            playField = emulateFlip(playField, down + 2, right + 2, 1);
        }

        if (down - 1 > 0 && right - 1 > 0 && playField[down - 1][right - 1] == 2 && playField[down - 2][right - 2] == 1) {
            playField = emulateFlip(playField, down - 1, right - 1, 1);
        }
        //up
        if (down == 3 && right == 3 && playField[down - 1][right - 1] == 2 && playField[down - 2][right - 2] == 2 && playField[down - 3][right - 3] == 1) {
            playField = emulateFlip(playField, down - 1, right - 1, 1);
            playField = emulateFlip(playField, down - 2, right - 2, 1);
        }

        playField[down][right] = 1;
        return playField;
    }

    public int calcCurrentCPUScore(int[][] playField) {
        int score = 0;
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if (playField[i][j] == 2)
                    score++;
            }
        }
        return score;
    }

    private int calcCurrentPlayerScore(int[][] playField) {
        int score = 0;
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if (playField[i][j] == 1)
                    score++;
            }
        }
        return score;

    }
}
