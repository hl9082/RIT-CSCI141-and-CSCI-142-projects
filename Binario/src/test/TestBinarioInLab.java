package src.test;

import src.backtracking.Configuration;
import src.binario.BinarioConfig;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A Junit 5 unit test for the In-Lab activity.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBinarioInLab {
    /** Test loading a puzzle file and fully storing all the data. */
    @Test
    @Order(1)
    public void testLoad() {
        try {
            // load binario-5.txt puzzle
            BinarioConfig config = new BinarioConfig("data/binario-5.txt");

            // check dimensions
            assertEquals(5, config.getDim());

            // check cursor - initially at (0, -1)
            assertEquals(0, config.getCursorRow());
            assertEquals(-1, config.getCursorCol());

            // check toString
            final String expected =
                    ". . . . 1" + System.lineSeparator() +
                            ". 0 . . ." + System.lineSeparator() +
                            ". . 1 . ." + System.lineSeparator() +
                            ". . . . ." + System.lineSeparator() +
                            "1 . . . 0" + System.lineSeparator();

            assertEquals(expected, config.toString());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /** Test successors for binario-1.txt */
    @Test
    @Order(2)
    public void testSuccessors1() {
        try {
            BinarioConfig config = new BinarioConfig("data/binario-1.txt");

            // GENERATE ALL SUCCESSORS
            List<Configuration> successors = config.getSuccessors();

            // VERIFY successors in (0,0)
            assertEquals(2, successors.size());

            // VERIFY 0 at (0,0)
            BinarioConfig bc = (BinarioConfig) successors.get(0);
            assertEquals(0, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));

            // VERIFY 1 at (0,0)
            bc = (BinarioConfig) successors.get(1);
            assertEquals(0, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /** Test successors for binario-2.txt */
    @Test
    @Order(2)
    public void testSuccessors2() {
        try {
            // this tests that the first 3 cells in this 2x2 board are populated correctly
            BinarioConfig config = new BinarioConfig("data/binario-2.txt");

            // GENERATE ALL SUCCESSORS
            List<Configuration> successors = config.getSuccessors();
            List<Configuration> zeroSuccessors = successors.get(0).getSuccessors();
            List<Configuration> oneSuccessors = successors.get(1).getSuccessors();
            List<Configuration> zero_zeroSuccessors = zeroSuccessors.get(0).getSuccessors();
            List<Configuration> zero_oneSuccessors = zeroSuccessors.get(1).getSuccessors();
            List<Configuration> one_zeroSuccessors = oneSuccessors.get(0).getSuccessors();
            List<Configuration> one_oneSuccessors = oneSuccessors.get(1).getSuccessors();

            // VERIFY first cell successors
            assertEquals(2, successors.size());

            BinarioConfig bc = (BinarioConfig) successors.get(0);
            assertEquals(0, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('.', bc.getVal(0, 1));
            assertEquals('.', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) successors.get(1);
            assertEquals(0, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('.', bc.getVal(0, 1));
            assertEquals('.', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            // VERIFY second cell successors
            bc = (BinarioConfig) zeroSuccessors.get(0);
            assertEquals(0, bc.getCursorRow());
            assertEquals(1, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('0', bc.getVal(0, 1));
            assertEquals('.', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) zeroSuccessors.get(1);
            assertEquals(0, bc.getCursorRow());
            assertEquals(1, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('1', bc.getVal(0, 1));
            assertEquals('.', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) oneSuccessors.get(0);
            assertEquals(0, bc.getCursorRow());
            assertEquals(1, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('0', bc.getVal(0, 1));
            assertEquals('.', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) oneSuccessors.get(1);
            assertEquals(0, bc.getCursorRow());
            assertEquals(1, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('1', bc.getVal(0, 1));
            assertEquals('.', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            // VERIFY third cell successors
            bc = (BinarioConfig) zero_zeroSuccessors.get(0);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('0', bc.getVal(0, 1));
            assertEquals('0', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) zero_zeroSuccessors.get(1);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('0', bc.getVal(0, 1));
            assertEquals('1', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) zero_oneSuccessors.get(0);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('1', bc.getVal(0, 1));
            assertEquals('0', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) zero_oneSuccessors.get(1);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('0', bc.getVal(0, 0));
            assertEquals('1', bc.getVal(0, 1));
            assertEquals('1', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) one_zeroSuccessors.get(0);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('0', bc.getVal(0, 1));
            assertEquals('0', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) one_zeroSuccessors.get(1);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('0', bc.getVal(0, 1));
            assertEquals('1', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) one_oneSuccessors.get(0);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('1', bc.getVal(0, 1));
            assertEquals('0', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));

            bc = (BinarioConfig) one_oneSuccessors.get(1);
            assertEquals(1, bc.getCursorRow());
            assertEquals(0, bc.getCursorCol());
            assertEquals('1', bc.getVal(0, 0));
            assertEquals('1', bc.getVal(0, 1));
            assertEquals('1', bc.getVal(1, 0));
            assertEquals('.', bc.getVal(1, 1));
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
