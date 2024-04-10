import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is used for hitboxes. People avoid hitboxes
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HitBox extends Actor
{
    public HitBox(int width, int height) {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(new Color(0, 0, 0));
        image.drawRect(0, 0, width-1, height-1);
        setImage(image);
    }
    /**
     * Act - do whatever the HitBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
