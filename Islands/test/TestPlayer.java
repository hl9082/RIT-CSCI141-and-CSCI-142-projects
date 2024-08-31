package test;

import islands.Cell;
import islands.Direction;
import islands.Player;
import islands.PlayerRole;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test framework for the islands.Player class.
 * <p>
 * Notice this test class is different from the other test classes in this package. In this class,
 * there is a dependency between the methods. The test methods have been set up to run in a specific order. Running
 * just one single test method may fail because it requires another test method to be executed first.
 *
 * @author RIT CS
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPlayer {

    private Player redPlayer;

    @BeforeAll
    public void setUp() {
        // in the game, the red player must check the board vertically to verify whether the game is over
        // in these test methods, the directions have been swap between the players
        // to ensure students do not have hard-coded the player - direction correspondence
        redPlayer = new Player(PlayerRole.RED, Direction.HORIZONTAL);
    }

    @Test
    @Order(1)
    public void testInit() {
        assertEquals(0, redPlayer.getNumIslands());
        assertEquals(PlayerRole.RED, redPlayer.getRole());
        assertEquals(Direction.HORIZONTAL, redPlayer.getDirection());

        // verifying there are not class fields (static fields)
        Player bluePlayer = new Player(PlayerRole.BLUE, Direction.VERTICAL);
        assertEquals(0, bluePlayer.getNumIslands());
        assertEquals(PlayerRole.BLUE, bluePlayer.getRole());
        assertEquals(PlayerRole.RED, redPlayer.getRole());
    }

    @Test
    @Order(2)
    public void testClaim() {
        // claiming first cell, this will create an island of size 1
        redPlayer.claim(new Cell(0, 1));
        assertEquals(1, redPlayer.getNumIslands());
        assertEquals(1, redPlayer.getIslands().size());

        // claiming second cell with no adjacent neighbors
        // this will create a new island
        redPlayer.claim(new Cell(2, 1));
        assertEquals(2, redPlayer.getNumIslands());
        assertEquals(2, redPlayer.getIslands().size());

        // claiming third cell in the diagonal of both first and second cell
        // diagonal cells are not considered neighbors, this will create a third island
        redPlayer.claim(new Cell(1, 2));
        assertEquals(3, redPlayer.getNumIslands());
        assertEquals(3, redPlayer.getIslands().size());

        // claiming fourth cell that is neighbor of all the previous claimed cells
        // this call should merge all the cells into one big island of size 4
        redPlayer.claim(new Cell(1, 1));
        assertEquals(1, redPlayer.getNumIslands());
        assertEquals(1, redPlayer.getIslands().size()); // number of islands
        assertEquals(4, redPlayer.getIslands().get(0).size()); // size of the island

        // this fifth cell is adjacent to the existing island
        // number of islands remain the same, the size of the island increments +1
        redPlayer.claim(new Cell(1, 0));
        assertEquals(1, redPlayer.getNumIslands());
        assertEquals(1, redPlayer.getIslands().size()); // number of islands
        assertEquals(5, redPlayer.getIslands().get(0).size()); // size of the island
    }

    @Test
    @Order(3)
    public void testTouchesBoardBoundaries() {
        // the test methods are executed in order
        // at this point, red player has only one 1 island of size 5  (cross shape)
        assertTrue(redPlayer.touchesBoardBoundaries(3, 3));
        // in a 4x3, the island doesn't touch the right boundary
        assertFalse(redPlayer.touchesBoardBoundaries(3, 4));

        Player bluePlayer = new Player(PlayerRole.BLUE, Direction.VERTICAL);
        assertFalse(bluePlayer.touchesBoardBoundaries(1, 1));
        bluePlayer.claim(new Cell(0, 0));
        assertTrue(bluePlayer.touchesBoardBoundaries(1, 1));
        assertFalse(bluePlayer.touchesBoardBoundaries(2, 1));
    }

    @Test
    @Order(4)
    public void testEquals() {
        Player redPlayer2 = new Player(PlayerRole.RED, Direction.VERTICAL);
        assertEquals(redPlayer, redPlayer2);

        redPlayer2.claim(new Cell(0, 0));
        assertEquals(redPlayer2, redPlayer2);

        Player bluePlayer = new Player(PlayerRole.BLUE, Direction.HORIZONTAL);
        assertNotEquals(redPlayer, bluePlayer);
        assertNotEquals("RED", redPlayer);
    }

    @Test
    @Order(5)
    public void testToString() {
        assertEquals("Player: RED, islands: 1", redPlayer.toString());

        redPlayer = new Player(PlayerRole.RED, Direction.VERTICAL);

        assertEquals("Player: RED, islands: 0", redPlayer.toString());

        Player bluePlayer = new Player(PlayerRole.BLUE, Direction.VERTICAL);

        assertEquals("Player: BLUE, islands: 0", bluePlayer.toString());
        assertEquals("Player: RED, islands: 0", redPlayer.toString());
    }
}
