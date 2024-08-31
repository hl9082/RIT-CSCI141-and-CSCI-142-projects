package puzzles.common.solver;
import puzzles.clock.ClockConfig;
import puzzles.dice.DiceConfig;

import java.util.*;

/**
 * This class is to solve for the dice and clock.
 * @author RIT CS.
 * @author Huy Le.
 */
public class Solver {
    // TODO


    /**
     * This function is to solve for the current configuration - the dice or the clock.
     * @param config the current configuration.
     */
    public void solve(Configuration config) {

            Map<Configuration, Configuration> map = new HashMap<>();

            List<Configuration> queue = new LinkedList<>();
            int totalconfig_count = 1;
            int uniqueconfig_count = 1;
            queue.add(config);
            map.put(config, null);
            List<Configuration> path = new LinkedList<>();
            while (!queue.isEmpty()) {
                var cur = queue.remove(0);
                if (cur.isSolution()) {
                    var tmp = cur;
                     while(tmp!=null) {
                            path.add(0,tmp);
                            tmp = map.get(tmp);

                    }
                    System.out.println("Total configs: " + totalconfig_count);
                    System.out.println("Unique configs: " + uniqueconfig_count);
                    for(int i=0;i<path.size();i++){
                        System.out.println("Step "+i+": "+path.get(i));
                    }
                    return;
                }
                var neighbors = cur.getNeighbors();
                for (var neighbor : neighbors) {
                    totalconfig_count++;
                    if (!map.containsKey(neighbor)) {
                        queue.add(neighbor);
                        map.put(neighbor, cur);
                        uniqueconfig_count++;
                    }
                }
            }



    }
}
