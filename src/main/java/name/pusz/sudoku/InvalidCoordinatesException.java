package name.pusz.sudoku;

public class InvalidCoordinatesException extends IndexOutOfBoundsException {

    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
