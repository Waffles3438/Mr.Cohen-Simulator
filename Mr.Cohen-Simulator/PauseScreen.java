import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseScreen extends World
{
    private Button menu = new Button("menu", 3, ".png");
    private TitleScreen titleScreen;
    private Simulator simulator;
    private Button resume = new Button("resume", 3, ".png");
    
    /**
     * Constructor for objects of class PauseScreen.
     * 
     */
    public PauseScreen(TitleScreen titleScreen, Simulator simulator)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1);
        this.titleScreen = titleScreen;
        this.simulator = simulator;
        addObject(menu, getWidth()/2, getHeight()/2);
        addObject(resume, getWidth()/2, getHeight()/2 + 150);
    }
    
    public void act(){
        if(menu.isPressed()){
            Greenfoot.setWorld(titleScreen);
            menu.setPressedCondition(false);
        }
        if(resume.isPressed()){
            Greenfoot.setWorld(simulator);
            resume.setPressedCondition(false);
        }
    }
}
