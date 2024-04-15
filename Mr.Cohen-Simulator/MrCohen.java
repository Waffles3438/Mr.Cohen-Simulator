import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MrCohen here.
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
 */
public class MrCohen extends Person
{
    private int daysSinceAngry; 
    private Computer computer;
    
    public MrCohen(Computer computer) {
        this.computer = computer;
    }
    
    public void act() {
        
    }
    
    public int getDaysSinceAngry()
    {
        return daysSinceAngry;
    }
    
}
