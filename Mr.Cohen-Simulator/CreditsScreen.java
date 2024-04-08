import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreditsScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreditsScreen extends World
{
    private Button back = new Button("back", 3, ".png");
    
    /**
     * Constructor for objects of class CreditsScreen.
     * 
     */
    public CreditsScreen()
    {    
        super(1260, 720, 1);
        addObject(back, 75, 75);
    }
    
    public void act(){
        if(back.isPressed()){
            Greenfoot.setWorld(TitleScreen.titleScreen);
            back.setPressedCondition(false);
        }
    }
}
