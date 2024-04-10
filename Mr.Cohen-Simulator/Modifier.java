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
    private ValueBox chaoValue;
    private boolean canFlipRight;
    private boolean canFlipLeft;
    private Label numOfDaysText; 
    private Label chanceOfLaptopBreakingText; 
    private Label studentIQText; 
    private Button back = new Button("back", 3, ".png");
    private Button startSim = new Button("start", 3, ".png");
    private Button leftFlipButton = new LeftFlipButton("left", 3, ".png");
    private Button rightFlipButton = new RightFlipButton("right", 3, ".png");

    private Button changeDeviceRight = new Button("left", 3, ".png");
    private Button changeDeviceLeft = new Button("right", 3, ".png");
    
    protected static int numDays;
    protected static int chanceOfComputerBreaking;
    protected static int studentIQ;
    protected static int customerSupportRespondChance;
    /*
    different computers have different number:
    AlienWare -> 0
    SteamDeck -> 1
    MacMini -> 2
    Desktop -> 3
     */
    protected static int computerType;
    private TitleScreen titleScreen;

    private boolean firstTime = true;

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

        this.titleScreen = titleScreen;
        prepare();

        if(firstTime){
            Button.init();
            firstTime = false;
        }


        //computerType = 0;
        //choosenType = computerList[computerType];
        canFlipLeft = false;
        canFlipRight = true;
    }

    private int y = 400;
    private int offSetT = 90;
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

        days = new ValueBox(10, 30,offSetT);
        breakingChance = new ValueBox(25, 100, offSetT);
        IQ = new ValueBox(80, 120, offSetT);
        supportChance = new ValueBox(0, 50, offSetT);
        chaoValue = new ValueBox(1, 5, offSetT);

        addObject(days, 280, y);
        addObject(breakingChance, 245 + 390, y);
        addObject(IQ, 210 + 2 * 390, y);
        addObject(supportChance, (280-1260), y);
        addObject(chaoValue, (245 + 390 - 1260), y);

        addObject(startSim, 1050, 665);
        addObject(back, 100, 665);

        // addObject(days, 280, 360);
        // addObject(breakingChance, 245 + 390, 360);
        // addObject(IQ, 210 + 2 * 390, 360);

        addObject(leftFlipButton, 60,360);
        addObject(rightFlipButton, 1190,360);
        leftFlipButton.setLocation(75,369);

        numOfDaysText = new Label("# Of Days", 40);
        addObject(numOfDaysText, 285, 285);
        chanceOfLaptopBreakingText = new Label("Chance of \n Laptop Breaking", 30);
        addObject(chanceOfLaptopBreakingText, 635, 285);
        studentIQText = new Label("Student IQ", 40);
        addObject(studentIQText, 990, 285);
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
            leftFlipButton.setPressedCondition(false);
            canFlipLeft = false;
            canFlipRight = true;
        }
        if(rightFlipButton.isPressed() && canFlipRight){
            rightFlipButton.action();
            rightFlipButton.setPressedCondition(false);
            canFlipLeft = true;
            canFlipRight = false;
        }
    }
}
