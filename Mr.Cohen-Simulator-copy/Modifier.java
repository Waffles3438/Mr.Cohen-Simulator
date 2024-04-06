import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Modifier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    private boolean firstTime = true;

    /*protected Computer computerList = {new AlienWare(), new SteamDeck(),
    new DeskTop(), new MacMini()};
    private int computerType;
    protected Computer choosenType;*/
    public Modifier(){
        super(1260, 720, 1);
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

    //protected int setTypesOfComputer(){
    //if(/*right button is clicked*/) computerType++;
    //else if(/*left button is clicked*/) computerType--;

    //if(computerType > 3) computerType = 0;
    //if(computerType < 0) computerType = 3;

    //choosenType = computerList[computerType];
    //}
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Bar bar1 = new Bar("numDays");
        Bar bar2 = new Bar("chanceOfLaptopBreaking");
        Bar bar3 = new Bar("studentIQ");
        Button startSim = new startButton("start", 3, ".png");
        Button backToTitle = new backButton("back", 3, ".png");
        addObject(bar1, 200, 200);
        addObject(bar2, 400, 200);
        addObject(bar3, 600, 200);

        addObject(startSim, 1050, 665);
        addObject(backToTitle, 100, 665);

        addObject(days, 200, 500);
        addObject(breakingChance, 400, 500);
        addObject(IQ, 600, 500);
        bar1.setLocation(266,277);
        bar2.setLocation(1021,277);
        days.setLocation(263,190);
        bar3.setLocation(645,277);
        breakingChance.setLocation(1021,190);
        IQ.setLocation(645,190);
    }
}
