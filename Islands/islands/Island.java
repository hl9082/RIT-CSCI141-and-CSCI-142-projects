package islands;
import java.util.ArrayList;
import islands.Direction;
/**
 * Represents an island in the game Islands. An island is a 
 * collection of connected cells claimed by the same player. 
 * The cells are connected 
 * in the four principal directions: North, South, East and West.
 *
 * @author RIT CS
 * @author HUY LE
 */
public class Island
{
    /**the collection of cells*/
    private final ArrayList<Cell> cells;
    
    /**
     * Create an empty island with no cells.
     * 
     */
    public Island(){
    this.cells=new ArrayList<Cell>();
    }
    
    /**
     * Create an island with the given cell.
     * @param cell - the island's cell.
     */
    
    public Island(Cell cell){
    this.cells=new ArrayList<Cell>();
    this.cells.add(cell);
    }
    
    /**
     * Merge the given Island object into this Island. 
     * Add the cells from the given Island into this Island.
     * @param island - the island to merge with
     * 
     */
    
    public void merge(Island island){
    this.cells.addAll(island.cells);
    }
    
    /**
     * Get the number of cells on this Island.
     * @return number of cells that comprises this Island.
     */
    
    public int size(){
    return this.cells.size();
    }
    
    /**
     * Does the island have this cell?
     * @param coordinates - the cell's coordinates.
     * @return whether this Island has a cell with the given
     * coordinates.
     */
    
    public boolean hasCell(Coordinates coordinates){
    return this.cells.contains(new Cell(coordinates.getRow(),
    coordinates.getCol()));
    }
    
    /**
     * Add the given cell into this Island.
     * @param cell - the cell to add.
     */
    
    public void addCell(Cell cell){
    this.cells.add(cell);
    }
    
    /**
     * Check whether this Island touches the boundaries of the board in the given direction. 
     * This method return true if an island of multiple cells has two cells at both 
     * boundaries of the board in that particular direction.
     * If an island has only one cell, that cell must touch 
     * both boundaries at the same time.
     * @param rows - the number of rows on the game's board (board's row dimension)
     * @param cols - the number of columns on the game's board (board's column dimension)
     * @param direction - the direction
     * @return whether the island has cells in the board's boundaries coordinates 
     * for the given direction.
     */
    public boolean touchesBoardBoundaries(int rows,int cols,Direction direction){
        boolean top=false;boolean bottom=false;
        for(Cell cell : this.cells){
            Coordinates c=cell.getCoordinates();
                if(direction==Direction.VERTICAL){// check for the top and bottom
                 if(c.getRow()==0){
                        top=true;
                    }
                    if (c.getRow()==rows-1){
                    bottom=true;
                    }
                }
                else{//check for left and right
                    if(c.getCol()==0){
                        top=true;
                    }
                    if(c.getCol()==cols-1){
                        bottom=true;
                    }
                }
                if(top && bottom){break;}
            }
        return top && bottom;
    }
    
    /**
     * Two islands are equals if they have the same collection of cells.
     * @param other - the other island to compare with.
     * @return - whether they are equals or not.
     */
    @Override
    public boolean equals(Object other){
    
    return other instanceof Island other_island && this.cells.equals(other_island.cells);
    }
    
    /**
     * Returns a string with the Island's size followed by the coordinates of every cell that conforms the island, e.g.:
       Island{size:3}
       (0,0)(0,1)(1,0)
     * This string will contain a new line and a tabulation only if 
     * the island has cells. To print a tabulation in Java, use "\t".
     * @Override toString in class Object.
     * @return the island's string representation
     */
    public String toString(){
    String res="Island{size:"+size()+"}";
    if(size()<1){
    ;
    }else{
    res+="\n\t";
    for(Cell cell:this.cells){
    res+="("+cell.getRow()+","+cell.getCol()+")";
    }
    }return res;
    }
}
