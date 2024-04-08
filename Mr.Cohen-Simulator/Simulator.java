import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Simulator here.
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
 */
public class Simulator extends World
{
    private Button back = new Button("back", 3, ".png");
    
    /**
     * Constructor for objects of class Simulator.
     * 
     */
    public Simulator()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1); 
        GreenfootImage image = new GreenfootImage(1280, 720);
        image.setColor(new Color(255, 255, 255));
        image.fillRect(0, 0, getWidth(), getHeight());
        image.setColor(new Color(0, 0, 0));
        image.fillRect(getWidth()/3*2, 0, 5, getHeight());
        image.fillRect(getWidth()/3*2, getHeight()/5*3, getWidth()/3, 5);
        setBackground(image);
        addObject(back, 75, 75);
    }
    
    public void act(){
        if(back.isPressed()){
            Greenfoot.setWorld(TitleScreen.titleScreen);
            back.setPressedCondition(false);
        }
    }
}
