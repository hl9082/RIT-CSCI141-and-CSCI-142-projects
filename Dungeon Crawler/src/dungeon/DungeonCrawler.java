package src.dungeon;

import java.io.IOException;
import java.util.*;


/**
 * The main program for the dungeon crawler.  It is run on the command
 * line with the dungeon file, e.g.:
 *
 * $ java DungeonCrawler data/dungeon-3.txt
 *
 *
 * @author RIT CS
 * @author Huy Le
 */
public class DungeonCrawler {
    /** the dungeon */
    private final IDungeon dungeon;
    
    /**the number of steps*/
    private int steps=0;

    /**
     * Create the instance and construct the dungeon from the file.  Then print the 
     * initial dungeon.
     *
     * @param filename the dungeon file
     * @throws IOException if there is a problem reading the file
     * @throws DungeonException if there is an error in the dungeon file
     */
    public DungeonCrawler(String filename) throws IOException, DungeonException {
        this.dungeon = new Dungeon(filename);
        System.out.println(this.dungeon);
    }
    

    /**
     * Find all the treasures in the dungeon, starting at the home position
     * find the shortest path to the first treasure.  For each additional treasure,
     * find the shortest path from the last collected treasure to the next uncollected one.
     * When the last treasure is collected, find the shortest path from it to the
     * home position.  All along we the way we keep track of the total number of steps taken.
     */
    public void findTreasures(){
        var pos=dungeon.getHome();
        for(var treasure : dungeon.getTreasures()){
        System.out.println("Collecting: "+treasure.getName());
        pos=BFS(pos,treasure.getLocation());
        if(pos.equals(treasure.getLocation())){
        treasure.collect();
        }
        System.out.println(dungeon);
        }
        System.out.println("Returning home from "+pos+" to "+dungeon.getHome());
        BFS(pos,dungeon.getHome());
        System.out.println("Total steps: "+steps);
    }
    
    /**
     * This function performs the Breadth-First Search from the source to the destination
     * to find the destination of the path from source to destination.
     * @param src the source.
     * @param dest the dest.
     * @return the destination.
     */
    private Coordinates BFS(Coordinates src, Coordinates dest){
    List<Coordinates>queue=new LinkedList<>();
    queue.add(src);
    Map<Coordinates,Coordinates>hash_table=new HashMap<>();
    hash_table.put(src,src);
    for(int i=0;i<queue.size();i++){
    var cur=queue.get(i);
    if(cur.equals(dest)){
    LinkedList<Coordinates>path=buildpath(queue,hash_table,dest);
    System.out.println("\tPath from "+src+" to "+dest+": "+path);
    System.out.println("\tSteps: "+(path.size()-1));
    return dest;
    }
    for(var neighbor : dungeon.getNeighbors(cur)){
    if(hash_table.containsKey(neighbor)){continue;}
    hash_table.put(neighbor,cur);
    queue.add(neighbor);
    }
    }
    System.out.println("\tNo path from "+src+" to "+dest+"!");
    return src;
    }
    
    /**
     * Given a dequeue of traversed nodes and predecessor hash table,
     * we construct a shortest path from the source to the destination.
     * @param deque the deque of traversed nodes.
     * @param hash_table the predecessor map.
     * @param dest the destination.
     * @return an orderedList from start to end of the shortest path.
     */
    private LinkedList<Coordinates>buildpath(List<Coordinates>queue,
    Map<Coordinates,Coordinates>hash_table,Coordinates dest){
    LinkedList<Coordinates>res=new LinkedList<>();
    Coordinates i=dest;
    while(!i.equals(queue.get(0))){
    res.add(0,i);
    i=hash_table.get(i);
    }steps+=(queue.size());
    res.add(0,queue.get(0));
    return res;
    }
    /**
     * The main method.
     *
     * @param args command line arguments (dungeon file)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java DungeonCrawler dungeon-file");
        } else {
            try {
                // construct the dungeon here
                DungeonCrawler dungeonCrawler = new DungeonCrawler(args[0]);
                // perform searches over our dungeon
                dungeonCrawler.findTreasures();
            } catch (IOException | DungeonException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
