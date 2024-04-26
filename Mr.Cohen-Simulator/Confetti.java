import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Confetti here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Confetti extends Actor
{
    public GifImage confettiAnimation = new GifImage("confetti.gif");;
    public Confetti()
    {
        //confettiAnimation = new GifImage("confetti.gif");
    }
    
    public void act()
    {
        setImage(confettiAnimation.getCurrentImage());
    }
}
