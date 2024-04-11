import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robber extends Person
{
    public Robber()
    {
        super();
    }
    
    public void act()
    {
        super.act();
        steal();
    }
    
    // Add code to steal laptop
    protected void steal()
    {
        pathFind(360, 150, 70);
    }
    
    
}
