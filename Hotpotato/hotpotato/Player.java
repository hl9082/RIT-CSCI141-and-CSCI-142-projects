package hotpotato; 

/**
 * Represents a single player in the game.
 *
 * @author RIT CS
 */
public class Player {
    /** player name */
    private String name;
    /** player id (unique) */
    private int id;

    /**
     * Create a new player.
     * @param name player name
     * @param id player id
     */
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Accessor for player's name.
     * @return player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for player's id.
     * @return player's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Two players are equal if they have the same name and id.
     * @param other the other player to compare to
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Player otherPlayer) {
            result = this.name.equals(otherPlayer.name) && this.id == otherPlayer.id;
        }
        return result;
    }

    /**
     * Returns a string in the form: "{Player name}({id})".
     * @return the string representation of the player
     */
    @Override
    public String toString() {
        return this.name + "(" + this.id + ")";
    }
}
