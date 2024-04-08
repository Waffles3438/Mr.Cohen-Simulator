import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** This is the computer class
 * A subclass instance will be seen during the simulation
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Computer extends Actor
{
    protected int durability;
    protected String type;
    protected GreenfootImage deviceImage;
    // change this possbily to a list
    protected GreenfootImage screenImage;
    protected GreenfootImage fullImage;
    
    public Computer(double durabilityMultiplier) {
        durability = Greenfoot.getRandomNumber(101);
    }
    
    public Computer() {
        
    }
    
    /**
     * Act - do whatever the Computer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public boolean isBroken()
    {
        if (durability > 0)
        {
            return false;
        }
        return true;
    }
    
    public void setScreen() {
        
    }
}
