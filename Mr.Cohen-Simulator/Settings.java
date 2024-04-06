import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends Button
{
    public Settings(String imagePath, int numStates, String imageType){
        super(imagePath, numStates, imageType);
    }
    
    /**
     * Act - do whatever the Settings wants to do. This method is called whenever
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
        Greenfoot.setWorld(new SettingsScreen());
    }
}
