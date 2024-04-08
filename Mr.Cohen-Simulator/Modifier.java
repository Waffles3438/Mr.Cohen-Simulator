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

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private GreenfootImage background = new GreenfootImage("images/Modifier.png");

    private MouseInfo mouse;
    private Label days = new Label(numDays, 100);
    private Label breakingChance = new Label(chanceOfLaptopBreaking, 100);
    private Label IQ = new Label(studentIQ, 100);

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

    private boolean firstTime = true;

    
    public Modifier(){
        super(1260, 720, 1, false);
        setBackground(background);

        numDays = 10;
        chanceOfLaptopBreaking = 25;
        studentIQ = 60;

        prepare();
        
        if(firstTime){
            Button.init();
            firstTime = false;
        }

        //computerType = 0;
        //choosenType = computerList[computerType];
    }

    public void act(){
        mouse = Greenfoot.getMouseInfo();
        updateValues();
    }

    public void updateValues(){
        days.setValue(numDays);
        breakingChance.setValue(chanceOfLaptopBreaking);
        IQ.setValue(studentIQ);
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

        Button startSim = new startButton("start", 3, ".png");
        Button backToTitle = new backButton("back", 3, ".png");

        addObject(startSim, 1050, 665);
        addObject(backToTitle, 100, 665);

        addObject(days, 280, 360);
        addObject(breakingChance, 245 + 390, 360);
        addObject(IQ, 210 + 2 * 390, 360);

        Button leftFlipButton = new LeftFlipButton("left", 3, ".png");
        Button rightFlipButton = new RightFlipButton("right", 3, ".png");
        addObject(leftFlipButton, 60,360);
        addObject(rightFlipButton, 1190,360);
        leftFlipButton.setLocation(75,369);
    }
}
