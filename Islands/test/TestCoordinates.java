package test;


import islands.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test framework for the islands.Coordinates class.
 *
 * @author RIT CS
 */
public class TestCoordinates {

    @Test
    public void testInit() {
        Coordinates coor1 = new Coordinates(0, 0);
        assertEquals(0, coor1.getRow());
        assertEquals(0, coor1.getCol());

        // making sure column and row are instance fields
        Coordinates coor2 = new Coordinates(3, 5);
        assertEquals(3, coor2.getRow());
        assertEquals(5, coor2.getCol());
    }

    @Test
    public void testEquals() {
        Coordinates coor1 = new Coordinates(1, 2);
        Coordinates coor2 = new Coordinates(2, 1);
        Coordinates coor3 = new Coordinates(4, -1);
        Coordinates coor4 = new Coordinates(1, 2);

        assertEquals(coor1, coor1);
        assertNotEquals(coor1, coor2);
        assertEquals(coor1, coor4);
        assertNotEquals(coor2, coor3);
        assertNotEquals("(1,2)", coor1);
        assertNotEquals("(2,1)", coor2);
    }

    @Test
    public void testGetNeighbors() {
        Coordinates coor1 = new Coordinates(0, 0);
        List<Coordinates> neighbors = coor1.getNeighbors();

        assertEquals(4, neighbors.size());
        assertTrue(neighbors.contains(new Coordinates(-1, 0)));
        assertTrue(neighbors.contains(new Coordinates(1, 0)));
        assertTrue(neighbors.contains(new Coordinates(0, -1)));
        assertTrue(neighbors.contains(new Coordinates(0, 1)));
    }

    @Test
    public void testToString() {
        Coordinates coor1 = new Coordinates(1, 2);
        Coordinates coor2 = new Coordinates(2, 1);
        Coordinates coor3 = new Coordinates(4, -1);
        Coordinates coor4 = new Coordinates(-5, 2);

        assertEquals("(1,2)", coor1.toString());
        assertEquals("(2,1)", coor2.toString());
        assertEquals("(4,-1)", coor3.toString());
        assertEquals("(-5,2)", coor4.toString());

        assertNotEquals("( 1,2 )", coor1.toString());
        assertNotEquals("(2 , 1)", coor2.toString());
        assertNotEquals("(2,-5)", coor4.toString());
        assertNotEquals("(2, -5)", coor4.toString());
    }
}
