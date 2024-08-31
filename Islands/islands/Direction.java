package islands;

/**
 * An enumeration that names the two directions to check whether an island bisects the board.
 * Here is an example of how you can use this enum in another class:
 *
 * <pre>
 * Direction dir = Direction.VERTICAL;        // the direction is VERTICAL
 * if (dir == Direction.VERTICAL) {
 *     System.out.println(dir);              // prints: VERTICAL
 * }
 * dir = Direction.HORIZONTAL;               // the direction is now HORIZONTAL
 * </pre>
 *
 * @author RIT CS
 */
public enum Direction {
    VERTICAL,
    HORIZONTAL
}
