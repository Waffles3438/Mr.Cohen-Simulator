import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Simulator here.
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
 */
public class Simulator extends World
{
    private Button back = new Button("back", 3, ".png");
    private TitleScreen titleScreen;
    private int dayNumber;
    private int numDays;
    private int chanceOfLaptopBreaking;
    private int chanceOfComputerBreaking;
    /**
     * Starts the simulation. Draws borders
     * Spawns the students and Mr. Cohen
     * 
     */
    public Simulator(TitleScreen titleScreen, int days, int chanceOfLaptopBreaking, int studentIQ, int customerSupportRespondChance, int startType)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1); 
        GreenfootImage image = new GreenfootImage(1260, 720);
        image.setColor(new Color(255, 255, 255));
        image.fillRect(0, 0, getWidth(), getHeight());
        image.drawImage(new GreenfootImage("school_image.png"), 0, 0);
        image.setColor(new Color(0, 0, 0));
        image.fillRect(getWidth()/3*2, 0, 5, getHeight());
        image.fillRect(getWidth()/3*2, getHeight()/5*3, getWidth()/3, 5);
        setBackground(image);
        addObject(back, 75, 75);
        this.titleScreen = titleScreen;
        this.numDays = days;
        this.chanceOfLaptopBreaking = chanceOfLaptopBreaking;
        this.chanceOfComputerBreaking = chanceOfLaptopBreaking;
        // starts are negative one as the coords are based in the middle
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Student(studentIQ), 420 + i*220, 360 + j*180 + 50);
            }
        }
        // if (startType == 0) {
            // addObject(new Alienware());
        // }
        
    }
    
    public void act(){
        if(back.isPressed()){
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
        }
    }
}
