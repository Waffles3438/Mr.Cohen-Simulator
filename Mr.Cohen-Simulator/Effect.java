import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * <p>
 * Loops through sprites to create an animation.
 * Can control speed/size of effect.
 * </p>
 * 
 * @author Evan Xi
 */
public class Effect extends Actor{
    
    // Prepare images and timer
    
    ArrayList<GreenfootImage> images = new ArrayList<>(0);
    SimpleTimer effectTimer = new SimpleTimer();
    
    int speed = 10;
    
    public Effect(String folder, String file, int fileCount, int speed){
            
        // Loads in all images
        images.ensureCapacity(fileCount); 
            
        for(int i = 0; i < fileCount; i++){
            images.add(new GreenfootImage("images/" + folder + "/" + file + i +".png"));
        } 
        
        this.speed = speed;
    }
    
    /*
     * Loops through a frame every 10 milliseconds
     * When complete, delete self from world
     */
    int frame = 0;
    public void act(){    
        if(effectTimer.millisElapsed() > speed){
            frame += 1;
            effectTimer.mark();
            if(frame == images.size()){            
                getWorld().removeObject(this);
            } 
            else{                
                setImage(images.get(frame));
            }
        }
    
    }
}
