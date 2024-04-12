import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <div>Students will walk around in the simulation and doing different tasks</div>
 * They have different IQs
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
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
    private int x;
    private int y;
    private boolean atDesk;
    
    /**
     * Creates a student which an iq close to the given iq
     *
     * @param iq The iq to set the student around at
     */
    public Student(int iq, int x, int y) {
        this.iq = iq + Greenfoot.getRandomNumber(40)-20;
        projectedMark = 70 * iq / 100;
        variation = Greenfoot.getRandomNumber(9) + 1;
        setImage("student" + variation + ".png");
        getImage().scale(60, 60);
        getImage().rotate(90);
        setRotation(-90);
        this.x = x;
        this.y = y;
        atDesk = true;
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
            pathFind(x, y, 0, true);
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
    
    protected void work() {
        
    }
}
