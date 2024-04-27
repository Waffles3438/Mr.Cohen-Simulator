import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is an effect that is used by the robber
 * 
 * @author Evan Xi
 * @version 0.0.1
 */
public class Smokescreen extends Fader{
    private int maxTransparency = 250;
    private int minTransparency = 5;
    private int fadeSpeed = 1;
    
    /**
     * Smokescreen Constructor
     *
     * @param image The image
     * @param max The max transparency
     * @param min The min transparency
     * @param speed The speed at which it fades in/out 
     */
    public Smokescreen(String image, int max, int min, int speed){
        super(image, max, min, speed);
        this.getImage().setTransparency(minTransparency);
    }   
    
    
    /**
     * Fades in and out
     *
     */
    public void act(){    
        Simulator world = (Simulator)getWorld();
        fadeInAndOut(200);
        world.setSmoked(false);
    }
}
