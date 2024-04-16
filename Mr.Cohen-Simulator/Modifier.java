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
    private boolean Janitors;
    private Label janitorsText;
    private CheckBox hasJanitors;
    private boolean Robbers;
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
    private GreenfootImage[] deviceImages;
    private Image computerImage;
    protected static int computerType;
    private TitleScreen titleScreen;
    private Image student = new Image("student", 9, ".png");
    private Image janitorImage = new Image("images/janitor.png");
    private Image robberImage = new Image("images/robber.png");

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
        this.titleScreen = titleScreen;

        numDays = 10;
        chanceOfComputerBreaking = 25;
        studentIQ = 60;
        customerSupportRespondChance = 0;
        chaos = false;
        computerType = 0;
        Janitors = false;
        Robbers = false;
        
        deviceImages = new GreenfootImage[] {
            new GreenfootImage("images/GamingLaptop.png"), // AlienWare -> 0
            new GreenfootImage("images/SteamDeck.png"),    // SteamDeck -> 1
            new GreenfootImage("images/MacMini.png"),      // MacMini -> 2
            new GreenfootImage("images/Desktop.png")       // Desktop -> 3
        };
        
        computerImage = new Image(deviceImages[computerType]);
        computerImage.adjustSize(250);
        janitorImage.adjustSize(150);
        robberImage.adjustSize(155);

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
        /**
         * Old positioning
         * addObject(new Box(), 280, 355);
         * addObject(new Box(), 245 + 390, 355);
         * addObject(new Box(), 210 + 2 * 390, 355);
         * addObject(new Box(), -(240 + 150), 355); 
         * addObject(new Box(), -(200 + 150 + 220 + 300), 355);
         */
        addObject(janitorImage, 630 - 1260, 220);
        janitorImage.getImage().setTransparency(1);
        addObject(robberImage, 630 - 1260, 220);
        robberImage.getImage().setTransparency(1);
        addObject(student, 630 - 1260, 230);
        
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

        addObject(days, 460, y);
        addObject(IQ, 360 - 1260, y);
        addObject(breakingChance, 460 - 2*1260, y);
        addObject(supportChance, 820 - 2*1260, y);
        addObject(hasJanitors, 630 - 1260, y);
        addObject(hasRobbers, 900 - 1260, y);
        
        addObject(startSim, 1050, 665);
        addObject(back, 100, 665);

        addObject(leftFlipButton, 60,360);
        addObject(rightFlipButton, 1190,360);
        addObject(changeDeviceRight, 400 - 2*1260, 200);
        addObject(changeDeviceLeft, 860 - 2*1260, 200);
        leftFlipButton.setLocation(75,369);
        addObject(computerImage, 630 - 2*1260, 200);

        numOfDaysText = new Label("# Of Days", 30);
        addObject(numOfDaysText, 460, 385);
        chaosMode = new Label("Chaos Mode", 30);
        addObject(chaosMode, 820, 385);
        studentIQText = new Label("Average \n Student IQ", 30);
        addObject(studentIQText, 360 - 1260, 400);
        janitorsText = new Label("has Janitors", 30);
        addObject(janitorsText, 630 - 1260, 385);
        robberText = new Label("has Robbers", 30);
        addObject(robberText, 900 - 1260, 385);
        chanceOfLaptopBreakingText = new Label("Chance of \n Laptop Breaking", 25);
        addObject(chanceOfLaptopBreakingText, 460 - 2*1260, 390);
        customerSupportRespond = new Label("customer support \n respond chance", 25);
        addObject(customerSupportRespond, (820 - 2*1260), 390);
    }

    //just an act method

    public void act(){
        mouse = Greenfoot.getMouseInfo();
        System.out.println(Janitors);
        updateImageEffect();
        addMoreImage();
        updateValues();
        checkButton();
        if(flipTimes < 0) flipTimes = 0;
        if(flipTimes > 2) flipTimes = 2;
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

        Janitors = hasJanitors.updateBoolean();
        Robbers = hasRobbers.updateBoolean();
    }

    protected void startFromFirstPage(){
        if(flipTimes > 0){
            for(int i = 0; i < flipTimes; i++){
                leftFlipButton.action();
                computerImage.setLocation(computerImage.getX() - 1260, computerImage.getY());
                changeDeviceLeft.setLocation(changeDeviceLeft.getX() - 1260, changeDeviceLeft.getY());
                changeDeviceRight.setLocation(changeDeviceRight.getX() - 1260, changeDeviceRight.getY());
            }
            flipTimes = 0;
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
            Greenfoot.setWorld(new Simulator(titleScreen, numDays, chanceOfComputerBreaking, studentIQ, customerSupportRespondChance, chaos, 0));
            startSim.setPressedCondition(false);
        }
        if(leftFlipButton.isPressed() && flipTimes > 0){
            leftFlipButton.action();
            changeDeviceLeft.setLocation(changeDeviceLeft.getX() - 1260, changeDeviceLeft.getY());
            changeDeviceRight.setLocation(changeDeviceRight.getX() - 1260, changeDeviceRight.getY());
            computerImage.setLocation(computerImage.getX() - 1260, computerImage.getY());
            student.setLocation(student.getX() - 1260, student.getY());
            janitorImage.setLocation(janitorImage.getX() - 1260, janitorImage.getY());
            robberImage.setLocation(robberImage.getX() - 1260, robberImage.getY());
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
            janitorImage.setLocation(janitorImage.getX() + 1260, janitorImage.getY());
            robberImage.setLocation(robberImage.getX() + 1260, robberImage.getY());
            flipTimes++;
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
        computerImage.updateRatio();
        computerImage.adjustSize(250);
    }
    
    private int frame = 1;
    private SimpleTimer timer = new SimpleTimer();
    private void updateImageEffect(){
        if(timer.millisElapsed() < 200) return;
        timer.mark();
        frame %= 9;
        student.setImage(student.list.get(frame++));
    }
    
    private void addMoreImage(){
        if(Janitors && !Robbers){
            student.setLocation(530, student.getY());
            janitorImage.setLocation(730, student.getY() - 10);
            janitorImage.getImage().setTransparency(255);
        } else if(Robbers && !Janitors){
            student.setLocation(530, student.getY());
            robberImage.setLocation(730, student.getY() - 5);
            robberImage.getImage().setTransparency(255);
        }
        
    }
}
