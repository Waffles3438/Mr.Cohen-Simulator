import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Modifier world which allows the user to change te startup value of 
 * the simulation. The users can chnage the number of days of the 
 * simulation, chance of Mr.Cohen's laptop break, student's IQ, the speed
 * customer support respond Mr.Cohen's email and the type of device 
 * Mr.Cohen starts with.
 * 
 * @ Author: Andy Feng
 * @ version 1.1 (Apr 6th, 2024)
 * 
 * refrence:
 * desktop image from vectorstock:
 * https://www.vectorstock.com/royalty-free-vector/desktop-monitor-pc-game-pixel-art-vector-47159299
 */
public class Modifier extends World
{
    private GreenfootImage background = new GreenfootImage("images/TitleScreen.jpeg");
    
    private MouseInfo mouse;
    // private Label days = new Label(numDays, 100);
    // private Label breakingChance = new Label(chanceOfLaptopBreaking, 100);
    // private Label IQ = new Label(studentIQ, 100);
    private ValueBox days;
    private ValueBox breakingChance;
    private ValueBox IQ;
    private ValueBox supportChance;
    private ValueBox chaosValue;

    private Label numOfDaysText; 
    private Label chanceOfLaptopBreakingText; 
    private Label studentIQText; 
    private Label customerSupportRespond;
    private Label chaosNumber;
    
    private boolean canFlipRight;
    private boolean canFlipLeft;
    
    private Button back = new Button("back", 3, ".png");
    private Button startSim = new Button("start", 3, ".png");
    private Button leftFlipButton = new LeftFlipButton("left", 3, ".png");
    private Button rightFlipButton = new RightFlipButton("right", 3, ".png");

    private Button changeDeviceRight = new Button("left", 3, ".png", 40);
    private Button changeDeviceLeft = new Button("right", 3, ".png", 40);
    
    protected static int numDays;
    protected static int chanceOfComputerBreaking;
    protected static int studentIQ;
    protected static int customerSupportRespondChance;
    protected static int chaos;
    /*
    different computers have different number:
    AlienWare -> 0
    SteamDeck -> 1
    MacMini -> 2
    Desktop -> 3
     */
    private GreenfootImage[] deviceImages;
    private Image computerImage;
    protected static int computerType;
    private TitleScreen titleScreen;

    private static boolean firstTime = true;

    /**
     * contructor of Modifier World
     * @ parameter
     * @ TitleScreen titleScreen: tell Modifier world which world it comes
     * from, so that we dont need to constantly creating new world
     */
    public Modifier(TitleScreen titleScreen){
        super(1260, 720, 1, false);
        setBackground(background);

        numDays = 10;
        chanceOfComputerBreaking = 25;
        studentIQ = 60;
        customerSupportRespondChance = 0;
        chaos = 1;
        computerType = 0;
        
        deviceImages = new GreenfootImage[] {
            new GreenfootImage("images/GamingLaptop.png"), // AlienWare -> 0
            new GreenfootImage("images/SteamDeck.png"),    // SteamDeck -> 1
            new GreenfootImage("images/MacMini.png"),      // MacMini -> 2
            new GreenfootImage("images/Desktop.png")       // Desktop -> 3
        };
        computerImage = new Image(deviceImages[computerType]);
        this.titleScreen = titleScreen;
        prepare();

        if(firstTime){
            Button.init();
            firstTime = false;
        }
        
        canFlipLeft = false;
        canFlipRight = true;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        /**
         * Old positioning
         * addObject(new Box(), 280, 355);
         * addObject(new Box(), 245 + 390, 355);
         * addObject(new Box(), 210 + 2 * 390, 355);
         * addObject(new Box(), -(240 + 150), 355); 
         * addObject(new Box(), -(200 + 150 + 220 + 300), 355);
         */
        addObject(new Box(), 280, 355);
        addObject(new Box(), 245 + 390, 355);
        addObject(new Box(), 210 + 2 * 390, 355);
        addObject(new Box(), (280-1260), 355);
        addObject(new Box(), (245+390-1260), 355);
        addObject(new Box(), (210+390*2-1260), 355);

        days = new ValueBox(10, 30, 130);
        breakingChance = new ValueBox(25, 100, 130);
        IQ = new ValueBox(80, 120, 130);
        supportChance = new ValueBox(0, 50, 130);
        chaosValue = new ValueBox(1, 5, 130);

        addObject(days, 280, 360);
        addObject(breakingChance, 245 + 390, 360);
        addObject(IQ, 210 + 2 * 390, 360);
        addObject(supportChance, (280-1260), 360);
        addObject(chaosValue, (245 + 390 - 1260), 360);
        
        addObject(startSim, 1050, 665);
        addObject(back, 100, 665);

        // addObject(days, 280, 360);
        // addObject(breakingChance, 245 + 390, 360);
        // addObject(IQ, 210 + 2 * 390, 360);

        addObject(leftFlipButton, 60,360);
        addObject(rightFlipButton, 1190,360);
        addObject(changeDeviceRight, (210+390*2-1260) - 75, 475);
        addObject(changeDeviceLeft, (210+390*2-1260) + 75, 475);
        leftFlipButton.setLocation(75,369);
        addObject(computerImage, (210+390*2-1260), 355);

        numOfDaysText = new Label("# Of Days", 40);
        addObject(numOfDaysText, 285, 285);
        chanceOfLaptopBreakingText = new Label("Chance of \n Laptop Breaking", 30);
        addObject(chanceOfLaptopBreakingText, 635, 285);
        studentIQText = new Label("Student IQ", 40);
        addObject(studentIQText, 990, 285);
        customerSupportRespond = new Label("customer support \n respond chance", 30);
        addObject(customerSupportRespond, (280-1260), 285);
        chaosNumber = new Label("level of chaos", 40);
        addObject(chaosNumber, (245+390-1260), 285);
    }

