import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * <p>
 * Modifier world which allows the user to change te startup value of 
 * the simulation. The users can chnage the number of days of the 
 * simulation, chance of Mr.Cohen's computer breaking, student's IQ, the speed
 * customer support respond Mr.Cohen's email and the type of device 
 * Mr.Cohen starts with.
 * </p>
 * 
 * <a href="https://www.vectorstock.com/royalty-free-vector/desktop-monitor-pc-game-pixel-art-vector-47159299"> Link to image</a> <br>
 * Image by VectorStock
 * 
 * @author Andy Feng
 * @version 1.1 (Apr 6th, 2024)
 * 
 */
public class Modifier extends World
{
    private GreenfootImage background = new GreenfootImage("images/TitleScreen.jpeg");
    
    private MouseInfo mouse;

    private Button back = new Button("back", 3, ".png");
    private Button startSim = new Button("start", 3, ".png");
    private Button leftFlipButton = new LeftFlipButton("left", 3, ".png");
    private Button rightFlipButton = new RightFlipButton("right", 3, ".png");

    private Button changeDeviceRight = new Button("left", 3, ".png", 50);
    private Button changeDeviceLeft = new Button("right", 3, ".png", 50);

    protected static int numDays;
    private Label numOfDaysText; 
    private ValueBox days;
    
    protected static int chanceOfComputerBreaking;
    private Label chanceOfLaptopBreakingText; 
    private ValueBox breakingChance;
    
    protected static int studentIQ;
    private Label studentIQText;
    private ValueBox IQ;
    private boolean janitors;
    private Label janitorsText;
    private CheckBox hasJanitors;
    private boolean robbers;
    private CheckBox hasRobbers;
    private Label robberText;
    
    protected static int customerSupportRespondChance;
    private Label customerSupportRespond;
    private ValueBox supportChance;
    protected static boolean chaos;
    private Label chaosMode;
    private CheckBox chaosModeCheckBox;
    private int flipTimes;
    /*
    different computers have different number:
    AlienWare -> 0
    SteamDeck -> 1
    MacMini -> 2
    Desktop -> 3
     */
    
    private Image student = new Image("student", 9, ".png");
    private Image janitorImage = new Image("images/janitor.png");
    private Image robberImage = new Image("images/robber.png");
    private Image chaosModeImage = new Image("images/happyFace.png");
    private GreenfootImage[] deviceImages;
    private int computerType;
    private Image computerImage;
    private Simulator simulator;
    private TitleScreen titleScreen;
    
    private static boolean firstTime = true;
    private ArrayList<Image> secondPageImage = new ArrayList<Image>();

    /**
     * Contructor of Modifier World
     * 
     * @titleScreen TitleScreen: tell Modifier world which world it comes
     * from, so that we dont need to constantly creating new world
     */
    public Modifier(TitleScreen titleScreen){
        super(1260, 720, 1, false);
        setBackground(background);
        this.titleScreen = titleScreen;

        numDays = 10;
        chanceOfComputerBreaking = 25;
        studentIQ = 60;
        customerSupportRespondChance = 0;
        chaos = false;
        computerType = 0;
        janitors = false;
        robbers = false;
        
        deviceImages = new GreenfootImage[] {
            new GreenfootImage("images/GamingLaptop.png"), // AlienWare -> 0
            new GreenfootImage("images/SteamDeck.png"),    // SteamDeck -> 1
            new GreenfootImage("images/MacMini.png"),      // MacMini -> 2
            new GreenfootImage("images/Desktop.png")       // Desktop -> 3
        };
        
        computerImage = new Image(deviceImages[computerType]);
        computerImage.adjustSize(250);
        janitorImage.getImage().scale(175, 175);
        robberImage.getImage().scale(140, 140);
        chaosModeImage.getImage().scale(150, 150);
        
        if(firstTime){
            Button.init();
            firstTime = false;
        }

        prepare();
        //computerType = 0;
        //choosenType = computerList[computerType];
        
        
    }

