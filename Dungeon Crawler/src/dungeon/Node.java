package src.dungeon;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import src.dungeon.Treasure;
import src.dungeon.Coordinates;

/**
 * CSCI-142 Computer Science 2 Recitation Presentation
 * 07-GraphIntro
 * Shortet Path
 *
 * This is the representation of a node in a graph.  A node is composed of a
 * unique name, and a list of neighbor Node's.
 * 
 * @author RIT CS
 */
public class Node {
    /** Neighbors of this node are stored as a set (adjacency list) */
    private Set<Node> neighbors;
    
    /**The treasure representation.*/
    private Treasure tr;
    
    /**The location of the node.*/
    private Coordinates location;

    /**
     * Constructor initializes Node with an empty list of neighbors.
     * 
     * @param name string representing the name associated with the node.
     */
    public Node(Coordinates location) { 
        this.location=location;
        this.neighbors = new LinkedHashSet<>();
    }

    /**
     * Get the location associated with the node.
     * 
     * @return location.
     */
    public Coordinates getLocation() { 
        return this.location;
    }

    /**
     * Method to return the adjacency list for this node containing all 
     * of its neighbors.
     * 
     * @return the list of neighbors of the given node
     */
    public Collection<Node> getNeighbors() {
        return Collections.unmodifiableSet(this.neighbors);
    }

    /**
     * Add a neighbor to this node.  Checks if already present, and does not
     * duplicate in this case.
     *
     * @param node: node to add as neighbor.
     */
    public void addNeighbor(Node node) {
        if(!neighbors.contains(node)) {
            neighbors.add(node);
        }
    }

    
    /**
     * Method to generate a string associated with the node, including the 
     * location of the node followed by the names of its neighbors.  Overrides
     * Object toString method.
     * 
     * @return string associated with the node.
     */
    @Override
    public String toString() {
        String result = this.getLocation() + ": ";
        for (Node neighbor: this.neighbors) {
            result += neighbor.getLocation() + " ";
        }
        return result;
    }

    /**
     *  Two Nodes are equal if they have the same name.
     *
     *  @param other The other object to check equality with
     *  @return true if equal; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Node) {
            Node n = (Node) other;
            result = this.getLocation().equals(n.getLocation());
        }
        return result;
    }

    /**
     * The hash code of a Node is just the hash code of the treasure's name, since no
     * two nodes can have the same name, and it is consistent with equals()
     */
    @Override
    public int hashCode() {
        return this.getLocation().hashCode();
    }
}
