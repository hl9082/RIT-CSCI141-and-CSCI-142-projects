package puzzles.clock;

import puzzles.common.solver.Configuration;

import java.util.Collection;
import java.util.*;

public class ClockConfig implements Configuration {

    private static int hours;


    private static int end;

    private int cur;


    public ClockConfig(int hours, int start, int endhr){
        this.hours=hours;
        end=endhr;
        this.cur=start;
    }
    public ClockConfig(int start){
        this.cur=start;
    }
    @Override
    public boolean isSolution() {
        return this.cur==this.end;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Set<Configuration>neighbors=new HashSet<>();
        if(this.cur==1){
            neighbors.add(new ClockConfig(hours));
        }
        else{
            neighbors.add(new ClockConfig(this.cur-1));
        }
        if(this.cur==hours){
            neighbors.add(new ClockConfig(0));
        }
        else{
            neighbors.add(new ClockConfig(this.cur+1));
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof ClockConfig){
            var otherclock= (ClockConfig) other;
            return otherclock.cur==this.cur;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.cur;
    }


    @Override
    public String toString() {
        return String.valueOf(this.cur);
    }
}
