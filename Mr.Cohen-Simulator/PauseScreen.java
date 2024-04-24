import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class PauseScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * edited by Andy Feng
 */
public class PauseScreen extends World
{
    private Button menu = new Button("menu", 3, ".png");
    private TitleScreen titleScreen;
    private Simulator simulator;
    private Button resume = new Button("resume", 3, ".png");
    private ArrayList<Actor> pauseLocation;
    private GreenfootImage overlay = new GreenfootImage("images/overlay.png");
    private GreenfootImage classroom = new GreenfootImage("school_image.png");
    private ValueBox volumeSlider;
    Image soundOnImg = new Image("sound_on.png");; 
    Image soundOffImg = new Image("sound_off.png");;
    protected static int volume;
    /**
     * Constructor for objects of class PauseScreen.
     * 
     */
    public PauseScreen(TitleScreen titleScreen, Simulator simulator, ArrayList<Actor> actors, Fader blackScreen)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1);
        volume = 0;
        addObject(new Panel(), getWidth() / 2, getHeight() / 2);
        this.titleScreen = titleScreen;
        this.simulator = simulator;
        GreenfootImage image = new GreenfootImage(1260, 720);
        image.setColor(new Color(255, 255, 255));
        image.fillRect(0, 0, getWidth(), getHeight());
        image.drawImage(classroom, 0, 0);
        image.setColor(new Color(0, 0, 0));
        image.fillRect(getWidth()/3*2, 0, 5, getHeight());
        image.fillRect(getWidth()/3*2, getHeight()/5*3, getWidth()/3, 5);
        setBackground(image); 
        addObject(menu, getWidth()/2, getHeight()/2);
        addObject(resume, getWidth()/2, getHeight()/2 + 150);
        pauseLocation = actors;
        getActorImage(blackScreen);
        volumeSlider = new ValueBox(0, 100, 35);
        addObject(volumeSlider, getWidth()/2, getHeight()-70);
        
        soundOnImg = new Image(100, 100); 
        addObject(soundOnImg, 100, 100);
        //soundOnImg.setLocation(getWidth()/2, getHeight()/2);
        soundOffImg = new Image(100, 100); 
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
        volume = volumeSlider.getValue();
    }
    
    private void getActorImage(Fader blackScreen){
        for(Actor actor : pauseLocation){
            if (actor == blackScreen) {
                continue;
            }
            GreenfootImage image = new GreenfootImage(actor.getImage());
            
            image.rotate(actor.getRotation());
            image.setTransparency(actor.getImage().getTransparency());
            drawImage(image, actor.getX() - image.getWidth()/2, actor.getY() - image.getHeight()/2);
        }
        if(blackScreen.getWorld() == null) return;
        GreenfootImage fader = new GreenfootImage(blackScreen.getImage());
        fader.setTransparency(blackScreen.getImage().getTransparency());
        drawImage(fader, blackScreen.getX() - fader.getWidth()/2, blackScreen.getY() - fader.getHeight()/2);
    }
    
    private void drawImage(GreenfootImage image, int x, int y){
        getBackground().drawImage(image, x, y);
    }
    
    public static int getVolume()
    {
        return volume;
    }
}
