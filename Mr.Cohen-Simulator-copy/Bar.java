import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * the scale and bar of the slider
 * When create a bar, a slider will be created together, they are one
 * set. So please keep in mind to create a bar instead of a slider
 * 
 * @ Andy
 * @ version 1.0 (Apr 5th, 2024)
 * 
 * reference: 
 * https://www.vecteezy.com/vector-art/22908990-slider-menu-set-with-different-color-in-pixel-art-style
 * Slider and bar image from vecteezy.com
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
