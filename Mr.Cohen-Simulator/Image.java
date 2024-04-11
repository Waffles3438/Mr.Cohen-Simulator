import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * An actor that displays an image and/or acts as a hitbox for the people pathfinding
 * An actor that has no purpose other than displaying an image
 * 
 * Wooden banner: https://en.ac-illust.com/clip-art/1354907/pixelated-banner-set
 * 
 * @author Benny
 * @author Felix
 * @author Andy
 */
public class Image extends Actor
{
    
    private double ratio;
    
    /**
     * @param imageName, name of the image
     */
    public Image(String imageName){
        setImage(imageName);
        ratio = (double)getImage().getHeight() / getImage().getWidth();
    }
    
    /**
     * Creates image with a width and height
     *
     * @param width Width of image
     * @param height Height of image
     */
    public Image(int width, int height) {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(new Color(0, 0, 0));
        image.drawRect(0, 0, width-1, height-1);
        setImage(image);
        ratio = (double)getImage().getHeight() / getImage().getWidth();
    }
    
    public Image(GreenfootImage image){
        setImage(image);
        ratio = (double)getImage().getHeight() / getImage().getWidth();
        System.out.println(ratio);
    }
    
    public void act(){
        
    }
    
    public void adjustSize(int x){
        getImage().scale(x, (int)(x*ratio));
    }
    
    /**
     * Updates the ratio of the image. Call this when the image gets updated
     *
     */
    public void updateRatio() {
        ratio = (double)getImage().getHeight() / getImage().getWidth();
    }
    
}