    //just an act method

    public void act(){
        mouse = Greenfoot.getMouseInfo();
        updateValues();
        checkButton();
    }

    /**
     * update the values of labels inside the modifier world
     */

    public void updateValues(){
        // days.setValue(numDays);
        // breakingChance.setValue(chanceOfLaptopBreaking);
        // IQ.setValue(studentIQ);
        numDays = days.getValue();
        chanceOfComputerBreaking = breakingChance.getValue();
        chanceOfComputerBreaking = breakingChance.getValue();
        studentIQ = IQ.getValue();
        customerSupportRespondChance = supportChance.getValue();
        chaos = chaosValue.getValue();
    }

    protected void startFromFirstPage(){
        if(canFlipLeft){
            leftFlipButton.action();
            canFlipLeft = false;
            canFlipRight = true;
        }
        else return;
    }

    /**
     * method which check which button is clicked
     * each button has their own function.
     */
    private void checkButton(){
        if(back.isPressed()){
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
        }
        if(startSim.isPressed()){
            Greenfoot.setWorld(new Simulator(titleScreen, numDays, chanceOfComputerBreaking, studentIQ, customerSupportRespondChance, 0));
            Greenfoot.setWorld(new Simulator(titleScreen, numDays, chanceOfComputerBreaking, studentIQ, customerSupportRespondChance, 0));
            startSim.setPressedCondition(false);
        }
        if(leftFlipButton.isPressed() && canFlipLeft){
            leftFlipButton.action();
            changeDeviceLeft.setLocation(changeDeviceLeft.getX() - 1260, changeDeviceLeft.getY());
            changeDeviceRight.setLocation(changeDeviceRight.getX() - 1260, changeDeviceRight.getY());
            leftFlipButton.setPressedCondition(false);
            canFlipLeft = false;
            canFlipRight = true;
        }
        if(rightFlipButton.isPressed() && canFlipRight){
            rightFlipButton.action();
            rightFlipButton.setPressedCondition(false);
            changeDeviceLeft.setLocation(changeDeviceLeft.getX() + 1260, changeDeviceLeft.getY());
            changeDeviceRight.setLocation(changeDeviceRight.getX() + 1260, changeDeviceRight.getY());
            canFlipLeft = true;
            canFlipRight = false;
        }
        if(changeDeviceRight.isPressed()){
            changeComputerType(true);
        }
        if(changeDeviceLeft.isPressed()){
            changeComputerType(false);
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
    }
}