    private int y = 485;
    private int offSetT = 90;

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        /*
         * Old positioning
         * addObject(new Box(), 280, 355);
         * addObject(new Box(), 245 + 390, 355);
         * addObject(new Box(), 210 + 2 * 390, 355);
         * addObject(new Box(), -(240 + 150), 355); 
         * addObject(new Box(), -(200 + 150 + 220 + 300), 355);
         */
        addObject(student, 630 - 1260, 230);
        addObject(chaosModeImage, 630, 230);
        
        addObject(new Box(), 460, 485);
        addObject(new Box(), 820, 485);
        addObject(new Box(), 360 - 1260, 485);
        addObject(new Box(), 630 - 1260, 485);
        addObject(new Box(), 900 - 1260, 485);
        addObject(new Box(), 460 - 2*1260, 485);
        addObject(new Box(), 820 - 2*1260, 485);

        days = new ValueBox(10, 30,offSetT);
        breakingChance = new ValueBox(25, 100, offSetT);
        IQ = new ValueBox(80, 120, offSetT);
        supportChance = new ValueBox(0, 50, offSetT);
        hasJanitors = new CheckBox();
        hasRobbers = new CheckBox();
        chaosModeCheckBox = new CheckBox();

        addObject(days, 460, y);
        addObject(IQ, 360 - 1260, y);
        addObject(breakingChance, 460 - 2*1260, y);
        addObject(supportChance, 820 - 2*1260, y);
        addObject(hasJanitors, 630 - 1260, y);
        addObject(hasRobbers, 900 - 1260, y);
        addObject(chaosModeCheckBox, 820, 485);
        
        addObject(startSim, 1050, 665);
        addObject(back, 100, 665);

        //addObject(leftFlipButton, 60,360);
        addObject(rightFlipButton, 1190,360);
        addObject(changeDeviceRight, 400 - 2*1260, 200);
        addObject(changeDeviceLeft, 860 - 2*1260, 200);
        addObject(computerImage, 630 - 2*1260, 200);
        
