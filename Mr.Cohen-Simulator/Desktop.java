import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Desktop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Desktop extends Computer
{
    
    public Desktop() {
        super();
        durability = 100;
        maxDurability = 100;
        
        deviceImage = new GreenfootImage("Desktop.png");
        // width is 440, height is 245
        deviceImage.scale(400, 325);

        screenImage = new GreenfootImage(deviceImage.getWidth()*57/64, deviceImage.getHeight()*50/90);
        screenImage.setColor(new Color(255, 100, 100));
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
    }
    
    public void setScreen() {
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2, screenY);
        setImage(fullImage);
    }
}
