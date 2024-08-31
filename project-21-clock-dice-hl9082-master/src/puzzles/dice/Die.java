package puzzles.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class is to represent the single Die.
 * @author Huy Le
 */
public class Die {
    private Map<Character, Set<Character>> neighbors;

    private char curface;

    public Die(String filename, char val) throws FileNotFoundException {
        this.curface=val;
        BufferedReader in=new BufferedReader(new FileReader(filename));
        Stream<String> lines=in.lines();
        String [] data=lines.toArray(String[]::new);
        this.neighbors=new HashMap<>();
        for(int j=2;j<data.length;j++){
            String curline=data[j];
            String [] str_arr=curline.strip().split("\\s+");
            Set<Character>set=new HashSet<>();
            for(int i=1;i<str_arr.length;i++){
                set.add(str_arr[i].charAt(0));
            }
            this.neighbors.put(str_arr[0].charAt(0),set);
        }
    }
    public Die(Die otherdie,char val){
         this.neighbors=otherdie.neighbors;
         this.curface=val;
    }
    public char getFace(){return this.curface;}

    public Set<Character>getNeighbors(){
        return this.neighbors.get(this.curface);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Die){
            Die otherdie=(Die) other;
            return this.curface==otherdie.curface;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.curface;
    }
}
