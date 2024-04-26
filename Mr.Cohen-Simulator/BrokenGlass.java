import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * Broken glass can hurt students and damage Mr Cohen's computer possibily.
 * Broken glass is caused by robbers breaking in through the windows
 * </p>
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public class BrokenGlass extends Projectile
{
    private double maxDistacne;
    
    /**
     * BrokenGlass Constructor
     *
     * @param owner The owner / creater of the projectile
     * @param speed The speed of the Obstacle, can be 0
     * @param pointX A The x-position of the point to point towards to
     * @param pointY A The y-position of the point to point towards to
     */
    public BrokenGlass(Actor owner , double speed, int pointX, int pointY) {
        super(owner, speed, pointX, pointY);
        maxDistacne = Greenfoot.getRandomNumber(400)+20;
        setImage("broken_glass.png");
    }
    
    /**
     * Calls super class act and then checks for students / cohen.
     * This projectile also stops moving after a certain distance
     */
    public void act()
    {
        super.act();
        maxDistacne -= speed;
        if (maxDistacne <= 0 && !isTouching(Image.class)) {
            speed = 0;
        }
        
        if (getWorld() == null) {
            return;
        }
        Student student = (Student)getOneIntersectingObject(Student.class);
        MrCohen cohen = (MrCohen)getOneIntersectingObject(MrCohen.class);
        if (student != null && student != owner) {
            student.changeProjectedMark(-10);
            student.daze();
            getWorld().removeObject(this);
        } else if (cohen != null) {
            cohen.daze();
            getWorld().removeObject(this);
        }
    }
}
