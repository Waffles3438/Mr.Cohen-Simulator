import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The title screen of the simulation
 * 
 * Image from: https://pc98backgrounds.tumblr.com/image/172810349592
 * 
 * @author Benny
 * @version 1.0.0
 * 
 * 
 */
public class TitleScreen extends World{
    private Button start = new Button("start", 3, ".png");
    private Button credits = new Button("credits", 3, ".png");
    protected Button back = new Button("back", 3, ".png");
    
    protected CreditsScreen creditsScreen;
    protected Modifier modifier;

    public static boolean firstTime = true;
    
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
    }
    
    public void act(){
        checkButtons();
    }
    
    /**
     * Check if buttons are pressed
     */
    private void checkButtons(){
        if(start.isPressed()){
            Greenfoot.setWorld(modifier);
            modifier.startFromFirstPage();
            start.setPressedCondition(false);
        }
        
        if(credits.isPressed()){
            Greenfoot.setWorld(creditsScreen);
            credits.setPressedCondition(false);
        }
    }
}
