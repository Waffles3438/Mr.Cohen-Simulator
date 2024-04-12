import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the world that contains the simulation
 * 
 * @author Felix Zhao
 * @version 0.0.1 April 11th, 2024
 */
public class Simulator extends World
{
    private Button back = new Button("back", 3, ".png");
    private TitleScreen titleScreen;
    private int dayNumber;
    private int numDays;
    private int chanceOfComputerBreaking;
    private int chaosNumber;
    /**
     * Starts the simulation. Draws borders
     * Spawns the students and Mr. Cohen
     * 
     */
    public Simulator(TitleScreen titleScreen, int days, int chanceOfComputerBreaking, int studentIQ, int customerSupportRespondChance, int chaosNumber, int startType)
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

        this.chanceOfComputerBreaking = chanceOfComputerBreaking;
        this.chaosNumber = chaosNumber;

        // starts are negative one as the coords are based in the middle
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Student(studentIQ, 354 + i*211, 360 + j*146 + 110), 354 + i*211, 360 + j*146 + 110);
            } 
        }
        if (startType == 0) {
            addObject(new Alienware(), 0, 0);
        }
        // 125 by 60 pixesl
        // starts are negative one as the coords are based in the middle
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Image(125, 60), 354 + i*211, 360 + j*146+38);
            }
        }
        addObject(new Image(75, 317), 807, 283);
        addObject(new Image(275, 85), 351, 118);
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
