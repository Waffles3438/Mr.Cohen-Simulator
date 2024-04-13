import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** This is the computer class
 * A subclass instance will be seen during the simulation
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Computer extends Actor
{
    protected int durability;
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
        mouse = new Mouse(getX()-screenImage.getWidth()/2, getX()+screenImage.getWidth()/2, getY()-getImage().getHeight()/2+screenY, getY()-getImage().getHeight()/2+screenY+screenImage.getHeight(), 10);
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
}
