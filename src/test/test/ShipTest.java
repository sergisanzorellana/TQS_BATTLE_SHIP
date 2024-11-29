package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Ship;

public class ShipTest {
    @Test
    public void testShipAttributes() {
        Ship ship = new Ship(3, "Destroyer");
        assertEquals(3, ship.getSize());
        assertEquals("Destroyer", ship.getName());
        assertFalse(ship.isSunk());
    }
    
    @Test
    public void testHit() {
        Ship ship = new Ship(3, "Destroyer");
        int[][] occupiedSquares = {{0, 0}, {0, 1}, {0, 2}};
        ship.setOccupiedSquares(occupiedSquares);

        assertTrue(ship.hit(0, 0));
        assertTrue(ship.hit(0, 1));
        assertTrue(ship.hit(0, 2));
        assertFalse(ship.hit(1, 1)); // Miss
    }

    @Test
    public void testSunk() {
        Ship ship = new Ship(3, "Destroyer");
        int[][] occupiedSquares = {{0, 0}, {0, 1}, {0, 2}};
        ship.setOccupiedSquares(occupiedSquares);

        ship.hit(0, 0);
        ship.hit(0, 1);
        ship.hit(0, 2);

        assertTrue(ship.isSunk());
    }
}


