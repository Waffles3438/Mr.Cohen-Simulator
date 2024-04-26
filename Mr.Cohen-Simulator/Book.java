import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This book is a projectile that moves and can hit students and cohen
 * 
 * @author Felix Zhao
 * @version 0.0.1
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
        setImage("cs_book.png");
    }
    
    
    /**
     * Calls the superclass and then checks if it hits a student/cohen.
     * The book can also break the computer
     */
    public void act()
    {
        super.act();
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
