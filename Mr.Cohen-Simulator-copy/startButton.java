import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startButton extends Button
{
    /**
     * Act - do whatever the start wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public startButton(String imagePath, int numStates, String imageType){
        super(imagePath, numStates, imageType);
    }
    
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    
    public void action(){
        Greenfoot.setWorld(new Modifier());
        //change this to Greenfoot.setWorld(new Simulator());
    }
}
