import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * The title screen of the simulation
 * </p>
 * 
 * <a href="https://pc98backgrounds.tumblr.com/image/172810349592"> Link to image</a>
 * Image by Tumblr
 * 
 * <a href="https://www.youtube.com/watch?v=atgjKEgSqSU"> Link to music</a>
 * Music by C418
 * 
 * @author Benny
 * @version 1.0.0
 */
public class TitleScreen extends World{
    private Button start = new Button("start", 3, ".png");
    private Button credits = new Button("credits", 3, ".png");
    protected Button back = new Button("back", 3, ".png");
    
    protected CreditsScreen creditsScreen;
    protected Modifier modifier;

    public static boolean firstTime = true;
    private static GreenfootSound mainmenu = new GreenfootSound("mainmenu.mp3");
    /**
     * Constructor for TitleScreen
     */
    public TitleScreen(){
        super(1260, 720, 1);
        addObject(start, getWidth()/2, getHeight()/2 + 110);
        addObject(credits, getWidth()/2, getHeight()/2 + 220);
        addObject(new Image("Banner.png"), getWidth()/2, getHeight()/2 - 200);
        if(firstTime){
            Button.init();
            CheckBox.init();
            firstTime = false;
        }
        creditsScreen = new CreditsScreen(this);
        modifier = new Modifier(this);
        mainmenu.setVolume(25);
        mainmenu.playLoop();
    }
    
    public void act(){
        checkButtons();
    }
    
    public void stopped(){
        mainmenu.pause();
    }
    
    public void started(){
        mainmenu.playLoop();
    }
    
    /**
     * Check if buttons are pressed
     */
    private void checkButtons(){
        if(start.isPressed()){
            mainmenu.setVolume(20);
            Greenfoot.setWorld(modifier);
            start.setPressedCondition(false);
        }
        
        if(credits.isPressed()){
            mainmenu.setVolume(15);
            Greenfoot.setWorld(creditsScreen);
            credits.setPressedCondition(false);
        }
    }
    
    /**
     * Plays music
     */
    public static void playMusic(){
        mainmenu.playLoop();
    }
    
    /**
     * Pauses music
     */
    public static void pauseMusic(){
        mainmenu.pause();
    }
    
    public static void setMusicVolume(int volume){
        mainmenu.setVolume(volume);
    }
}
