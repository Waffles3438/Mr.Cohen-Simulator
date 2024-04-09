import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An actor that stores an integer in a range. The number can be set by typing it in
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ValueBox extends Actor
{
    private final static Color BASIC_FILL = new Color(255, 255, 255);
    
    private Bar bar;
    private int minVal;
    private int maxVal;
    private int currentVal;
    private Label textLabel;
    private int yOffset;
    
    // Pressing variables
    private boolean mouseDown = false;
    private boolean hover = false;
    private boolean isTyping = false;
    private String currentlyTyping = "";
    private int typeCooldown = 20;
    
    public ValueBox(int min, int max, int yOffSet, String controlVariable) {
        minVal = min;
        maxVal = max;
        currentVal = min;
        textLabel = new Label(min, 100);
        setImage(textLabel.getImage());
        bar = new Bar(this, controlVariable);
        this.yOffset = yOffset;
        System.out.println(yOffset);
    }
    
    public void addedToWorld(World w) {
        System.out.println(yOffset);
        w.addObject(bar, getX(), getY()+yOffset);
    }
    /**
     * Act - do whatever the ValueBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        textLabel.setFillColor(BASIC_FILL);
        if (Greenfoot.mouseMoved(this)) {
            hover = true;
            
        } else if (Greenfoot.mouseMoved(null)) {
            hover = false;
            mouseDown = false;
        }
        
        if (Greenfoot.mousePressed(this) && !mouseDown) {
            mouseDown = true;
        }
                                                                                                                                                                                                                                                                       
        if (Greenfoot.mouseClicked(this) && mouseDown) {
            mouseDown = false;
            isTyping = true;
        } else if (Greenfoot.mouseClicked(null)) {
            mouseDown = false;
            isTyping = false;
            currentlyTyping = "";
            textLabel.setValue(currentVal);
            setImage(textLabel.getImage());
        }
        typeCooldown -= 1;
        if (isTyping && typeCooldown <= 0) {
            if (currentlyTyping.length() < 3) {
                if (Greenfoot.isKeyDown("1")) {
                    currentlyTyping += "1";    
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("2")) {
                    currentlyTyping += "2";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("3")) {
                    currentlyTyping += "3";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("4")) {
                    currentlyTyping += "4";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("5")) {
                    currentlyTyping += "5";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("6")) {
                    currentlyTyping += "6";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("7")) {
                    currentlyTyping += "7";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("8")) {
                    currentlyTyping += "8";   
                    typeCooldown = 20;
                } else if (Greenfoot.isKeyDown("9")) {
                    currentlyTyping += "9";   
                } else if (Greenfoot.isKeyDown("0")) {
                    currentlyTyping += "0";   
                    typeCooldown = 20;
                } 
            } 
            if (Greenfoot.isKeyDown("Enter")) {
                isTyping = false;
                int value = Integer.parseInt(currentlyTyping);
                value = Math.max(minVal, value);
                value = Math.min(maxVal, value);
                textLabel.setValue(value);
                currentVal = value;
                currentlyTyping = "";
                typeCooldown = 20;
                
            } else if (Greenfoot.isKeyDown("Escape")) {
                isTyping = false;
                currentlyTyping = "";
                textLabel.setValue(currentVal);
                typeCooldown = 20;
            } else if (Greenfoot.isKeyDown("Backspace") && currentlyTyping.length() > 0) {
                currentlyTyping = currentlyTyping.substring(0, currentlyTyping.length()-1);
                typeCooldown = 20;
            }
            // has to check again because it could be changed
            
            if (isTyping) {
                if (currentlyTyping.equals("")) {
                    textLabel.setFillColor(new Color(100, 100, 100, 50));
                    textLabel.setValue("-");
                } else {
                    textLabel.setValue(currentlyTyping);
                }
            }
            setImage(textLabel.getImage());
        }
        
    }
    
    public void update(double percent) {
        System.out.println(percent);
        currentVal = (int)(minVal*(1-percent)+maxVal*percent);
        textLabel.setValue(currentVal);
        setImage(textLabel.getImage());
    }
}
