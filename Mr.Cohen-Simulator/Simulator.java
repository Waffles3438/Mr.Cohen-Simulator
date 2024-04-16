import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the world that contains the simulation
 * 
 * @author Felix Zhao
 * @version 0.0.1 April 11th, 2024
 */
public class Simulator extends World
{
    Image computerImage; 
    private Button back = new Button("back", 3, ".png");
    private TitleScreen titleScreen;
    private int dayNumber;
    private int numDays;
    private int chanceOfComputerBreaking;
    private boolean chaosMode;
    private int actsCount = 0;
    private int dayCount = 1;
    private Label day = new Label("Day: " + dayCount, 50);

    private SuperStatBar averageProjectedMark;

    private Fader blackScreen;
    private boolean transitionToNextDay = false;
    private boolean fadeIn = false;
    private boolean fadeOut = false;
    private boolean firstTime = true;
    private FinishedWorld finishedWorld;

    /**
     * Starts the simulation. Draws borders
     * Spawns the students and Mr. Cohen
     * 
     */
    public Simulator(TitleScreen titleScreen, int days, int chanceOfComputerBreaking, int studentIQ, int customerSupportRespondChance, boolean chaosMode, int startType)
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
        
        finishedWorld = new FinishedWorld();

        this.chanceOfComputerBreaking = chanceOfComputerBreaking;
        this.chaosMode = chaosMode;
        
        addObject(day, 1175, 30);
        computerImage = new Image("temp_computer.png");
        computerImage.adjustSize(70);
        addObject(computerImage, 360, 150); 
        
        // starts are negative one as the coords are based in the middle
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Student(studentIQ, 354 + i*211, 360 + j*146 + 110), 354 + i*211, 360 + j*146 + 110);
            } 
        }
        Computer computer = new Alienware();
        
        if (startType == 0) {
            computer = new Alienware();
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
        
        addObject(new MrCohen(computer), 360, 35);
        
        averageProjectedMark = new SuperStatBar(100, 0, null, 360, 20, 0, new Color(100, 255, 100), new Color(0, 0, 0));
        addObject(averageProjectedMark, 1050, 100);
        // if (startType == 0) {
            // addObject(new Alienware());
        // }
        blackScreen = new Fader("Blackscreen.png", 255, 1, 1);
    }
    
    public void act(){
        if(back.isPressed()){
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
        }
        
        int dayChecker = dayNumber-1;
        if (Greenfoot.getRandomNumber(100) < chanceOfComputerBreaking && dayChecker < dayNumber) {
            //System.out.println(dayNumber);
            //removeObject(computerImage);
        }
        
        
        
        actsCount++;
        if(actsCount >= 600){
            transitionToNextDay = true;
            dayCount++;
            addObject(blackScreen, getWidth()/2, getHeight()/2);
            fadeIn = true;
            actsCount = 0;
        }
        
        if(transitionToNextDay){
            if(dayCount > Modifier.getNumberOfDays()){
                Greenfoot.setWorld(finishedWorld);
            }
            
            if(fadeIn){
                blackScreen.fadeIn();
                if(blackScreen.getImage().getTransparency() >= 254){
                    fadeIn = false;
                    fadeOut = true;
                }
            }
            
            if(fadeOut){
                if(firstTime){
                    day.setValue("Day: " + dayCount);
                    for(Student student : getObjects(Student.class)){
                        student.returnToDesk();
                    }
                    firstTime = false;
                }
                blackScreen.fadeOut();
            }
            
            if(blackScreen.getWorld() == null){
                fadeOut = false;
                actsCount = 0;
                transitionToNextDay = false;
                firstTime = true;
            }
        }
    }
}
