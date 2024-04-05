import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Art from: https://www.vectorstock.com/royalty-free-vectors/back-button-game-ui-vectors
 * 
 */
public class Back extends Button
{
    public Back(String imagePath, int numStates, String imageType){
        super(imagePath, numStates, imageType);
    }
    
    /**
     * Act - do whatever the Back wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act()
    {
        super.act();
    }
    
    /**
     * Action does nothing in this class but should be in the subclasses
     */
    public void action() {
        Greenfoot.setWorld(new TitleScreen());
    }
}
