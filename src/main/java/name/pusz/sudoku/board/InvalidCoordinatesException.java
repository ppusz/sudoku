package name.pusz.sudoku.board;

public class InvalidCoordinatesException extends IndexOutOfBoundsException {

    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
