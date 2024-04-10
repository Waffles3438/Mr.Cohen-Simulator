import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>This is the puddle class. The puddle class will cause students to slip.
 * Additionally, if the puddle is flying through the air it can also mess up students
 * </p>
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Puddle extends Projectile
{   
    /**
     * Puddle Constructor
     *
     * @param speed The speed of the Obstacle, can be 0
     * @param pointX A The x-position of the point to point towards to
     * @param pointY A The y-position of the point to point towards to
     */
    public Puddle(double speed, int pointX, int pointY) {
        super(speed, pointX, pointY);
    }
    
    /**
     * Act - do whatever the Puddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        Person person = (Person)getOneIntersectingObject(Person.class);
    }
}
