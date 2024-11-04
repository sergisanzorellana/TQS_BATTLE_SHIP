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

}
