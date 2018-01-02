package name.pusz.sudoku;

import name.pusz.sudoku.game.SudokuGameProcessor;

public class SudokuRunner {

    public static void main(String[] args) {

        SudokuGameProcessor gameProcessor;
        boolean gameFinished = false;
        System.out.println(SudokuGameProcessor.WELCOME_MESSAGE);
        while (!gameFinished ) {
            gameProcessor = new SudokuGameProcessor();
            gameFinished = !gameProcessor.resolveSudoku();
        }
    }
}
