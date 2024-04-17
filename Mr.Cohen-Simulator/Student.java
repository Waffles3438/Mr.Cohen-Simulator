import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * <div>Students will walk around in the simulation and doing different tasks</div>
 * They have different IQs
 * 
 * @author Felix Zhao 
 * @author Benny Wang
 * @version 1.0.0
 */
public class Student extends Person
{
    private int iq;
    private int randomMoveCounter = 0;
    private int randomMoveCooldown = 240;
    private double projectedMark;
    private static int variation;
    private boolean counter = false;
    private boolean goingBackToWork = false;
    private int deskX;
    private int deskY;
    private boolean atDesk;

    private int workTimer;

    private int wasteTimeCounter;

    private int talkingCounter;
    private Student talkStudent;

    
    /**
     * Creates a student which an iq close to the given iq
     *
     * @param iq The iq to set the student around at
     * @param deskX The x position of the students desk
     * @param deskY The y position of the students desk
     */
    public Student(int iq, int deskX, int deskY) {
        this.iq = iq + Greenfoot.getRandomNumber(40)-20;
        projectedMark = 60 * iq / 100;
        variation = Greenfoot.getRandomNumber(9) + 1;
        setImage("student" + variation + ".png");
        getImage().scale(66, 66);
        getImage().rotate(90);
        setRotation(-90);
        this.deskX = deskX;
        this.deskY = deskY;
        atDesk = true;
        //isWorking = false;
        workTimer = -1;
        //isWastingTime = false;
        wasteTimeCounter = -1;
        //isTalking = false;
        talkStudent = null;
        talkingCounter = -1;
    }
    
    /**
     * Act - do whatever the Student wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {   
        super.act();
        if(Greenfoot.getRandomNumber(200) == 0){
            slowerOrFaster();
        }
        
        if (currentPath.size() == 0) {
            randomMoveCounter++;
        }
        
        if (randomMoveCounter >= randomMoveCooldown && !goingBackToWork && doingNothing()) {
            int task = Greenfoot.getRandomNumber(2);
            if (task == 0) {
                moveRandom();
            } else if (task == 1) {
                talkToSomeone();
            }
            
        }
        
        if(Greenfoot.getRandomNumber(500) == 0 && !atDesk && !goingBackToWork && doingNothing()){
            goingBackToWork = true;
            pathFind(deskX, deskY, 0, true);
        }
        
        if (goingBackToWork && currentPath.size() == 0) {
            goingBackToWork = false;
            atDesk = true;
            setRotation(-90);
            randomMoveCounter = 0;
        }

        if (atDesk && doingNothing()) {
            // Added a curve so people with low iq don't study way less
            double chance = Math.sqrt(Greenfoot.getRandomNumber(iq))*10;
            if (chance >= 70) {
                work();
            } else if (chance <= 30) {
                wasteTime();
            } else if (chance <= 10) {
                moveRandom();
            }
            
        }
        
        if (workTimer > 0) {
            workTimer -= 1;
        } else if (workTimer == 0) {
            workTimer--;
            getWorld().removeObject(speech);
            speech = null;

            //moveRandom();
        }
        
        if (wasteTimeCounter > 0) {
            wasteTimeCounter --;
        } else if (wasteTimeCounter == 0) {
            wasteTimeCounter --;
            getWorld().removeObject(speech);
            speech = null;

        }
        
        
        
        if (talkStudent != null && currentPath.size() == 0 && talkStudent.getPathSize() == 0 && talkingCounter == -1) {
            turnTowards(talkStudent);
            talkingCounter = 80;
            if (speech != null) {
                getWorld().removeObject(speech);
            }
            speech = new BubbleSpeech("talk_bubble.png");
            if (Greenfoot.getRandomNumber(2) == 0) {
                projectedMark += (iq + talkStudent.getIQ()) / 100.0;
            } else {
                projectedMark += 100.0 / (iq + talkStudent.getIQ());
                speech = new BubbleSpeech("happy_emotion0.png");
            }
            getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        }
        
        if (talkingCounter > 0) {
            talkingCounter--;
        } else if (talkingCounter == 0) {
            talkingCounter--;
            talkStudent = null;
            getWorld().removeObject(speech);
            speech = null;
            moveRandom();
        }
    }
    
    protected void work() {
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        workTimer = Greenfoot.getRandomNumber(iq)+50;
        projectedMark += workTimer / 100.0;
        speech = new BubbleSpeech("study_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    private void moveRandom() {
        atDesk = false;
        if (pathFind(Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(640)+40, 0, true)) {
            randomMoveCounter = 0;
        } else {
            randomMoveCounter = randomMoveCooldown / 2;
        }
    }
    
    private void wasteTime() {
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        if(Greenfoot.getRandomNumber(2) == 0){
            speech = new BubbleSpeech("happy_emotion0.png");
        } else {
            speech = new BubbleSpeech("happy_emotion1.png");
        }
        
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        wasteTimeCounter = Greenfoot.getRandomNumber(80)+40;
        projectedMark -= (double)wasteTimeCounter / iq;
    }
    
    private void talkToSomeone() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        for (Student student : students) {
            if (!student.isTalking() && student != this) {
                if (pathFind(student, 75, true)) {
                    student.requestToTalk(this);
                    talkStudent = student;
                } 
                
                break;
            }
        }
        
        
    }
    
    private boolean doingNothing() {
        return workTimer == -1 && wasteTimeCounter == -1 && talkStudent == null && talkingCounter == -1;
    }
    
    /**
     * A student will request another student to talk
     *
     * @param student The student that requested to talk
     */
    public void requestToTalk(Student student) {
        clearPath();
        goingBackToWork = false;
        talkStudent = student;
    }
    
    /**
     * Returns students to desk
     */
    public void returnToDesk(){
        setLocation(deskX, deskY);
        setRotation(-90);
        clearPath();
        workTimer = -1;
        wasteTimeCounter = -1;
        talkStudent = null;
        talkingCounter = -1;
        getWorld().removeObject(speech);
        speech = null;
        //System.out.println("should return to desk");
    }
    
    /**
     * Returns if the student is talking or not
     *
     */
    public boolean isTalking() {
        return talkStudent != null || talkingCounter >= 0;
    }
    
    /**
     * Updates the projected mark of the student (Increase/decrease)
     *
     * @param amountLearned How much to change it by, the amount actually gained/lost is determined by IQ
     */
    public void changedProjectedMark(double amountLearned) {
        if (amountLearned > 0) {
            projectedMark += amountLearned * iq / 150.0;
        } else {
            projectedMark += amountLearned * 100.0 / iq;
        }

    }
    
    /**
     * Returns the student's projected mark
     *
     */
    public double getProjectedMark() {
        return projectedMark;
    }
    
    public int getIQ() {
        return iq;
    }
}
