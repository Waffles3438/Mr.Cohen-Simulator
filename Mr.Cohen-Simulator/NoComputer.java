import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * This class is used when Mr Cohen computers are all broken.
 * This class makes it more simple to have no computer as it implements all the methods with no use
 * </p>
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NoComputer extends Computer
{
    
    /**
     * Creates a non-existent computer
     *
     */
    public NoComputer() {
        super();
        deviceImage = new GreenfootImage(50, 50);
        // width is 440, height is 245
        deviceImage.scale(440, 245);

        screenImage = new GreenfootImage(50, 50);
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2, screenY);
        setImage(fullImage);
        durability = 0;
        maxDurability = 100;
    }
    
    /**
     * Overides the addedToWorld method in computer so it does not spawn a mouse
     *
     * @param w The world
     */
    public void addedToWorld(World w) {
        
    }
    
    /**
     * Act - do whatever the NoComputer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Overides the method in the superclass. Since this computer does not exist, it does not have a mouse.
     * Thus, this does nothing
     *
     */
    public void deleteMouse() {
        
    }
    
    /**
     * Returns 0 because no computer is nothing
     *
     * @return Always returns 0
     */
    public int getDurability() {
        return 0;
    }
    
    /**
     * This computer subclass cannot be broken
     *
     */
    public void breakComputer() {
        durability = 1;
    }
    
    public void setScreen() {
        
    }
}
