import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class RightFlipButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RightFlipButton extends Button
{
    /**
     * Act - do whatever the RightFlipButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public RightFlipButton(String imagePath, int numStates, String imageType){
        super(imagePath, numStates, imageType);
    }
    
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    
    /**
     * get all Box, Bar, Slider and Label objects.
     */
    public void action(){
        List<Box> boxes = getWorld().getObjects(Box.class);
        List<Bar> bars = getWorld().getObjects(Bar.class);
        List<Slider> sliders = getWorld().getObjects(Slider.class);
        List<Label> labels = getWorld().getObjects(Label.class);
        List<ValueBox> valueBoxes = getWorld().getObjects(ValueBox.class);
        
        moveObjects(boxes);
        moveObjects(bars);
        moveObjects(sliders);
        moveObjects(labels);
        moveObjects(valueBoxes);
    }
    
    /**
     * move every object passed into this method 1260 pixels right
     */
    private void moveObjects(List <? extends Actor> objects){
        for(Actor object : objects){
            object.setLocation(object.getX() + 1260, object.getY());
        }
    }
}
