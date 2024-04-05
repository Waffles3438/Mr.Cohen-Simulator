import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private Button start = new Start("start", 3, ".png");
    private Button settings = new Settings("settings", 3, ".png");
    private Button credits = new Credits("credits", 3, ".png");
    
    public static boolean firstTime = true;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1);
        addObject(start, getWidth()/2, getHeight()/2);
        addObject(settings, getWidth()/2, getHeight()/2 + 110);
        addObject(credits, getWidth()/2, getHeight()/2 + 220);
        if(firstTime){
            Button.init();
            firstTime = false;
        }
        
    }
    
}
