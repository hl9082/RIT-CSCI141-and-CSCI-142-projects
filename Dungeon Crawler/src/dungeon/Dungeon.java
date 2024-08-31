package src.dungeon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import src.dungeon.DungeonException;
import src.dungeon.Node;

/**
 * Student implementation of the dungeon.
 *
 * @author Huy Le
 */
public class Dungeon implements src.dungeon.IDungeon {
    /**The number of rows in the dungeon.*/
    private int rows;
    
    /**The number of cols in the dungeon.*/
    private int cols;
    
    /**The collection of treasures in the dungeon.*/
    private Collection<src.dungeon.Treasure>treasures;
    
    /**The home location of the dungeon.*/
    private src.dungeon.Coordinates home;
    
    /**The hash table that maps the coordinates of the cells to the
       the node that stores the treasure and location.*/
    private Map<src.dungeon.Coordinates,Node>dungeon;

    /**
     * Create a new dungeon from a file.
     *
     * @param filename the name of the dungeon file
     * @throws IOException      if a problem is encountered reading the file
     * @throws DungeonException if there is an error in the dungeon file
     */
    public Dungeon(String filename) throws IOException, DungeonException {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            // first line is: rows cols
            String[] fields = in.readLine().split("\\s+");
            // e.g. to read number of rows: Integer.parseInt(fields[0]);
            // e.g. to read number of columns: Integer.parseInt(fields[1]);
            this.rows=Integer.parseInt(fields[0]);
            this.cols=Integer.parseInt(fields[1]);
            if(rows<1){
            throw new DungeonException("rows must be greater than zero: "+rows);
            }
            if(cols<1){
            throw new DungeonException("columns must be greater than zero: "+cols);
            }
            Queue<String> tokens = new LinkedList<>();
            this.treasures=new ArrayList<>();
            this.dungeon=new TreeMap<>();
            for(int i=0;i<this.rows;i++){
            tokens.addAll(List.of(in.readLine().split("\\s+")));
            for(int j=0;j<this.cols;j++){
            var coord=new src.dungeon.Coordinates(i,j);
            dungeon.put(coord,new Node(coord));
            }
            }
            for(src.dungeon.Coordinates coordinate:this.dungeon.keySet()){
            Map<String, src.dungeon.Coordinates> directions=new HashMap<>();
            String s=tokens.poll();  
    if(s.contains(NORTH)){
            directions.put("a north",new src.dungeon.Coordinates(coordinate.row()-1,coordinate.col()));
            }
            if(s.contains(SOUTH)){
            directions.put("a south",new src.dungeon.Coordinates(coordinate.row()+1,coordinate.col()));
            }
            if(s.contains(EAST)){
            directions.put("a east",new src.dungeon.Coordinates(coordinate.row(),
            coordinate.col()+1));
            }
            if(s.contains(WEST)){
            directions.put("a west",new src.dungeon.Coordinates(coordinate.row(),
            coordinate.col()-1));
            }
        
            for(var entry : directions.entrySet()){
                Coordinates neighbor=entry.getValue();
            if(!hasCoordinates(neighbor)){
            throw new DungeonException("cannot have "+entry.getKey()
            +" neighbor: "+coordinate.toString());
            }
            this.dungeon.get(coordinate).addNeighbor(dungeon.get(neighbor));
            }
            
            }
            
            var homelist=in.readLine().split("\\s+");
            if(!homelist[0].equals(HOME)){
            throw new DungeonException("home position not specified!");
            }
            this.home=new Coordinates(homelist[1]);
            int tmp=Integer.parseInt(in.readLine());
            for(var i=0;i<tmp;i++
            ){
            var treasurelist=in.readLine().split("\\s+");
            this.treasures.add(new Treasure(treasurelist[0],
            new Coordinates(treasurelist[1])));
            }
        } // any exceptions generated will get thrown to the main program        
    }
    
    /**
     * Return the number of rows in the dungeon.
     * @return rows.
     */
    @Override
    public int getRows() {
        // TODO
        return rows;
    }

    /**
     * Return the number of columns in the dungeon.
     * @return cols.
     */
    @Override
    public int getCols() {
        // TODO
        return cols;
    }

    /**
     * Check whether the location coordinates are valid.
     * @param location - the location
     * @return whether the cell is valid or not.
     */
    @Override
    public boolean hasCoordinates(Coordinates location) {
        return location.row()<rows && location.row()>=0
        && location.col()>=0 && location.col()<cols;
    }

    /**
     * Check whether the source and the destination are neighbors.
     * @param src-the source cell.
     * @param dest - the destination cell.
     * @return whether they are neighbors.
     */
    @Override
    public boolean isNeighbor(Coordinates src, Coordinates dest) {
        return dungeon.get(src).getNeighbors().contains(dungeon.get(dest));
    }

    /**
     * Get the string value of the cell at a location in the dungeon. 
     * It returns one of three things. 
     * The HOME cell for the home position, the treasure name 
     * if the treasure is not collected yet, or 
     * CELL if the treasure was collected.
     * @param location = the location.
     * @return the string at that location.
     */
    @Override
    public String getCell(Coordinates location) {
        String res=CELL;
        if(location.row()==home.row()&&location.col()==home.col()){
        res=HOME;
        }
        for(var tr : treasures){
        if(location.row()==tr.getLocation().row()&&location.col()==tr.getLocation().col()
        &&!tr.isCollected()){
        res=tr.getName();
        break;
        }
        }
        return res;
    }

    /**
     * Get the home position (must exist).
     * @return home position.
     */
    @Override
    public Coordinates getHome() {
        // TODO
        return home;
    }

    /**
     * Is there a treasure at a specific location?
     * @param location - the location.
     * @return whether there is a treasure at this location or not.
     */
    @Override
    public boolean hasTreasure(Coordinates location) {
        boolean res=false;
        for(var treasure:treasures){
        if(location.equals(treasure.getLocation())){
        res=true;break;
        }
        }
        return res;
    }

    /**
     * Get the colletion of all treasures in the dungeon.
     * @return the treasure collection.
     */
    @Override
    public Collection<Treasure> getTreasures() {
        return this.treasures;
    }

    /**
     * Get the neighbors of a cell in the dungeon.
     * @param location the cell.
     * @return the cell's neighbors.
     */
    @Override
    public Collection<Coordinates> getNeighbors(Coordinates location) {
        Collection<Coordinates>neighbors=new ArrayList<>();
        this.dungeon.get(location).getNeighbors().
        forEach(ele->{
        neighbors.
        add(ele.getLocation());});
        return neighbors;
    }

    @Override
    public String toString() {
        return display();
    }
}
