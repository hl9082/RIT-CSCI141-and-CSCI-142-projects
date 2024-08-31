package puzzles.dice;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * The main class to solve the dice.
 * @author RIT CS.
 * @author Huy Le.
 */
public class Dice {

    /**
     * This method is to print the map representation of the dice file.
     * @param filename the filename
     * @throws FileNotFoundException the exception when the file isn't found.
     */
    public static void printformat(String filename) throws FileNotFoundException {
        BufferedReader in=new BufferedReader(new FileReader(filename));
        Stream<String> lines=in.lines();
        String [] data=lines.toArray(String[]::new);
        Map<Character,Set<Character>>neighbors=new LinkedHashMap<>();
        for(int j=2;j<data.length;j++){
            String curline=data[j];
            String [] str_arr=curline.strip().split("\\s+");
            Set<Character>set=new LinkedHashSet<>();
            for(int i=1;i<str_arr.length;i++){
                set.add(str_arr[i].charAt(0));
            }
            neighbors.put(str_arr[0].charAt(0),set);
        }
        for(var entry:neighbors.entrySet()){
            System.out.println("\t"+entry.getKey()+"="+entry.getValue());
        }
    }

    /**
     * The main method for the diceconfig.
     * @param args an array of arguments.
     * @throws FileNotFoundException the exception at which the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 3) {
            System.out.println("Usage: java Dice start end die1 die2...");
        } else {
            // TODO
            String start=args[0];
            String end=args[1];
            String [] dices=new String[args.length-2];
            for(int i=2;i<args.length;i++){
                dices[i-2]=args[i];
            }
            ArrayList<Die>dicelist=new ArrayList<>();
            ArrayList<Die>endlist=new ArrayList<>();
            for(int i=0;i<dices.length;i++){
                System.out.println("Die #"+i+": File: "+"die-"
                +dices[i]+".txt,"+" Faces: "+dices[i]);
                printformat("die-"+dices[i]+".txt");
                dicelist.add(new Die("die-"+dices[i]+".txt",start.charAt(i)));
                endlist.add(new Die("die-"+dices[i]+".txt",end.charAt(i)));
            }
            System.out.println("Start: "+start+", End: "+end);
            Solver solver=new Solver();
            solver.solve(new DiceConfig(dicelist,endlist));
        }
    }
}