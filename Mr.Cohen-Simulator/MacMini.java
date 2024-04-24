import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The MacMini computer
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public class MacMini extends Computer
{
    public MacMini() {
        super();
        deviceImage = new GreenfootImage("images/MacMini.png");
        //setImage(deviceImage);
        deviceImage.scale(392, 156);
        
        screenImage = new GreenfootImage(deviceImage.getWidth() / 2 + 10, (int) deviceImage.getHeight() * 95 / 117);
        screenImage.setColor(new Color(0, 0, 0));
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
        fullImage.drawImage(screenImage, deviceImage.getWidth() / 2 - screenImage.getWidth() / 2 - 1, 14);
        setImage(fullImage);
    }
}
