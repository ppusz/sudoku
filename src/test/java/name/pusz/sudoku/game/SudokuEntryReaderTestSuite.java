package name.pusz.sudoku.game;

import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.exception.InvalidCoordinatesException;
import name.pusz.sudoku.exception.InvalidValueException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class SudokuEntryReaderTestSuite {

    @Test
    public void testRead() {
        //Given
        SudokuEntryReader reader = new SudokuEntryReader();
        String entry1 = "1,2,3";
        String entry2 = "1,2,3,4,5,6,7,8, 9";
        String entry3 = "1,2,3,4";
        String entry4 = "1,2,3,4,5,a";
        String entry5 = "1,a,3,4,5,6";

        //When
        Map<Coordinates, Integer> readEntries1 = reader.read(entry1);
        Map<Coordinates, Integer> readEntries2 = reader.read(entry2);

        Exception exceptionEntry3 = new Exception();
        try {
            reader.read(entry3);
        } catch (Exception e) {
            exceptionEntry3 = e;
        }

        Exception exceptionEntry4 = new Exception();
        try {
            reader.read(entry4);
        } catch (Exception e) {
            exceptionEntry4 = e;
        }

        Exception exceptionEntry5 = new Exception();
        try {
            reader.read(entry5);
        } catch (Exception e) {
            exceptionEntry5 = e;
        }

        //Then
        Assert.assertEquals(1, readEntries1.size());
        Assert.assertEquals(3, readEntries2.size());
        Assert.assertEquals(new InvalidValueException("").getClass(), exceptionEntry3.getClass());
        Assert.assertEquals(new InvalidValueException("").getClass(), exceptionEntry4.getClass());
        Assert.assertEquals(new InvalidCoordinatesException("").getClass(), exceptionEntry5.getClass());
    }
}
