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
 */
public class Modifier extends World
{
    private GreenfootImage background = new GreenfootImage("images/Modifier.png");

    private MouseInfo mouse;
    private Label days = new Label(numDays, 100);
    private Label breakingChance = new Label(chanceOfLaptopBreaking, 100);
    private Label IQ = new Label(studentIQ, 100);
    private boolean canFlipRight;
    private boolean canFlipLeft;

    private Button back = new Button("back", 3, ".png");
    private Button startSim = new Button("start", 3, ".png");
    private Button leftFlipButton = new LeftFlipButton("left", 3, ".png");
    private Button rightFlipButton = new RightFlipButton("right", 3, ".png");
    
    protected static int numDays;
    protected static int chanceOfLaptopBreaking;
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
        chanceOfLaptopBreaking = 25;
        studentIQ = 60;
        
        this.titleScreen = titleScreen;
        prepare();
        
        if(firstTime){
            Button.init();
            firstTime = false;
        }

<<<<<<< HEAD
        //computerType = 0;
        //choosenType = computerList[computerType];
=======
        canFlipLeft = false;
        canFlipRight = true;
>>>>>>> AndyFeng
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Box(), 280, 360);
        addObject(new Box(), 245 + 390, 360);
        addObject(new Box(), 210 + 2 * 390, 360);
        addObject(new Box(), -(240 + 150), 360);
        addObject(new Box(), -(200 + 150 + 220 + 300), 360);

        Bar bar1 = new Bar("numDays");
        Bar bar2 = new Bar("chanceOfLaptopBreaking");
        Bar bar3 = new Bar("studentIQ");

        addObject(bar1, 280, 490);
        addObject(bar2, 245 + 390, 490);
        addObject(bar3, 210 + 2 * 390, 490);

        addObject(startSim, 1050, 665);
        addObject(back, 100, 665);

        addObject(days, 280, 360);
        addObject(breakingChance, 245 + 390, 360);
        addObject(IQ, 210 + 2 * 390, 360);

        addObject(leftFlipButton, 60,360);
        addObject(rightFlipButton, 1190,360);
        leftFlipButton.setLocation(75,369);
    }

<<<<<<< HEAD
=======
    //just an act method
>>>>>>> AndyFeng
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        updateValues();
        checkButton();
    }

<<<<<<< HEAD
=======
    /**
     * update the values of labels inside the modifier world
     */
>>>>>>> AndyFeng
    public void updateValues(){
        days.setValue(numDays);
        breakingChance.setValue(chanceOfLaptopBreaking);
        IQ.setValue(studentIQ);
    }

<<<<<<< HEAD
=======
    /**
     * method which check which button is clicked
     * each button has their own function.
     */
>>>>>>> AndyFeng
    private void checkButton(){
        if(back.isPressed()){
            Greenfoot.setWorld(titleScreen);
            back.setPressedCondition(false);
        }
        if(startSim.isPressed()){
            Greenfoot.setWorld(new Simulator(titleScreen));
            startSim.setPressedCondition(false);
        }
<<<<<<< HEAD
        if(leftFlipButton.isPressed()){
            leftFlipButton.action();
            leftFlipButton.setPressedCondition(false);
        }
        if(rightFlipButton.isPressed()){
            rightFlipButton.action();
            rightFlipButton.setPressedCondition(false);
=======
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
>>>>>>> AndyFeng
        }
    }
}
