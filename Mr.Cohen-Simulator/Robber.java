import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The robber will rob Mr Cohen's current computer
 * 
 * @author Evan Xi 
 * @version 0.0.1
 */
public class Robber extends Person{
    private boolean hasRobbed = false;
    private boolean hasEntered = false;
    
    Smokescreen smokescreen = new Smokescreen("smokescreen.png", 250, 5, 3); 
    
    /**
     * Creates the robber
     *
     */
    public Robber(){
        super();
        getImage().scale(45, 45);
    }
    
    public void act(){
        super.act();
        if(!hasEntered){
            throwSmokeBomb();
        }
        
        if(!hasRobbed){
            pathFind(360, 217, 0, false);
            hasRobbed = true;
        }    
        
        if(this.getX() == 360){
            sleepFor(100);
            chooseRandomWindow();
        }
        
        if((this.getX() == 0) && hasRobbed && currentPath.size() == 0){
            getWorld().removeObject(this);
        }
            
    }
    
    private void throwSmokeBomb(){
        getWorld().addObject(smokescreen, 415, 355);
        ((Simulator)getWorld()).setSmoked(true);  
        hasEntered = true;
    }
    
    private void chooseRandomWindow(){
        int windowChoice = Greenfoot.getRandomNumber(2);
        if(windowChoice == 0){
            pathFind(0, 220, 0, true);
        }
        else if(windowChoice == 1){
            pathFind(0, 510, 0, true);
        }
    }

}
