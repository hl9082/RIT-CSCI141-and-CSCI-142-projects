package src.dungeon;

import java.util.Collection;

/**
 * An interface for a two dimensional dungeon.
 *
 * @author RIT CS
 */
public interface IDungeon {
    // DUNGEON FILE CONTENTS
    /** cell string (no home or treasure) */
    String CELL = ".";
    /** north neighbor */
    String NORTH = "N";
    /** south neighbor */
    String SOUTH = "S";
    /** east neighbor */
    String EAST = "E";
    /** west neighbor */
    String WEST = "W";

    // DUNGEON OUTPUT
    /** empty */
    String EMPTY = " ";
    /** horizontal wall */
    String HORI_WALL = "-";
    /** vertical wall */
    String VERT_WALL = "|";
    /** home string */
    String HOME = "*";

    /**
     * Get the number of rows in the dungeon.
     *
     * @return number of rows
     */
    int getRows();

    /**
     * Get the number of columns in the dungeon.
     *
     * @return number of columns
     */
    int getCols();

    /**
     * Get the home position (must exist).
     *
     * @return home position
     */
    Coordinates getHome();

    /**
     * Is there a treasure at a specific location?
     *
     * @param location the location
     * @return whether there is a treasure at this location or not
     */
    boolean hasTreasure(Coordinates location);

    /**
     * Get the collection of all treasures in the dungeon.
     *
     * @return the treasure collection
     */
    Collection<Treasure> getTreasures();

    /**
     * Is dest a neighbor of src in the dungeon?
     *
     * @param src the source cell
     * @param dest the destination cell
     * @return whether they are neighbors or not
     */
    boolean isNeighbor(Coordinates src, Coordinates dest);

    /**
     * Is a location in the dungeon valid or not?
     *
     * @param location the location
     * @return whether the cell is valid or not
     */
    boolean hasCoordinates(Coordinates location);

    /**
     * Get the neighbors of a cell in the dungeon.
     *
     * @param location the cell
     * @return the cell's neighbors
     */
    Collection<Coordinates> getNeighbors(Coordinates location);

    /**
     * Get the string value of the cell at a location in the dungeon.
     * It returns one of three things.  The HOME cell for the home
     * position, the treasure name if the treasure is not collected yet,
     * or CELL if the treasure was collected.
     *
     * @param location the location
     * @return the string at that location
     */
    String getCell(Coordinates location);

    /**
     * Returns a string representation of the dungeon.
     * For example:<br>
     * <pre>
     *   0 1 2 3
     *   -------
     * 0|*|. . A|
     *       - -
     * 1|.|. B|.|
     *       -
     * 2|. . .|C|
     *   -------
     * </pre>
     */
    default String display() {
        // use a StringBuilder for efficiency
        StringBuilder result = new StringBuilder("  ");

        // top rows are the column indices and horizontal bars
        for (int col=0; col<getCols(); ++col) {
            result.append(col).append(" ");
        }
        result.append(System.lineSeparator()).append("  ");
        result.append(HORI_WALL.repeat(Math.max(0, getCols()*2-1)));
        result.append(System.lineSeparator());

        // each row gets built out here with the row number and cell values
        for (int row=0; row<getRows(); ++row) {
            result.append(row).append(VERT_WALL);
            // this loop is for the row of cells and the horizontal connections
            for (int col = 0; col < getCols(); ++col) {
                // get the cell value to display for this coord
                result.append(getCell(new Coordinates(row, col)));
                if (col < getCols() - 1) {
                    if (isNeighbor(new Coordinates(row, col), new Coordinates(row, col + 1))) {
                        result.append(EMPTY);
                    } else {
                        result.append(VERT_WALL);
                    }
                }
            }
            result.append(VERT_WALL).append(System.lineSeparator());
            // this loop is for the row below that contains the vertical connections
            if (row < getRows() - 1) {
                result.append("  ");
                for (int col = 0; col < getCols(); ++col) {
                    if (isNeighbor(new Coordinates(row, col), new Coordinates(row + 1, col))) {
                        result.append(EMPTY).append(" ");
                    } else {
                        result.append(HORI_WALL).append(" ");
                    }
                }
                result.append(System.lineSeparator());
            }
        }
        // the bottom row
        result.append("  ");
        result.append(HORI_WALL.repeat(Math.max(0, getCols()*2-1)));
        return result.toString();
    }
}
