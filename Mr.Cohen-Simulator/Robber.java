import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

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
        speed = 2.5;
        getImage().rotate(90);
    }
    
    /**
     * Controls the logic of the robber
     * 
     */
    public void act(){
        super.act();
        if(!hasEntered){
            throwSmokeBomb();
            int shardsCap = 3;
            
            if (((Simulator)getWorld()).chaosEnabled()) {
                shardsCap *= 2;
            }
            for (int i = 0; i < shardsCap; i++) {
                getWorld().addObject(new BrokenGlass(this, 2, Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(600)+60), getX(), getY());
            }
        }
        
        if(!hasRobbed){
            pathFind(360, 217, 0, false);
            hasRobbed = true;
        }    
        
        if(this.getX() == 360){
            ArrayList<MrCohen> cohenList = (ArrayList<MrCohen>)getWorld().getObjects(MrCohen.class);
            if (cohenList.size() > 0) {
                cohenList.get(0).getRobbed();
            }
            
            sleepFor(100);
            chooseRandomWindow();
        }
        
        if((this.getX() == 0) && hasRobbed && currentPath.size() == 0){
            int shardsCap = 3;
            if (((Simulator)getWorld()).chaosEnabled()) {
                shardsCap *= 2;
            }
            for (int i = 0; i < shardsCap; i++) {
                getWorld().addObject(new BrokenGlass(this, 5, Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(600)+60), getX(), getY());
            }
            getWorld().removeObject(this);
            
        }
            
    }
    
    /**
     * Throws a smoke bomb
     */
    private void throwSmokeBomb(){
        getWorld().addObject(smokescreen, 415, 355);
        ((Simulator)getWorld()).setSmoked(true);  
        hasEntered = true;
    }
    
    /**
     * Choses a random windo to leave from
     */
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
