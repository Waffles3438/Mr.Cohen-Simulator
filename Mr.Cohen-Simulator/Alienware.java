import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alienware here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alienware extends Computer
{
    
    public Alienware() 
    {
        super();
        deviceImage = new GreenfootImage("laptop_temo.png");
        // width is 440, height is 245
        deviceImage.scale(440, 245);
        
        screenImage = new GreenfootImage(deviceImage.getWidth()*49/64, deviceImage.getHeight()*72/90);
        screenImage.setColor(new Color(255, 100, 100));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2, 18);
        setImage(fullImage);
    }
    
    public void addedToWorld(World w) {
        setLocation(1050, 575);
    }
    
    /**
     * Act - do whatever the Alienware wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void setScreen() {
        
    }
}