package name.pusz.sudoku.solver;

import name.pusz.sudoku.board.Board;
import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.board.InvalidValueException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public List<Board> solve() throws CannotSolveException {
        List<Board> solutions = new ArrayList<>();

        solveEvident(this.board);
        if (board.isSolved()) {
            System.out.println("Found solution:");
            System.out.println(board);
            solutions.add(board);
        } else {
            Board backtrackBoard;
            Coordinates emptyCellCoordinates = board.findEmptyCell();
            if (emptyCellCoordinates != null) {
                for (Integer guessValue : board.getCellAvailableValues(emptyCellCoordinates.getRow(), emptyCellCoordinates.getColumn())) {
                    //System.out.println("Guessing " + guessValue + " on " + emptyCellCoordinates);
                    try {
                        backtrackBoard = board;
                        board = board.copy();

                        try {
                            board.setValueToCell(emptyCellCoordinates.getRow(), emptyCellCoordinates.getColumn(), guessValue);
                        } catch (InvalidValueException e)
                        {
                            System.out.println("This should never happen during guessing. " + e);
                        }

                        Solver solver = new Solver(board);

                        try {
                            List<Board> foundSolutions = solver.solve();
                            solutions.addAll(foundSolutions);
                            //System.out.println("Solutions found: " + foundSolutions.size());
                        } catch (CannotSolveException e) {
                            //System.out.println("No solution found.");
                        }

                        board = backtrackBoard;
                    } catch (CloneNotSupportedException e) {
                        System.out.println(e);
                    }
                }
            } else {
                throw new CannotSolveException("No empty cells found!");
            }
        }

        return solutions;
    }

    private Board solveEvident(Board board) throws CannotSolveException {
        boolean progress;
        do {
            progress = solveCellsHavingOneAvailableValue(board);
            progress = progress || solveRows(board);
            progress = progress || solveColumns(board);
            progress = progress || solveSections(board);
        } while (progress);

        return board;
    }

    public boolean solveCellsHavingOneAvailableValue(Board board) throws CannotSolveException {
        boolean solvedAnything = false;
        for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
            for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
                if (board.getCellValue(r, c) == null) {
                    int availableValuesCount = board.getCellAvailableValues(r, c).size();
                    if (availableValuesCount == 0) {
                        throw new CannotSolveException();
                    } else if (availableValuesCount == 1) {
                        board.setValueToCell(r, c, board.getCellAvailableValues(r, c).iterator().next());
                        solvedAnything = true;
                    }
                }
            }
        }
        return solvedAnything;
    }

    public boolean solveRows(Board board) {
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
                            board.setValueToCell(r, c, value);
                            solvedAnything = true;
                            break;
                        }
                    }
                }
            }
        }
        return solvedAnything;
    }

    public boolean solveColumns(Board board) {
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
                            board.setValueToCell(r, c, value);
                            solvedAnything = true;
                            break;
                        }
                    }
                }
            }
        }
        return solvedAnything;
    }

    public boolean solveSections(Board board) {
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
                                    board.setValueToCell(r, c, value);
                                    solvedAnything = true;
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

}
