package name.pusz.sudoku.board;

import org.junit.Assert;
import org.junit.Test;

public class CellTestSuite {

    @Test
    public void testNewCell() {
        //Given
        //When
        Cell cell = new Cell();

        //Then
        Integer cellValue = cell.getValue();
        Assert.assertEquals(null, cellValue);
        Assert.assertEquals(9, cell.getAvailableValues().size());
    }

    @Test
    public void testSetValue() {
        //Given
        Cell cell = new Cell();

        //When
        cell.setValue(1);
        int receivedValue = cell.getValue();

        //Then
        Assert.assertEquals(1, receivedValue);
        Assert.assertEquals(0, cell.getAvailableValues().size());
        Assert.assertFalse(cell.getAvailableValues().contains(1));
    }

    @Test
    public void testEmptyCellToString() {
        //Given
        Cell cell = new Cell();

        //When
        String cellToString = cell.toString();

        //Then
        Assert.assertEquals(" ", cellToString);
    }
}
