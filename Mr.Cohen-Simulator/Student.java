import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <div>Students will walk around in the simulation and doing different tasks</div>
 * They have different IQs
 * 
 * @author Felix Zhao 
 * @author Benny Wang
 * edited by Evan Xi
 * @version 1.0.0
 */
public class Student extends Person
{
    private int iq;
    private int randomMoveCounter = 0;
    private int randomMoveCooldown = 240;
    private int projectedMark;
    private static int variation;
    private boolean counter = false;
    private boolean goingBackToWork = false;
    private int deskX;
    private int deskY;
    private boolean atDesk;
    private boolean isSlipping = false;
    
    /**
     * Creates a student which an iq close to the given iq
     *
     * @param iq The iq to set the student around at
     * @param deskX The x position of the students desk
     * @param deskY The y position of the students desk
     */
    public Student(int iq, int deskX, int deskY) {
        this.iq = iq + Greenfoot.getRandomNumber(40)-20;
        projectedMark = 70 * iq / 100;
        variation = Greenfoot.getRandomNumber(9) + 1;
        setImage("student" + variation + ".png");
        getImage().scale(66, 66);
        getImage().rotate(90);
        setRotation(-90);
        this.deskX = deskX;
        this.deskY = deskY;
        atDesk = true;
    }
    
    /**
     * Act - do whatever the Student wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if(isSlipping){
            for(int i = 0; i < 12; i++){
                setRotation(getRotation() + (i * 5));
                sleepFor(5);
            }
            
        }
        else{                   
            if(Greenfoot.getRandomNumber(100) == 0){
                slowerOrFaster();
            }
            
            if(Simulator.isSmoked()){
                sleepFor(400);
            }
            
            checkFall();
            
            super.act();
            if (currentPath.size() == 0) {
                randomMoveCounter++;
            }
            
            if (randomMoveCounter >= randomMoveCooldown && !goingBackToWork) {
                atDesk = false;
                if (pathFind(Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(640)+40, 0, true)) {
                    randomMoveCounter = 0;
                } else {
                    randomMoveCounter = randomMoveCooldown / 2;
                }
                
            }
            
            if(Greenfoot.getRandomNumber(1000) == 0){
                goingBackToWork = true;
                pathFind(deskX, deskY, 0, true);
            }
            
            if (goingBackToWork && currentPath.size() == 0) {
                goingBackToWork = false;
                atDesk = true;
                setRotation(-90);
                randomMoveCounter = 0;
            }
            // Change to if IQ is a certain amount or greater, so smart students study
            if (atDesk)
            {
                work();
            }
        }
    }
        
        
    
    protected void work() {
        
    }
    
    private void checkFall(){
        if(isTouching(Puddle.class)){
            isSlipping = true;
        }
    }
}
