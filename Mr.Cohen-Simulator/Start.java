import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends Button
{
    public Start(String imagePath, int numStates, String imageType){
        super(imagePath, numStates, imageType);
    }
    
    /**
     * Act - do whatever the Start wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    
    /**
     * Action does nothing in this class but should be in the subclasses
     */
    public void action() {
        Greenfoot.setWorld(new Simulator());
    }
}
