import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * art from vecteezy.com
 * https://www.vecteezy.com/vector-art/5146435-old-paper-in-pixel-art-style
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
