import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SteamDeck here.
 * 
 * @author Felix
 * @author Andy
 * @version 0.0.1
 */
public class Steamdeck extends Computer
{
    /**
     * Act - do whatever the Steamdeck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Steamdeck(){
        super();
        deviceImage = new GreenfootImage("images/SteamDeck.png");
        //setImage(deviceImage);
        deviceImage.scale(392, 156);
        
        screenImage = new GreenfootImage(deviceImage.getWidth() / 2 + 10, (int) deviceImage.getHeight() * 95 / 117);
        screenImage.setColor(new Color(0, 0, 0));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        screenY = 14;
        screenX = -1;
        fullImage.drawImage(screenImage, deviceImage.getWidth() / 2 - screenImage.getWidth() / 2 - 1, screenY);
        
        setImage(fullImage);
        durability = 50;
        maxDurability = 50;
    }
    
    public void addedToWorld(World w) {
        setLocation(1050, 575);
        super.addedToWorld(w);
    }
    
    public void act()
    {
        super.act();
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
        fullImage.drawImage(screenImage, deviceImage.getWidth() / 2 - screenImage.getWidth() / 2 - 1, screenY);
        setImage(fullImage);
    }
}
