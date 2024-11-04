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
        assertEquals(95, waterCount); 
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
        assertEquals(5, initialShipCount); 
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
}