        numOfDaysText = new Label("# Of Days", 30);
        addObject(numOfDaysText, 460, 385);
        chaosMode = new Label("Chaos Mode", 30);
        addObject(chaosMode, 820, 385);
        studentIQText = new Label("Average Student IQ", 20);
        addObject(studentIQText, 360 - 1260, 390);
        janitorsText = new Label("Has Janitors", 30);
        addObject(janitorsText, 630 - 1260, 385);
        robberText = new Label("Has Robbers", 30);
        addObject(robberText, 900 - 1260, 385);
        chanceOfLaptopBreakingText = new Label("Chance of \n Laptop Breaking", 18);
        addObject(chanceOfLaptopBreakingText, 460 - 2*1260, 385);
        customerSupportRespond = new Label("Customer Support \n Responce Chance", 18);
        addObject(customerSupportRespond, (820 - 2*1260), 385);
    }

    
    /**
     * This act method deals with everything that changes in the modifier world
     */
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        updateImageEffect();
        addMoreImage();
        updateValues();
        checkButton();
        if(flipTimes <= 0){
            flipTimes = 0;
            if(leftFlipButton != null){
                removeObject(leftFlipButton);
            }
        }
        if(flipTimes >= 2){
            flipTimes = 2;
            removeObject(rightFlipButton);
        }
        if(flipTimes > 0 && flipTimes < 2){
            addObject(leftFlipButton, 60,360);
            addObject(rightFlipButton, 1190,360);
        }
    }

    /**
     * update the values of labels inside the modifier world
     */

    public void updateValues(){
        numDays = days.getValue();
        chanceOfComputerBreaking = breakingChance.getValue();
        chanceOfComputerBreaking = breakingChance.getValue();
        studentIQ = IQ.getValue();
        customerSupportRespondChance = supportChance.getValue();

        janitors = hasJanitors.updateBoolean();
        robbers = hasRobbers.updateBoolean();
        chaos = chaosModeCheckBox.updateBoolean();
    }

    protected void startFromFirstPage(){
        if(flipTimes > 0){
            addObject(rightFlipButton, 1190,360);
            for(int i = 0; i < flipTimes; i++){
                leftFlipButton.action();
                computerImage.setLocation(computerImage.getX() - 1260, computerImage.getY());
                changeDeviceLeft.setLocation(changeDeviceLeft.getX() - 1260, changeDeviceLeft.getY());
                changeDeviceRight.setLocation(changeDeviceRight.getX() - 1260, changeDeviceRight.getY());
            }
            if (rightFlipButton.getWorld() == null) {
                addObject(rightFlipButton, 1190, 360);
            }
            flipTimes = 0;
        }
    }

    /**
     * method which check which button is clicked
     * each button has their own function.
     */
    private void checkButton(){
        if(back.isPressed()){
            TitleScreen.setMusicVolume(25);
            TitleScreen.playMusic();
            startFromFirstPage();
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
        }
        if(startSim.isPressed()){
            TitleScreen.pauseMusic();
            simulator = new Simulator(titleScreen, numDays, chanceOfComputerBreaking, studentIQ, customerSupportRespondChance, chaos, computerType, janitors, robbers);
            Greenfoot.setWorld(simulator);
            startSim.setPressedCondition(false);
        }
        if(leftFlipButton.isPressed() && flipTimes > 0){
            leftFlipButton.action();
            changeDeviceLeft.setLocation(changeDeviceLeft.getX() - 1260, changeDeviceLeft.getY());
            changeDeviceRight.setLocation(changeDeviceRight.getX() - 1260, changeDeviceRight.getY());
            computerImage.setLocation(computerImage.getX() - 1260, computerImage.getY());
            student.setLocation(student.getX() - 1260, student.getY());
            chaosModeImage.setLocation(chaosModeImage.getX() - 1260, chaosModeImage.getY());
            if(janitorImage.getWorld() != null) janitorImage.setLocation(janitorImage.getX() - 1260, janitorImage.getY());
            if(robberImage.getWorld() != null) robberImage.setLocation(robberImage.getX() - 1260, robberImage.getY());
            leftFlipButton.setPressedCondition(false);
            flipTimes--;
        }
        if(rightFlipButton.isPressed() && flipTimes < 2){
            rightFlipButton.action();
            rightFlipButton.setPressedCondition(false);
            changeDeviceLeft.setLocation(changeDeviceLeft.getX() + 1260, changeDeviceLeft.getY());
            changeDeviceRight.setLocation(changeDeviceRight.getX() + 1260, changeDeviceRight.getY());
            computerImage.setLocation(computerImage.getX() + 1260, computerImage.getY());
            student.setLocation(student.getX() + 1260, student.getY());
            chaosModeImage.setLocation(chaosModeImage.getX() + 1260, chaosModeImage.getY());
            if(janitorImage.getWorld() != null) janitorImage.setLocation(janitorImage.getX() + 1260, janitorImage.getY());
            if(robberImage.getWorld() != null) robberImage.setLocation(robberImage.getX() + 1260, robberImage.getY());
            flipTimes++;
        }
        if(changeDeviceRight.isPressed()){
            changeComputerType(false);
        }
        if(changeDeviceLeft.isPressed()){
            changeComputerType(true);
        }
    }
    
    private void changeComputerType(boolean next) {
        if (next) {
            computerType = (computerType + 1) % deviceImages.length; // Move to next type, cycle to 0 if at the end
        } else {
            computerType = (computerType - 1 + deviceImages.length) % deviceImages.length; // Move to previous type, cycle to last if at the beginning
        }
        updateDeviceImage();
    }
    
    private void updateDeviceImage() {
        GreenfootImage chosenDeviceImage = deviceImages[computerType];
        computerImage.setImage(chosenDeviceImage);
        computerImage.updateRatio();
        computerImage.adjustSize(250);
    }
    
    private int frame = 1;
    private SimpleTimer timer = new SimpleTimer();
    private void updateImageEffect(){
        if(timer.millisElapsed() < 600) {
           return; 
        }
        timer.mark();
        student.setImage(student.list.get(frame % 9));
        frame++;
    }
    
    private boolean robberImageAdded = false;
    private boolean janitorImageAdded = false;
    private void addMoreImage(){
        if(janitors && !janitorImageAdded){
            secondPageImage.add(janitorImage);
            janitorImageAdded = true;
        }
        if(!janitors){
            if(janitorImage.getWorld() != null) removeObject(janitorImage);
            secondPageImage.remove(janitorImage);
            janitorImageAdded = false;
        }
        if(robbers && !robberImageAdded){
            secondPageImage.add(robberImage);
            robberImageAdded = true;
        }
        if(!robbers){
            if(robberImage.getWorld() != null) removeObject(robberImage);
            secondPageImage.remove(robberImage);
            robberImageAdded = false;
        }
        if(!robbers && !janitors){
            student.setLocation(630, student.getY());
        }
        if(chaos){
            chaosModeImage.setImage("images/chaosMode.png");
        } else {
            chaosModeImage.setImage("images/happyFace.png");
        }
        chaosModeImage.getImage().scale(150, 150);
        drawImage();
    }
    
    private void drawImage(){
        int length = secondPageImage.size();
        int gap = 200;
        if(length == 1){
            for(Image image : secondPageImage){
                removeObject(image);
            }
            student.setLocation(530, student.getY());
            for(Image image : secondPageImage){
                addObject(image, 530 + length*gap, student.getY());
            }
        }
        if(length == 2){
            for(Image image : secondPageImage){
                removeObject(image);
            }
            student.setLocation(430, student.getY());
            for(Image image : secondPageImage){
                if(secondPageImage.indexOf(image) != 0){
                    addObject(image, secondPageImage.get(secondPageImage.indexOf(image) - 1).getX() + 200, student.getY());
                }
                else addObject(image, student.getX() + 200, student.getY());
            }
        }
        if(flipTimes == 1){
            student.setLocation(student.getX(), student.getY());
            if(robberImage.getWorld() != null) robberImage.setLocation(robberImage.getX(), student.getY());
            if(janitorImage.getWorld() != null) janitorImage.setLocation(janitorImage.getX(), student.getY());
        }
        if(flipTimes == 0){
            student.setLocation(student.getX() - 1260, student.getY());
            chaosModeImage.setLocation(630, chaosModeImage.getY());
            if(robberImage.getWorld() != null) robberImage.setLocation(robberImage.getX() - 1260, student.getY());
            if(janitorImage.getWorld() != null) janitorImage.setLocation(janitorImage.getX() - 1260, student.getY());
        }
        if(flipTimes == 2){
            student.setLocation(student.getX() + 1260, student.getY());
            if(robberImage.getWorld() != null) robberImage.setLocation(robberImage.getX() + 1260, student.getY());
            if(janitorImage.getWorld() != null) janitorImage.setLocation(janitorImage.getX() + 1260, student.getY());
        }
    }
    
    /**
     * Returns number of days
     */
    public static int getNumberOfDays(){
        return numDays;
    }
    
    /**
     * Returns simulator world
     *
     * @return Returns the simulator world
     */
    public Simulator getSimulatorWorld(){
        return simulator;
    }
    
    /**
     * Returns the chance of laptop breaking
     *
     * @return Returns the chance of laptop breaking
     */
    public static int getchanceOfLaptopBreaking(){
        return chanceOfComputerBreaking;
    }
    
    /**
     * Stops main menu music when greenfoot is stopped
     *
     */
    public void stopped() {
        titleScreen.pauseMusic();
    }
    
    /**
     * Starts main menu music when greenfoot is started
     *
     */
    public void started() {
        titleScreen.playMusic();
    }
    
    /**
     * Returns chaos
     */
    public static boolean getChaos(){
        return chaos;
    }
}
