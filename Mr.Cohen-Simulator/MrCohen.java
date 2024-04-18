import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class MrCohen here.
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
 */
public class MrCohen extends Person
{
    private Computer[] computerList;
    private Computer computer;
    private int teachingTimer;
    private int callingTimer;
    private int angerMeter;
    
    /**
     * Creates Mr Cohen
     *
     * @param computer Mr Cohen's computer
     * @Param startType The number that coresponds to the computer
     */
    public MrCohen(Computer computer, int startType) {
        computerList = new Computer[]{new Alienware(), new Desktop(), new MacMini(), new Steamdeck()};
        this.computer = computer;
        computerList[startType] = this.computer;
        teachingTimer = -1;
        callingTimer = -1;
        angerMeter = 0;
        speed = 2;
    }
    
    public void addedToWorld(World w) {
        newDay();
    }
    
    public void act() {
        if (teachingTimer > 0) {
            teachingTimer --;
        } else if (teachingTimer == 0) {
            teachingTimer--;
            getWorld().removeObject(speech);
            speech = null;
            // After teaching, if computer is broken call support
            
            if (computer.isBroken()) {
                callSupport();
            }
        }
        
        if (callingTimer > 0) {
            callingTimer --;
        } else if (callingTimer == 0) {
            callingTimer--;
            getWorld().removeObject(speech);
            speech = null;
        }
    }
    
    private void callSupport() {
        Simulator world = (Simulator)getWorld();
        callingTimer = 80;
        if (speech != null) {
            world.removeObject(speech);
        }
        speech = new BubbleSpeech("calling_bubble.png");
        world.addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        
        if (Greenfoot.getRandomNumber(100)+1 <= world.getSupportChance()) {
            
        }
    }
    
    private void teachStudents() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        
        for (Student student : students) {
            student.changedProjectedMark(Greenfoot.getRandomNumber(5)+1);
        }
        teachingTimer = 80;
        speech = new BubbleSpeech("study_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    private void rage() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        angerMeter += 5;
        for (Student student : students) {
            student.changedProjectedMark(Greenfoot.getRandomNumber(angerMeter/15+5)-(angerMeter/15+5));
            if (angerMeter >= 100) {
                student.changedProjectedMark(-5);
            }
        }
        
        teachingTimer = 80;
        speech = new BubbleSpeech("angry_emotion.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    /**
     * Sets Mr Cohen for the beginning of a new day
     *
     */
    public void newDay() {
        setLocation(360, 35);
        getWorld().removeObject(speech);
        speech = null;
        teachingTimer = -1;
        callingTimer = -1;
        if (computer.isBroken()) {
            rage();
        } else {
            teachStudents();
        }
    }
    
    public int getAnger() {
        return angerMeter;
    }
}
