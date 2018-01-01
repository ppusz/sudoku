package name.pusz.sudoku;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolverTestSuite {

    Board board;
    Solver solver;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        solver = new Solver(board);
    }

    @Test
    public void testSolveCellsHavingOneAvailableValue() {
        //Given
        board.getCells()[4][4].getAvailableValues().clear();
        board.getCells()[4][4].getAvailableValues().add(5);

        //When
        boolean solved =  solver.solveCellsHavingOneAvailableValue();
        System.out.println(board);

        //Then
        Assert.assertTrue(solved);
        Assert.assertEquals(5, (int) board.getCellValue(4, 4));
    }

    @Test
    public void testSolveRows() throws Exception {
        //Given
        int row = 4;
        board.setValueToCell(row,0, 1);
        board.setValueToCell(row,1, 2);
        board.setValueToCell(row,4, 5);
        board.setValueToCell(row,5, 6);
        board.setValueToCell(row,6, 7);
        board.setValueToCell(row,7, 8);
        System.out.println(board);

        board.getCells()[row][2].removeAvailableValue(4);
        board.getCells()[row][3].removeAvailableValue(3);
        board.getCells()[row][3].removeAvailableValue(9);
        board.getCells()[row][8].removeAvailableValue(4);

        //When
        boolean solved =  solver.solveRows();
        System.out.println(board);

        //Then
        Assert.assertTrue(solved);
        Assert.assertEquals(4, (int) board.getCellValue(row, 3));
    }

    @Test
    public void testSolveColumns() throws Exception {
        //Given
        int column = 3;
        board.setValueToCell(0, column,1);
        board.setValueToCell(1, column,2);
        board.setValueToCell(4, column,5);
        board.setValueToCell(5, column,6);
        board.setValueToCell(6, column,7);
        board.setValueToCell(7, column,8);
        System.out.println(board);

        board.getCells()[2][column].removeAvailableValue(4);
        board.getCells()[3][column].removeAvailableValue(3);
        board.getCells()[3][column].removeAvailableValue(9);
        board.getCells()[8][column].removeAvailableValue(4);

        //When
        boolean solved =  solver.solveColumns();
        System.out.println(board);

        //Then
        Assert.assertTrue(solved);
        Assert.assertEquals(4, (int) board.getCellValue(3, column));
    }

    @Test
    public void testSolveSections() throws Exception {
        //Given
        board.setValueToCell(3, 3,1);
        board.setValueToCell(3, 4,2);
        board.setValueToCell(4, 4,5);
        board.setValueToCell(4, 5,6);
        board.setValueToCell(5, 3,7);
        board.setValueToCell(5, 4,8);
        System.out.println(board);

        board.getCells()[3][5].removeAvailableValue(4);
        board.getCells()[4][3].removeAvailableValue(3);
        board.getCells()[4][3].removeAvailableValue(9);
        board.getCells()[5][5].removeAvailableValue(4);

        //When
        boolean solved =  solver.solveSections();
        System.out.println(board);
        board.getCells()[5][5].removeAvailableValue(3);
        solver.solveSections();
        System.out.println(board);

        //Then
        Assert.assertTrue(solved);
        Assert.assertEquals(4, (int) board.getCellValue(4, 3));
        Assert.assertEquals(3, (int) board.getCellValue(3, 5));
    }

    @Test
    public void testSolve() throws Exception {
        //When
        board.setValueToCell(0, 1, 2);
        board.setValueToCell(0, 3, 5);
        board.setValueToCell(0, 5, 1);
        board.setValueToCell(0, 7, 9);
        board.setValueToCell(1, 0, 8);
        board.setValueToCell(1, 3, 2);
        board.setValueToCell(1, 5, 3);
        board.setValueToCell(1, 8, 6);
        board.setValueToCell(2, 1, 3);
        board.setValueToCell(2, 4, 6);
        board.setValueToCell(2, 7, 7);
        board.setValueToCell(3, 2, 1);
        board.setValueToCell(3, 6, 6);
        board.setValueToCell(4, 0, 5);
        board.setValueToCell(4, 1, 4);
        board.setValueToCell(4, 7, 1);
        board.setValueToCell(4, 8, 9);
        board.setValueToCell(5, 2, 2);
        board.setValueToCell(5, 6, 7);
        board.setValueToCell(6, 1, 9);
        board.setValueToCell(6, 4, 3);
        board.setValueToCell(6, 7, 8);
        board.setValueToCell(7, 0, 2);
        board.setValueToCell(7, 3, 8);
        board.setValueToCell(7, 5, 4);
        board.setValueToCell(7, 8, 7);
        board.setValueToCell(8, 1, 1);
        board.setValueToCell(8, 3, 9);
        board.setValueToCell(8, 5, 7);
        board.setValueToCell(8, 7, 6);
        System.out.println(board);

        //Then
        solver.solve();
        System.out.println(board);
    }
}
