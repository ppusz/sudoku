package name.pusz.sudoku.solver;

public class CannotSolveException extends RuntimeException {

    public CannotSolveException() {
    }

    public CannotSolveException(String s) {
        super(s);
    }
}
