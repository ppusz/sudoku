package name.pusz.sudoku;

import name.pusz.sudoku.game.SudokuGameProcessor;
import org.apache.log4j.Logger;

public class SudokuRunner {

    private static final Logger logger = Logger.getLogger(SudokuRunner.class);
    public static final String WELCOME_MESSAGE = "Welcome to Sudoku game!\nFirst fill empty sudoku board with values"
            + " entering row, column and value (e.g. 1,2,3). You can enter multiple values at once (e.g. 1,1,2,1,2,3,...)\n"
            + "When ready enter 'SUDOKU' to solve your sudoku puzzle.";

    public static void main(String[] args) {

        SudokuGameProcessor gameProcessor;
        boolean gameFinished = false;
        System.out.println(WELCOME_MESSAGE);
        while (!gameFinished) {
            logger.debug("New main loop run");
            gameProcessor = new SudokuGameProcessor();
            gameFinished = !gameProcessor.resolveSudoku();
        }
    }
}
