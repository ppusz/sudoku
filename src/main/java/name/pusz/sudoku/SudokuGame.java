package name.pusz.sudoku;

import java.util.Scanner;

public class SudokuGame {

    public static Scanner scanner = new Scanner(System.in);

    public boolean resolveSudoku() {
        boolean result = false;
        boolean answered = false;
        String answer;

        while (!answered) {
            System.out.println("Another game? (Yes/No)");
            answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("yes")) {
                result = true;
                answered = true;
            } else if (answer.equals("no")) {
                result = false;
                answered = true;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        return result;
    }
}
