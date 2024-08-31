package test;

import islands.Cell;
import islands.Coordinates;
import islands.Direction;
import islands.Island;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test framework for the islands.Island class.
 *
 * @author RIT CS
 */
public class TestIsland {

    @Test
    public void testInit() {
        Island empty = new Island();
        assertEquals(0, empty.size());

        Island singleton = new Island(new Cell(0, 2));
        assertEquals(1, singleton.size());
    }

    @Test
    public void testTouchesBoardBoundaries() {
        // testing an island of size 1 with a 1x1 board
        Island singleton = new Island(new Cell(0,0));
        assertTrue(singleton.touchesBoardBoundaries(1, 1, Direction.VERTICAL));

        Island island = new Island(new Cell(0, 1));
        assertFalse(island.touchesBoardBoundaries(2, 2, Direction.VERTICAL));
        assertFalse(island.touchesBoardBoundaries(2, 2, Direction.HORIZONTAL));

        // corrupted island with cells that are not adjacent to each other, do not worry about it
        // this shouldn't happen in the real game
        island.addCell(new Cell(1, 0));
        assertTrue(island.touchesBoardBoundaries(2, 2, Direction.VERTICAL));
        assertTrue(island.touchesBoardBoundaries(2, 2, Direction.HORIZONTAL));

        // now the island is correct
        island.addCell(new Cell(1, 1));
        assertTrue(island.touchesBoardBoundaries(2, 2, Direction.VERTICAL));
        assertTrue(island.touchesBoardBoundaries(2, 2, Direction.HORIZONTAL));

        assertFalse(island.touchesBoardBoundaries(3, 2, Direction.VERTICAL));
        assertTrue(island.touchesBoardBoundaries(3, 2, Direction.HORIZONTAL));

        assertTrue(island.touchesBoardBoundaries(2, 3, Direction.VERTICAL));
        assertFalse(island.touchesBoardBoundaries(2, 3, Direction.HORIZONTAL));
    }

    @Test
    public void testHasCell() {
        Island island = new Island(new Cell(0, 1));
        assertFalse(island.hasCell(new Coordinates(2, 3)));
        assertTrue(island.hasCell(new Coordinates(0, 1)));
        assertFalse(island.hasCell(new Coordinates(1, 0)));

        // making sure the collections of cells is not a static field
        Island empty = new Island();
        assertFalse(empty.hasCell(new Coordinates(1, 0)));
        assertFalse(empty.hasCell(new Coordinates(0, 1)));
    }

    @Test
    public void testMerge() {
        Island island1 = new Island();
        Island island2 = new Island(new Cell(3, 4));

        assertEquals(0, island1.size());
        assertEquals(1, island2.size());

        island1.addCell(new Cell(1, 1));
        assertEquals(1, island1.size());

        island1.merge(island2);
        assertTrue(island1.hasCell(new Coordinates(1, 1)));
        assertTrue(island1.hasCell(new Coordinates(3, 4)));
        assertTrue(island2.hasCell(new Coordinates(3, 4)));

        island2.addCell(new Cell(3, 3));
        assertTrue(island2.hasCell(new Coordinates(3, 3)));
        assertFalse(island1.hasCell(new Coordinates(3, 3)));

        island2.merge(island1);
        assertEquals(4, island2.size());
        assertEquals(2, island1.size());
        assertTrue(island2.hasCell(new Coordinates(1, 1)));

    }

    @Test
    public void testEquals() {
        Island island1 = new Island(new Cell(4, 5));
        Island island2 = new Island(new Cell(5, 4));
        Island island3 = new Island();
        island3.addCell(new Cell(4, 5));

        assertEquals(island1, island3);
        assertEquals(island2, island2);
        assertNotEquals(island1, island2);

        island1.merge(island2);
        assertNotEquals(island1, island3);
        assertNotEquals(island1, island2);

        Island empty1 = new Island();
        Island empty2 = new Island();
        assertEquals(empty2, empty1);
    }

    @Test
    public void testToString() {
        Island island = new Island();
        assertEquals("Island{size:0}", island.toString());

        island.addCell(new Cell(0, 4));
        assertEquals("Island{size:1}\n\t(0,4)", island.toString());

        island.addCell(new Cell(2, 3));
        assertEquals("Island{size:2}\n\t(0,4)(2,3)", island.toString());

        Island island2 = new Island(new Cell(1, 1));
        assertEquals("Island{size:1}\n\t(1,1)", island2.toString());
    }
}
