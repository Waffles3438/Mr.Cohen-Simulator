import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Confetti here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Confetti extends Actor
{
    private GreenfootImage[] confettiArrays = new GreenfootImage[100];

    //public GifImage confettiAnimation = new GifImage("confetti.gif");

    public Confetti()
    {
        //confettiAnimation = new GifImage("confetti.gif");
    }

    public void act()
    {
        for (int i = 0; i < confettiArrays.length; i++)
        {
            confettiArrays[i] = new GreenfootImage("frame_000_delay-0.0" + i + "s.gif");
            confettiArrays[i].scale(100, 100);
        }
    }
}