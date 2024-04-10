import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All projectiles are a sublcass of this
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends SuperSmoothMover
{
    protected double speed;
    /**
     * Obstacle Constructor
     *
     * @param speed The speed of the Obstacle, can be 0
     * @param pointX A The x-position of the point to point towards to
     * @param pointY A The y-position of the point to point towards to
     */
    public Projectile(double speed, int pointX, int pointY) {
        this.speed = speed;
    }
    
    /**
     * Act - do whatever the Obstacle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(speed);
        
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
