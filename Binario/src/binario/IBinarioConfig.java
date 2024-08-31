package src.binario;

/**
 * An interface to the binario puzzle representation that is used by the JUnit test
 * and BinarioConfig's toString().
 */
public interface IBinarioConfig {
    /**
     * Get the square dimension of the puzzle.
     * @return number of rows
     */
    int getDim();

    /**
     * Get the value at the cell.
     * @param row the row
     * @param col the coordinate
     * @return the cell value
     */
    char getVal(int row, int col);

    /**
     * Get the cursor row location.
     * @return cursor row
     */
    int getCursorRow();

    /**
     * Get the cursor column location.
     * @return cursor column
     */
    int getCursorCol();

    /**
     * A default implementation that the config's toString() will call
     * to get the board values in string form.
     *
     * @return the board in string form
     */
    default String display() {
        StringBuilder result = new StringBuilder();
        for (int row=0; row<getDim(); ++row) {
            for (int col=0; col<getDim(); ++col) {
                result.append(getVal(row, col));
                if (col<getDim()-1) {
                    result.append(" ");
                }
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }
}
