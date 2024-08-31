package puzzles.clock;

import puzzles.common.solver.Solver;

/**
 * The main class for the clock configuration.
 * @author RIT CS.
 * @author Huy Le.
 */
public class Clock {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Clock hours start end");
        } else {
            // TODO
            int hours=Integer.valueOf(args[0]);
            int start=Integer.valueOf(args[1]);
            int end=Integer.valueOf(args[2]);
            ClockConfig clock=new ClockConfig(hours,start,end);
            Solver solve=new Solver();
            System.out.println("Hours: "+hours+", Start: "+start+", End: "+end);
            solve.solve(clock);
        }
    }
}
