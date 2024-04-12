import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For simple fade in or fade out effects
 * Can control speed and max/min transparency
 * 
 * @Author Evan Xi
 */

public class Fader extends Actor{
    private int maxTransparency = 250;
    private int minTransparency = 0;
    private int fadeSpeed = 1;
    
    public Fader(String image, int max, int min, int speed){
        setImage(image);
        maxTransparency = max;
        minTransparency = min;  
        fadeSpeed = speed;
        this.getImage().setTransparency(maxTransparency);
    }

    public void fadeOut(){        
        this.getImage().setTransparency(getImage().getTransparency() - fadeSpeed);
        if (this.getImage().getTransparency() < (minTransparency + fadeSpeed) ){
            getWorld().removeObject(this);
        }
    }
    
    public void fadeIn(){        
        this.getImage().setTransparency(getImage().getTransparency() + fadeSpeed);
        if (this.getImage().getTransparency() > (maxTransparency + fadeSpeed) ){
            return;
        }
    }
}