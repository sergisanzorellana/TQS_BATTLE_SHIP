package model;

public class Ship {
    private int size;
    private String name;
    private boolean sunk;
    private int[][] occupiedSquares;

    public Ship(int size, String name) {
        assert size > 0 : "Size must be positive"; // Precondition: size must be positive
        assert name != null && !name.isEmpty() : "Name cannot be null or empty"; // Precondition: name cannot be null or empty
        this.size = size;
        this.name = name;
        this.sunk = false;
        // Postcondition: after construction, size and name must be properly initialized
        assert this.size == size : "Size initialization failed";
        assert this.name.equals(name) : "Name initialization failed";
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
        assert occupiedSquares != null : "Occupied squares cannot be null"; // Precondition: occupiedSquares cannot be null
        assert occupiedSquares.length == size : "Occupied squares length must match ship size"; // Precondition: length must match size
        for (int[] square : occupiedSquares) {
            assert square != null && square.length == 2 : "Each square must have two coordinates"; // Precondition: each square must have two coordinates
        }
        this.occupiedSquares = occupiedSquares;
        // Postcondition: occupied squares should be properly set
        assert this.occupiedSquares.length == occupiedSquares.length : "Occupied squares not properly assigned";
    }
    
    public int[][] getOccupiedSquares() {
        return this.occupiedSquares;
    }

    public boolean hit(int x, int y) {
        assert occupiedSquares != null : "Occupied squares must be set before hitting"; // Precondition: occupiedSquares must be set
        boolean hit = false;
        for (int i = 0; i < size; i++) {
            if (occupiedSquares[i][0] == x && occupiedSquares[i][1] == y) {
                occupiedSquares[i][0] = -1; // Mark as hit
                occupiedSquares[i][1] = -1;
                checkSunk();
                hit = true;
                break;
            }
        }
        // Postcondition: if a ship part is hit, it should be marked as sunk if all parts are hit
        assert (!hit && !sunk) || (hit && sunk) || (hit && !sunk) || sunk : "Ship sinking status inconsistent with hits";
        return hit;
    }

    private void checkSunk() {
        for (int i = 0; i < size; i++) {
            if (occupiedSquares[i][0] != -1) {
                return; // Ship is not sunk
            }
        }
        sunk = true; // Ship is sunk
    }
}
