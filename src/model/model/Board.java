package model;

public class Board {
	private char[][] grid;
    private int ships;

    public Board() {
        grid = new char[10][10]; 
        ships = 5;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '~'; // Agua
            }
        }
        placeShips();
    }

    private void placeShips() {
        int shipsPlaced = 0;
        while (shipsPlaced < ships) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if (grid[x][y] == '~') {
                grid[x][y] = 'S';
                shipsPlaced++;
            }
        }
    }
    
    public char[][] getGrid() {
        return grid;
    }
    
    public boolean shoot(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || grid[x][y] == 'X' || grid[x][y] == 'O') {
            return false;
        }
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X'; // Tocado
            return true;
        } else {
            grid[x][y] = 'O'; // Agua
            return false;
        }
    }
    
    public boolean allShipsSunk() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }

}
