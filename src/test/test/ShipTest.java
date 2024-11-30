package test;

import model.Ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShipTest {
    private Ship ship;

    @BeforeEach
    public void setUp() {
        ship = new Ship(3, "Battleship");
    }

    // Tests for Ship Constructor
    @Test
    public void testConstructor() {
        // Valid constructor
        Ship validShip = new Ship(2, "Destroyer");
        assertEquals(2, validShip.getSize());
        assertEquals("Destroyer", validShip.getName());
        assertFalse(validShip.isSunk());

        // Invalid size (0 or negative)
        assertThrows(AssertionError.class, () -> new Ship(0, "InvalidShip"));
        assertThrows(AssertionError.class, () -> new Ship(-1, "InvalidShip"));

        // Invalid name (empty or null)
        assertThrows(AssertionError.class, () -> new Ship(3, ""));
        assertThrows(AssertionError.class, () -> new Ship(3, null));
    }

    // Tests for setOccupiedSquares
    @Test
    public void testSetOccupiedSquares() {
        // Valid occupied squares
        int[][] validSquares = {{1, 1}, {1, 2}, {1, 3}};
        ship.setOccupiedSquares(validSquares);
        assertArrayEquals(validSquares, ship.getOccupiedSquares());

        // Invalid length (should match ship size)
        int[][] invalidLengthSquares = {{1, 1}, {1, 2}};
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(invalidLengthSquares));

        // Invalid square format (each square should have two coordinates)
        int[][] invalidFormatSquares = {{1, 1}, {1, 2, 3}, {1, 3}};
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(invalidFormatSquares));

        // Null occupied squares
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(null));

        // Null individual square in the array
        int[][] nullSquare = {{1, 1}, null, {1, 3}};
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(nullSquare));

        // Invalid coordinate length (each coordinate should have two integers)
        int[][] invalidCoordinateLength = {{1, 1}, {1}, {1, 3}};
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(invalidCoordinateLength));
    }

    // Tests for hit method
    @Test
    public void testHit() {
        int[][] squares = {{1, 1}, {1, 2}, {1, 3}};
        ship.setOccupiedSquares(squares);

        // Ensure occupiedSquares is not null
        assertThrows(AssertionError.class, () -> {
            Ship anotherShip = new Ship(3, "Cruiser");
            anotherShip.hit(1, 1);
        });

        // Valid hit
        assertTrue(ship.hit(1, 1));
        assertEquals(-1, ship.getOccupiedSquares()[0][0]);
        assertEquals(-1, ship.getOccupiedSquares()[0][1]);

        // Misses
        assertFalse(ship.hit(2, 2));  // Miss
        assertFalse(ship.hit(1, 0));  // Miss
        assertFalse(ship.hit(0, 1));  // Miss

        // Check if ship sinks after all parts are hit
        ship.hit(1, 2);
        ship.hit(1, 3);
        assertTrue(ship.isSunk()); // Ship is sunk

        // Check ship is not sunk if not all parts are hit for another ship
        Ship anotherShip = new Ship(3, "Cruiser");
        anotherShip.setOccupiedSquares(new int[][] {{2, 1}, {2, 2}, {2, 3}});
        anotherShip.hit(2, 1);
        anotherShip.hit(2, 2);
        assertFalse(anotherShip.isSunk()); // Ship is not sunk yet

        // Check ship with one square (edge case)
        Ship oneSquareShip = new Ship(1, "TinyShip");
        oneSquareShip.setOccupiedSquares(new int[][]{{0, 0}});
        assertTrue(oneSquareShip.hit(0, 0));  // Should immediately sink
        assertTrue(oneSquareShip.isSunk());

        // Check large ship with 100 squares (boundary case)
        Ship largeShip = new Ship(100, "LargeShip");
        int[][] largeShipSquares = new int[100][2];
        for (int i = 0; i < 100; i++) {
            largeShipSquares[i] = new int[]{i, i};
        }
        largeShip.setOccupiedSquares(largeShipSquares);
        for (int i = 0; i < 100; i++) {
            assertTrue(largeShip.hit(i, i));  // All hits should sink the ship
        }
        assertTrue(largeShip.isSunk());
    }
}
