import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * <div>Students will walk around in the simulation and doing different tasks</div>
 * They have different IQs<br>
 * 
 * Edited slightly by Andy Feng<br>
 * Art by Benny Wang
 * 
 * @author Felix Zhao 
 * @version 0.0.1
 * 
 */
public class Student extends Person
{
    private int iq;
    private int randomMoveCounter = 0;
    private int randomMoveCooldown = 360;
    protected double projectedMark;
    private static int variation;
    private boolean counter = false;
    private boolean goingBackToWork = false;
    private int deskX;
    private int deskY;
    private boolean atDesk;

    private int slippingTimer;
    private int workTimer;
    private int wasteTimeCounter;
    private int dazeTimer;

    private int talkingTimer;
    private Person talkPerson;
    private boolean frozen;

    
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
        workTimer = -1;
        wasteTimeCounter = -1;
        talkPerson = null;
        talkingTimer = -1;
        slippingTimer = -1;
        dazeTimer = -1;
        frozen = false;
    }
    
    /**
     * This act method controls the logic of the student and how they behave
     */
    public void act(){
        if (frozen) {
          return;
        }
        
        super.act();
        
        checkFall();
        if(slippingTimer > 0) {
            setRotation(getRotation() + 5);
            slippingTimer--;
            return;
        } else if (slippingTimer == 0) {
            slippingTimer--;
        }
        
        if (dazeTimer > 0) {
            dazeTimer--;
        } else if (dazeTimer == 0) {
            dazeTimer--;
            getWorld().removeObject(speech);
            speech = null;
        }
        
        handleRandomSpeedChange();
        handleRandomMovement();
        handleReturnToDesk();
        handleWorkBehavior();
        
        handleTalking();
        handleTimers();
    }
        
    /**
     * Handles random speed changes
     */
    private void handleRandomSpeedChange() {
        if(Greenfoot.getRandomNumber(200) == 0){
            slowerOrFaster();
        }
    }
    
    /**
     * Handles random movement
     */
    private void handleRandomMovement() {
        if (currentPath.size() == 0) {
            randomMoveCounter++;
            if (getWorld() instanceof FinishedWorld) {
                randomMoveCounter += 10;
            }
        }
        //System.out.println(randomMoveCounter);
        if (randomMoveCounter >= randomMoveCooldown && !goingBackToWork && doingNothing()) {
            int task = Greenfoot.getRandomNumber(3);
            if (task == 0) {
                moveRandom();
            } else if (task == 1 && getWorld() instanceof Simulator) {
                talkToSomeone();
            } else if (task == 2 && getWorld() instanceof Simulator) {
                talkToCohen();
            }
        }
    }
    
    // Handles the returning to their desk
    private void handleReturnToDesk() {
        if (getWorld() instanceof FinishedWorld) {
            return;
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
    }
    
    /**
     * Handles what the student will do at their desk
     */
    private void handleWorkBehavior() {
        if (atDesk && doingNothing()) {
            double chance = Math.sqrt(Greenfoot.getRandomNumber(Math.max(iq, 1)))*10;
            if (chance >= 70 && getWorld() instanceof Simulator) {
                work();
            } else if (chance <= 30 && getWorld() instanceof Simulator) {
                wasteTime();
            } else if (chance <= 10) {
                moveRandom();
            }
        }
    }
    
    /**
     * Handles some of the timers
     */
    private void handleTimers() {
        if (workTimer > 0) {
            workTimer -= 1;
        } else if (workTimer == 0) {
            workTimer--;
            getWorld().removeObject(speech);
            speech = null;
        }
        
        if (wasteTimeCounter > 0) {
            wasteTimeCounter --;
        } else if (wasteTimeCounter == 0) {
            wasteTimeCounter --;
            getWorld().removeObject(speech);
            speech = null;
        }
    }
    
    /**
     * Handles talking
     */
    private void handleTalking() {
        if (talkPerson != null && currentPath.size() == 0 && talkPerson.getPathSize() == 0 && talkingTimer == -1) {
            turnTowards(talkPerson);
            talkingTimer = 80;
            if (speech != null) {
                getWorld().removeObject(speech);
            }
            speech = new BubbleSpeech("talk_bubble.png");
            if (talkPerson instanceof Student) {
                Student talkStudent = (Student) talkPerson;
                if (Greenfoot.getRandomNumber(2) == 0) {
                    projectedMark += (iq + talkStudent.getIQ()) / 100.0;
                } else {
                    projectedMark += 100.0 / (iq + talkStudent.getIQ());
                    speech = new BubbleSpeech("happy_emotion0.png");
                }
            } else if (talkPerson instanceof MrCohen) {
                changeProjectedMark(10);
            }
            workTimer = -1;
            wasteTimeCounter = -1;
            
            getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        }
        
        if (talkingTimer > 0) {
            talkingTimer--;
        } else if (talkingTimer == 0) {
            talkingTimer--;
            
            talkPerson = null;
            getWorld().removeObject(speech);
            speech = null;
            goingBackToWork = true;
            pathFind(deskX, deskY, 0, true);
        }
    }

    /**
     * Makes them work
     */
    private void work() {
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        workTimer = Greenfoot.getRandomNumber(iq)+50;
        projectedMark += workTimer / 200.0;
        speech = new BubbleSpeech("study_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    /**
     * Make students move to random location
     */
    private void moveRandom() {
        atDesk = false;
        if (pathFind(Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(640)+40, 0, true)) {
            randomMoveCounter = 0;
        } else {
            randomMoveCounter = randomMoveCooldown / 2;
        }
    }
    
    /**
     * Makes students waste time
     */
    private void wasteTime() {
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        int randomValue = Greenfoot.getRandomNumber(2);
        if(randomValue == 0) {
            speech = new BubbleSpeech("happy_emotion0.png");
        } else {
            speech = new BubbleSpeech("happy_emotion1.png");
        }
        
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        wasteTimeCounter = Greenfoot.getRandomNumber(80)+40;
        projectedMark -= (double)wasteTimeCounter / iq;
        
        if(getWorld() instanceof FinishedWorld) return;
        if (((Simulator)getWorld()).chaosEnabled() && randomValue == 0 && Greenfoot.getRandomNumber(2) == 0) {
            getWorld().addObject(new Book(this, 6, Greenfoot.getRandomNumber(720)+60, Greenfoot.getRandomNumber(600)+60), getX(), getY());
        }
    }
    
    /**
     * Spawns in a happy face, used in the finished world
     *
     */
    public void happy() {
        if (speech != null) {
            getWorld().removeObject(speech);
        }
        speech = new BubbleSpeech("happy_emotion0.png");
        
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    /**
     * Spawns in a sleepy face, used in the finished world
     *
     */
    public void feelNothing(){
        if(speech != null){
            getWorld().removeObject(speech);
        }
        speech = new BubbleSpeech("happy_emotion1.png");
        
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        
    }
    
    /**
     * Spawns in a sad face, used in the finished world
     *
     */
    public void sad(){
        if(speech != null){
            getWorld().removeObject(speech);
        }
        speech = new BubbleSpeech("sad_emotion.png");
        
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        
    }
    
    /** Trys to talk to someone
     * Trys to make students talk to someone
     */
    private void talkToSomeone() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        for (Student student : students) {
            if (student.canTalk() && student != this) {
                if (pathFind(student, 80, true)) {
                    student.requestToTalk(this);
                    talkPerson = student;
                    atDesk = false;
                } 
                break;
            }
        }
    }
    
    /**
     * Students try to talk to Mr. Cohen
     */
    private void talkToCohen() {
        MrCohen cohen = (getWorld().getObjects(MrCohen.class)).get(0);
        if (cohen.doingNothing()) {
            atDesk = false;
            cohen.requestToTalk(this);
            pathFind(cohen, 80, true);
            talkPerson = cohen;
        }
    }
    
    /**
     * Returns true if the student is doing nothing
     *
     * @return Returns true if doing nothing
     */
    public boolean doingNothing() {
        return workTimer == -1 && wasteTimeCounter == -1 && talkPerson == null && talkingTimer == -1 && slippingTimer == -1 && dazeTimer == -1;
    }
    
    /**
     * A student will request another student to talk
     *
     * @param student The student that requested to talk
     */
    public void requestToTalk(Person person) {
        clearPath();
        goingBackToWork = false;
        atDesk = false;
        talkPerson = person;
    }
    
    /**
     * Cancel Talking
     *
     */
    public void cancelTalk() {
        clearPath();
        talkPerson = null;
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
        talkPerson = null;
        talkingTimer = -1;
        slippingTimer = -1;
        dazeTimer = -1;
        getWorld().removeObject(speech);
        speech = null;
        atDesk = true;
    }
    
    /**
     * Returns if the student is talking or not
     *
     * @return Returns true if the person can talk
     */
    public boolean canTalk() {
        return talkPerson == null && talkingTimer == -1 && dazeTimer == -1 && slippingTimer == -1;
    }
    
    /**
     * Updates the projected mark of the student (Increase/decrease)
     *
     * @param amountLearned How much to change it by, the amount actually gained/lost is determined by IQ
     */
    public void changeProjectedMark(double amountLearned) {
        if (amountLearned > 0) {
            projectedMark += amountLearned * iq / 150.0;
        } else {
            projectedMark += amountLearned * 100.0 / iq;
        }
    }
    
    /**
     * Returns the student's projected mark
     *
     * @return Returns projected mark
     */
    public double getProjectedMark() {
        return projectedMark;
    }
    
    /**
     * Returns IQ
     *
     * @return Returns the IQ of the student
     */
    public int getIQ() {
        return iq;
    }
    
    /**
     * Check for puddles
     */
    private void checkFall(){
        Puddle puddle = (Puddle)getOneIntersectingObject(Puddle.class);
        if(puddle != null){
            getWorld().removeObject(puddle);
            slippingTimer = 80;
            projectedMark -= 5;
            if (talkPerson != null) {
                cancelTalk();
            }
            getWorld().removeObject(speech);
            speech = null;
        }
    }
    
    /**
     * Dazes the student
     *
     */
    public void daze() {
        cancelTalk();
        atDesk = false;
        goingBackToWork = false;
        workTimer = -1;
        wasteTimeCounter = -1;
        talkingTimer = -1;
        getWorld().removeObject(speech);
        speech = new BubbleSpeech("dazed_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        dazeTimer = 60;
        if (talkPerson != null) {
            if (talkPerson instanceof Student) {
                ((Student)talkPerson).cancelTalk();
            } else if (talkPerson instanceof MrCohen) {
                ((MrCohen)talkPerson).cancelTalk();
            }
        }
    }
    
    /**
     * Sets the frozen state of the person. 
     * If frozen, students have no interactions
     *
     */
    public void freezeState(boolean state) {
        frozen = state;
    }
}
