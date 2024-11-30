package model;

public class Ship {
    private int size;
    private String name;
    private boolean sunk;
    private int[][] occupiedSquares;

    public Ship(int size, String name) {
        assert size > 0 : "Size must be positive";
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        this.size = size;
        this.name = name;
        this.sunk = false;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setOccupiedSquares(int[][] occupiedSquares) {
        assert occupiedSquares != null : "Occupied squares cannot be null";
        assert occupiedSquares.length == size : "Occupied squares length must match ship size";
        for (int[] square : occupiedSquares) {
            assert square != null && square.length == 2 : "Each square must have two coordinates";
        }
        this.occupiedSquares = occupiedSquares;
    }
    
    public int[][] getOccupiedSquares() {
    	return this.occupiedSquares;
    }

    public boolean hit(int x, int y) {
        assert occupiedSquares != null : "Occupied squares must be set before hitting";
        for (int i = 0; i < size; i++) {
            if (occupiedSquares[i][0] == x && occupiedSquares[i][1] == y) {
                occupiedSquares[i][0] = -1; // Mark as hit
                occupiedSquares[i][1] = -1;
                checkSunk();
                return true;
            }
        }
        return false;
    }

    private void checkSunk() {
        for (int i = 0; i < size; i++) {
            if (occupiedSquares[i][0] != -1) {
                return;
            }
        }
        sunk = true;
    }
}
