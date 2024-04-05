import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Has the logic to detect the mouse relation to the button
 * Calls the function in the subclass
 * 
 * @author Felix Zhao
 * @version 1/18/2023
 */
public class Button extends Actor
{
    //private static GreenfootSound clickEffect = new GreenfootSound("sounds/click_sound.mp3");
    private boolean hover = false;
    private boolean mouseDown = false;
    protected GreenfootImage[] imageStates;
    
    /**
     * The construtor for the Button
     * @param imageStates Where the image is located
     * @param numstates Is how many different states there are of that image (at most 3 are used)
     * @param imageType The file type (.png, .jpeg, etc.) (Include the dot)
     */
    public Button(String imagePath, int numStates, String imageType) {
        GreenfootImage image = new GreenfootImage(imagePath + "_1." + imageType);
        setImage(image);
        imageStates = new GreenfootImage[numStates];
        imageStates[0] = image;
        
        for (int i = 1; i < numStates; i++) {
            imageStates[i] = new GreenfootImage(imagePath + "_" + (i+1) + ".png");
        }
    }
    
    /**
     * Checks the state of the mouse in relation to the button
     * If the mouse is hovering
     * If the mouse is clicked on the button
     * If the mouse let go after clicking on the button
     */
    public void act()
    {
        if (Greenfoot.mouseMoved(this)) {
            hover = true;
            if (imageStates.length > 1) {
                setImage(imageStates[1]);
            }
        } else if (Greenfoot.mouseMoved(null)) {
            hover = false;
            mouseDown = false;
            
            if (imageStates.length > 2) {
                setImage(imageStates[0]);
            }
        }
        
        if (Greenfoot.mousePressed(this) && !mouseDown) {
            mouseDown = true;
            if (imageStates.length > 2) {
                setImage(imageStates[2]);
            }
        }
                                                                                                                                                                                                                                                                       
        if (Greenfoot.mouseClicked(this) && mouseDown) {
            //clickEffect.play();
            action();
            mouseDown = false;
            if (imageStates.length > 2) {
                setImage(imageStates[0]);
            }

        } else if (Greenfoot.mouseClicked(null)) {
            mouseDown = false;
            if (imageStates.length > 2) {
                setImage(imageStates[0]);
            }

        }
    }
    
    /**
     * Sets the scale for the buttons
     * @param width sets the width of the button to width
     * @param height sets the height of the button to height
     */
    public void setScale(int width, int height) {
        for (int i = 0; i < imageStates.length; i++) {
            imageStates[i].scale(width, height);
        }
    }
    
    /**
     * Action does nothing in this class but should be in the subclasses
     */
    public void action() {
        // Does nothing. Should be added in the subclasses
    }
}