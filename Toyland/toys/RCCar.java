package toys;


/**
 * RCCar is a concrete class that inherits from BatteryPowered.
 *
 * @author HUY LE
 * @author RIT
 */

public class RCCar extends BatteryPowered
{
    
    /**
     * The initial speed of the car.
     */
    
    public final static int STARTING_SPEED=10;
    
    /**
     * The speed increase in the car.
     */
    
    public final static int SPEED_INCREASE=5;
    
    /**
     * The id to keep track of the car's product code.
     */
    private static int id=300;
    
    /**
     * The car's current speed.
     */
    
    private int speed=STARTING_SPEED;
    
    /**
     * A constructor that takes the name and number of batteries. 
     * The unique product code starts at 300 and increases by 
     * 1 each time a new one is created.
     * @param name the car's name.
     * @param numBatteries the car's battery number.
     */
    
    protected RCCar(String name,int numBatteries){
    super(id,name,numBatteries);
    id+=1;
    }
    
    /**
     * Returns the current speed 
     * (starts at STARTING_SPEED and increases when played with).
     * @return speed
     */
    
    public int getSpeed(){
    return this.speed;
    }
    
    /**
     * The following things happen in order:
     * 1. A tab indented, "\t", message is displayed to 
     * standard output in the format: 
     * {name} races around at {speed}mph!
     * For example: Herbie races around at 10mph!
     * 2. The batteries are used for the amount of time.
     * 3. The wear increases by the current speed.
     * 4.The speed increases by SPEED_INCREASE.
     * @param time the time amount in which the car runs.
     */
    
    @Override
    protected void specialPlay(int time){
    System.out.println("\t"+getName()+" races around at "
    +getSpeed()+"mph!");
    useBatteries(time);
    increaseWear(getSpeed());
    this.speed+=SPEED_INCREASE;
    }
    
    /**
     * Returns a string representation in the format:
     * Toy{PC:?, N:?, H:?, R:?, W:?}, 
     * BatteryPowered{BL:?, NB:?}, RCCar{S:?}
     * The Toy part of the string comes from Toy's toString()
     * The BatteryPowered part of the string comes from 
     * BatteryPowered's toString()
     * S for the speed
     * For example:
     * Toy{PC:300, N:Herbie, H:40, R:false, W:10.0}, 
     * BatteryPowered{BL:56, NB:4}, RCCar{S:15}
     * @return a string representation of the RCCar.
     */
    
    @Override
    public String toString(){
    return super.toString()+", RCCar{S:"+getSpeed()+"}"; 
    }
    
}
