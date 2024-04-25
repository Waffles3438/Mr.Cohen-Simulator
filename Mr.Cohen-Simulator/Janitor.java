import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Walks around the classroom for 6 seconds and leaves a puddle on the ground
 * The puddle 
 * 
 * @author Evan Xi 
 * Art by Evan Xi
 */
public class Janitor extends Person{
    private int spillCount = 0;
    private boolean isCleaning = true;
    private int cleaningTimer = 0;
    
    Puddle puddle = new Puddle("puddle.png", 250, 0, 2);
    
    public Janitor(){
        super();
        spillCount = 0;
        getImage().rotate(90);
    }
    
    public void act(){
        super.act();
        if(isCleaning){
            cleaningTimer++;
            cleanFloor();
            attemptSpill();
        }
        
        exitRoom();
    }
    
    private void attemptSpill(){
        if(spillCount < 3){
            if(Greenfoot.getRandomNumber(60) == 1){
                getWorld().addObject(puddle, this.getX(), this.getY());
                spillCount++;
            }
        }
    }
    
    /**
     * Finds one of 8 random locations to pathfind towards
     */
    private void cleanFloor(){
        int location = Greenfoot.getRandomNumber(8);
        switch(location){
            case 1:
                pathFind(685, 630, 20, false);
                break;
            case 2:
                pathFind(685, 325, 20, false);
                break;
            case 3:
                pathFind(455, 325, 20, false);
                break;
            case 4:
                pathFind(455, 630, 20, false);
                break;                
            case 5:
                pathFind(250, 630, 20, false);
                break;
            case 6:
                pathFind(250, 325, 20, false);
                break;
            case 7:
                pathFind(40, 325, 20, false);
                break;
            case 8:
                pathFind(40, 630, 20, false);
                break;
            default:
                break;
        }
    }
    
    /**
     * If it has been in the room for long enough, immediately override the current path and leave
     */
    private void exitRoom(){
        if(cleaningTimer >= 360){
            isCleaning = false;
            pathFind(820, 585, 20, true);
        }
        if((this.getX() > 780) && (!isCleaning)){
            getWorld().removeObject(this);  
        }
    }
}
