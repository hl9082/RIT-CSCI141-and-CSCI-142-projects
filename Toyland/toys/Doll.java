package toys;


/**
 * Doll is a concrete class that inherits from Toy.
 *
 * @author HUY LE
 * @author RIT
 */

public class Doll extends Toy
{
    
/**
 * The haircolor of the doll.
 */

private Color hairColor;

/**
 * The age of the doll.
 */

private int age;

/**
 * The catchphrase of the doll.
 */

private String speak;

/**
 * The id to keep track of the toy's product code.
 */

private static int id=200;

/**
 * A constructor that takes the name, hair color, age and spoken catchphrase.
 * @param name the name of the doll.
 * @param hairColor the haircolor of the doll.
 * @param age the age of the doll.
 * @param speak the catchphrase of the doll.
 */

protected Doll(String name
    ,Color hairColor, int age,
    String speak){
      super(id,name);
      this.hairColor=hairColor;
      this.age=age;
      this.speak=speak;
      id+=1;
    }

/**
 * A constructor used by subclass ActionFigure that takes its 
 * product code plus all the same arguments as 
 * the first constructor.
 * @param productCode the product code of the doll.
 * @param name the name of the doll.
 * @param hairColor the haircolor of the doll.
 * @param age the age of the doll.
 * @param speak the catchphrase of the doll. 
 */

protected Doll(int productCode,String name,
Color hairColor,int age,String speak){
super(productCode,name);
this.hairColor=hairColor;
      this.age=age;
      this.speak=speak;
}

/**
 * Returns the hair color.
 * @return hairColor
 */

public Color getHairColor(){
return this.hairColor;
}

/**
 * Returns the age.
 * @return age
 */

public int getAge(){
return this.age;
}

/**
 * Returns the catchphrase.
 * @return speak.
 */

public String getSpeak(){
return this.speak;
}

/**
 * The following things happen in order:
 * 1.A tab indented, "\t", message is displayed to standard output in 
 * the format:
 * {name} brushes their {hair color} hair and says, "{speak}"                
 * For example:
 * GabbyGabby brushes their RED hair and says, "Will_you_be_my_friend?"               
 * The wear increases by the age.
 * @param time the amount of time that the doll is played with.
 */

@Override
protected void specialPlay(int time){
System.out.println("\t"+super.getName()+" brushes their "
+getHairColor()+" hair and says, "
+"\""+getSpeak()+"\"");
super.increaseWear(getAge());
}

/**
 * Returns a string representation in the format.
 * @return a string representation in the format.
 */

@Override
public String toString(){
return super.toString()+", Doll{HC:"+getHairColor()
    +", A:"+getAge()+", S:"+getSpeak()+"}";
}

}
