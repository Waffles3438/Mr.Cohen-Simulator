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
    private int daysSinceAngry; 
    private Computer computer;
    private boolean isTeaching;
    private int teachingTimer;
    
    public MrCohen(Computer computer) {
        this.computer = computer;
        isTeaching = false;
        teachingTimer = 0;
    }
    
    public void addedToWorld(World w) {
        newDay();
    }
    
    public void act() {
        if (teachingTimer > 0) {
            teachingTimer --;
        } else if (teachingTimer == 0) {
            getWorld().removeObject(speech);
            speech = null;
            isTeaching = false;
        }
    }
    
    public int getDaysSinceAngry() {
        return daysSinceAngry;
    }
    
    public void teachStudents() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        
        for (Student student : students) {
            student.changedProjectedMark(Greenfoot.getRandomNumber(5)+1);
        }
        isTeaching = true;
        teachingTimer = 80;
        speech = new BubbleSpeech("study_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    public void rage() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        
        for (Student student : students) {
            student.changedProjectedMark(Greenfoot.getRandomNumber(5)-10);
        }
        isTeaching = true;
        teachingTimer = 80;
        speech = new BubbleSpeech("angry_emotion.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    public void newDay() {
        setLocation(360, 35);
        if (computer.isBroken()) {
            rage();
        } else {
            teachStudents();
        }
    }
}
