package test;

import model.Board;
import model.ShipPlacement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(); // Initializes a new Board before each test
    }

    // Test for placing a ship on the board
    @Test
    public void testPlaceShip() {
        // Valid placement (horizontal) for Battleship
        ShipPlacement validPlacement = new ShipPlacement(0, 0, 3, "Battleship", true);
        board.placeShip(validPlacement);
        
        // Ensure the ship is placed correctly on the board
        char[][] grid = board.getGrid();
        assertEquals('S', grid[0][0]);
        assertEquals('S', grid[0][1]);
        assertEquals('S', grid[0][2]);

        // Valid placement (vertical) for Destroyer, placed at a different location
        validPlacement = new ShipPlacement(0, 3, 3, "Destroyer", false);
        board.placeShip(validPlacement);
        
        // Ensure the ship is placed correctly on the board
        grid = board.getGrid();
        assertEquals('S', grid[0][3]);
        assertEquals('S', grid[1][3]);
        assertEquals('S', grid[2][3]);

        // Invalid placement (out of bounds, horizontal)
        ShipPlacement invalidPlacement = new ShipPlacement(8, 8, 3, "Submarine", true);
        assertThrows(IllegalArgumentException.class, () -> board.placeShip(invalidPlacement));

        // Invalid placement (out of bounds, vertical)
        ShipPlacement invalidPlacement2 = new ShipPlacement(8, 8, 3, "Submarine", false);
        assertThrows(IllegalArgumentException.class, () -> board.placeShip(invalidPlacement2));

        // Invalid placement (overlapping with another ship)
        ShipPlacement invalidPlacement3 = new ShipPlacement(0, 0, 2, "Cruiser", true); // Overlaps with Battleship
        assertThrows(IllegalArgumentException.class, () -> board.placeShip(invalidPlacement3));
    }


    // Test for shooting at the board
    @Test
    public void testShoot() {
        ShipPlacement validPlacement = new ShipPlacement(0, 0, 3, "Battleship", true);
        board.placeShip(validPlacement);

        // Shooting at a ship (hit)
        boolean hit = board.shoot(0, 0);
        assertTrue(hit, "Should hit the ship at (0, 0)");
        char[][] grid = board.getGrid();
        assertEquals('X', grid[0][0]);

        // Shooting at an empty space (miss)
        boolean miss = board.shoot(3, 3);
        assertFalse(miss, "Should miss the shot at (3, 3)");
        assertEquals('O', grid[3][3]);

        // Shooting at a previously shot location (should not affect the grid)
        miss = board.shoot(3, 3);
        assertFalse(miss, "Should return false for shooting at an already shot location");

        // Shooting out of bounds
        assertFalse(board.shoot(10, 10), "Should return false for out-of-bounds shot");
    }

    // Test for checking if all ships are sunk
    @Test
    public void testAllShipsSunk() {
        ShipPlacement validPlacement = new ShipPlacement(0, 0, 3, "Battleship", true);
        board.placeShip(validPlacement);

        // Before sinking, all ships should not be sunk
        assertFalse(board.allShipsSunk(), "All ships should not be sunk yet");

        // Hit all parts of the ship
        board.shoot(0, 0);  // Hit
        board.shoot(1, 0);  // Hit
        board.shoot(2, 0);  // Hit

        // After sinking the ship, all ships should be sunk
        assertTrue(board.allShipsSunk(), "All ships should be sunk after hitting all parts");

        // Add another ship and test
        validPlacement = new ShipPlacement(4, 4, 3, "Cruiser", true);
        board.placeShip(validPlacement);
        assertFalse(board.allShipsSunk(), "All ships should not be sunk before sinking this one");

        // Hit all parts of the second ship
        board.shoot(4, 4);  // Hit
        board.shoot(5, 4);  // Hit
        board.shoot(6, 4);  // Hit

        // Now all ships should be sunk
        assertTrue(board.allShipsSunk(), "All ships should be sunk after hitting all parts");
    }
}
