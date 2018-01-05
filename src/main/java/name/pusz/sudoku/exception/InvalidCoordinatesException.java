package name.pusz.sudoku.exception;

public class InvalidCoordinatesException extends IndexOutOfBoundsException {

    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
