package name.pusz.sudoku.game;

import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.exception.InvalidCoordinatesException;
import name.pusz.sudoku.exception.InvalidValueException;

import java.util.HashMap;
import java.util.Map;

public class SudokuEntryReader {

    private static final int COORDINATES_AND_VALUE_ENTRY_LENGTH = 6;
    private static final String WRONG_ENTRY = "Wrong entry.";
    private static final String INVALID_COORDINATE = "Invalid coordinate: ";
    private static final String INVALID_VALUE = "Invalid value: ";
    private static final String NOT_COMMA = " is not a comma.";

    public Map<Coordinates, Integer> read(String entry) throws InvalidCoordinatesException, InvalidValueException {
        Map<Coordinates, Integer> readEntries = new HashMap<>();
        int row;
        int column;
        int value;

        entry = entry.replaceAll("\\s+", "");
        if (entry.length() % COORDINATES_AND_VALUE_ENTRY_LENGTH != 5) {
            throw new InvalidValueException(WRONG_ENTRY);
        }

        for (int i = COORDINATES_AND_VALUE_ENTRY_LENGTH - 2; i < entry.length(); i = i + COORDINATES_AND_VALUE_ENTRY_LENGTH) {
            if (i > 4) {
                readComma(entry.substring(i - 5, i - 4));
            }
            row = readCoordinate(entry.substring(i - 4, i - 3)) - 1;
            readComma(entry.substring(i - 3, i - 2));
            column = readCoordinate(entry.substring(i - 2, i - 1)) - 1;
            readComma(entry.substring(i - 1, i));
            value = readValue(entry.substring(i, i + 1));
            readEntries.put(new Coordinates(row, column), value);
        }

        return readEntries;
    }

    private int readCoordinate(String coordinate) throws InvalidCoordinatesException {
        try {
            int result = Integer.valueOf(coordinate);
            if (result < 1 || result > 9) {
                throw new InvalidCoordinatesException(INVALID_COORDINATE + coordinate);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new InvalidCoordinatesException(INVALID_COORDINATE + coordinate);
        }
    }

    private void readComma(String comma) throws InvalidValueException {
        if (!comma.equals(",")) {
            throw new InvalidValueException(comma + NOT_COMMA);
        }
    }

    private int readValue(String value) throws InvalidValueException {
        try {
            int result = Integer.valueOf(value);
            if (result < 1 || result > 9) {
                throw new InvalidValueException(INVALID_VALUE + value);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new InvalidValueException(INVALID_VALUE + value);
        }
    }
}
