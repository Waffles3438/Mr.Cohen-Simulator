import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SteamDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        screenImage.setColor(new Color(255, 100, 100));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth() / 2 - screenImage.getWidth() / 2 - 1, 14);
        setImage(fullImage);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
