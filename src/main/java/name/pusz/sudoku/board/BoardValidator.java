package name.pusz.sudoku.board;

import name.pusz.sudoku.exception.InvalidCoordinatesException;
import name.pusz.sudoku.exception.InvalidValueException;

public class BoardValidator {

    private static final String INVALID_VALUE = "Invalid value: ";
    private static final String INVALID_COLUMN = "Invalid column coordinate: ";
    private static final String INVALID_ROW = "Invalid row coordinate: ";

    private final Board board;

    BoardValidator(Board board) {
        this.board = board;
    }

    public boolean checkValueSuit(int row, int column, int value) throws InvalidCoordinatesException, InvalidValueException {
        checkValue(value);
        checkCoordinates(row, column);

        return validateColumn(row, column, value)
                && validateRow(row, column, value)
                && validateSector(row, column, value);
    }

    private boolean validateRow(int row, int column, int value) {
        for (int i = Board.MIN_INDEX; i <= Board.MAX_INDEX; i++) {
            Integer checkedValue = board.getCells()[row][i].getValue();
            if (checkedValue != null && checkedValue == value && i != column) {
                return false;
            }
        }
        return true;
    }

    private boolean validateColumn(int row, int column, int value) {
        for (int i = Board.MIN_INDEX; i <= Board.MAX_INDEX; i++) {
            Integer checkedValue = board.getCells()[i][column].getValue();
            if (checkedValue != null && checkedValue == value && i != row) {
                return false;
            }
        }
        return true;
    }

    private boolean validateSector(int row, int column, int value) {
        int columnSector = column / 3;
        int rowSector = row / 3;
        for (int i = rowSector * 3; i < rowSector * 3 + 3; i++) {
            for (int j = columnSector * 3; j < columnSector * 3 + 3; j++) {
                Integer checkedValue = board.getCells()[i][j].getValue();
                if (checkedValue != null && checkedValue == value
                        && i != row && j != column) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkCoordinates(int row, int column) throws InvalidCoordinatesException {
        if (column < Board.MIN_INDEX || column > Board.MAX_INDEX) {
            throw new InvalidCoordinatesException(INVALID_COLUMN + column);
        }
        if (row < Board.MIN_INDEX || row > Board.MAX_INDEX) {
            throw new InvalidCoordinatesException(INVALID_ROW + row);
        }
    }

    private static void checkValue(int value) throws InvalidValueException {
        if (value < 1 || value > 9) {
            throw new InvalidValueException(INVALID_VALUE + value);
        }
    }
}
