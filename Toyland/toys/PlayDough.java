package toys;


/**
 * PlayDough is a concrete class that inherits from Toy.
 *
 * @author RIT
 * @author HUY LE
 * 
 */
public class PlayDough extends Toy
{
    /**
     * The multiplier of wear amount.
     */
    
    public final static double WEAR_MULTIPLIER=0.05;
    
    /**
     * The id to keep track of the toy's product code.
     */
    private static int id=100;
    
    /**
     * The color of the toy
     */
    
    private Color color;
    
   
    
    /**
     * A constructor that takes the name and color 
     * (specified with the Color enum). 
     * The unique product code starts at 100 and increases 
     * by 1 each time a new one is created.
     * @param name the name of the toy
     * @param color the color of the toy
     */
    
    protected PlayDough(String name,Color color){
    super(id,name);
    this.color=color;
    id+=1;
    }
    
    /**
     * Returns the color.
     * @return the color of the toy.
     */
    
    public Color getColor(){
    return this.color;
    }
    
    /**
     * The following things happen in order:
     * A tab indented, "\t", message is displayed to 
     * standard output in the format:
     * Arts and crafting with {color} {name}
     * For example: Arts and crafting with GREEN Play-Doh
     * The wear increases by a multiple of WEAR_MULTIPLIER * time.
     * @param time the time that the toy is played with.
     */
    
    @Override    
    protected void specialPlay(int time){
    System.out.println("\tArts and crafting with "
        +getColor()+" "+getName());
        increaseWear(WEAR_MULTIPLIER*time);
    }
    
    /**
     * Returns a string representation in the format:
     * Toy{PC:?, N:?, H:?, R:?, W:?}, PlayDough{C:?}
     * The Toy part of the string comes from Toy's toString()
     * C for the color
     * For example:
     * Toy{PC:100, N:Play-Doh, H:40, R:false, W:2.0}, 
     * PlayDough{C:GREEN}
     */
    
    @Override
    public String toString(){
    return super.toString()+", PlayDough{C:"
        +getColor()+"}";
    }
}
