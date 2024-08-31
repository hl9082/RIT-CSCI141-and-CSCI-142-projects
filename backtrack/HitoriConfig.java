 

import javafx.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * CSCI-142 Computer Science 2 Recitation Pair Exercise
 * 13-DFS_Backtracking
 * Hitori
 *
 * A class to represents a single instance of a Hitori puzzle.
 *
 * This is the student version.
 *
 * @author RITCS
 */
public class HitoriConfig implements Configuration {
    /** black will have a value of 0 which is unused by the puzzle */
    private final static int BLACK = 0;
    /** display the black cells with an X */
    private final static String BLACK_STR = "X";
    /** the square dimension of the board */
    private static int DIM;
    /** the grid of numbers */
    private int board[][];
    /** the cursor row we just placed at */
    private int row;
    /** the cursor column we just placed at */
    private int col;
    /** the number of cells that are numbered and not black */
    private int numberedCells;

    /**
     * Construct a new configuration.  This is called by the main program to
     * read in the initial configuration from a file.
     *
     * @param filename the name of the file with the numbers
     * @throws FileNotFoundException if the file cannot be found
     */
    public HitoriConfig(String filename) throws FileNotFoundException {
        // create a scanner tied to the input file
        Scanner in = new Scanner(new File(filename));

        // read the square dimension of the board
        DIM = in.nextInt();

        // create the board
        this.board = new int[DIM][DIM];

        // populate the board
        for (int row=0; row<DIM; ++row) {
            for (int col=0; col<DIM; ++col) {
                this.board[row][col] = in.nextInt();
            }
        }

        // close the scanner/file
        in.close();

        // set the cursor so it is off the board initially
        this.row = 0;
        this.col = -1;

        // all cells are numbered (non-black) to begin with
        this.numberedCells = this.DIM * this.DIM;
    }

    /**
     * Make a copy of a current configuration.  This routine is called when
     * generating successors by copying the previous valid config and
     * generating new ones where the cursor is advanced to the next
     * cell and either the cell retains its number, or it is blacked out.
     *
     * @param other the config to copy
     * @param black should we populate the cursor cell black or leave it?
     */
    public HitoriConfig(HitoriConfig other, boolean black) {
        // copy the cursor position and numbered cells from other
        this.row = other.row;
        this.col = other.col;
        this.numberedCells = other.numberedCells;

        // advance cursor to this cell (for checking in isValid)
        this.col += 1;
        if (this.col == this.DIM) {
            this.row += 1;
            this.col = 0;
        }

        // create copy of board
        this.board = new int[DIM][DIM];
        for (int row=0; row<this.DIM; ++row) {
            System.arraycopy(other.board[row], 0, this.board[row], 0, this.DIM);
        }

        // if the cell is meant to be black, change it and make note, otherwise
        // leave it be
        if (black) {
            this.board[this.row][this.col] = BLACK;
            this.numberedCells -= 1;
        }
    }


    /**
     * There are two successors that can come from a valid config.  They
     * are generated here by using the copy constructor.  At the cursor
     * cell we either leave the number, or replace it with black.
     *
     * @return the successors
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        // create a collection to hold the next two successor configs
        List<Configuration> successors = new LinkedList<>();

        // TODO: Step 1

        // create a copy config with the next cell not blackened and add it
        // to the successors
        // create a copy config with the next cell blackened and add it to
        // the successors

        // return the successors
         Configuration c_1=new HitoriConfig(this,false);
         successors.add(c_1);
         Configuration c_2=new HitoriConfig(this,true);
         successors.add(c_2);
        return successors;
    }

    /**
     * A helper function for recursively counting the number of numbered cells.
     *
     * @param cell the (row, col) to check
     * @param visited the already visited cells (to prevent cycle loop)
     * @return the total number cells visited with the search
     */
    private int countNumberedCellsDFS(Pair<Integer, Integer> cell, Set<Pair<Integer, Integer>> visited) {
        int count = 0;
        int row = cell.getKey();
        int col = cell.getValue();

        // check N
        Pair<Integer, Integer> north = new Pair<>(row-1, col);
        if (row-1 >= 0  &&
                this.board[row-1][col] != BLACK &&
                !visited.contains(north)) {
            visited.add(north);

            // TODO: Step 2
            // the count is the count, plus 1, plus the recursive call with the north cell
            count+=(1+countNumberedCellsDFS(north,visited));
        }

        // check S
        Pair<Integer, Integer> south = new Pair<>(row+1, col);
        if (row+1 < DIM  &&
                this.board[row+1][col] != BLACK &&
                !visited.contains(south)) {
            visited.add(south);

            // TODO: Step 2

            // the count is the count, plus 1, plus the recursive call with the south cell
            count+=(1+countNumberedCellsDFS(south,visited));
        }

        // check E
        Pair<Integer, Integer> east = new Pair<>(row, col+1);
        if (col+1 < DIM  &&
                this.board[row][col+1] != BLACK &&
                !visited.contains(east)) {
            visited.add(east);

            // TODO: Step 2

            // the count is the count, plus 1, plus the recursive call with the east cell
            count+=(1+countNumberedCellsDFS(east,visited));
        }

        // check W
        Pair<Integer, Integer> west = new Pair<>(row, col-1);
        if (col-1 >= 0  &&
                this.board[row][col-1] != BLACK &&
                !visited.contains(west)) {
            visited.add(west);

            // TODO: Step 2

            // the count is the count, plus 1, plus the recursive call with the west cell
            count+=(1+countNumberedCellsDFS(west,visited));
        }

        return count;
    }

