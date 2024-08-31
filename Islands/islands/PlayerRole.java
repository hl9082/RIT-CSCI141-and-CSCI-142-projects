package islands;

/**
 * This class is an enumeration for representing the players in the
 * Islands game. Here is an example of how you can use this enum in another
 * class:
 *
 * <pre>
 * PlayerRole role = PlayerRole.Red;        // player role is Red
 * if (role == PlayerRole.Red) {
 *     System.out.println(role);              // prints: RED
 *     System.out.println(role.getLabel());   // prints: *
 * }
 * role = PlayerRole.BLUE;                   // player role is now Blue
 *
 * // validating if a given player role's name is valid
 * boolean isValid = PlayerRole.isPlayerRole("red");
 * if (isValid) {
 *     // get the enum constant with that given name
 *     PlayerRole role = PlayerRole.valueOf("red");
 * }
 *
 * PlayerRole.isPlayerRole("Fake"); // returns false, there is no constant with name value "Fake"
 * </pre>
 *
 * @author RIT CS
 */
public enum PlayerRole {
    /**
     * The red player, label value "*"
     */
    RED("*"),           // red player is: *
    /**
     * The blue player, label value "-"
     */
    BLUE("-"),          // blue player is: -
    /**
     * No player specified. Useful to specify a cell has no owner.
     * Label value " "
     */
    NONE(" ");          // If no player is specified it is a space

    /**
     * the label associated with the role
     */
    private final String label;

    /**
     * Create the role with the label. This should not get called directly by the
     * user - it happens implicitly when the definitions above are read in.
     *
     * @param label the label for the role
     */
    PlayerRole(String label) {
        this.label = label;
    }

    /**
     * See if the player's role is legitimate.
     *
     * @param role the player's role to check
     * @return true only if this role value is valid. (Case must match.)
     */
    public static boolean isPlayerRole(String role) {
        for (PlayerRole playerRole : values()) {
            if (playerRole.toString().equals(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the player role's label.
     *
     * @return the string label
     */
    public String getLabel() {
        return this.label;
    }
}
