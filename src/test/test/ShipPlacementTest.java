package test;

import model.ShipPlacement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShipPlacementTest {

    private ShipPlacement shipPlacement;

    @BeforeEach
    public void setUp() {
        shipPlacement = new ShipPlacement(0, 0, 3, "Battleship", true);
    }

    // Test for ShipPlacement Constructor
    @Test
    public void testConstructor() {
        // Valid inputs
        ShipPlacement validPlacement = new ShipPlacement(3, 4, 3, "Cruiser", true);
        assertEquals(3, validPlacement.getX());
        assertEquals(4, validPlacement.getY());
        assertEquals(3, validPlacement.getSize());
        assertEquals("Cruiser", validPlacement.getName());
        assertTrue(validPlacement.isHorizontal());

        // Invalid x-coordinate (out of bounds)
        assertThrows(AssertionError.class, () -> new ShipPlacement(-1, 4, 3, "Submarine", true));
        assertThrows(AssertionError.class, () -> new ShipPlacement(10, 4, 3, "Submarine", true));

        // Invalid y-coordinate (out of bounds)
        assertThrows(AssertionError.class, () -> new ShipPlacement(3, -1, 3, "Submarine", true));
        assertThrows(AssertionError.class, () -> new ShipPlacement(3, 10, 3, "Submarine", true));

        // Invalid ship size (<= 0)
        assertThrows(AssertionError.class, () -> new ShipPlacement(3, 4, 0, "Submarine", true));
        assertThrows(AssertionError.class, () -> new ShipPlacement(3, 4, -1, "Submarine", true));

        // Invalid name (null or empty)
        assertThrows(AssertionError.class, () -> new ShipPlacement(3, 4, 3, "", true));
        assertThrows(AssertionError.class, () -> new ShipPlacement(3, 4, 3, null, true));
      
    }

    // Tests for getX, getY, getSize, getName, isHorizontal
    @Test
    public void testGetters() {
        // Verify that the values passed into the constructor are correctly returned
        assertEquals(0, shipPlacement.getX());
        assertEquals(0, shipPlacement.getY());
        assertEquals(3, shipPlacement.getSize());
        assertEquals("Battleship", shipPlacement.getName());
        assertTrue(shipPlacement.isHorizontal());
    }

    // Test for boundary cases (x and y must be between 0 and 9)
    @Test
    public void testBoundaryConditions() {
        // Valid boundary cases
        ShipPlacement boundaryPlacement1 = new ShipPlacement(0, 0, 3, "Battleship", true);
        ShipPlacement boundaryPlacement2 = new ShipPlacement(9, 9, 4, "Carrier", false);
        assertNotNull(boundaryPlacement1);
        assertNotNull(boundaryPlacement2);

        // Invalid boundary cases
        assertThrows(AssertionError.class, () -> new ShipPlacement(10, 9, 3, "Destroyer", true));
        assertThrows(AssertionError.class, () -> new ShipPlacement(0, -1, 3, "Submarine", false));
        assertThrows(AssertionError.class, () -> new ShipPlacement(9, 10, 3, "Battleship", true));
    }
}
