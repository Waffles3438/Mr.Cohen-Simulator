import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Image from: https://pc98backgrounds.tumblr.com/image/172810349592
 * 
 */
public class TitleScreen extends World{
    private Button start = new Button("start", 3, ".png");
    private Button settings = new Button("settings", 3, ".png");
    private Button credits = new Button("credits", 3, ".png");
<<<<<<< HEAD
    
=======
    private Modifier modifier;
>>>>>>> AndyFeng
    public static boolean firstTime = true;
    
    public TitleScreen(){
        super(1260, 720, 1);
        addObject(start, getWidth()/2, getHeight()/2);
        addObject(settings, getWidth()/2, getHeight()/2 + 110);
        addObject(credits, getWidth()/2, getHeight()/2 + 220);
        addObject(new Image("Banner.png"), getWidth()/2, getHeight()/2 - 200);
        if(firstTime){
            Button.init();
            firstTime = false;
        }
<<<<<<< HEAD
=======
        
        modifier = new Modifier(this);
>>>>>>> AndyFeng
    }
    
    public void act(){
        checkButtons();
    }
    
    private void checkButtons(){
        if(start.isPressed()){
            Greenfoot.setWorld(new Simulator());
        }
        if(settings.isPressed()){
<<<<<<< HEAD
            Greenfoot.setWorld(new SettingsScreen());
=======
            Greenfoot.setWorld(modifier);
            settings.setPressedCondition(false);
>>>>>>> AndyFeng
        }
        if(credits.isPressed()){
            Greenfoot.setWorld(new CreditsScreen());
        }
    }
}
