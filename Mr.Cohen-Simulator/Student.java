import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <div>Students will walk around in the simulation and doing different tasks</div>
 * They have different IQs
 * 
 * @author Felix Zhao 
 * @author Benny Wang
 * @version 1.0.0
 */
public class Student extends Person
{
    private int iq;
    private int randomMoveCounter = 0;
    private int randomMoveCooldown = 240;
    private double projectedMark;
    private static int variation;
    private boolean counter = false;
    private boolean goingBackToWork = false;
    private int deskX;
    private int deskY;
    private boolean atDesk;
    private boolean isWorking;
    private int workTimer;
    private boolean isWastingTime;
    private int wasteTimeCounter;
    
    /**
     * Creates a student which an iq close to the given iq
     *
     * @param iq The iq to set the student around at
     * @param deskX The x position of the students desk
     * @param deskY The y position of the students desk
     */
    public Student(int iq, int deskX, int deskY) {
        this.iq = iq + Greenfoot.getRandomNumber(40)-20;
        projectedMark = 60 * iq / 100;
        variation = Greenfoot.getRandomNumber(9) + 1;
        setImage("student" + variation + ".png");
        getImage().scale(66, 66);
        getImage().rotate(90);
        setRotation(-90);
        this.deskX = deskX;
        this.deskY = deskY;
        atDesk = true;
        isWorking = false;
        workTimer = 0;
        isWastingTime = false;
        wasteTimeCounter = 0;
    }
    
    /**
     * Act - do whatever the Student wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.getRandomNumber(100) == 0){
            slowerOrFaster();
        }
        
        super.act();
        if (currentPath.size() == 0) {
            randomMoveCounter++;
        }
        
        if (randomMoveCounter >= randomMoveCooldown && !goingBackToWork && !isWorking) {
            moveRandom();
        }
        
        if(Greenfoot.getRandomNumber(1000) == 0 && !atDesk && !goingBackToWork){
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
        if (atDesk && !isWorking && !isWastingTime) {
            // Added a curve so people with low iq don't study way less
            double chance = Math.sqrt(Greenfoot.getRandomNumber(iq))*10;
            if (chance >= 70) {
                work();
            } else if (chance <= 20) {
                wasteTime();
            }
            
        }
        
        if (workTimer > 0) {
            workTimer -= 1;
        } else if (workTimer == 0) {
            workTimer--;
            getWorld().removeObject(speech);
            speech = null;
            isWorking = false;
            //moveRandom();
        }
        
        if (wasteTimeCounter > 0) {
            wasteTimeCounter --;
        } else if (wasteTimeCounter == 0) {
            wasteTimeCounter --;
            getWorld().removeObject(speech);
            speech = null;
            isWastingTime = false;
        }
    }
    
    protected void work() {
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        isWorking = true;
        workTimer = Greenfoot.getRandomNumber(iq)+50;
        projectedMark += workTimer / 100.0;
        speech = new Fader("study_bubble.png", 255, 0, 10);
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    private void moveRandom() {
        atDesk = false;
        if (pathFind(Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(640)+40, 0, true)) {
            randomMoveCounter = 0;
        } else {
            randomMoveCounter = randomMoveCooldown / 2;
        }
    }
    
    private void wasteTime() {
        isWastingTime = true;
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        speech = new Fader("happy_emotion.png", 255, 0, 10);
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        wasteTimeCounter = Greenfoot.getRandomNumber(80)+40;
        projectedMark -= (double)wasteTimeCounter / iq;
    }
}
