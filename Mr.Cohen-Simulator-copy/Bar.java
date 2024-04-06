import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bar extends Actor
{
    /**
     * Act - do whatever the Bar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage bar = new GreenfootImage("images/bar.png");
    private Slider s;
    
    public Bar(String controlVariable){
        setImage(bar);
        s = new Slider(this, controlVariable);
    }
    
    public void act()
    {
        // Add your action code here.
        getWorld().addObject(s, getX() - getImage().getWidth() / 2, getY());
    }
}
