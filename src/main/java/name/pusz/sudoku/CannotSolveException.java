package name.pusz.sudoku;

public class CannotSolveException extends RuntimeException {

    public CannotSolveException() {
    }

    public CannotSolveException(String s) {
        super(s);
    }
}
