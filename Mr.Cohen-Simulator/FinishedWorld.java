import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class FinishedWorld here.
 * 
 * @author Andy Feng
 * @version 0.0.1 (April 25th, 2024)
 */
public class FinishedWorld extends World
{
    private int averageMark;
    private int numDays;
    private int studentIQ;
    private int customerSupportChance;
    private int laptopBreakingChance;

    private boolean hasRobber;
    private boolean hasJanitor;
    private boolean chaosMode;
    
    private boolean added = false;
    
    private Image pizza;
    
    private MrCohen cohen;
    private BubbleSpeech happy = new BubbleSpeech("happy_emotion0.png");
    private BubbleSpeech sleepy = new BubbleSpeech("happy_emotion1.png");
    private BubbleSpeech rage = new BubbleSpeech("angry_emotion.png");
    
    private SimpleTimer timer = new SimpleTimer();
    /**
     * Constructor for objects of class FinishedWorld.
     * 
     */
    public FinishedWorld(int averageMark, int numDays, int studentIQ, int customerSupportChance, int laptopBreakingChance, boolean hasRobber, boolean hasJanitor, boolean chaosMode)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1, true); 
        GreenfootImage image = new GreenfootImage(1260, 720);
        image.setColor(new Color(255, 255, 255));
        image.fillRect(0, 0, getWidth(), getHeight());
        image.drawImage(new GreenfootImage("school_image.png"), 0, 0);
        image.setColor(new Color(0, 0, 0));
        image.fillRect(getWidth()/3*2, 0, getWidth(), getHeight());
        setBackground(image);
        
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Student(studentIQ, 354 + i*211, 360 + j*146 + 132), 354 + i*211, 360 + j*146 + 132);
            } 
        }
        
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                addObject(new Image(125, 60), 354 + i*211, 360 + j*146+60);
            }
        }
        addObject(new Image(75, 317), 807, 283);
        addObject(new Image(275, 85), 351, 140);
        cohen = new MrCohen(new Alienware(), 0);
        addObject(cohen, 360, 55);
        
        this.averageMark = averageMark;
    }
    
    public void act(){
        // the average mark the class has determine the type of ending 
        // of the simulation.
        if(averageMark > 100) averageMark = 100;
        if(averageMark < 0) averageMark = 0;
        if(averageMark >= 85) {
            endingOne();
        } else if(averageMark >= 65 && averageMark < 85) {
            endingTwo();
        } else {
            endingThree();
        }
    }
    
    private void endingOne(){
        if(!added){
            addObject(happy, cohen.getX()+cohen.getImage().getWidth()/2, cohen.getY()-cohen.getImage().getHeight());
            added = true;
        }
        spawnPizza();
    }
    
    private void endingTwo(){
        
    }
    
    private void endingThree(){
        
    }
    
    private void spawnPizza(){
        if(timer.millisElapsed() < 100) return;
        timer.mark();
        int xLocation = Greenfoot.getRandomNumber(getWidth()/3*2 - 5) + 5;
        pizza = new Image("Pizza.png");
        pizza.getImage().scale(70, 70);
        addObject(pizza, xLocation, 0);
    }
}
