package model;

public class Ship {
    private int size;
    private String name;
    private boolean sunk;
    private int[][] occupiedSquares;

    public Ship(int size, String name) {
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
        this.occupiedSquares = occupiedSquares;
    }

    public boolean hit(int x, int y) {
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


