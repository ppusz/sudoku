package name.pusz.sudoku.exception;

public class CannotSolveException extends RuntimeException {

    public CannotSolveException() {
    }

    public CannotSolveException(String s) {
        super(s);
    }
}
