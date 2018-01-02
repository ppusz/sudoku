package name.pusz.sudoku.game;

import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.board.InvalidCoordinatesException;
import name.pusz.sudoku.board.InvalidValueException;

import java.util.HashMap;
import java.util.Map;

public class SudokuEntryReader {

    public Map<Coordinates, Integer> read(String entry) throws InvalidCoordinatesException, InvalidValueException {
        Map<Coordinates, Integer> readEntries = new HashMap<>();
        int row;
        int column;
        int value;

        entry = entry.replaceAll("\\s+", "");
        if (entry.length() % 6 != 5) {
            throw new InvalidValueException("Wrong entry.");
        }

        for (int i = 4; i < entry.length(); i = i + 6) {
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
        int result;
        try {
            result = Integer.valueOf(coordinate);
            if (result < 1 || result > 9) {
                throw new InvalidCoordinatesException("Invalid coordinate: " + coordinate);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCoordinatesException("Invalid coordinate: " + coordinate);
        }

        return result;
    }

    private void readComma(String comma) throws InvalidValueException {
        if (!comma.equals(",")) {
            throw new InvalidValueException(comma + " is not a comma.");
        }
    }

    private int readValue(String value) throws InvalidValueException {
        int result;
        try {
            result = Integer.valueOf(value);
            if (result < 1 || result > 9) {
                throw new InvalidValueException("Invalid value: " + value);
            }
        } catch (NumberFormatException e) {
            throw new InvalidValueException("Invalid value: " + value);
        }

        return result;
    }
}
