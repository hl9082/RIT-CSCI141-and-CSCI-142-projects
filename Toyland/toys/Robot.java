package toys;


/**
 * Robot is a concrete class that inherits from BatteryPowered.
 *
 * @author HUY LE
 * @author RIT
 */
public class Robot extends BatteryPowered
{
    
    /**
     * The flying speed of the robot.
     */
    
    public static final int FLY_SPEED=25;
    
    /**
     * The running speed of the robot.
     */
    
    public static final int RUN_SPEED=10;
    
    /**
     * The robot's initial speed.
     */
    
    public static final int INITIAL_SPEED=0;
    
    /**
     * The id to keep track of the car's product code.
     */
    
    private static int id=400;
    
    /**
     * To check whether the robot is flying.
     */
    
    private boolean flying;
    
    /**
     * The current distance of the robot, starting at INITIAL_SPEED.
     */
    
    private int distance=INITIAL_SPEED;
    
    /**
     * A constructor that takes the name, 
     * number of batteries and whether it flies or not. 
     * The unique product code starts at 400 and increases by 
     * 1 each time a new one is created.
     * @param name the name of the robot.
     * @param numBatteries the batteries number of the robot.
     * @param flying to check whether the robot is flying.
     */
    
    protected Robot(String name,
    int numBatteries,boolean flying){
    super(id,name,numBatteries);
    this.flying=flying;
    this.id+=1;
    }
    
    /**
     * Returns whether it flies or not.
     * @param flying.
     */
    
    public boolean isFlying(){
    return this.flying;
    }
    
    /**
     * Returns the total distance traveled 
     * when played with (starts at INITIAL_SPEED).
     * @return distance.
     */
    
    public int getDistance(){
    return this.distance;
    }
    
    /**
     * The following things happen in order:
     * 1. If flying:
     *    1. The distance is increased by time * FLY_SPEED
     *    2. A tab indented, "\t", message is displayed to standard output 
     *    in the format:
     *               {name} is flying around with total distance: {distance}
                
     *     For example:
     *           Voltron is flying around with total distance: 2675
                
     *    3.The wear increases by FLY_SPEED
     *2.If not flying:
     *     1.The distance is increased by time * RUN_SPEED
     *     2.A tab indented, "\t", message is displayed to standard 
     *     output in the format:
                    {name} is running around with total distance: {distance}
                
     *     For example:
                    Wall-E is running around with total distance: 700
                
     *     3.The wear increases by RUN_SPEED
     *3.The batteries are used for the amount of time.
     *@param time the time the robot is played with.
     */
    
    protected void specialPlay(int time){
    if(isFlying()){
    this.distance+=(time*FLY_SPEED);
    System.out.println("\t"+getName()
    +" is flying around with total distance: "+getDistance());
    increaseWear(FLY_SPEED);
    }
    else{
    this.distance+=(time*RUN_SPEED);
    System.out.println("\t"+getName()
    +" is running around with total distance: "+getDistance());
    increaseWear(RUN_SPEED);
    }
    useBatteries(time);
    }
    
    /**
     * Returns a string representation in the format:
     *   Toy{PC:?, N:?, H:?, R:?, W:?}, BatteryPowered{BL:?, NB:?},  Robot{F:?, D:?}
     * The Toy part of the string comes from Toy's toString()
     * The BatteryPowered part of the string comes from BatteryPowered's toString()
     * F for flying or not (true or false)
     * D for distance
     * For example:
     * Toy{PC:403, N:IronGiant, H:73, R:false, W:50.0}, 
     * BatteryPowered{BL:3, NB:12}, Robot{F:true, D:1825}
     * @return the string representation of the robot.
     */
    
    @Override
    public String toString(){
    return super.toString()+", Robot{F:"
        +isFlying()+", D:"+getDistance()+"}";
    }
}
