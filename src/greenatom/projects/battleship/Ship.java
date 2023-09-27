package greenatom.projects.battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    // An array list for storing this ships coordinates
    private ArrayList<Cell> coordinates;

    public Ship(Cell[] cells) {
        coordinates = new ArrayList<>(List.of(cells));
    }

    public Ship(ArrayList<Cell> cells) {
        coordinates = cells;
    }

    public ArrayList<Cell> getCoordinates() {
        return coordinates;
    }

    public void shipHit(Cell cell) {
        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).equals(cell)) {
                coordinates.remove(i);
                break;
            }
        }
    }

    public boolean isSunk() {
        return coordinates.isEmpty();
    }

    public boolean hasCoordinates(Cell theCell) {
        boolean rsl = false;
        for (Cell cell : coordinates) {
            if (cell.equals(theCell)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public List<Cell> borders(int[][] boardStatus) {
        List<Cell> rsl = new ArrayList<>();
        for (Cell cell : coordinates) {
            int x = cell.getX();
            int y = cell.getY();
            for (int dx = (x > 0 ? -1 : 0); dx <= (x < boardStatus.length - 1 ? 1 : 0); ++dx) {
                for (int dy = (y > 0 ? -1 : 0); dy <= (y < boardStatus[0].length - 1 ? 1 : 0); ++dy) {
                    if (dx != 0 || dy != 0) {
                        Cell newCell = new Cell(x + dx, y + dy);
                        if (!coordinates.contains(newCell) && !rsl.contains(newCell)) {
                            rsl.add(newCell);
                        }
                    }
                }
            }
        }
        return rsl;
    }
}
