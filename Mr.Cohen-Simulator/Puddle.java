import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Puddle here.
 * 
 * @author Evan Xi 
 * @version 0.0.1
 */
public class Puddle extends Fader{
    private int maxTransparency = 250;
    private int minTransparency = 5;
    private int fadeSpeed = 1;
    
    public Puddle(String image, int max, int min, int speed){
        super(image, max, min, speed);
        this.getImage().setTransparency(minTransparency);
    }   
    
    
    public void act(){        
        fadeInAndOut(500);
    }
}
