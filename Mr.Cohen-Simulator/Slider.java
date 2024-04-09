import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Slider extends Actor
{
    /**
     * The slider which is used to change the value in the modifier world
     * 
     * @ Andy Feng
     * @ version 1.0 (Apr 5th, 2024)
     * 
     * reference: 
     * https://www.vecteezy.com/vector-art/22908990-slider-menu-set-with-different-color-in-pixel-art-style
     * Slider and bar image from vecteezy.com
     */
    private MouseInfo mouse;
    private GreenfootImage slider = new GreenfootImage("images/Slider.png");
    private Bar scale;
    private int leftBoundary;
    private int rightBoundary;
    private String variable;
    private int originalX;
    private boolean isDragging;
    private boolean valueUpdate;
    
    public Slider(Bar bar, String controlVariable){
        setImage(slider);
        slider.scale((int) (getImage().getWidth() * 1.25), (int) (getImage().getHeight() * 1.5));
        scale = bar;
        variable = controlVariable;
        isDragging = false;
        valueUpdate = false;
    }
    
    public void act()
    {
        originalX = scale.getX() - scale.getImage().getWidth() / 2;
        mouse = Greenfoot.getMouseInfo();
        
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
    
    private void boundary(){
        leftBoundary = scale.getX() - scale.getImage().getWidth() / 2;
        rightBoundary = scale.getX() + scale.getImage().getWidth() / 2;
        if(getX() < leftBoundary) setLocation(leftBoundary, scale.getY());
        if(getX() > rightBoundary) setLocation(rightBoundary, scale.getY());
    }
    
    private void changeAndUpdateValue(){
        Modifier settings = (Modifier) getWorld();
        int distance = getX() - originalX;
        if(variable.equals("numDays")){
            int increase = (int) (distance / 6.4);
            settings.numDays = 10 + increase;
        } else if(variable.equals("chanceOfLaptopBreaking")){
            int increase = (int) (distance / 1.706);
            settings.chanceOfLaptopBreaking = 25 + increase;
        } else if(variable.equals("studentIQ")){
            int increase = (int) (distance / 2.13);
            settings.studentIQ = 60 + increase;
        }
    }
    
    public void setDrag(boolean state) {
        isDragging = state;
    }
}
