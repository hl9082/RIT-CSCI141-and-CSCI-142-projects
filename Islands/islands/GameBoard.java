package islands;
import islands.Player;
import islands.Cell;
import islands.PlayerRole;
import islands.Island;
import islands.Direction;
public class GameBoard {
    /**
     * the blue player
     */
    
    private final Player bluePlayer=new Player(PlayerRole.BLUE,Direction.HORIZONTAL);
    
    /**
     * The red player
     */
    
    private final Player redPlayer=new Player(PlayerRole.RED,Direction.VERTICAL);

    /**
     * number of rows
     */
    private final int rows;
    /**
     * Number of columns
     */
    private final int columns;
    /**
     * the current player playing
     */
    private Player currentPlayer=this.redPlayer;
    
    /**
     * the grid of cells. 
     */
    
    private final Cell[][] board;
    
    /**
     * The number of moves made by both players.
     */
    private int moves;
    
    /**
     * Creates the game board. The red player
     * goes first.
     * @param rows - number of rows.
     * @param columns - number of columns.
     */
    
    public GameBoard(int rows,int columns){
    this.rows=rows;this.columns=columns;
    this.board=new Cell [rows][columns];
    for(int i=0;i<rows;i++){
        for(int j=0;j<columns;j++){
            board[i][j]=new Cell(i,j);
        }
    }
    }
    
    /**
     * Whose turn is it anyway?
     * @return the player who has the current turn
     * (the current player)
     */
    
    public Player whoseTurn(){
    return this.currentPlayer;
    }
    
    /**
     * Make a move in the game given a valid cell 
     * to claim. A move is made by 
     * specifying an unclaimed cell 
     * to be owned by the current player. 
     * Once the cell is claimed, 
     * the number of moves in the game 
     * increases by one and the next turn is 
     * swapped to the other player.
     * @param row - the cell's row.
     * @param col - the cell's column.
     * @rit.pre - the coordinates for the cell 
     * have already been checked for validity 
     * and can be claimed
     */
    
    public void claimCell(int row,int col){
    this.currentPlayer.claim(this.board[row][col]);
    this.moves++;
    nextTurn();
    }
    
    /**
     * Swap the turn to the other player
     */
    
    private void nextTurn(){
    this.currentPlayer=(this.currentPlayer==this.redPlayer)?this.bluePlayer:this.redPlayer;
    }
    
    /**
     * Are there any cell with no owner?
     * @return whether there are any unclaimed cell.
     */
    
    private boolean hasUnclaimedCell(){
    boolean res=false;
    // When you say break main, it will break from the outer loop (main loop) instead of the inner loop.
    // Loop label is useful for breaking nested loops.
    main:for(int i=0;i<this.rows;i++){
        for(int j=0;j<this.columns;j++){
            if(!this.board[i][j].hasOwner()){
                  res=true;
                  break main;
            }    
    }
    }
    return res;
    }
    
    /**
     * Are the coordinates for this cell valid or not? 
     * To be valid it must be a cell that exists in the board and 
     * is unclaimed.
     * @param row - the cell's row.
     * @param col - the cell's column.
     * @return - whether the cell is valid or not.
     */
    
    public boolean isCellValid(int row,int col){
            return (row<this.rows && row>=0 && col<this.columns && col>=0
            && !this.board[row][col].hasOwner());
    }
    
    /**
     * Is the game over? This happens when there are no more cells to claim, a red player's island 
     * touches the board from 
     * top to bottom or a blue's player island touches the board from left to right.
     * @return whether the game is over.
     */
    
    public boolean gameOver(){
    
    return (this.redPlayer.touchesBoardBoundaries(this.rows,this.columns)||
    this.bluePlayer.touchesBoardBoundaries(this.rows,this.columns)||
    !hasUnclaimedCell());
    }
    
    /**
     * Display in the standard output the player's information followed by 
     * each of the islands owned by the player.
     * Player: RED, islands: 2
     * Island{size:2}
     * (0,1)(0,0)
     * Island{size:3}
     * (2,2)(1,2)(2,1)
     * @param playerRole - the player's role
     */
    
    public void displayIslands(PlayerRole playerRole){
    Player p = (playerRole==PlayerRole.RED)? redPlayer : bluePlayer;
    System.out.println(p.toString());
    for(Island island : p.getIslands()){
    System.out.print(island.toString());
    }
    
}
    
    /**
     * Return a string representation of the board, e.g. a 3x3 board:
     * <br><pre>
     *       0  1  2
     *     _________
     *  0 |  *     -
     *  1 |     -  *
     *  2 |  -  *
     *
     * Player: RED, islands: #, Player: BLUE, islands: #
     * Moves: #, Turn: {Player}
     * </pre>
     *
     * If the game is over, it will print who is the winner or whether it is a tie, e.g:
     *<br><pre>
     *      0  1  2
     *     _________
     *  0 |  *  *  *
     *  1 |  -  -  -
     *  2 |  -  *
     *
     * Player: RED, islands: #, Player: BLUE, islands: #
     *
     * {Player} wins # to #!
     * </pre>
     *
     * @return the string representation of the game's status
     * @see Player#toString()
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(" ");
        result.append(System.lineSeparator());
        // first display the game board
        result.append("    ");
        for (int col = 0; col < this.columns; col++) {
            result.append(String.format("%3d", col));
        }
        result.append(System.lineSeparator());
        result.append("    ");
        for (int col = 0; col < this.columns; col++) {
            result.append("___");
        }
        result.append(System.lineSeparator());

        // rows of indices and then cell values
        for (int row = 0; row < this.rows; row++) {
            result.append(String.format("%2d |", row));

            // cell values
            for (int column = 0; column < this.columns; column++) {
                String sep = "  ";
                result.append(sep);
                result.append(this.board[row][column].getOwner().getLabel());
            }
            result.append(System.lineSeparator());
        }
        // players status
        result.append(System.lineSeparator());
        result.append(this.redPlayer);
        result.append(", ");
        result.append(this.bluePlayer);
        result.append(System.lineSeparator());

        // next display the game status
        if (!gameOver()) {
            result.append("Moves: ");
            result.append(this.moves);
            result.append(", ");
            result.append("Turn: ");
            result.append(this.currentPlayer.getRole());
        } else {
            if (this.redPlayer.getNumIslands() > this.bluePlayer.getNumIslands()) {
                result.append("\nRED wins ");
                result.append(this.redPlayer.getNumIslands());
                result.append(" to ");
                result.append(this.bluePlayer.getNumIslands());
                result.append("!");
            } else if (this.bluePlayer.getNumIslands() > this.redPlayer.getNumIslands()) {
                result.append("\nBLUE wins ");
                result.append(this.bluePlayer.getNumIslands());
                result.append(" to ");
                result.append(this.redPlayer.getNumIslands());
                result.append("!");
            } else {
                result.append("\nTIE game ");
                result.append(this.redPlayer.getNumIslands());
                result.append(" to ");
                result.append(this.bluePlayer.getNumIslands());
                result.append("!");
            }
        }
        return result.toString();
    }
}
