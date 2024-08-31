package toys;


/**
 * IToy is an interface for the public behaviors of Toy.
 *
 * @author RIT
 * @author Huy Le
 * 
 */
public interface IToy
{
    /**
     * Returns the unique integer product code of the toy.
     * @return the unique integer product code of the toy.
     */
    
    public int getProductCode();
    
    /**
     * Returns the name of the toy.
     */
    
    public  String getName();
    
    /**
     * Returns the toys happiness 
     * (starts at INITIAL_HAPPINESS and 
     * increases when played with).

     */
    
    public  int getHappiness();
    
    /**
     * A toy is retired when its happiness level 
     * reaches/exceeds MAX_HAPPINESS (starts as not retired).
     */
    
    public  boolean isRetired();
    
    /**
     * Returns the toys current wear amount 
     * (starts at INITIAL_WEAR and increases when played with).
     */
    
    public  double getWear();
    
    /**
     * Increase the wear by the amount passed in.
     */
    
    public  void increaseWear(double amount);
    
    /**
     * 
     * 
     * When played with, the following things happen in order:
     *  1.A playing message is displayed to standard output 
     *  in the format 
     * (## is the number of minutes):
     * PLAYING(##): toy-toString
     * For example:
     * PLAYING(40): Toy{PC:100, N:Play-Doh, H:0, R:false, W:0.0}, 
     * PlayDough{C:GREEN}          
     * 2.The special play is invoked for the same number of minutes.
     * 3.The happiness of the toy increases by the number of minutes.
     * 4.If the happiness of the toy reaches or exceeds MAX_HAPPINESS, 
     * the toy retires and displays a message to standard output in the 
     * format: RETIRED: toy-toString
     * For example:
     * RETIRED: Toy{PC:100, N:Play-Doh, H:101, R:true, 
     *    W:5.050000000000001},
     * PlayDough{C:GREEN}
     */
    
    public void play(int time);
}
