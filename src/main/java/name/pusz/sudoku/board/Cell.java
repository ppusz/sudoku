package name.pusz.sudoku.board;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cell extends Prototype<Cell> {

    private Integer value;
    private Set<Integer> availableValues;

    public Cell() {
        availableValues = new HashSet<>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

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
            throw new InvalidValueException("Value " + value + " it's not possible here.");
        }
    }

    public void removeAvailableValue(int value) {
        availableValues.remove(value);
    }

    public Cell copy() throws CloneNotSupportedException {
        Cell clonedCell = super.clone();
        clonedCell.availableValues = new HashSet<>();
        for (Integer i : this.availableValues) {
            clonedCell.availableValues.add(i);
        }
        return clonedCell;
    }

    @Override
    public String toString() {
        return "" + (value == null ? " " : value);
    }
}
