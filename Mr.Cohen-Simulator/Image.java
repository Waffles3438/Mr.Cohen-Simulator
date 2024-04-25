import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * An actor that displays an image and/or acts as a hitbox for the people pathfinding
 * 
 * Wooden banner: 
 * <a href="https://en.ac-illust.com/clip-art/1354907/pixelated-banner-set"> Link to image</a>
 * Image by aimu
 * 
 * @author Benny
 * @author Felix
 * @author Andy
 */
public class Image extends Actor
{
    
    private double ratio;
    protected ArrayList<GreenfootImage> list = new ArrayList<GreenfootImage>();
    
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
        //image.drawRect(0, 0, width-1, height-1);
        setImage(image);
        ratio = (double)getImage().getHeight() / getImage().getWidth();
    }
    
    /**
     * Creates an image using GreenfootImage
     *
     * @param image Set the image to the given image
     */
    public Image(GreenfootImage image){
        setImage(image);
        ratio = (double)getImage().getHeight() / getImage().getWidth();
    }
    
    public Image(String name, int numberOfImage, String fileType){
        for(int i = 0; i < numberOfImage; i++){
            list.add(new GreenfootImage(name + (i+1) + fileType));
        }
        for(GreenfootImage image : list){
            image.scale(150, 150);
        }
    }
    
    public void act(){
        
    }
    
    /**
     * Changes the width of the image to x and height is changed according the ratio and x
     *
     * @param x The width of the new image
     */
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
    
    protected void addImage(Image other, int gap){
        setLocation(getX() - gap, getY());
        other.setLocation(getX() + gap, getY());
    }
}
