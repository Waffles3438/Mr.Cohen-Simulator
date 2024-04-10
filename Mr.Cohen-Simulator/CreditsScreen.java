import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreditsScreen here.
 * 
 * @author Benny
 * @version 1.0.0
 */
public class CreditsScreen extends World
{
    private Button back = new Button("back", 3, ".png");
    private TitleScreen titleScreen;
    /**
     * Constructor for objects of class CreditsScreen.
     * 
     */
    public CreditsScreen(TitleScreen titleScreen)
    {    
        super(1260, 720, 1);
        addObject(back, 75, 75);
        this.titleScreen = titleScreen;
    }
    
    public void act(){
        if(back.isPressed()){
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
            
        }
    }
}
