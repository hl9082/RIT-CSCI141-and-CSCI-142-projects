package islands;

import islands.PlayerRole;

import java.util.Scanner;

/**
 * The main program for the pen and paper game, Islands.  It is run on the command line as:<br>
 * <br>
 * java Islands #_of_rows #_of_columns<br>
 * <br>
 *
 * @author RIT CS
 */

public class Islands {

    /**
     * the prompt for the user to enter commands
     */
    public final static String PROMPT = "> ";
    /**
     * the game board
     */
    private final GameBoard gameBoard;

    /**
     * Initialize the game.
     * @param rows the number of rows in the board.
     * @param cols the number of columns in the board.
     */
    public Islands(int rows, int cols) {
        this.gameBoard = new GameBoard(rows, cols);
    }

    /**
     * The game play loop. This function process all the user input to play the game.
     * <br><br>
     * After every valid move, the new status of the game will be printed.
     */
    public void play() {
        System.out.println("Welcome to Islands!");
        displayHelp();

        System.out.println("\n" + this.gameBoard);
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.print(PROMPT);
                String line = in.nextLine();
                String[] words = line.split("\\s+");
                if (words.length > 0) {
                    if (words[0].startsWith("q")) {
                        System.out.println("Bye!");
                        break;
                    } else if (words[0].startsWith("c")) {
                        if (!this.gameBoard.gameOver()) {
                            if (makeMove(words)) {
                                System.out.println(this.gameBoard);
                            }
                        }
                    } else if (words[0].startsWith("s")) {
                        displayPlayer(words);
                    } else {
                        displayHelp();
                    }
                }
            }
        }
    }

    /**
     * Display all the commands available in the game.
     */
    private void displayHelp() {
        System.out.println("List of commands available:");
        System.out.println("\tc(laim) row column  -- claim the cell in that row and column");
        System.out.println("\ts(how) {red|blue}   -- show the islands claimed by the given player");
        System.out.println("\th(elp)              -- display this list of commands");
        System.out.println("\tq(uit)              -- quit the game");
    }

    /**
     * A helper function for making a single move in the game for
     * the current player.
     * <br><br>
     * If the number of argument is incorrect, this function prints an error message
     * of the type: Invalid user input!
     * <br><br>
     * If the row and column are invalid: they do not exist in the game's board or the cell is already claimed,
     * this function prints an error message of the type: Invalid cell coordinates!
     *
     * @param args row and column user input
     * @return whether the move was successfully made or not, e.g. the coordinates are out of bounds of the board
     * or the cell has been already claimed.
     */
    private boolean makeMove(String[] args) {
        System.out.println(this.gameBoard.whoseTurn().getRole() + "'s move...");
        if (args.length != 3) {
            System.out.println("Invalid user input!");
            return false;
        }

        // get the cell coordinates
        int row = Integer.parseInt(args[1]);
        int column = Integer.parseInt(args[2]);

        // check if the coordinates are inside the board's bound and
        // the cell has no owner
        if (this.gameBoard.isCellValid(row, column)) {
            // if it's ok, make the move on the board
            this.gameBoard.claimCell(row, column);
        } else {
            System.out.println("Invalid cell coordinates!");
            return false;
        }
        return true;
    }

    /**
     * Display all the islands owned by the given player.
     * <br><br>
     * If the number of arguments is incorrect, this function prints an error message of the type:
     * Invalid user input!
     *
     * @param args the player's label
     */
    private void displayPlayer(String[] args) {
        if (args.length != 2 || !PlayerRole.isPlayerRole(args[1].toUpperCase())) {
            System.out.println("Invalid user input!");
        } else {
            gameBoard.displayIslands(PlayerRole.valueOf(args[1].toUpperCase()));
        }
    }

    /**
     * The main method gets the command-line arguments, constructs
     * and plays the game.
     *
     * @param args the game board's dimension (number of rows and columns)
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Islands rows columns");
        } else {
            Islands game = new Islands(Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]));
            game.play();
        }
    }
}
