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
    
    public Computer(int durability, String type) {
        this.durability = durability;
        this.type = type;
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
            return true;
        }
        return false;
    }
}
