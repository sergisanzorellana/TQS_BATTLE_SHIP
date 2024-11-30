package model;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private char[][] grid;
    private List<Ship> ships;

    public Board() {
        grid = new char[10][10];
        ships = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '~'; // Water
            }
        }
        // Invariant: the grid should always be a 10x10 matrix initialized to '~'
        assert grid.length == 10 : "Grid should have 10 rows";
        assert grid[0].length == 10 : "Grid should have 10 columns";
    }

    public void placeShip(ShipPlacement placement) {
        assert placement != null : "Ship placement cannot be null"; // Precondition: placement cannot be null
        int x = placement.getX();
        int y = placement.getY();
        int size = placement.getSize();
        boolean isHorizontal = placement.isHorizontal();
        String shipName = placement.getName();

        // Ensure ship placement is valid
        if (canPlaceShip(x, y, size, isHorizontal)) {
            Ship ship = new Ship(size, shipName);
            int[][] occupiedSquares = new int[size][2];
            for (int i = 0; i < size; i++) {
                if (isHorizontal) {
                    grid[x][y+i] = 'S';
                    occupiedSquares[i] = new int[]{x + i, y};
                } else {
                    grid[x+i][y] = 'S';
                    occupiedSquares[i] = new int[]{x, y + i};
                }
            }
            ship.setOccupiedSquares(occupiedSquares);
            ships.add(ship);
        } else {
            throw new IllegalArgumentException("Ship placement is invalid."); // Precondition failure
        }
    }

    private boolean canPlaceShip(int x, int y, int size, boolean horizontal) {
        assert x >= 0 && x < 10 && y >= 0 && y < 10 : "Coordinates out of bounds"; // Precondition: x and y must be valid
        if (horizontal) {
            if (y + size - 1 >= 10) return false; // Ship would overflow the board
            for (int i = 0; i < size; i++) {
                if (grid[x][y + i] != '~') return false; // Check if there's space
            }
        } else {
            if (x + size - 1 >= 10) return false; // Ship would overflow the board
            for (int i = 0; i < size; i++) {
                if (grid[x + i][y] != '~') return false; // Check if there's space
            }
        }
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean shoot(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || grid[x][y] == 'X' || grid[x][y] == 'O') {
            return false;
        }
        for (Ship ship : ships) {
            if (ship.hit(x, y)) {
                grid[x][y] = 'X'; // Hit
                return true;
            }
        }
        grid[x][y] = 'O'; // Miss
        return false;
    }

    public boolean allShipsSunk() {
        boolean allSunk = true;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                allSunk = false;
                break;
            }
        }
        return allSunk;
    }
}
