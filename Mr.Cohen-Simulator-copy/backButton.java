import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class backButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class backButton extends Button
{
    /**
     * Act - do whatever the bacl wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public backButton(String imagePath, int numStates, String imageType){
        super(imagePath, numStates, imageType);
    }
    
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    
    public void action(){
        Greenfoot.setWorld(new Modifier());
    }
}
