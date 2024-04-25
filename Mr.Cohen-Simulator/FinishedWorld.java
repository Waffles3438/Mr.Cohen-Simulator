import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FinishedWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FinishedWorld extends World
{
    private int averageMark;
    /**
     * Constructor for objects of class FinishedWorld.
     * 
     */
    public FinishedWorld(int averageMark)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 400, 1); 
        this.averageMark = averageMark;
    }
    
    public void act(){
        if(averageMark >= 85) {
            endingOne();
        } else if(averageMark >= 65 && averageMark < 85) {
            endingTwo();
        } else {
            endingThree();
        }
    }
    
    private void endingOne(){
        
    }
    
    private void endingTwo(){
        
    }
    
    private void endingThree(){
        
    }
}