    /**
     * Locate the first numbered cell in the grid starting at (0,0).
     *
     * @return the first cell with a number (not black)
     */
    private Pair<Integer, Integer> findFirstNumberedCell() {
        int row=0;
        int col=0;

        // just loop until we find the first numbered cell
        while (true) {
            if (this.board[row][col] != BLACK) {
                break;
            }
            col += 1;
            if (col == DIM) {
                row += 1;
                col = 0;
            }
        }

        // wrap the cell into a pair and return it
        return new Pair<>(row, col);
    }

    /**
     * A function that checks there are no duplicate numbers (exclusing black)
     * in a row or column.
     *
     * @return true if there are no duplicates, false otherwise
     */
    private boolean noDuplicateNumbers() {
        // can check a row when we populated in the last column
        if (this.col == DIM-1) {
            // check this row for duplicates
            List<Integer> numbers = new ArrayList<>(DIM);
            for (int col=0; col<DIM; ++col) {
                if (this.board[this.row][col] != BLACK) {
                    numbers.add(this.board[this.row][col]);
                }
            }
            if (numbers.stream().distinct().count() != numbers.size()) {
                return false;
            }
        }

        // can check a column when we populated in the last row
        if (this.row == DIM-1) {
            // check this column for duplicates
            List<Integer> numbers = new ArrayList<>(DIM);
            for (int row=0; row<DIM; ++row) {
                if (this.board[row][this.col] != BLACK) {
                    numbers.add(this.board[row][this.col]);
                }
            }
            if (numbers.stream().distinct().count() != numbers.size()) {
                return false;
            }
        }

        // there are no duplicate numbers
        return true;
    }

    /**
     * Check to make sure if a black cell was just placed that it is
     * not adjacent (N,S,E,W) to an existing black cell.
     *
     * @return true if there are no adjacent black cells, false otherwise
     */
    private boolean noAdjacentBlackCell() {
        // don't need to check if this cell is not black
        if (this.board[this.row][this.col] != BLACK) {
            return true;
        }

        // because of how we populate from left to right and top
        // to bottom we only need to check north and west

        // check if north is a black cell
        if (this.row-1 >= 0 && this.board[row-1][col] == BLACK) {
            return false;
        }

        // check if west is a black cell
        if (this.col-1 >= 0 && this.board[row][col-1] == BLACK) {
            return false;
        }

        // there are no adjacent black cells
        return true;
    }

    /**
     * Check that all numbered cells are connected to each other and are
     * reachable.
     *
     * @return true if all numbered cells are connected, otherwise false
     */
    private boolean allNumbersConnected() {
        // create an empty visted set
        Set<Pair<Integer, Integer>> visited = new HashSet<>();

        // add the first numbered cell to the visited list.  we will start
        // our counting DFS with this cell
        Pair<Integer, Integer> firstNumberedCell = findFirstNumberedCell();
        visited.add(firstNumberedCell);

        // perform a DFS and count the number of numbered cells and make sure it
        // equals the total number of numbered cells.  if not it means the numbered
        // cells are not all connected.  we add one here to account for the initial
        // cell that the search started at.
        return 1 + countNumberedCellsDFS(firstNumberedCell, visited) == this.numberedCells;
    }

    /**
     * There are three conditions that must be checked to determine if a successor
     * config is valid:
     *
     * 1. There are no duplicate numbes in a row or column (excluding black cells).
     * 2. There are no adjacent black cells (N,S,E,W).
     * 3. All the numbered cells are connected and reachable.
     *
     * @return whether this new config is valid or not
     */
    @Override
    public boolean isValid() {
        // check if there is a duplicate number in a row or column
        if (!noDuplicateNumbers()) {
            return false;
        }

        // check if a black cell was populated that there are no adjacent
        // black cells.
        if (!noAdjacentBlackCell()) {
            return false;
        }

        // check that all the numbered cells are connected to each other
        if (!allNumbersConnected()) {
            return false;
        }

        // all checks passed this is a valid config
        return true;
    }

    /**
     * Check if a configuration is a goal or not.  Because of all the work
     * done in isValid this is trivial.  We just make sure the cursor is at
     * the last cell in the grid.  Since we know the config is valid it
     * is a solution.
     *
     * @return whether this config is a goal config or not
     */
    @Override
    public boolean isGoal() {
        // TODO: Step 3

        // if the cursor (row, col) is at the last cell in the board, this is a goal,
        // otherwise it is not
        return this.row==DIM-1 && this.col==DIM-1;
    }

    /**
     * Return a string representation of the grid with either the numbered
     * cells number, or an X for a black cell.
     *
     * @return a string of the board
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row=0; row<DIM; ++row) {
            result.append("\n");
            for (int col=0; col<DIM; ++col) {
                if (this.board[row][col] == BLACK) {
                    result.append(BLACK_STR);
                } else {
                    result.append(this.board[row][col]);
                }
                result.append(" ");
            }
        }
        return result.toString();
    }
}
