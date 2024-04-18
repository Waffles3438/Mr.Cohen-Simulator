import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * This is the computer class
 * A subclass instance will be seen during the simulation
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public class Computer extends Actor
{
    protected int durability;
    protected int maxDurability;
    protected String type;
    protected GreenfootImage deviceImage;
    // change this possbily to a list
    protected GreenfootImage screenImage;
    protected GreenfootImage fullImage;

    /*
     * These are for the mouse
     * There is no screenX as the computers should be symmetrical on the x-axis
     */
    protected int screenY;
    protected Mouse mouse;
    
    
    public Computer() {
        
    }
    
    public void addedToWorld(World w) {
        mouse = new Mouse(getX()-screenImage.getWidth()/2, getX()+screenImage.getWidth()/2, getY()-getImage().getHeight()/2+screenY, getY()-getImage().getHeight()/2+screenY+screenImage.getHeight(), 15);
        w.addObject(mouse, getX(), getY()-getImage().getHeight()/2+screenY+screenImage.getHeight()/2);
    }

    
    /**
     * Act - do whatever the Computer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        int screenLeftBound = getX()-screenImage.getWidth()/2;
        int screenRightBound = getX()+screenImage.getWidth()/2;
        int screenTopBound = screenY-screenImage.getHeight()/2;
        int screenBottomBound = screenY+screenImage.getHeight()/2;
    }
    
    /**
     * Method isBroken
     *
     * @return Returns true if computer is broken
     */
    public boolean isBroken()
    {
        if (durability > 0)
        {
            return false;
        }
        return true;
    }
    
    public void setScreen() {
        
    }
    
    /**
     * Returns the durability of the computer
     *
     * @return Returns the durability
     */
    public int getDurability() {
        return durability;
    }
    
    /**
     * Instantly breaks the computer
     *
     */
    public void breakComputer() {
        durability = 0;
    }
    
    /**
     * Fixes the computer with a random durability ranging from 5 to default for the computer
     *
     */
    public void fixComputer() {
        durability = Greenfoot.getRandomNumber(maxDurability) + 6;
    }
    
    
    /**
     * Method getMaxDurability
     *
     * @return Returns the max durability of the computer
     */
    public int getMaxDurability() {
        return maxDurability;
    }
}
