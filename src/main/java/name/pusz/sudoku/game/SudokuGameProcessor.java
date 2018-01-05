package name.pusz.sudoku.game;

import name.pusz.sudoku.board.Board;
import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.solver.Solver;

import java.util.Map;
import java.util.Scanner;

public class SudokuGameProcessor {

    private static final Scanner scanner = new Scanner(System.in);

    public SudokuGameProcessor() {
        run();
    }

    private void run() {
        Board board = new Board();
        String userEntry;
        boolean finished = false;

        System.out.println(board.getPrintForm());
        while (!finished) {
            System.out.print("> ");
            userEntry = scanner.nextLine();
            String option = userEntry.trim().toUpperCase();

            switch (option) {
                case "SUDOKU":
                case "SOLVE":
                    solveBoard(board);
                    finished = true;
                    break;
                case "QUIT":
                case "EXIT":
                    finished = true;
                    break;
                default:
                    readEntry(board, userEntry);
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
            switch (answer) {
                case "yes":
                case "y":
                    result = true;
                    answered = true;
                    break;
                case "no":
                case "n":
                    result = false;
                    answered = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        return result;
    }

    private void readEntry(Board board, String userEntry) {
        SudokuEntryReader entryReader = new SudokuEntryReader();
        Map<Coordinates, Integer> sudokuEntries;

        try {
            sudokuEntries = entryReader.read(userEntry);
            for (Map.Entry<Coordinates, Integer> entry : sudokuEntries.entrySet()) {
                board.setValueToCell(entry.getKey().getRow(),
                        entry.getKey().getColumn(), entry.getValue());
            }
            System.out.println(board.getPrintForm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void solveBoard(Board board) {
        Solver solver = new Solver(board);
        int solutions = solver.solve().size();
        System.out.println("In total found " + solutions + " solution(s).");
    }
}
