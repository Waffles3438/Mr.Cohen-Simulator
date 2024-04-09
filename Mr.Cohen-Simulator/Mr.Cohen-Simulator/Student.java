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
            if (pathFind(Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(640)+40, 0)) {
                randomMoveCounter = 0;
            } else {
                randomMoveCounter = randomMoveCooldown / 2;
            }
            
        }
    }
}
