import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Confetti appears when students do well
 * 
 * @author Dylan Dinesh
 * @version 0.0.1
 */
public class Confetti extends Actor
{
    private GreenfootImage[] confettiArrays = new GreenfootImage[100];
    private SimpleTimer animationTimer = new SimpleTimer();
    private int imageIndex = 0;
    private int width;
    private int height; 
    
    public Confetti(int width, int height)
    {
        this.width = width;
        this.height = height;
        for (int i = 0; i < confettiArrays.length; i++)
        {
            if (i < 10)
            {
                confettiArrays[i] = new GreenfootImage("images/confetti/frame_00" + i + "_delay-0.01s.png");
            }
            else
            {
                confettiArrays[i] = new GreenfootImage("images/confetti/frame_0" + i + "_delay-0.01s.png");
            }
            confettiArrays[i].scale(width, height);
            //setImage(confettiArrays[i]);
        }
    }
    
    public void act()
    {
        animateConfetti();
    }

    /**
     * Animates confetti
     */
    public void animateConfetti()
    {
        if (animationTimer.millisElapsed() < 100)
        {
            return;
        }
        setImage(confettiArrays[imageIndex]);
        imageIndex = ((imageIndex + 1) % confettiArrays.length);
        animationTimer.mark();
    }
}