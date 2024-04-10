import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * <p>
 * The slider which is used to change the value in the modifier world
 * </p>
 * reference: 
 * https://www.vecteezy.com/vector-art/22908990-slider-menu-set-with-different-color-in-pixel-art-style
 * Slider and bar image from vecteezy.com
 * <br>
 * Edited by Felix Zhao
 * 
 * @author Andy Feng
 * @version 1.0 (Apr 5th, 2024)
 * 
 * 
 */
public class Slider extends Actor
{
    
    private MouseInfo mouse;
    private GreenfootImage slider = new GreenfootImage("images/Slider.png");
    private Bar scale;
    private ValueBox valueBox;
    private int leftBoundary;
    private int rightBoundary;
    private String variable;
    private int originalX;
    private boolean isDragging;
    
    private double percent;
    /**
     * the contructor of slider class
     * 
     * @ parameter
     * @ Bar bar: the bar which created it, the bar passed it and the 
     * slider will be one group of slider and bar
     * @ String controlVariable: tell the slider which instance variable
     * in the modifier world it is going to manipulate
     */
    public Slider(ValueBox valueBox, Bar bar){
        setImage(slider);
        slider.scale((int) (getImage().getWidth() * 1.25), (int) (getImage().getHeight() * 1.5));
        scale = bar;
        slider.scale((int) (getImage().getWidth() * 1.25), (int) (getImage().getHeight() * 1.5));
        isDragging = false;
        percent = 0;
        this.valueBox = valueBox;
    }
    
    //just an normal act method
    public void act()
    {
        originalX = scale.getX() - scale.getImage().getWidth() / 2;
        mouse = Greenfoot.getMouseInfo();

        //check if the mouse is dragging the slider
        if (mouse != null) {
            if (Greenfoot.mousePressed(this)) {
                isDragging = true;
            }
            
            if (isDragging) {
                setLocation(mouse.getX(), getY());                
            }

            if (Greenfoot.mouseDragEnded(null) || Greenfoot.mouseClicked(this)) {
                isDragging = false;
            }
        }
        
        boundary();
        changeAndUpdateValue();
    }
    
    /**
     * method which limits the slider to move only on the bar
     */
    private void boundary(){
        leftBoundary = scale.getX() - scale.getImage().getWidth() / 2;
        rightBoundary = scale.getX() + scale.getImage().getWidth() / 2;
        if(getX() < leftBoundary) setLocation(leftBoundary, scale.getY());
        if(getX() > rightBoundary) setLocation(rightBoundary, scale.getY());
    }
    
    /**
     * change and update the value of instance variables in the modifier
     * world by calculating the distance between the current position
     * and the original position
     */
    private void changeAndUpdateValue(){
        Modifier settings = (Modifier) getWorld();
        int distance = getX() - originalX;
        // if(variable.equals("numDays")){
            // int increase = (int) (distance / 6.4);
            // settings.numDays = 10 + increase;
        // } else if(variable.equals("chanceOfLaptopBreaking")){
            // int increase = (int) (distance / 1.706);
            // settings.chanceOfLaptopBreaking = 25 + increase;
        // } else if(variable.equals("studentIQ")){
            // int increase = (int) (distance / 2.13);
            // settings.studentIQ = 60 + increase;
        // }
        percent = (double)distance / scale.getImage().getWidth();
        if (isDragging) {
            valueBox.update(percent);
        }
        
    }
    
    public void setDrag(boolean state) {
        isDragging = state;
    }
    
}
