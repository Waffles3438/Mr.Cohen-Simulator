import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Janitor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Janitor extends Person
{
    private int spillChance;
    
    public Janitor()
    {
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
    
    protected int getSpillChance()
    {
        return spillChance;
    }
}
