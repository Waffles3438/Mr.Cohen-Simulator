import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The janitor
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Janitor extends Person
{
    private int spillChance;
    
    public Janitor()
    {
        super();
        spillChance = Greenfoot.getRandomNumber(101);
    }
    
    public void act()
    {
        
    }
    
    protected void spill()
    {
        
    }
    
    protected void cleanFloor()
    {
        
    }
    
    public int getSpillChance()
    {
        return spillChance;
    }
}
