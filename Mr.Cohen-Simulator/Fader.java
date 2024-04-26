import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * For simple fade in or fade out effects.
 * Can control speed and max/min transparency
 * </p>
 * Edited by Benny Wang
 * 
 * @author Benny Wang
 */

public class Fader extends Actor{

    private int maxTransparency = 255;
    private int minTransparency = 1;
    private int fadeSpeed = 1;
    private boolean done = false;
    private boolean finished = false;
    
    public Fader(String image, int max, int min, int speed) {
        setImage(image);
        maxTransparency = max;
        minTransparency = min;  
        fadeSpeed = speed;
        this.getImage().setTransparency(minTransparency);
    }

    public void fadeOut(){        
        if(getImage().getTransparency() - fadeSpeed >= minTransparency){
            this.getImage().setTransparency(getImage().getTransparency() - fadeSpeed);
        }
        else{
            getWorld().removeObject(this);
            return;
        }
    }
    
    public void fadeIn(){        
        if(getImage().getTransparency() + fadeSpeed <= maxTransparency){
            this.getImage().setTransparency(getImage().getTransparency() + fadeSpeed);
        }
    }
    

    public void fadeInAndOut(int duration){
        if(!done){
            if(getImage().getTransparency() + fadeSpeed <= maxTransparency){
                this.getImage().setTransparency(getImage().getTransparency() + fadeSpeed);
            }
            
            else{
                done = true;
                sleepFor(duration);

            }
        } else{
            if(getImage().getTransparency() - fadeSpeed >= minTransparency){
                this.getImage().setTransparency(getImage().getTransparency() - fadeSpeed);
            }
            
            else{
                getWorld().removeObject(this);
                return;
            }
        }
    }
    
    public boolean getFinished(){
        return finished;
    }
}