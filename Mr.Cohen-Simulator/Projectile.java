import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All projectiles are a sublcass of this
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public abstract class Projectile extends SuperSmoothMover
{
    protected double speed;
    protected int pointX;
    protected int pointY;
    protected Actor owner;
    /**
     * Projectile Constructor
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
    
    /**
     * This method makes the projectile turn toward the target
     *
     * @param w The world
     */
    public void addedToWorld(World w) {
        turnTowards(pointX, pointY);
    }
    
    /**
     * Moves the projectile and removes it at the edge
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
