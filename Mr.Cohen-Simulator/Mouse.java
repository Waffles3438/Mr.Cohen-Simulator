import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The mouse class is paired up with the computer class and will move around randomly
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
 */
public class Mouse extends SuperSmoothMover
{
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
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.topBound = topBound;
        this.bottomBound = bottomBound;
        this.margin = margin;
        isMoving = false;
        enableStaticRotation();
        mouseSpeed = 2;

    }
    
    public void act()
    {
        // Add your action code here.
        if (mouseMoveCounter >= mouseMoveCooldown) {
            targetX = Greenfoot.getRandomNumber(rightBound-leftBound-margin)+leftBound+margin;
            targetY = Greenfoot.getRandomNumber(bottomBound-topBound-margin)+topBound+margin;
            isMoving = true;
            turnTowards(targetX, targetY);
            mouseMoveCounter = 0;
        }
        
        if (isMoving) {
            double distance = Person.getDistance(new int[]{getX(), getY()}, new int[]{targetX, targetY});
            if (mouseSpeed >= distance) {
                setLocation(targetX, targetY);
                isMoving = false;
            } else {
                move(mouseSpeed);
            }
        } else {
            mouseMoveCounter++;
        }
    }
}
