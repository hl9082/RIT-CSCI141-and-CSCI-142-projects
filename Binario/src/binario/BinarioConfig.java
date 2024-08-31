package src.binario;

import src.backtracking.Configuration;
import src.backtracking.Backtracker;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The representation of a binario configuration, including the ability
 * to backtrack and also give information to the JUnit tester.
 *
 * @author RIT CS
 * @author Huy Le
 */
public class BinarioConfig implements Configuration, IBinarioConfig {
    /** a cell that has not been assigned a value yet */
    private final static char EMPTY = '.';
    /** cell for 0 */
    private final static char ZERO = '0';
    /** cell for 1 */
    private final static char ONE = '1';

    // TODO:  Add your private state here
    /**The dimension of the board.*/
    private static int DIM;
    
    /**The board.*/
    private char board[][];
    
    /**The row cursor.*/
    private int row_cursor;
    /**The column cursor.*/
    private int col_cursor;
    /**
     * Read in the binario puzzle from the filename.  After reading in, it should display:
     * - the filename
     * - the square dimension of the puzzle
     * - the initial config with all empty cells
     *
     * @param filename the name of the file
     * @throws IOException thrown if there is a problem opening or reading the file
     */
    public BinarioConfig(String filename) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            // read first line: rows cols
            this.row_cursor=0;
            this.col_cursor=-1;
            String[] fields = in.readLine().split("\\s+");
            
            // TODO: Finish implementing the constructor
            this.DIM=Integer.parseInt(fields[0]);
            this.board=new char[DIM][DIM];
            for(int i=0;i<DIM;i++){
                fields = in.readLine().split("\\s+");
                for(int j=0;j<DIM;j++){
                this.board[i][j]=fields[j].charAt(0);
                }
            }
            // display the initial state
            System.out.println("File: " + filename);
            System.out.println("Dim: " + this.DIM);  // TODO: fix this
            System.out.println("Initial config:" + System.lineSeparator() + this);

        } // <3 Jim
    }

    /**
     * The copy constructor which advances the cursor, creates a new grid,
     * and populates the grid at the cursor location with val
     *
     * @param other the board to copy
     * @param val   the value to store at new cursor location
     */
    private BinarioConfig(BinarioConfig other, char val) {
        this.row_cursor = other.row_cursor;
        

        // advance cursor to this cell (for checking in isValid)
        this.col_cursor = other.col_cursor+1;
        if (this.col_cursor == this.DIM) {
            this.row_cursor += 1;
            this.col_cursor = 0;
        }

        // create copy of board
        this.board=new char [DIM][DIM];
        for(int i=0;i<DIM;i++){
            for(int j=0;j<DIM;j++){
            this.board[i][j]=other.board[i][j];
            }
        }
        this.board[row_cursor][col_cursor]=val;
    }


    /**
     * Generate the successor configs.  For minimal pruning, this should be
     * done in the order: if next cell empty - 0 and 1, if next cell populated,
     * just the one with the value unchanged.
     *
     * @return the collection of successors
     */
    @Override
    public List<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();
        int tmp_cur_row=this.row_cursor;
        int tmp_cur_col=this.col_cursor+1;
        if(tmp_cur_col==this.DIM){
        tmp_cur_col=0;
        tmp_cur_row+=1;
        }
        if(this.board[tmp_cur_row][tmp_cur_col]==EMPTY){
        successors.add(new BinarioConfig(this,ZERO));
        successors.add(new BinarioConfig(this,ONE));
        }
        else{
        successors.add(new BinarioConfig(this,getVal(tmp_cur_row,tmp_cur_col)));
        }
        return successors;
    }
    
    /**
     * Checks to make sure a successor is valid or not.
     *
     * @return whether this config is valid or not
     */
    @Override
    public boolean isValid() {
        Set<String>rowset=new HashSet<>();
        Set<String>colset=new HashSet<>();
        if(this.row_cursor==DIM-1 && this.col_cursor==DIM-1){
        for(int i=0;i<DIM;i++){
            String tmp=new String(this.board[i]);
            if(rowset.contains(tmp)){
            return false;
        }
        else{
        rowset.add(tmp);
        }
    }
            for(int j=0;j<DIM;j++){
                char [] tmp = new char [DIM];
             for(int i=0;i<DIM;i++){
                 tmp[i]=this.board[i][j];
        }String s=new String(tmp);
            if(colset.contains(s)){
           return false;
        }
        else{
        colset.add(s);
        }
    }
}
    if(this.col_cursor>=2){
        var row=this.board[this.row_cursor];
            int i=this.col_cursor;
                if(row[i]==row[i-1] && row[i]==row[i-2]){
                    return false;
                }
}
    if(this.row_cursor>1){
        char [] col = new char [DIM];
        for(int i=0;i<DIM;i++){
        col[i]=this.board[i][this.col_cursor];
        }
            int i=this.row_cursor;
                if(col[i]==col[i-1] && col[i]==col[i-2]){
                    return false;
    }
}
    if(this.col_cursor==this.DIM-1){    
    int zero=0,one=0;
            boolean full=true;
        char [] row=this.board[this.row_cursor];
            for(int i=0;i<row.length;i++){
                if(row[i]==this.EMPTY){
                full=false;
                }
                else if(row[i]==this.ZERO){
                    zero++;
                }
                else if(row[i]==this.ONE){
                one++;
                }
            }
            if(zero!=one && full==true){
                return false;
            }
    }
    if(this.row_cursor==DIM-1){
        
        int zero=0,one=0;boolean full=true;
        char [] col = new char [DIM];
        for(int i=0;i<DIM;i++){
        col[i]=this.board[i][this.col_cursor];
        }
            for(int i=0;i<col.length;i++){
                if(col[i]==this.EMPTY){
                full=false;
                }
                else if(col[i]==this.ZERO){
                    zero++;
                }
                else if(col[i]==this.ONE){
                one++;
                }
            }
            if(zero!=one && full==true){
                return false;}
    }
        return true;
    }
    
    

    @Override
    public boolean isGoal() {
        // TODO
        return this.row_cursor==DIM-1 && this.col_cursor==DIM-1;
    }

    /**
     * Returns a string representation of the puzzle including all necessary info.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return display();
    }

    // IBinarioConfig

    @Override
    public int getDim() {
        // TODO
        return this.DIM;
    }

    @Override
    public char getVal(int row, int col) {
        // TODO
        return this.board[row][col];
    }

    @Override
    public int getCursorRow() {
        // TODO
        return this.row_cursor;
    }

    @Override
    public int getCursorCol() {
        // TODO
        return this.col_cursor;
    }
}
