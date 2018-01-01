package name.pusz.sudoku;

public class SudokuRunner {

    public static void main(String[] args) {

        SudokuGame theGame;
        boolean gameFinished = false;
        while (!gameFinished ) {
            theGame = new SudokuGame();
            gameFinished = theGame.resolveSudoku();
        }
    }
}
