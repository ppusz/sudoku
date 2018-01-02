package name.pusz.sudoku;

public class SudokuRunner {

    public static void main(String[] args) {

        SudokuGame theGame;
        boolean gameFinished = false;
        System.out.println(SudokuGame.WELCOME_MESSAGE);
        while (!gameFinished ) {
            theGame = new SudokuGame();
            gameFinished = !theGame.resolveSudoku();
        }
    }
}
