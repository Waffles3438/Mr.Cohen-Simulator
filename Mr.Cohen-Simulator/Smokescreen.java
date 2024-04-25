import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Smokescreen here.
 * 
 * @author Evan Xi
 * @version (a version number or a date)
 */
public class Smokescreen extends Fader{
    private int maxTransparency = 250;
    private int minTransparency = 5;
    private int fadeSpeed = 1;
    
    public Smokescreen(String image, int max, int min, int speed){
        super(image, max, min, speed);
        this.getImage().setTransparency(minTransparency);
    }   
    
    
    public void act(){    
        Simulator world = (Simulator)getWorld();
        fadeInAndOut(200);
        world.setSmoked(false);
    }
}
