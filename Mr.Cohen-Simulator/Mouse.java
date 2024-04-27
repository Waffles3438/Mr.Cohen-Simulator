import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The mouse class is paired up with the computer class and will move around randomly<br>
 * 
 * 
 * @author Felix Zhao
 * @author Andy Feng
 * @version (a version number or a date)
 * 
 */
public class Mouse extends SuperSmoothMover
{
    private GreenfootSound mouseClick = new GreenfootSound("sounds/mouseClick.mp3");
    private GreenfootImage mouseIcon = new GreenfootImage("images/Mouse.png");
    
    private int leftBound;
    private int rightBound;
    private int topBound;
    private int bottomBound;
    private int margin;
    
    private int mouseMoveCooldown = 180;
    private int mouseMoveCounter = 0;
    
    private int targetX;
    private int targetY;
    private boolean isMoving;
    private double mouseSpeed;
    
    private boolean playClickSound = true;
    /**
     * Mouse Constructor
     *
     * @param leftBound The left bound of the area that the mouse can move in
     * @param rightBound The right bound of the area that the mouse can move in
     * @param topBound The top bound of the area that the mouse can move in
     * @param bottomBound The bottom bound of the area that the mouse can move in
     * @param margin The mininun distance the distance can be away from the edge
     */
    public Mouse(int leftBound, int rightBound, int topBound, int bottomBound, int margin) {
        setImage(mouseIcon);
        
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.topBound = topBound;
        this.bottomBound = bottomBound;
        this.margin = margin;
        isMoving = false;
        enableStaticRotation();
        mouseSpeed = 2;

    }
    
    public void act() {
        if (!isMoving) {
            checkAndInitiateMovement();
        } else {
            executeMovement();
        }
    }
    
    /**
     * Finds a random location to go to
     */
    private void checkAndInitiateMovement() {
        if (mouseMoveCounter >= mouseMoveCooldown) {
            targetX = Greenfoot.getRandomNumber(rightBound - leftBound) + leftBound;
            targetY = Greenfoot.getRandomNumber(bottomBound - topBound) + topBound;
            turnTowards(targetX, targetY);
            isMoving = true;
            mouseMoveCounter = 0;
        } else {
            mouseMoveCounter++;
        }
    }
    
    /**
     * Goes to a random location
     */
    private void executeMovement() {
        double distance = Math.hypot(targetX - getX(), targetY - getY());
        if (mouseSpeed >= distance) {
            setLocation(targetX, targetY);
            isMoving = false;
        } else {
            move(mouseSpeed);
        }
    }
}
