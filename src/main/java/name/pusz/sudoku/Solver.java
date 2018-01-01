package name.pusz.sudoku;

import java.util.HashSet;
import java.util.Set;

public class Solver {

    Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public Board solve() throws CannotSolveException {
        boolean progress;

        do {
            progress = false;
            progress = solveCellsHavingOneAvailableValue();
            progress = progress || solveRows();
            progress = progress || solveColumns();
            progress = progress || solveSections();
        } while (progress);

        return new Board();
    }

    public boolean solveCellsHavingOneAvailableValue() throws CannotSolveException {
        boolean solvedAnything = false;
        for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
            for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
                if (board.getCellValue(r, c) == null) {
                    int availableValuesCount = board.getCellAvailableValues(r, c).size();
                    if (availableValuesCount == 0) {
                        throw new CannotSolveException();
                    } else if (availableValuesCount == 1) {
                        try {
                            board.setValueToCell(r, c,
                                    board.getCellAvailableValues(r, c).iterator().next());
                            solvedAnything = true;
                        } catch (InvalidValueException e) {
                            System.err.println("Ups, this should never happen. Problem during solving cells with one available value. " + e);
                        }
                    }
                }
            }
        }
        return solvedAnything;
    }

    public boolean solveRows() {
        boolean solvedAnything = false;
        Set<Integer> examinedValues;
        for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
            examinedValues = new HashSet<>();
            for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
                for (Integer value : board.getCells()[r][c].getAvailableValues()) {
                    if (!examinedValues.contains(value)) {
                        boolean found = false;
                        for (int i = c + 1; i <= Board.MAX_INDEX; i++) {
                            if (board.getCells()[r][i].getAvailableValues().contains(value)) {
                                examinedValues.add(value);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            try {
                                board.setValueToCell(r, c, value);
                                solvedAnything = true;
                            } catch (InvalidValueException e) {
                                System.err.println("Ups, this should never happen. Problem during solving rows. " + e);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return solvedAnything;
    }

    public boolean solveColumns() {
        boolean solvedAnything = false;
        Set<Integer> examinedValues;
        for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
            examinedValues = new HashSet<>();
            for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
                for (Integer value : board.getCells()[r][c].getAvailableValues()) {
                    if (!examinedValues.contains(value)) {
                        boolean found = false;
                        for (int i = r + 1; i <= Board.MAX_INDEX; i++) {
                            if (board.getCells()[i][c].getAvailableValues().contains(value)) {
                                examinedValues.add(value);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            try {
                                board.setValueToCell(r, c, value);
                                solvedAnything = true;
                            } catch (InvalidValueException e) {
                                System.err.println("Ups, this should never happen. Problem during solving rows. " + e);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return solvedAnything;
    }

    public boolean solveSections() {
        boolean solvedAnything = false;
        Set<Integer> examinedValues;
        for (int sectionRow = Board.MIN_INDEX; sectionRow <= Board.MAX_INDEX / 3; sectionRow++) {
            for (int sectionColumn = Board.MIN_INDEX; sectionColumn <= Board.MAX_INDEX / 3; sectionColumn++) {
                examinedValues = new HashSet<>();
                for (int r = sectionRow * 3; r < sectionRow * 3 + 3; r++) {
                    for (int c = sectionColumn * 3; c < sectionColumn * 3 + 3; c++) {
                        for (Integer value : board.getCells()[r][c].getAvailableValues()) {
                            if (!examinedValues.contains(value)) {
                                boolean found = false;
                                for (int rowIndex = ((c == sectionColumn * 3 + 2) ? r + 1 : r);
                                     rowIndex < sectionRow * 3 + 3; rowIndex++) {
                                    for (int columnIndex = ((rowIndex == r) ? c + 1 : sectionColumn * 3);
                                         columnIndex < sectionColumn * 3 + 3; columnIndex++) {
                                        if (board.getCells()[rowIndex][columnIndex].getAvailableValues().contains(value)) {
                                            examinedValues.add(value);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (!found) {
                                    try {
                                        board.setValueToCell(r, c, value);
                                        solvedAnything = true;
                                    } catch (InvalidValueException e) {
                                        System.err.println("Ups, this should never happen. Problem during solving rows. " + e);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return solvedAnything;
    }

//    private Integer searchForOnlyPossibleCellSolution(int row, int column) throws CannotSolveException {
//        if (board.getCellValue(row, column) == null) {
//            int availableValuesCount = board.getCellAvailableValues(row, column).size();
//            if (availableValuesCount == 0) {
//                throw new CannotSolveException();
//            }  else if (availableValuesCount == 1) {
//                try {
//                    board.setValueToCell(row, column,
//                            board.getCellAvailableValues(row, column).iterator().next());
//                } catch (InvalidValueException e) {
//                    System.err.println("Ups, this should never happen. " + e);
//                }
//            } else {
//                for (Integer value : board.getCellAvailableValues(row, column)) {
//                    // checking row
//
//                    //checking column
//
//                    //checking section
//
//                }
//
//            }
//        }
//    }

    //private Integer searchForOnlyPossibleCellSolution (row, column)

}
