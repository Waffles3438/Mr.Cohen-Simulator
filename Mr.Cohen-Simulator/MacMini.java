import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MacMini here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MacMini extends Computer
{
    public MacMini() {
        super();
        deviceImage = new GreenfootImage("images/MacMini.png");
        //setImage(deviceImage);
        deviceImage.scale(392, 156);
        
        screenImage = new GreenfootImage(deviceImage.getWidth() / 2 + 10, (int) deviceImage.getHeight() * 95 / 117);
        screenImage.setColor(new Color(255, 100, 100));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth() / 2 - screenImage.getWidth() / 2 - 1, 14);
        setImage(fullImage);

        durability = 120;
        maxDurability = 120;
    }
    
    public void addedToWorld(World w) {
        setLocation(1050, 575);
        super.addedToWorld(w);
    }
    
    /**
     * Act - do whatever the MacMini wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        
    }
    
    public void setScreen() {
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth() / 2 - screenImage.getWidth() / 2 - 1, 14);
        setImage(fullImage);
    }
}
