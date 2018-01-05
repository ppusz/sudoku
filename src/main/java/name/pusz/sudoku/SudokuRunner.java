package name.pusz.sudoku;

import name.pusz.sudoku.game.SudokuGameProcessor;

public class SudokuRunner {

    public static final String WELCOME_MESSAGE = "Welcome to Sudoku game!\nFirst fill empty sudoku board with values"
            + " entering row, column and value (e.g. 1,2,3). You can enter multiple values at once (e.g. 1,1,2,1,2,3,...)\n"
            + "When ready enter 'SUDOKU' to solve your sudoku puzzle.";

    public static void main(String[] args) {

        SudokuGameProcessor gameProcessor;
        boolean gameFinished = false;
        System.out.println(WELCOME_MESSAGE);
        while (!gameFinished) {
            gameProcessor = new SudokuGameProcessor();
            gameFinished = !gameProcessor.resolveSudoku();
        }
    }
}
