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
    private int randomMoveCooldown = 180;
    private int projectedMark;
    private static int variation;
    
    /**
     * Creates a student which an iq close to the given iq
     *
     * @param iq The iq to set the student around at
     */
    public Student(int iq) {
        this.iq = iq + Greenfoot.getRandomNumber(40)-20;
        projectedMark = 70 * iq / 100;
        variation = Greenfoot.getRandomNumber(9) + 1;
        setImage("student" + variation + ".png");
        getImage().scale(50, 50);
        getImage().rotate(90);
        variation++;
    }
    
    /**
     * Act - do whatever the Student wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
        if (currentPath.size() == 0) {
            randomMoveCounter++;
        }
        if (randomMoveCounter >= randomMoveCooldown) {
            if (pathFind(Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(640)+40, 0, true)) {
                randomMoveCounter = 0;
            } else {
                randomMoveCounter = randomMoveCooldown / 2;
            }
            
        }
        
        
        // Change to if IQ is a certain amount or greater, so smart students study
        if (true)
        {
            work();
        }
    }
    
    protected void work() {
        
    }
}
