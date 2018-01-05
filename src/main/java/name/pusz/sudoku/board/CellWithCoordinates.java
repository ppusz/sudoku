package name.pusz.sudoku.board;

public class CellWithCoordinates {

    private final Cell cell;
    private final Coordinates coordinates;

    CellWithCoordinates(Cell cell, Coordinates coordinates) {
        this.cell = cell;
        this.coordinates = coordinates;
    }

    public Cell getCell() {
        return cell;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
