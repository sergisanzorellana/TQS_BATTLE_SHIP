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
    }
}


