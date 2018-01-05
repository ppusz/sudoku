package name.pusz.sudoku.solver;

import name.pusz.sudoku.board.Board;
import name.pusz.sudoku.board.CellWithCoordinates;
import name.pusz.sudoku.board.Coordinates;
import name.pusz.sudoku.exception.CannotSolveException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    private final static String SOLUTION_FOUND = "Found solution:";
    private final static String NO_EMTY_CELLS_FOUND = "No empty cells found!";

    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public List<Board> solve() throws CannotSolveException {
        List<Board> solutions = new ArrayList<>();

        solveEvident(this.board);
        if (board.isSolved()) {
            System.out.println(SOLUTION_FOUND);
            System.out.println(board.getPrintForm());
            solutions.add(board);
        } else {
            Board backtrackBoard;
            Coordinates emptyCellCoordinates = board.findEmptyCell();
            if (emptyCellCoordinates != null) {
                for (Integer guessValue : board.getCellAvailableValues(emptyCellCoordinates.getRow(), emptyCellCoordinates.getColumn())) {
                    try {
                        backtrackBoard = board;
                        board = board.copy();

                        board.setValueToCell(emptyCellCoordinates.getRow(), emptyCellCoordinates.getColumn(), guessValue);

                        Solver solver = new Solver(board);
                        try {
                            List<Board> foundSolutions = solver.solve();
                            solutions.addAll(foundSolutions);
                        } catch (CannotSolveException e) {
                        }

                        board = backtrackBoard;
                    } catch (CloneNotSupportedException e) {
                        System.out.println(e);
                    }
                }
            } else {
                throw new CannotSolveException(NO_EMTY_CELLS_FOUND);
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
        for (int r = Board.MIN_INDEX; r <= Board.MAX_INDEX; r++) {
            CellWithCoordinates[] row = board.getCellsInRow(r);
            solvedAnything = solvedAnything || solveEvidentCells(row);
        }
        return solvedAnything;
    }

    public boolean solveColumns(Board board) {
        boolean solvedAnything = false;
        for (int c = Board.MIN_INDEX; c <= Board.MAX_INDEX; c++) {
            CellWithCoordinates[] column = board.getCellsInColumn(c);
            solvedAnything = solvedAnything || solveEvidentCells(column);

        }
        return solvedAnything;
    }

    public boolean solveSections(Board board) {
        boolean solvedAnything = false;
        for (int sectionRow = Board.MIN_INDEX; sectionRow <= Board.MAX_INDEX / 3; sectionRow++) {
            for (int sectionColumn = Board.MIN_INDEX; sectionColumn <= Board.MAX_INDEX / 3; sectionColumn++) {
                CellWithCoordinates[] section = board.getCellsInSection(sectionRow, sectionColumn);
                solvedAnything = solvedAnything || solveEvidentCells(section);
            }
        }
        return solvedAnything;
    }

    private boolean solveEvidentCells(CellWithCoordinates[] cells) {
        boolean solvedAnything = false;
        Set<Integer> examinedValues = new HashSet<>();
        for (int i = 0; i < cells.length; i++) {
            for (Integer value : cells[i].getCell().getAvailableValues()) {
                if (!examinedValues.contains(value)) {
                    boolean found = false;
                    for (int j = i + 1; j < cells.length; j++) {
                        if (cells[j].getCell().getAvailableValues().contains(value)) {
                            examinedValues.add(value);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        board.setValueToCell(cells[i].getCoordinates().getRow(), cells[i].getCoordinates().getColumn(), value);
                        solvedAnything = true;
                        break;
                    }
                }
            }
        }
        return solvedAnything;
    }
}
