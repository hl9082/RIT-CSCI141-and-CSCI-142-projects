package islands;

/**
 * Represents a single cell in the game Islands. A cell is uniquely
 * identified by its row and column coordinates.
 *
 * @author RIT CS
 * @author HUY LE
 */
public class Cell {
    /**
     * the cell's coordinates
     */
    private final Coordinates location;
    
    /**
     * The owner of the cell, islands.PlaYerRole.None if none.
     */
    
    private PlayerRole owner;

    /**
     * Create the cell with the given integer values as row and column.
     * By default, the cell has no owner (islands.PlayerRole.None).
     *
     * @param row the row value
     * @param col the column
     */
    public Cell(int row, int col) {
        this.location=new Coordinates(row,col);
        this.owner=PlayerRole.NONE;
    }

    /**
     * Claim the cell. A cell should be claimed by a player
     * only if the cell has no owner. This method should be called
     * exactly once.
     *
     * @param player the owner that has claimed the cell
     */
    public void claim(PlayerRole player){
        this.owner=player;
    }

    /**
     * Does the cell have an owner?
     *
     * @return whether the cell has an owner
     */
    public boolean hasOwner() {
        return this.owner!=PlayerRole.NONE;
    }

    

    /**
     * Get the coordinates of this cell.
     *
     * @return the cell's location
     */
    public Coordinates getCoordinates() {
        // TODO
        return this.location;
    }

    /**
     * Get the owner of the cell.
     *
     * @return the owner, islands.PlayerRole.NONE if not owned
     */
    public PlayerRole getOwner() {
        // TODO
        return this.owner;
    }

    /**
     * Two cells are equals if they have the same row and column.
     *
     * @param other the cell to compare with
     * @return whether they are equals or not
     */
    @Override
    public boolean equals(Object other) {
        if(other instanceof Cell othercell){
        return this.location.getRow()==othercell.location.getRow()
        && this.location.getCol()==othercell.location.getCol();
    }return false;
    }

    /**
     * Returns the cell's owner followed by the coordinates, e.g.:
     * <pre>
     * RED: (0,0)
     * </pre>
     *
     * @return the cell's owner followed by the cell's coordinates.
     * @see PlayerRole
     * @see Coordinates#toString()
     */
    @Override
    public String toString() {
        // TODO
        return owner.name()+": ("+location.getRow()
        +","+location.getCol()+")";
    }
    
    /**
     * Get the row.
     * @return the row.
     */
    
    public int getRow(){
    return this.location.getRow();
    }
    
    /**
     * Get the column.
     * @return the column.
     */
    
    public int getCol(){
    return this.location.getCol();
    }
}
