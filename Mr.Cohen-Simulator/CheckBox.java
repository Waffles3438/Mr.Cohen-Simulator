import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckBox here.
 * 
 * @author Andy Feng
 * @version 0.0.1
 */
public class CheckBox extends Actor
{

    private GreenfootImage empty = new GreenfootImage("empty.png");
    private GreenfootImage check = new GreenfootImage("check.png");
    private static GreenfootSound[] click;
    private static int clickIndex = 0;
    
    private boolean variable;
    public CheckBox(){
        setImage(empty);
        variable = false;
    }
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            playClick();
            variable = !variable;
            if(getImage() != empty) setImage(empty);
            else setImage(check);
        }
    }
    
    /**
     * Preload sounds
     */
    public static void init(){
        clickIndex = 0;
        click = new GreenfootSound[100];
        for (int i = 0; i < click.length; i++){
            click[i] = new GreenfootSound("click.mp3");
            click[i].play();
            Greenfoot.delay(1);
            click[i].stop();
        }
    }
    
    /**
     * Play click sound
     */
    public void playClick(){
        click[clickIndex].setVolume(50);
        click[clickIndex].play();
        clickIndex++;
        if (clickIndex >= click.length){
            clickIndex = 0;
        }
    }
    
    public boolean updateBoolean(){
        return variable;
    }
}
