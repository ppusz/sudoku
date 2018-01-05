package name.pusz.sudoku.board;

import name.pusz.sudoku.exception.InvalidValueException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTestSuite {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testNewBoard() throws InvalidValueException {
        //When
        board.getCells()[0][0].setValue(9);

        //Then
        int receivedValue = board.getCells()[0][0].getValue();
        Assert.assertEquals(9, receivedValue);
    }

    @Test
    public void testBoardToString() throws InvalidValueException {
        //Given
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.getCells()[i][j].setValue((i + j) % 9 + 1);
            }
        }

        System.out.println(board.getPrintForm());
    }

    @Test
    public void testClone() throws Exception {
        //Given
        board.getCells()[0][1].setValue(2);
        board.getCells()[0][7].setValue(3);
        board.getCells()[8][1].setValue(1);
        board.getCells()[8][7].setValue(4);
        board.getCells()[6][6].setValue(9);
        board.getCells()[7][8].setValue(6);
        System.out.println(board.getPrintForm());

        //When
        Board clonedBoard = board.copy();
        System.out.println(clonedBoard.getPrintForm());

        //Then
        for (int i = 0; i <= Board.MAX_INDEX; i++) {
            for (int j = 0; j <= Board.MAX_INDEX; j++) {
                Assert.assertNotEquals(board.getCells()[i][j], clonedBoard.getCells()[i][j]);
            }
        }
    }

    @Test
    public void testSetValueToCell() {
        //When
        board.setValueToCell(3, 4, 5);
        Integer readValue = board.getCellValue(3, 4);

        //Then
        Assert.assertEquals(new Integer(5), readValue);
        Assert.assertTrue(board.getCells()[8][8].getAvailableValues().contains(5));
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                Assert.assertFalse(board.getCells()[i][j].getAvailableValues().contains(5));
            }
        }
        for (int i = 0; i <= Board.MAX_INDEX; i++) {
            Assert.assertFalse(board.getCells()[3][i].getAvailableValues().contains(5));
            Assert.assertFalse(board.getCells()[i][4].getAvailableValues().contains(5));
        }
    }

    @Test
    public void testGetCellsInRow() {
        //When
        CellWithCoordinates[] cellsWithCoordinates = board.getCellsInRow(0);

        //Then
        Assert.assertEquals(Board.MAX_INDEX + 1, cellsWithCoordinates.length);
    }

    @Test
    public void testGetCellsInColumn() {
        //When
        CellWithCoordinates[] cellsWithCoordinates = board.getCellsInColumn(0);

        //Then
        Assert.assertEquals(Board.MAX_INDEX + 1, cellsWithCoordinates.length);
    }

    @Test
    public void testGetCellsInSection() {
        //When
        CellWithCoordinates[] cellsWithCoordinates = board.getCellsInSection(0, 0);

        //Then
        Assert.assertEquals(Board.MAX_INDEX + 1, cellsWithCoordinates.length);
    }
}
