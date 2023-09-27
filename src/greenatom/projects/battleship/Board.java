package greenatom.projects.battleship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private final int size = 10;
    private int[][] boardStatus;
    private ArrayList<Ship> fleet = new ArrayList<>();
    private Set<Cell> borders = new HashSet<>();
    private ArrayList<Cell> hitCells = new ArrayList<>();

    /** пусть статус 0 - пусто, не было выстрела
     * статус 1 - пусто, был выстрел
     * статус 2 - занято, не было выстрела
     * статус 3 - занято, был выстрел
     */
    public Board() {
        boardStatus = new int[size][size];
    }

    public int[][] getBoardStatus() {
        return boardStatus;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public boolean canPlaceShip(Ship ship) {
        boolean rsl = true;
        for (Cell cell : ship.getCoordinates()) {
            int x = cell.getX();
            int y = cell.getY();
            if (x >= size || y >= size || x < 0 || y < 0) {
                rsl = false;
            } else {
                Set<Cell> bordersList = getBorders();
                if (bordersList.contains(cell) || boardStatus[x][y] != 0) {
                    rsl = false;
                }
            }
        }
        return rsl;
    }

    private Set<Cell> getBorders() {
        if (!fleet.isEmpty()) {
            for (Ship battleship : fleet) {
                borders = Stream.concat(borders.stream(), battleship.borders(boardStatus).stream()).collect(Collectors.toSet());
            }
        }
        return borders;
    }

    public void placeShip(Ship ship) throws IndexOutOfBoundsException, FieldOccupiedException {
        for (Cell cell : ship.getCoordinates()) {
            int x = cell.getX();
            int y = cell.getY();
            if (x >= size || y >= size) {
                throw new IndexOutOfBoundsException("Координаты корабля выходят за границы поля");
            }
            if (boardStatus[x][y] != 0) {
                throw new FieldOccupiedException("Клетка (" + cell.getX() + ";" + cell.getY() + "): занята");
            }
            boardStatus[x][y] = 2;
        }
        fleet.add(ship);
    }

    public int placeShot(Cell cell, Board enemyBoard) throws InvalidShotException {
        int x = cell.getX();
        int y = cell.getY();
        if (this.boardStatus[x][y] == 1 || this.boardStatus[x][y] == 3) {
            throw new InvalidShotException("В клетку (" + cell.getX() + ";" + cell.getY()
                    + ") уже был произведен выстрел");
        } else if (enemyBoard.boardStatus[x][y] == 1) {
            throw new InvalidShotException("Клетка (" + cell.getX() + ";" + cell.getY()
                    + ") является границей утонувшего корабля");
        } else if (this.boardStatus[x][y] == 0) {
            System.out.println("Мимо!");
            this.boardStatus[x][y] = 1;
            enemyBoard.boardStatus[x][y] = 1;
        } else {
            this.boardStatus[x][y] = 3;
            enemyBoard.boardStatus[x][y] = 3;
            removeCellFromShipOrShipFromFleet(cell, enemyBoard);
        }
        return boardStatus[x][y];
    }

    private void removeCellFromShipOrShipFromFleet(Cell cell, Board enemyBoard) {
        for (Ship ship : this.fleet) {
            if (ship.hasCoordinates(cell)) {
                ship.shipHit(cell);
                enemyBoard.hitCells.add(cell);
                if (ship.isSunk()) {
                    System.out.println("Корабль потоплен!");
                    this.fleet.remove(ship);
                    ArrayList<Cell> sunkCells = new ArrayList<>(enemyBoard.hitCells);
                    enemyBoard.fleet.add(new Ship(sunkCells));
                    Set<Cell> enemyBorderList = enemyBoard.getBorders();
                    for (Cell borderCell : enemyBorderList) {
                        this.boardStatus[borderCell.getX()][borderCell.getY()] = 1;
                        enemyBoard.boardStatus[borderCell.getX()][borderCell.getY()] = 1;
                    }
                    enemyBoard.hitCells.clear();
                } else {
                    System.out.println("Корабль подбит!");
                }
                break;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(boardStatus[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void generateShip(int deckNumber, int shipCount) {
        boolean rsl;
        for (int i = 0; i < shipCount; i++) {
            rsl = true;
            while (rsl) {
                Ship ship = getRandomShip(deckNumber);
                if (canPlaceShip(ship)) {
                    try {
                        placeShip(ship);
                        rsl = false;
                    } catch (IndexOutOfBoundsException | FieldOccupiedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private Ship getRandomShip(int deckNumber) {
        Random random = new Random();
        //Пусть 0 - это вверх, 1 - вправо, 2 - вниз, 3 - влево
        int direction = random.nextInt(4);
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        Cell[] cells = new Cell[deckNumber];
        cells[0] = new Cell(x, y);
        switch (direction) {
            case 0:
                for (int j = 1; j < deckNumber; j++) {
                    cells[j] = new Cell(x - j, y);
                }
                break;

            case 1:
                for (int j = 1; j < deckNumber; j++) {
                    cells[j] = new Cell(x, y + j);
                }
                break;

            case 2:
                for (int j = 1; j < deckNumber; j++) {
                    cells[j] = new Cell(x + j, y);
                }
                break;

            case 3:
                for (int j = 1; j < deckNumber; j++) {
                    cells[j] = new Cell(x, y - j);
                }
                break;

            default:
                break;
        }
        return new Ship(cells);
    }
}
