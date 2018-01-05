package name.pusz.sudoku.board;

import name.pusz.sudoku.exception.InvalidCoordinatesException;
import name.pusz.sudoku.exception.InvalidValueException;

import java.util.Set;

public class Board extends Prototype<Board> {

    public final static int MIN_INDEX = 0;
    public final static int MAX_INDEX = 8;
    private Cell[][] cells = new Cell[MAX_INDEX + 1][MAX_INDEX + 1];

    public Board() {
        for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
            for (int j = MIN_INDEX; j <= MAX_INDEX; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Board copy() throws CloneNotSupportedException {
        Board clonedBoard = this.clone();
        clonedBoard.cells = new Cell[MAX_INDEX + 1][MAX_INDEX + 1];
        for (int i = 0; i <= MAX_INDEX; i++) {
            for (int j = 0; j <= MAX_INDEX; j++) {
                clonedBoard.cells[i][j] = this.cells[i][j].copy();
            }
        }
        return clonedBoard;
    }

    public boolean setValueToCell(int row, int column, int value) throws InvalidCoordinatesException, InvalidValueException {
        BoardValidator validator = new BoardValidator(this);
        if (validator.checkValueSuit(row, column, value)) {
            cells[row][column].setValue(value);
            int columnSector = column / 3;
            int rowSector = row / 3;
            for (int i = rowSector * 3; i < rowSector * 3 + 3; i++) {
                for (int j = columnSector * 3; j < columnSector * 3 + 3; j++) {
                    cells[i][j].removeAvailableValue(value);
                }
            }
            for (int i = 0; i <= MAX_INDEX; i++) {
                cells[row][i].removeAvailableValue(value);
                cells[i][column].removeAvailableValue(value);
            }
            return true;
        }

        return false;
    }

    public Integer getCellValue(int row, int column) throws InvalidCoordinatesException {
        BoardValidator.checkCoordinates(row, column);
        return cells[row][column].getValue();
    }

    public Set<Integer> getCellAvailableValues(int row, int column) throws InvalidCoordinatesException {
        BoardValidator.checkCoordinates(row, column);
        return cells[row][column].getAvailableValues();
    }

    public boolean isSolved() {
        for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
            for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
                if (getCellValue(r, c) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Coordinates findEmptyCell() {
        for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
            for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
                if (getCellValue(r, c) == null) {
                    return new Coordinates(r, c);
                }
            }
        }
        return null;
    }

    public String getPrintForm() {
        StringBuilder sb = new StringBuilder();
        for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
            if (i % 3 == 0 && i > 0) {
                sb.append("---+---+---\n");
            }
            for (int j = MIN_INDEX; j <= MAX_INDEX; j++) {
                if (j % 3 == 0 && j > 0) {
                    sb.append("|");
                }
                sb.append(cells[i][j]);
            }
            if (i < MAX_INDEX) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public CellWithCoordinates[] getCellsInRow(int rowIndex) {
        CellWithCoordinates[] cellsWithCoordinates = new CellWithCoordinates[MAX_INDEX + 1];
        for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
            cellsWithCoordinates[i] = new CellWithCoordinates(cells[rowIndex][i], new Coordinates(rowIndex, i));
        }
        return cellsWithCoordinates;
    }

    public CellWithCoordinates[] getCellsInColumn(int columnIndex) {
        CellWithCoordinates[] cellsWithCoordinates = new CellWithCoordinates[MAX_INDEX + 1];
        for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
            cellsWithCoordinates[i] = new CellWithCoordinates(cells[i][columnIndex], new Coordinates(i, columnIndex));
        }
        return cellsWithCoordinates;
    }

    public CellWithCoordinates[] getCellsInSection(int rowSectionIndex, int columnSectionIndex) {
        CellWithCoordinates[] cellsWithCoordinates = new CellWithCoordinates[MAX_INDEX + 1];
        int i = 0;
        for (int row = rowSectionIndex * 3; row < rowSectionIndex * 3 + 3; row++) {
            for (int column = columnSectionIndex * 3; column < columnSectionIndex * 3 + 3; column++) {
                cellsWithCoordinates[i++] = new CellWithCoordinates(cells[row][column], new Coordinates(row, column));
            }
        }
        return cellsWithCoordinates;
    }
}
