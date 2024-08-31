package islands;
import java.util.ArrayList;


/**
 * Represents a player in the Islands game..
 *
 * @author RIT CS
 * @author Huy Le
 * 
 */
public class Player
{
   /**
    * The player's direction to verify whether the game is over.
    */ 
   
   private final Direction direction;
   
   /**
    * The player's collection of islands.
    */
   
   private final ArrayList<Island>islands;
   
   /**
    * The player's role.
    */
   
   private final PlayerRole role;
   
   /**
    * Creates the player. By default, the player has no
    * islands.
    * 
    * @param role - The player's role.
    * @param direction - The orientation associated to the player to 
    * check whether the game is over. 
    * For example, if player has Direction.HORIZONTAL, 
    * the game will be over if the player has an island 
    * that touches both the left and right boundaries of the board.
    * 
    * 
    */
   
   public Player(PlayerRole role,Direction direction){
    this.islands=new ArrayList<>();
    this.role=role;
    this.direction=direction;
    }
    
   /**
    * Get the role.
    * @return the player's role.
    */ 
   
   public PlayerRole getRole(){
    return this.role;
    }

   /**
    * Get the direction associated to this player.
    * 
    * @return the direction this player checks to detect whether the game is over.

    */ 
   public Direction getDirection(){
    return this.direction;
    }
   
    /**
     * Creates an island with the given cell and adds it to the player's 
     * collection of islands.
     * This method must first claim the cell.
     * Then, it will initially create an island containing only the given cell.
     * Finally, it will merge this new island with its four cardinal neighbor islands.
     * @param cell - the cell to be claimed.
     */
    
    public void claim(Cell cell){
        cell.claim(getRole());
        mergeIslands(cell);
    }
    
    /**
     * Checks whether any of the player's island touches 
     * both the boundaries of the board according to the 
     * player's direction.
     * @param rows - the board's number of rows.
     * @param cols - the board's number of columns.
     * @return whether this player has an island 
     * that touches the board's boundaries.
     */
    
    public boolean touchesBoardBoundaries(int rows,int cols){
    boolean res=false;
    for(Island island:islands){
            if(island.touchesBoardBoundaries(rows,cols,this.direction)){
                res=true;
                break;
            }
        }return res;
    }
    
   
    /**
     * Get the number of islands this player is owner.
     * @return the number of islands.
     */
    
    public int getNumIslands(){
    return this.islands.size();
    }
    /**
     * Get the collection of islands this player is owner.
     * @return the collection of islands.
     */
    
    public ArrayList<Island>getIslands(){
    return this.islands;
    }
    
    /**
     * Attempt to merge this cell with its four 
     * cardinal neighbor islands.
     * Create initially a new island, newIsland, 
     * with only the given cell.
     * Get this player's islands that are neighbors 
     * to the given cell.
     * Iterate over the collection of neighbors islands:
?    * merge the island into newIsland
?    * remove the island from the player's collection of islands
     * Add newIsland into the player's collection of islands
     * @param cell-the cell claimed by the player.
     */
    
    private void mergeIslands(Cell cell){
            Island newIsland = new Island(cell);
            ArrayList<Island>neighbors=getNeighborIslands(cell);
            for(Island neighbor : neighbors){
            newIsland.merge(neighbor);
            }
            this.islands.removeAll(neighbors);
            this.islands.add(newIsland);
    }
    
    /**
     * Gets all this player's islands that 
     * are neighbors to the given cell.
     * @param cell - the cell.
     * @return The player's islands that 
     * are neighbors in the four cardinal directions 
     * of the given cell.
     */
    private ArrayList<Island> getNeighborIslands(Cell cell){
    ArrayList<Island>res=new ArrayList<>();
    ArrayList<Coordinates>neighbors=cell.getCoordinates().getNeighbors();
    for(Island island : this.islands){
        for(Coordinates neighbor : neighbors){
    if(island.hasCell(neighbor)){//to check if the island is a neighbor
        res.add(island);
        break;
    }
    }
}
    return res;
    }
    
    /**
     * Two players are equal if they have the same role.
     * @param other - the player to compare with.
     * @return whether they are equals or not.
     */
    public boolean equals(Object other){
    return other instanceof Player p && this.role==p.role;
    }
    
    /**
     * Get the player's role and their number of islands, e.g.:
     * Player: RED, islands: 3
     * @return a string 
     * in the format "Player: {role}, islands: {#_of_islands}"
     */
    
    public String toString(){
    return "Player: "+role.name()+", islands: "+getNumIslands();
    }
}
