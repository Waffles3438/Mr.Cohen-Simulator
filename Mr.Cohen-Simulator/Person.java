import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.PriorityQueue;

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends SuperSmoothMover
{
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void pathFind(int targetX, int targetY) {
        int currentX = getX()/5*5;
        int currentY = getY()/5*5;
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
    }
    
    public void pathFind(Actor actor) {
        
    }
}
