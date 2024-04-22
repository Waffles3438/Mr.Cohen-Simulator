import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alienware here.
 * 
 * @author Felix Zhao
 * @version April 11th, 2024
 */
public class Alienware extends Computer
{
    
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
        screenImage.setColor(new Color(255, 100, 100));
        screenImage.fillRect(0, 0, screenImage.getWidth(), screenImage.getHeight());
        fullImage = new GreenfootImage(deviceImage);
        screenY = 27;
        screenX = 2;
        fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2+screenX, screenY);
        setImage(fullImage);
        durability = 80;
        maxDurability = 80;
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
        if(Greenfoot.getRandomNumber(75000) <= Modifier.getchanceOfLaptopBreaking() && durability > 0){
            screenImage = new GreenfootImage("bsod.png");
            fullImage.drawImage(screenImage, deviceImage.getWidth()/2-screenImage.getWidth()/2+screenX, screenY);
            setImage(fullImage);
            getWorld().removeObject(mouse);
            breakComputer();
        }
    }
    
    public void setScreen() {
        
    }
}