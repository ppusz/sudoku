package name.pusz.sudoku.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardValidatorTestSuite {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.getCells()[0][1].setValue(2);
        board.getCells()[0][7].setValue(3);
        board.getCells()[8][1].setValue(1);
        board.getCells()[8][7].setValue(4);
        board.getCells()[6][6].setValue(9);
        board.getCells()[7][8].setValue(6);
        System.out.println(board.getPrintForm());
    }

    @Test
    public void testCheckValueSuit() {
        //Given
        BoardValidator validator = new BoardValidator(board);

        //When
        boolean check1 = validator.checkValueSuit(0, 4, 5);
        boolean check2 = validator.checkValueSuit(8, 0, 4);
        boolean check3 = validator.checkValueSuit(3, 7, 5);
        boolean check4 = validator.checkValueSuit(3, 7, 3);
        boolean check5 = validator.checkValueSuit(8, 8, 5);
        boolean check6 = validator.checkValueSuit(8, 8, 6);

        //Then
        Assert.assertTrue(check1);
        Assert.assertFalse(check2);
        Assert.assertTrue(check3);
        Assert.assertFalse(check4);
        Assert.assertTrue(check5);
        Assert.assertFalse(check6);
    }
}
