import greenfoot.*;

/**
 * <h2>Class Panel</h2>
 * <b>A class to dislay text and run a restart time while a world is paused.</b>
 * <p>Displays a 'game paused' message until click on; then gives a 'restart in 3...2...1' message before re-activating the paused world.</p>
 */
public class Panel extends Actor
{
    /** 
     * The number of act cycles between each instance of counting down in the time to restart; adjust
     * this value to suit your needs.
     */
    static final int TIME_UNIT=300;
    int timer=3*TIME_UNIT;
    boolean running;
    
    /**
     * Used for getting the dimensions of the world to determine size of image to create.
     *
     * @param world the world to be visually duplicated
     */
    public void addedToWorld(World world)
    {
        int cs=world.getCellSize();
        int w=world.getWidth()*cs, h=world.getHeight()*cs;
        GreenfootImage image=new GreenfootImage(w, h);
        setImage(image);
        updateImage();
    }
    
    /**
     * Builds the image to display.
     */
    private void updateImage()
    {
        GreenfootImage image=getImage();
        int w=image.getWidth(), h=image.getHeight();
        image.setColor(new Color(192, 192, 192, 96));
        image.fill();
    }
}
