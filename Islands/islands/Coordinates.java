//package src.islands;
package islands;
import java.util.ArrayList;

/**
 * A handy class for manipulating 2D locations. The coordinates are integers, 
 * making them suitable for grid-based computations.
 *
 * @author RIT CS
 * @author HUY LE
 */
public class Coordinates
{   /** the column coordinate*/
    private final int col;
    /**the row coordinate*/
    private final int row;
    /**
     * Initialize this object with the actual integer values.
     * @param row - the row value
     * @param col - the column value
     */
    
    public Coordinates(int row,int col){
    this.row=row;this.col=col;
    }
    
    /**
     * What is the row coordinate?
     * @return the row value stored in this Coordinates object.
     */
    
    public int getRow(){
    return this.row;
    }
    /**
     * What is the column coordinate?
     * @return the column value stored in this Coordinates object.
     */
    
    public int getCol(){
    return this.col;
    }
    
    /**
     * Generate the Coordinates objects of the four "compass" neighbors 
     * (north, south, east and west) 
     * of this Coordinates object.
     * This method will always generate four neighbors 
     * even if those Coordinates neighbors are invalid 
     * (out of the bounds of the game's board).
     * @return a list with the 4 neighbors coordinates.
     */
    
    public ArrayList<Coordinates>getNeighbors(){
    ArrayList<Coordinates>res= new ArrayList<>(4);
    res.add(new Coordinates(getRow()-1,getCol()));
    res.add(new Coordinates(getRow()+1,getCol()));
    res.add(new Coordinates(getRow(),getCol()+1));
    res.add(new Coordinates(getRow(),getCol()-1));
    return res;
    }
    
    /**
     * Compare this object to another Coordinates object.
     * @override equals in class Object.
     * @param other - the other Coordinates object.
     * @return true only if both the rows and columns are equal.
     */
    
    public boolean equals(Object other){
        if(other instanceof Coordinates){
        Coordinates c=(Coordinates)other;
        return getRow()==c.getRow() && getCol()==c.getCol();
        }return false;
    }
    
    /**
     * Produce a human-readable string of this object.
     * @override toString in class Object.
     * @return a string in the format "(row,column)"
     */
    
    public String toString(){
    return "("+this.row+","+this.col+")";
    }
}
