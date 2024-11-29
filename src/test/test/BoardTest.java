package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Board;

public class BoardTest {
    @Test
    public void testBoard() {
        Board board = new Board();
        char[][] grid = board.getGrid();
        int waterCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == '~') {
                    waterCount++;
                }
            }
        }
        assertEquals(83, waterCount); 
    }
    
    @Test
    public void testPlaceShip() {
        Board board = new Board();
        int initialShipCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getGrid()[i][j] == 'S') {
                    initialShipCount++;
                }
            }
        }
        assertEquals(17, initialShipCount); 
    }
    
    @Test
    public void testShoot() {
        Board board = new Board();
        boolean hit = board.shoot(0, 0);
        if (board.getGrid()[0][0] == 'X') {
            assertTrue(hit);
        } else if (board.getGrid()[0][0] == 'O') {
            assertFalse(hit);
        }
    }
    
    @Test
    public void testAllShipsSunk() {
        Board board = new Board();
        // Simula hundir todos los barcos
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getGrid()[i][j] == 'S') {
                    board.shoot(i, j);
                }
            }
        }
        assertTrue(board.allShipsSunk());
    }
    
    @Test
    public void testShootOutOfBounds() {
        Board board = new Board();
        
        assertFalse(board.shoot(-1, 0), "Shooting at (-1, 0) should return false.");
        assertFalse(board.shoot(0, -1), "Shooting at (0, -1) should return false.");
        assertFalse(board.shoot(10, 0), "Shooting at (10, 0) should return false.");
        assertFalse(board.shoot(0, 10), "Shooting at (0, 10) should return false.");
        assertFalse(board.shoot(11, 11), "Shooting at (11, 11) should return false.");
        assertFalse(board.shoot(-5, -5), "Shooting at (-5, -5) should return false.");
    }
    
}
