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

        // Invalid size
        assertThrows(AssertionError.class, () -> new Ship(0, "InvalidShip"));

        // Invalid name
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

        // Invalid length
        int[][] invalidLengthSquares = {{1, 1}, {1, 2}};
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(invalidLengthSquares));

        // Invalid square format
        int[][] invalidFormatSquares = {{1, 1}, {1, 2, 3}, {1, 3}};
        assertThrows(AssertionError.class, () -> ship.setOccupiedSquares(invalidFormatSquares));
    }

    // Tests for hit
    @Test
    public void testHit() {
        int[][] squares = {{1, 1}, {1, 2}, {1, 3}};
        ship.setOccupiedSquares(squares);

        // Valid hit
        assertTrue(ship.hit(1, 1));
        assertEquals(-1, ship.getOccupiedSquares()[0][0]);
        assertEquals(-1, ship.getOccupiedSquares()[0][1]);

        // Miss
        assertFalse(ship.hit(2, 2));

        // Check sunk after all hits
        ship.hit(1, 2);
        ship.hit(1, 3);
        assertTrue(ship.isSunk());

        // Check not sunk if not all parts are hit
        Ship anotherShip = new Ship(3, "Cruiser");
        anotherShip.setOccupiedSquares(squares);
        anotherShip.hit(1, 1);
        anotherShip.hit(1, 2);
        assertFalse(anotherShip.isSunk());
    }
}
