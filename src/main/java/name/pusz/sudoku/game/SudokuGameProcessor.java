package name.pusz.sudoku.game;

import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.board.Board;
import name.pusz.sudoku.solver.Solver;

import java.util.Map;
import java.util.Scanner;

public class SudokuGameProcessor {

    public static final String WELCOME_MESSAGE = "Welcome to Sudoku game!\nFirst fill empty sudoku board with values"
        + " entering row, column and value (e.g. 1,2,3). You can enter multiple values at once (e.g. 1,1,2,1,2,3,...)\n"
        + "When ready enter 'SUDOKU' to solve your sudoku puzzle.";

    private static final Scanner scanner = new Scanner(System.in);

    public SudokuGameProcessor() {
        run();
    }

    private void run() {
        Board board = new Board();
        SudokuEntryReader entryReader = new SudokuEntryReader();
        String userEntry;
        Map<Coordinates, Integer> sudokuEntries;
        boolean finished = false;

        System.out.println(board);
        while (!finished) {
            System.out.print("> ");
            userEntry = scanner.nextLine();

            if (userEntry.trim().toUpperCase().equals("SUDOKU") || userEntry.trim().toUpperCase().equals("SOLVE")) {
                Solver solver = new Solver(board);
                int solutions = solver.solve().size();
                System.out.println("In total found " + solutions + " solution(s).");
                finished = true;
            } else if (userEntry.trim().toLowerCase().equals("quit")
                    || userEntry.trim().toLowerCase().equals("exit")) {
                finished = true;
            } else if (userEntry.trim().length() > 0) {
                try {
                    sudokuEntries = entryReader.read(userEntry);
                    for (Map.Entry<Coordinates, Integer> entry : sudokuEntries.entrySet()) {
                        board.setValueToCell(entry.getKey().getRow(),
                                entry.getKey().getColumn(), entry.getValue());
                    }
                    System.out.println(board);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public boolean resolveSudoku() {
        boolean result = false;
        boolean answered = false;
        String answer;

        while (!answered) {
            System.out.print("Another sudoku? (Y)es/(N)o ");
            answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                result = true;
                answered = true;
            } else if (answer.equals("no") || answer.equals("n")) {
                result = false;
                answered = true;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        return result;
    }
}
