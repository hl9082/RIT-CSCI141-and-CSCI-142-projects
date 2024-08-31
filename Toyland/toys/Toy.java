package toys;


/**
 * Toy is the abstract superclass of the toy inheritance hierarchy.
 *
 * @author HUY LE
 * @author RIT
 */
public abstract class Toy implements IToy
{
    /**
     * The initial happiness of the toys
     */
    
    public final static int INITIAL_HAPPINESS=0;
    
    /**
     * The max happiness of the toys
     */
    
    public final static int MAX_HAPPINESS=100;
    
    /**
     * The initial wear amount of the toys
     */
    
    public final static double INITIAL_WEAR=0;
    
    /**
     * The unique product code of the toy.
     */
    
    private int productCode;
    
    /**
     * the name of the toy
     */
    
    private String name;
    
    /**
     * the toys happiness
     */
    
    private int happiness;
    
    /**
     * The toys current wear amount.
     */
    
    private double wear=INITIAL_WEAR;
    
    /**
     * A constructor that takes the product code and
     * name.
     * @param productCode the unique 
     * integer product code of the toy.
     * @param name the name of the toy
     */
    
    protected Toy(int productCode, String name){
    this.productCode=productCode;
    this.name=name;
    }
    
    /**
     * Returns the unique integer product code of the toy.
     * @return the unique integer product code of the toy.
     */
    
    @Override
    public int getProductCode(){
    return this.productCode;
    }
    
    /**
     * Returns the toys happiness 
     * (starts at INITIAL_HAPPINESS and 
     * increases when played with).
     * @return the toy's happiness.
     */
    
    @Override
    public int getHappiness(){
    return this.happiness;
    }
    
    /**
     *  A toy is retired when its happiness level reaches/exceeds 
     *  MAX_HAPPINESS (starts as not retired).
     *  @return whether the toy's happiness equals MAX_HAPPINESS.
     */
    
    /**
     * Return the name of the toy.
     */
    
    @Override
    public String getName(){
    return this.name;
    }
    
    @Override
    public boolean isRetired(){
    return getHappiness()>=this.MAX_HAPPINESS;
    }
    
    /**
     * Returns the toys current wear amount 
     * (starts at 
     * INITIAL_WEAR and increases when played with).
     * @return the toys current wear amount.
     */
    
    @Override
    public double getWear(){
    return this.wear;
    }
    
    /**
     * Increase the wear by the amount passed in.
     * @param amount the wear amount passed in.
     */
    
    @Override
    public void increaseWear(double amount){
    this.wear+=amount;
    }
    
    /**
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
     * @param time the amount of time that the toy is played with.
     */
    
    public void play(int time){
    System.out.println("PLAYING("+time+"): "
    +toString());
    specialPlay(time);
    this.happiness+=time;
    if(isRetired()){
    System.out.println("RETIRED: "+toString());
    }
    
    }
    
    /**
     * Each toy has its own defined behavior when being played 
     * with and is therefore abstract here.
     * @param time the time amount that the toy is played with
     */
    
    protected abstract void specialPlay(int time);
    
    /**
     * Returns a string representation of the toy in the format 
     * (must be exact):
     * Toy{PC:?, N:?, H:?, R:?, W:?}       
     * PC for product code
     * N for name
     * H for current happiness
     * R for retired (true or false)
     * W for current wear
     * For example:
     *  Toy{PC:100, N:Play-Doh, H:40, R:false, W:2.0}
     *  @return a string representation of the toy.
     */
    
    @Override
    public String toString(){
    return "Toy{PC:"+getProductCode()+", N:"+getName()
        +", H:"+getHappiness()+", R:"+isRetired()
        +", W:"+getWear()+"}";
    }

}
