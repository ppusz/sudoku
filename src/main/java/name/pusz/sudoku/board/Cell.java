package name.pusz.sudoku.board;

import name.pusz.sudoku.exception.InvalidValueException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cell extends Prototype<Cell> {

    private final static String VALUE = "Value ";
    private final static String NOT_POSSIBLE = " it's not possible here.";


    private Integer value;
    private Set<Integer> availableValues = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public Integer getValue() {
        return value;
    }

    public Set<Integer> getAvailableValues() {
        return availableValues;
    }

    public void setValue(Integer value) throws InvalidValueException {
        if (availableValues.contains(value)) {
            this.value = value;
            availableValues.clear();
        } else {
            throw new InvalidValueException(VALUE + value + NOT_POSSIBLE);
        }
    }

    public void removeAvailableValue(int value) {
        availableValues.remove(value);
    }

    public Cell copy() throws CloneNotSupportedException {
        Cell clonedCell = super.clone();
        clonedCell.availableValues = new HashSet<>(availableValues);
        return clonedCell;
    }

    @Override
    public String toString() {
        return "" + (value == null ? " " : value);
    }
}
