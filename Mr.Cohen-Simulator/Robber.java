import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Has a chance to enter every day
 * Calls in a smokebomb which stuns people in the classroom upon entrance
 * Goes to the teacher's desk and steals items, increasing the rage bar
 * 
 * 
 * @author Evan Xi 
 * Art by Evan Xi
 */
public class Robber extends Person{
    private boolean hasRobbed = false;
    private boolean hasEntered = false;
    
    Smokescreen smokescreen = new Smokescreen("smokescreen.png", 250, 5, 3); 
    Glass brokenGlass1 = new Glass("broken_glass.png", 250, 5, 3);
    Glass brokenGlass2 = new Glass("broken_glass.png", 250, 5, 3);
    
    public Robber(){
        super();
        getImage().scale(45, 45);              
    }
    
    public void act(){
        super.act();
        if(!hasEntered){
            chooseRandomWindow();
            throwSmokeBomb();
        }
        
        //Constantly pathfind towards teacher's table until reaching it
        if(!hasRobbed){
            pathFind(360, 195, 0, false);
            hasRobbed = true;
        }    
        
        //If at teacher's table, pause for a short time, leave
        if(this.getX() == 360){
            sleepFor(100);
            chooseRandomWindow();
        }
        
        if((this.getX() == 0) && hasRobbed && currentPath.size() == 0){
            getWorld().removeObject(this);
        }
            
    }
    
    /**
     * Throws a smokebomb upon first entrance
     */
    private void throwSmokeBomb(){
        getWorld().addObject(smokescreen, 415, 355);
        ((Simulator)getWorld()).setSmoked(true);  
        hasEntered = true;
    }
    
    /**
     * Selects one of two windows based of a random number
     * If it is the first time entering, call in the broken glass effect
     */
    private void chooseRandomWindow(){
        int windowChoice = Greenfoot.getRandomNumber(2);
        if(windowChoice == 0){
            pathFind(0, 220, 0, true);
            if(!hasRobbed){
                getWorld().addObject(brokenGlass1, 30, 170);
            }
        }
        else if(windowChoice == 1){
            pathFind(0, 510, 0, true);
            if(!hasRobbed){
                getWorld().addObject(brokenGlass2, 30, 450);
            }
            
        }
    }
}
