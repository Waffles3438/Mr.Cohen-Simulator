import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <div>
 * The box used to hold the different modifers 
 * </div>
 * Art from vecteezy.com
 * https://www.vecteezy.com/vector-art/5146435-old-paper-in-pixel-art-style
 * 
 * @author Andy Feng
 * @version 1.0.0
 *
 */
public class Box extends Actor
{
    /**
     * Act - do whatever the Box wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage box = new GreenfootImage("images/Box.png");
    
    /**
     * contructor of box, really just a box
     */
    public Box(){
        setImage(box);
    }
}
