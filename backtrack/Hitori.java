 

import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * CSCI-142 Computer Science 2 Recitation Pair Exercise
 * 13-DFS_Backtracking
 * Hitori
 *
 * The main program for the Hitori puzzle solver that uses backtracking.
 *
 * $ java Hitori input4.txt
 * Initial config:
 * 1 4 3 2 1
 * 3 1 2 3 2
 * 3 2 2 3 1
 * 4 2 1 5 3
 * 1 3 3 1 3
 * Elapsed time: 0.036 seconds.
 * Solution:
 * 1 4 3 2 X
 * X 1 X 3 2
 * 3 X 2 X 1
 * 4 2 1 5 3
 * X 3 X 1 X
 *
 * @author RIT CS
 */
public class Hitori {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Hitori filename");
        } else {
            try {
                // create the initial configuration by reading from file
                HitoriConfig init = new HitoriConfig(args[0]);

                // display the initial config
                System.out.println("Initial config:" + init);

                // create the backtracker
                Backtracker bt = new Backtracker(false);

                // start the clock
                double start = System.currentTimeMillis();

                // attempt to solve the puzzle
                Optional<Configuration> sol = bt.solve(init);

                // compute the elapsed time
                System.out.println("Elapsed time: " +
                        (System.currentTimeMillis() - start) / 1000.0 + " seconds.");

                // indicate whether there was a solution or not
                if (sol.isPresent()) {
                    System.out.println("Solution:" + sol.get());
                } else {
                    System.out.println("No solution!");
                }
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
            }
        }
    }
}
