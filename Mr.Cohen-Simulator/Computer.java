import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * This is the computer class <br>
 * A subclass instance will be seen during the simulation
 * <br>
 * <a href="https://www.youtube.com/watch?v=f8mL0_4GeV0">Link to Sound</a>
 * Sound by Rush
 * 
 * @author Felix Zhao
 * @version 0.0.1
 * 
 */
public abstract class Computer extends Actor
{
    protected int durability;
    protected int maxDurability;
    protected String type;
    protected GreenfootImage deviceImage;
    // change this possbily to a list
    protected GreenfootImage screenImage;
    protected GreenfootImage fullImage;
    private int changeScreenCounter;

    /*
     * Offset of the screen image
     */
    protected int screenX;
    protected int screenY;
    protected Mouse mouse;
    protected static GreenfootSound breaking = new GreenfootSound("computer_breaking.mp3");
    
    public Computer() {
        screenY = 0;
        breaking.setVolume(20);
        screenX = 0;
        changeScreenCounter = 120;
    }
    
    public void addedToWorld(World w) {
        mouse = new Mouse(getX()-screenImage.getWidth()/2+screenX, getX()+screenImage.getWidth()/2+screenX, getY()-getImage().getHeight()/2+screenY, getY()-getImage().getHeight()/2+screenY+screenImage.getHeight(), 15);
        w.addObject(mouse, getX(), getY()-getImage().getHeight()/2+screenY+screenImage.getHeight()/2);
        changeScreenCounter = 180;
        setScreen(new GreenfootImage("screen_" + (Greenfoot.getRandomNumber(4)+1) + ".png"));
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
        changeScreenCounter--;
        if (durability > 0 && changeScreenCounter <= 0) {
            changeScreenCounter = 180;
            setScreen(new GreenfootImage("screen_" + (Greenfoot.getRandomNumber(3)+1) + ".png"));
        }
        //setScreen(new GreenfootImage("screen_1.png"));
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
    
    public abstract void setScreen(GreenfootImage image);
    
    /**
     * Deletes the mouse from the screen
     *
     */
    public void deleteMouse() {
        getWorld().removeObject(mouse);
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
     * Instantly breaks the computer and turns off the screen
     *
     */
    public void breakComputer() {
        breaking.play();
        durability = 0;
        GreenfootImage blackScreen = new GreenfootImage(100, 100);
        blackScreen.setColor(new Color(0, 0, 0));
        blackScreen.fillRect(0, 0, 100, 100);
        setScreen(blackScreen);
    }
    
    /**
     * Makes the computer take some damage
     *
     * @param damage The damage the computer will take
     */
    public void takeDamage(int damage) {
        durability -= damage;
        if (durability <= 0) {
            breakComputer();
        }
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
    
    /**
     * Sets the volume
     *
     * @param volume The new volume
     */
    public static void setBreakingVolume(int volume){
        breaking.setVolume(volume);
    }
}
