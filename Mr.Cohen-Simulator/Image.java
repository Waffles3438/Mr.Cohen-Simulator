import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * An actor that displays an image and/or acts as a hitbox for the people pathfinding
 * 
 * Wooden banner: https://en.ac-illust.com/clip-art/1354907/pixelated-banner-set
 */
public class Image extends Actor
{
    /**
     * @param imageName, name of the image
     */
    public Image(String imageName){
        setImage(imageName);
    }
    
    public Image(int width, int height) {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(new Color(0, 0, 0));
        image.drawRect(0, 0, width-1, height-1);
        setImage(image);
    }
}
