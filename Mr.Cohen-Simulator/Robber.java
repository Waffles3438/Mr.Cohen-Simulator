import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robber here.
 * 
 * @author Evan Xi 
 * @version (a version number or a date)
 */
public class Robber extends Person{
    private boolean hasRobbed = false;
    private boolean hasEntered = false;
    
    Smokescreen smokescreen = new Smokescreen("smokescreen.png", 250, 5, 3); 
    
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
        
        if(!hasRobbed){
            pathFind(360, 195, 0, false);
        }    
        
        if(this.getX() == 360){
            hasRobbed = true;
            sleepFor(100);
            chooseRandomWindow();
        }
        
        if((this.getX() == 0) && hasRobbed){
            getWorld().removeObject(this);
        }
            
    }
    
    private void throwSmokeBomb(){
        getWorld().addObject(smokescreen, 415, 355);
        Simulator.setSmoked(true);  
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
