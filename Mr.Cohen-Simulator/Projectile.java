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
    protected int pointX;
    protected int pointY;
    protected Actor owner;
    /**
     * Obstacle Constructor
     *
     *
     * @param speed The speed of the Obstacle, can be 0
     * @param pointX A The x-position of the point to point towards to
     * @param pointY A The y-position of the point to point towards to
     */
    public Projectile(Actor owner, double speed, int pointX, int pointY) {
        this.speed = speed;
        this.pointX = pointX;
        this.pointY = pointY;
        this.owner = owner;
    }
    
    public void addedToWorld(World w) {
        turnTowards(pointX, pointY);
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
        } else if (getX() > 840) {
            getWorld().removeObject(this);
        }
    }
}
