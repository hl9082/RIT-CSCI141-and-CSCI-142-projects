package test;

import islands.Cell;
import islands.Coordinates;
import islands.PlayerRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test framework for the islands.Cell class.
 *
 * @author RIT CS
 */
public class TestCell {

    @Test
    public void testInit() {
        Cell c1 = new Cell(3, 4);
        assertFalse(c1.hasOwner());
        assertEquals(4, c1.getCol());
        assertEquals(3, c1.getRow());
        assertEquals(new Coordinates(3, 4), c1.getCoordinates());
        assertEquals(PlayerRole.NONE, c1.getOwner());

        // making sure there are no class member (static fields) in Player
        Cell c2 = new Cell(1, 5);
        assertFalse(c2.hasOwner());
        assertEquals(new Coordinates(3, 4), c1.getCoordinates());
        assertEquals(new Coordinates(1, 5), c2.getCoordinates());
    }

    @Test
    public void testClaim() {
        Cell c1 = new Cell(3, 4);
        assertFalse(c1.hasOwner());
        assertEquals(PlayerRole.NONE, c1.getOwner());

        c1.claim(PlayerRole.RED);
        assertTrue(c1.hasOwner());
        assertEquals(PlayerRole.RED, c1.getOwner());

        // this action will not be allowed in the real game
        // a player can only claim a cell with no owner
        // no need to check for no owner in this method
        c1.claim(PlayerRole.BLUE);
        assertTrue(c1.hasOwner());
        assertEquals(PlayerRole.BLUE, c1.getOwner());
    }

    @Test
    public void testEquals() {
        Cell c1 = new Cell(1, 2);
        Cell c2 = new Cell(2, 1);
        Cell c3 = new Cell(4, -1);
        Cell c4 = new Cell(1, 2);
        c4.claim(PlayerRole.RED);

        assertEquals(c1, c1);
        assertNotEquals(c1, c2);
        assertEquals(c1, c4);
        assertNotEquals(c2, c3);
        assertNotEquals( " ", c1);
        assertNotEquals(" ", c2);
    }

    @Test
    public void testToString() {
        Cell c1 = new Cell(1, 2);

        assertEquals("NONE: (1,2)", c1.toString());

        c1.claim(PlayerRole.BLUE);
        assertEquals("BLUE: (1,2)", c1.toString());

        c1.claim(PlayerRole.RED);
        assertEquals("RED: (1,2)", c1.toString());

        Cell c2 = new Cell(0, 0);
        assertEquals("RED: (1,2)", c1.toString());
        assertEquals("NONE: (0,0)", c2.toString());
    }
}
