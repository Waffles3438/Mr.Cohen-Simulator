import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * The screen with credits / the creators
 * </p>
 * 
 * <a href="https://pc98backgrounds.tumblr.com/image/172810349592"> Link to image</a>
 * Image by Tumblr
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
            TitleScreen.setMusicVolume(25);
            TitleScreen.playMusic();
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
        }
    }
    
    /**
     * Stop music when paused
     */
    public void stopped(){
        TitleScreen.pauseMusic();
    }
    
    /**
     * Play music when started
     */
    public void started(){
        TitleScreen.playMusic();
    }
}
