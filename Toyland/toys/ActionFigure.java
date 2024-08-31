package toys;


/**
 * ActionFigure is a concrete class that inherits from Doll.
 *
 * @author HUY LE
 * @author RIT
 */
public class ActionFigure extends Doll
{
    /**
     * The minimum energy level of the action figure.
     */
    
    public final static int MIN_ENERGY_LEVEL=1;
    
    /**
     * The hair color of all actions figures.
     */
    
    public final static Color HAIR_COLOR=Color.ORANGE;
    
    /**
     * The id to keep track of the toy's 
     * product code.
     */

    private static int id=500;
    
    /**
     * The current energy level of action figure.
     */
    
    private int energy_level=MIN_ENERGY_LEVEL;
    
    /**
     * A constructor that takes the name, age and spoken catchphrase.
     * All action figures have HAIR_COLOR (orange) hair. 
     * The unique product code starts at 500 and increases by 
     * 1 each time a new one is created.
     * @param name actionfigure's name.
     * @param age actionfigure's age.
     * @param speak the actionfigure's catchphrase.
     */
    
    protected ActionFigure(String name
    ,int age,String speak){
    super(id,name,HAIR_COLOR,
    age,speak);
    id+=1;
    }
    
    /**
     * Returns the energy level 
     * (starts at MIN_ENERGY_LEVEL and 
     * increases when played with).
     * @return energy_level
     */
    
    public int getEnergyLevel(){
    return this.energy_level;
    }
    
    /**
     * The following things happen in order:
     * 1.A tab indented, "\t", 
     * message is displayed to standard output 
     * in the format (where the energy formula 
     * is computed as: energy * time):
     *{name} kung foo chops with {energy-formula} 
     *energy!
     * For example:
     * He-man kung foo chops with 46 energy!
     * 2.The energy is increased by 1.
     * 3.The special play of Doll is called.
     * @param time the time that the toy is played with.
     */
    
    @Override
    protected void specialPlay(int time){
    System.out.println("\t"+getName()+
    " kung foo chops with "+getEnergyLevel()*time+
    " energy!");
    this.energy_level+=1;
    super.specialPlay(time);
    }
    
    /**
     * Returns a string representation in the format:
     * Toy{PC:?, N:?, H:?, R:?, W:?}, 
     * Doll{HC:?, A:?, S:?}, ActionFigure{E:?}
     * The Toy part of the string comes from 
     * Toy's toString()
     * The Doll part of the string comes from 
     * Doll's toString()
     * E for the energy
     * For example:
     * Toy{PC:500, N:He-man, H:63, R:false, W:60.0}, 
     * Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, 
     * ActionFigure{E:3}
     * @return a string representation in the format.
     */
    
    @Override
    public String toString(){
    return super.toString()+", ActionFigure{E:"
        +getEnergyLevel()+"}";
    }
    
}
