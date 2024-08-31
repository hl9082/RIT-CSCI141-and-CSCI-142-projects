package puzzles.dice;

import puzzles.common.solver.Configuration;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the configuration of the dice
 * @author RIT CS.
 * @author Huy Le.
 */
public class DiceConfig implements Configuration {


/**The list of dices*/
    private ArrayList<Die>dices;

    /**The list of dices we desire to get*/
    private static ArrayList<Die> endlist;

    /**
     * The constructor of the diceconfig that takes the arraylist of dices
     * and the desired arraylist of dices as parameters.
     * @param dieArrayList - the current arraylist
     * @param end - the end arraylist.
     */
    public DiceConfig(ArrayList<Die>dieArrayList, ArrayList<Die> end){
        this.dices=dieArrayList;
        endlist=end;
    }

    /**
     * The constructor of the diceconfig that takes the arraylist as parameter.
     * @param dieArrayList
     */
    public DiceConfig(ArrayList<Die>dieArrayList){
        this.dices=dieArrayList;
    }

    /**
     * Check if the arraylist of dices equals the desired list of dices.
     * @return whether the current list is the solution.
     */

    @Override
    public boolean isSolution() {
        return this.dices.equals(endlist);
    }

    /**
     * To return the set of neighboring configurations.
     * @return the set of neighboring configurations.
     */
    @Override
    public Collection<Configuration> getNeighbors() {
       Set<Configuration>res=new HashSet<>();
       for(int i=0;i<dices.size();i++){
            Die cur=dices.get(i);
            //we're gonna make new configs
           //replace 1 die with 1 of its neighbors
           int curI = i;
           cur.getNeighbors().forEach(ele->{
               ArrayList<Die>tmp= new ArrayList<Die>(this.dices);
               tmp.set(curI,new Die(cur,ele));
               res.add(new DiceConfig(tmp));
            });
       }
       return res;
    }

    /**
     *Check if the current Diceconfig equals other diceconfigs.
     * @param other the other object to be checked.
     * @return whether 2 diceconfigs are the same.
     */
    @Override
    public boolean equals(Object other) {
        if(other instanceof DiceConfig){
            DiceConfig otherdice=(DiceConfig) other;
            return otherdice.dices.equals(this.dices);
        }
        return false;
    }

    /**
     * Return the hashcode of the diceconfig.
     * @return the hashCode.
     */

    @Override
    public int hashCode() {
        return this.dices.hashCode();
    }

    /**
     * Return the string representation of the diceconfig.
     * @return the string representation of the diceconfig.
     */
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Die die:this.dices){
            sb.append(die.getFace());
        }
        return sb.toString();
    }
}
