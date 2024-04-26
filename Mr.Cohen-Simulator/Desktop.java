import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * The desktop computer
 * </p>
 * 
 * <a href="https://www.vectorstock.com/royalty-free-vector/desktop-monitor-pc-game-pixel-art-vector-47159299">Art from Vectorstock</a>
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public class Desktop extends Computer
{
    
    /**
     * Creates the desktop with the right screen size
     *
     */
    public Desktop() {
        super();
        durability = 100;
        maxDurability = 100;
        
        deviceImage = new GreenfootImage("Desktop.png");
        deviceImage.scale(400, 325);

        screenImage = new GreenfootImage(deviceImage.getWidth()*57/64, deviceImage.getHeight()*50/90);
        screenImage.setColor(new Color(0, 0, 0));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        screenY = 10;
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2, screenY);
        setImage(fullImage);

    }
    
    public void addedToWorld(World w) {
        setLocation(1050, 610);
        super.addedToWorld(w);
    }
    
    /**
     * Act - do whatever the Decktop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
        if(Greenfoot.getRandomNumber(100000) <= Modifier.getchanceOfLaptopBreaking() && durability > 0){
            setScreen(new GreenfootImage("bsod.png"));
            getWorld().removeObject(mouse);
            durability = 0;
        }
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
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2, screenY);
        setImage(fullImage);
    }
}
