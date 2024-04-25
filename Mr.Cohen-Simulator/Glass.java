import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Appears beside a window upon Robber break-in, fades out
 * 
 * @author Evan Xi
 * Art by Evan Xi
 */
public class Glass extends Fader{
    private int maxTransparency = 250;
    private int minTransparency = 5;
    private int fadeSpeed = 1;
    
    public Glass(String image, int max, int min, int speed){
        super(image, max, min, speed);
        this.getImage().setTransparency(minTransparency);
    }   
    
    
    public void act(){        
        fadeOut();
    }
}
