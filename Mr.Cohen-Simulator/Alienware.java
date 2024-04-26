import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * The Alienware computer
 * </p>
 * 
 * <a href="https://www.vectorstock.com/royalty-free-vector/online-laptop-gaming-game-pixel-art-vector-47158987">Link to art</a>
 * Art by Vectorstock
 * 
 * <a href="https://www.youtube.com/watch?v=Nobn5wAenro&t=4s">Link to sound</a>
 * Sound by Bob Pellerin
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public class Alienware extends Computer
{
    private static GreenfootSound beep = new GreenfootSound("beep.mp3");
    
    /**
     * Creates an alienware laptop to be used in the simulation
     *
     */
    public Alienware() 
    {
        super();
        deviceImage = new GreenfootImage("GamingLaptop.png");
        // width is 440, height is 245
        deviceImage.scale(491, 347);

        screenImage = new GreenfootImage(deviceImage.getWidth()*43/64, deviceImage.getHeight()*50/90);
        screenImage.setColor(new Color(0, 0, 0));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        screenY = 27;
        screenX = 2;
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2+screenX, screenY);
        setImage(fullImage);
        durability = 80;
        maxDurability = 80;
        beep.setVolume(20);
    }
    
    public void addedToWorld(World w) {
        setLocation(1050, 640);
        super.addedToWorld(w);
    }
    
    /**
     * Act - do whatever the Alienware wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(Greenfoot.getRandomNumber(100000) <= Modifier.getchanceOfLaptopBreaking() && durability > 0){
            beep.play();
            setScreen(new GreenfootImage("bsod.png"));
            getWorld().removeObject(mouse);
            //System.out.println("beep!");
            durability = 0;
        }
    }
    
    /**
     * Set beep volume
     * 
     * @param volume New volume
     */
    public static void setBeepVolume(int volume){
        beep.setVolume(volume);
    }
    
    /**
     * Pause sounds
     */
    public static void pauseBeep(){
        beep.pause();
    }
    
    /**
     * Sets a new screen for the computer
     *
     * @param newScreenImage The new screen image
     */
    public void setScreen(GreenfootImage newScreenImage) {
        double newRatio = newScreenImage.getHeight() / (double) newScreenImage.getWidth();
        double oldRatio = screenImage.getHeight() / (double) screenImage.getWidth();
        if (oldRatio >= newRatio) {
            newScreenImage.scale((int)(screenImage.getHeight()*(1/newRatio)), screenImage.getHeight());
        } else {
            newScreenImage.scale(screenImage.getWidth(), (int)(newRatio*screenImage.getWidth()));
        }
        screenImage.drawImage(newScreenImage, screenImage.getWidth()/2-newScreenImage.getWidth()/2, screenImage.getHeight()/2 - newScreenImage.getHeight()/2);
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2+screenX, screenY);
        setImage(fullImage);
    }
}