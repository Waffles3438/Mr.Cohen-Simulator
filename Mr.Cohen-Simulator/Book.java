import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Book here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book extends Projectile
{
    
    /**
     * Book Constructor
     *
     * @param owner The owner
     * @param speed The speed
     * @param pointX The x target
     * @param pointY The y target
     */
    public Book(Actor owner, double speed, int pointX, int pointY) {
        super(owner, speed, pointX, pointY);
    }
    
    
    /**
     * Act - do whatever the Book wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if (getWorld() != null) {
            return;
        }
        Student student = (Student)getOneIntersectingObject(Student.class);
        if (student != null && student != owner) {
            student.changeProjectedMark(-10);
            
        }
    }
}
