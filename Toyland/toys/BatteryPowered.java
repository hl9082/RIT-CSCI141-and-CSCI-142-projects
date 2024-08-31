package toys;


/**
 * BatteryPowered is an abstract subclass of Toy. 
 * RCCar and Robot inherit from this class to obtain 
 * its state and behaviors.
 *
 * @author HUY LE
 * @author RIT
 */


public abstract class BatteryPowered extends Toy
{
    
/**
 * The state of being fully charged.
 */

public final static int FULLY_CHARGED=100;

/**
 * The state of being depleted.
 */

public final static int DEPLETED=0;

/**
 * The battery number of the toy.
 */

private int numBatteries;

/**
 * The current battery level.
 */

private int batteryLevel=FULLY_CHARGED;

/**
 * A constructor that takes the product code, name and 
 * number 
 * of batteries.
 * @param productCode
 * @param name
 * @param numBatteries
 */

protected BatteryPowered(int productCode,String name
,int numBatteries){
super(productCode,name);
this.numBatteries=numBatteries;
}

/**
 * Returns the current battery level (starts at FULLY_CHARGED).
 * @return batteryLevel.
 */

public int getBatteryLevel(){
return this.batteryLevel;
}

/**
 * Returns the number of batteries.
 * @return numBatteries.
 */

public int getNumBatteries(){
return this.numBatteries;
}

/**
 * When played with the batteries are used and the 
 * following things happen in order:
 * 1.The battery level is reduced by: time + number-of-batteries.
 * 2.If the battery level is depleted, 
 * the following happens in order:
 *    1.The battery level is capped at DEPLETED
 *    2.A tab indented, "\t", depletion message is 
 *    displayed in the format:DEPLETED:toy-toString
 *    3.The battery level is restored to FULLY_CHARGED.
 *    4.A tab indented, "\t", recharge message is 
 *    displayed in the format:RECHARGED:toy-toString
 *@param the time that the batteries are used.
 */

public void useBatteries(int time){
this.batteryLevel-=(time+getNumBatteries());
if(this.batteryLevel<=DEPLETED){
    this.batteryLevel=DEPLETED;
System.out.println("\tDEPLETED:"+toString());
this.batteryLevel=FULLY_CHARGED;
System.out.println("\tRECHARGED:"+toString());
}
}

/**
 *  Returns a string representation in the format:
 *  Toy{PC:?, N:?, H:?, R:?, W:?}, BatteryPowered{BL:?, NB:?}
 * The Toy part of the string comes from Toy's toString()
 *     BL for the battery level
 *     NB for the number of batteries
 * For example:
 * Toy{PC:300, N:Herbie, H:101, R:true, W:45.0}, 
 * BatteryPowered{BL:100, NB:4}
 */

@Override
public String toString(){
return super.toString()+", BatteryPowered{BL:"
    +getBatteryLevel()+", NB:"+getNumBatteries()+"}";
}
}
