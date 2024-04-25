import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * <p>
 * This is the world that contains the simulation
 * </p>
 * 
 * <a href="https://www.freepik.com/premium-vector/pixel-art-illustration-laptop-pixelated-notebook-classic-laptop-computer-icon-pixelated-game_80323384.htm">Link to Art</a>
 * 
 * Edited by Andy Feng <br>
 * 
 * <a href="https://www.youtube.com/watch?v=259C4AaOHn0"> Link to music</a>
 * Music by Pokemon
 * 
 * @author Felix Zhao
 * @version 0.0.1 April 11th, 2024
 */
public class Simulator extends World
{
    public final static Color BLACK = new Color(0, 0, 0);
    public final static Color GREEN = new Color(100, 255, 100);
    public final static Color ORANGE = new Color(240, 100, 10);
    public final static Color BLUE = new Color(80, 80, 255);
    
    Image computerImage; 
    private TitleScreen titleScreen;
    private int dayNumber;
    private int numDays;
    private int chanceOfComputerBreaking;
    private int chaosNumber;
    private static boolean smoked = false;
    private int customerSupportRespondChance;
    private boolean chaosMode;
    private int actsCount = 0;
    private int dayCount = 1;
    private Label day = new Label("Day: " + dayCount, 50);

    private SuperStatBar averageProjectedMark;
    private SuperStatBar cohenAngerMeter;
    private SuperStatBar computerDurability;

    private Fader blackScreen;
    private boolean transitionToNextDay = false;
    private boolean fadeIn = false;
    private boolean fadeOut = false;
    private boolean firstTime = true;
    private FinishedWorld finishedWorld;
    private MrCohen cohen;
    private Computer computer;
    private int secondsPerDay = 20; 
    //private PauseScreen pause;
    
    private ArrayList<Actor> actorList;
    private ArrayList<GreenfootImage> images;
    
    private GreenfootSound music = new GreenfootSound("music.mp3");
    
    /**
     * Starts the simulation. Draws borders
     * Spawns the students and Mr. Cohen
     * 
     */
    public Simulator(TitleScreen titleScreen, int days, int chanceOfComputerBreaking, int studentIQ, int customerSupportRespondChance, boolean chaosMode, int startType, boolean hasJanitors, boolean hasRobbers)
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
        this.titleScreen = titleScreen;
        this.numDays = days;
        //pause = new PauseScreen(titleScreen, this);
        
        finishedWorld = new FinishedWorld();
        this.customerSupportRespondChance = customerSupportRespondChance;
        this.chanceOfComputerBreaking = chanceOfComputerBreaking;
        this.chaosMode = chaosMode;
        
        addObject(day, 1175, 30);
        computerImage = new Image("temp_computer.png");
        computerImage.adjustSize(70);
        addObject(computerImage, 360, 140); 
        
        // starts are negative one as the coords are based in the middle
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Student(studentIQ, 354 + i*211, 360 + j*146 + 110), 354 + i*211, 360 + j*146 + 110);
            } 
        }
        computer = new Alienware();
        
        if (startType == 0) {
            computer = new Alienware();
        } else if (startType == 1) {
            computer = new Steamdeck();
        } else if (startType == 2) {
            computer = new MacMini();
        } else {
            computer = new Desktop();
        }

        // starts are negative one as the coords are based in the middle
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Image(125, 60), 354 + i*211, 360 + j*146+38);
            }
        }
        addObject(new Image(75, 317), 807, 283);
        addObject(new Image(275, 85), 351, 118);
        cohen = new MrCohen(computer, startType);
        addObject(computer, 0, 0);
        addObject(cohen, 360, 35);
        
        averageProjectedMark = new SuperStatBar(100, 0, null, 360, 20, 0, GREEN, BLACK);
        cohenAngerMeter = new SuperStatBar(100, 0, null, 360, 20, 0, ORANGE, BLACK);
        computerDurability = new SuperStatBar(computer.getMaxDurability(), 0, null, 360, 20, 0, BLUE, BLACK);
        addObject(averageProjectedMark, 1050, 100);
        addObject(cohenAngerMeter, 1050, 140);
        addObject(computerDurability, 1050, 180);

        blackScreen = new Fader("Blackscreen.png", 255, 1, 1);
        
        setPaintOrder(Fader.class);
        
        music.setVolume(25);
        music.playLoop();
    }
    
    public void act(){
        /*if(Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(pause);
        }*/
        
        actorList = (ArrayList<Actor>) getObjects(Actor.class);
        images = (ArrayList<GreenfootImage>) getObjects(GreenfootImage.class);
        pause();
        
        int dayChecker = dayNumber-1;
        if (Greenfoot.getRandomNumber(100) < chanceOfComputerBreaking && dayChecker < dayNumber) {
            //System.out.println(dayNumber);
            //removeObject(computerImage);
        }

        double mark = 0;
        for (Student student : getObjects(Student.class)) {
            mark += student.getProjectedMark();
        }
        averageProjectedMark.update((int)(mark/9));
        cohenAngerMeter.update(cohen.getAnger());
        computerDurability.update(computer.getDurability());
        
        actsCount++;
        if(actsCount >= secondsPerDay * 60){
            transitionToNextDay = true;
            dayCount++;
            addObject(blackScreen, getWidth()/4 - 125, getHeight()/2);
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
                    if (Greenfoot.getRandomNumber(100)+1 <= chanceOfComputerBreaking) {
                        computer.breakComputer();
                    }
                    cohen.returnToDesk();
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
                
                cohen.newDay();
                
            }
        }
    }
    
    /**
     * Returns the chance of customer support responding
     *
     * @return Returns support chance
     */
    public int getSupportChance() {
        return customerSupportRespondChance;
    }
    
    /**
     * Updates Mr Cohen's current computer
     *
     * @param computer The new computer
     */
    public void updateComputer(Computer newComputer) {
        //System.out.println(computer);
        computer.deleteMouse();
        removeObject(computer);
        this.computer = newComputer;
        addObject(computer, 0, 0);
        computerDurability.setMaxVal(computer.getMaxDurability());
    }
    
    private void pause(){
        if(Greenfoot.mouseClicked(null)){
            Greenfoot.setWorld(new PauseScreen(titleScreen, this, actorList, blackScreen));
        }
    }
    
    public static void setSmoked(boolean status){
        smoked = status;
    }
    
    public static boolean isSmoked(){
        return smoked;
    }
}


