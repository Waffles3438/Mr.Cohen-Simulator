import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Mr Cohen is the teacher of the class
 * 
 * <a href="https://www.youtube.com/watch?v=C6UkVtPGKxc">Link to Sound</a>
 * Sound by ChilledKeebs
 * 
 * <a href="https://www.youtube.com/watch?v=vvxSErVEQGA">Link to Sound</a>
 * Sound by Meme Archive
 * 
 * @author Felix Zhao
 * @version 0.0.1
 */
public class MrCohen extends Person
{
    private Computer[] computerList;
    private Computer currentComputer;
    private int teachingTimer;
    private int callingTimer;
    private int angerMeter;
    private Computer startingComputer;
    private int talkingTimer;
    private Student talkStudent;
    private boolean frozen;
    private boolean brokeToday;
    private int dazeTimer;
    
    private int actCount = 0;
    private static GreenfootSound typing = new GreenfootSound("working.mp3");
    private static GreenfootSound anger = new GreenfootSound("anger.mp3");
    
    /**
     * Creates Mr Cohen
     *
     * @param computer Mr Cohen's computer
     * @Param startType The number that coresponds to the computer
     */
    public MrCohen(Computer computer, int startType) {
        setImage("mrcohen.png");
        computerList = new Computer[]{new Alienware(),  new Steamdeck(), new MacMini(), new Desktop()};
        currentComputer = computer;
        startingComputer = computer;
        computerList[startType] = currentComputer;
        teachingTimer = -1;
        callingTimer = -1;
        talkingTimer = -1;
        dazeTimer = -1;
        angerMeter = 0;
        speed = 3;
        talkStudent = null;
        frozen = false;
        brokeToday = false;
        getImage().scale(66, 66);
        getImage().rotate(-90);
        setRotation(90);
        typing.setVolume(75);
        anger.setVolume(50);
    }

    
    /**
     * Sets up Mr Cohen when added to the world
     *
     * @param w The world
     */
    public void addedToWorld(World w) {
        if (w instanceof FinishedWorld) {
            return;
        }
        newDay();
    }
    
    public void act() {
        actCount++;
        if (frozen) {
            return;
        }
        super.act();
        if(getWorld() instanceof FinishedWorld) return;
        if (teachingTimer > 0) {
            teachingTimer --;
        } else if (teachingTimer == 0) {
            teachingTimer--;
            getWorld().removeObject(speech);
            speech = null;
            // After teaching, if computer is broken call support
            boolean isBroken = false;
            for (Computer computer : computerList) {
                if (computer.isBroken()) {
                    isBroken = true;
                }
            }
            if (isBroken) {
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
        
        if (doingNothing() && currentPath.size() == 0) {
            setRotation(90);
            if (Greenfoot.getRandomNumber(500) == 0) {
                talkToStudent();
            } else if (currentComputer.getDurability() == 0 && !brokeToday) {
                rage(1);
                brokeToday = true;
            }
            
        }
        
        if (talkStudent != null && currentPath.size() == 0 && talkStudent.getPathSize() == 0 && talkingTimer == -1) {
            turnTowards(talkStudent);
            talkingTimer = 80;
            if (speech != null) {
                getWorld().removeObject(speech);
            }
            speech = new BubbleSpeech("talk_bubble.png");
            angerMeter = Math.max((int)(angerMeter-Math.sqrt(angerMeter)), 0);
            getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        }
        
        if (talkingTimer > 0) {
            talkingTimer--;
        } else if (talkingTimer == 0) {
            talkingTimer--;
            talkStudent = null;
            getWorld().removeObject(speech);
            speech = null;
            pathFind(360, 55, 0, true);
        }
        
        Puddle puddle = (Puddle)getOneIntersectingObject(Puddle.class);
        if(puddle != null){
            getWorld().removeObject(speech);
            speech = null;
            daze();
        }
        
        if (dazeTimer > 0) {
            dazeTimer--;
        } else if (dazeTimer == 0) {
            dazeTimer--;
            getWorld().removeObject(speech);
            speech = null;
            pathFind(360, 55, 0, true);
        }
        if(getX() == 360 && getY() == 55 && Greenfoot.getRandomNumber(600) == 0 && actCount >= 370){
            typing.play();
            actCount = 0;
        } else if (getX() != 360 && getY() != 55){
            typing.stop();
        } 
    }
    
    /**
     * Set volume
     * 
     * @param Volume New volume
     */
    public static void setAngerVolume(int volume){
        anger.setVolume(volume);
    }
    
    /**
     * Play typing sounds
     */
    public static void playTyping(){
        typing.play();
    }
    
    /**
     * Pause typing sounds
     */
    public static void pauseTyping(){
        typing.pause();
    }
    
    /**
     * Set typing volume
     * 
     * @param volume New volume for typing
     */
    public static void setTypingVolume(int volume){
        typing.setVolume(volume);
    }
    
    private void callSupport() {
        if(getWorld() instanceof FinishedWorld) return;
        Simulator world = (Simulator)getWorld();
        callingTimer = 80;
        if (speech != null) {
            world.removeObject(speech);
        }
        speech = new BubbleSpeech("calling_bubble.png");
        world.addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        for (Computer computer : computerList) {
            if (!computer.isBroken()) {
                continue;
            }
            callingTimer += 40;

            if (Greenfoot.getRandomNumber(100)+1 <= world.getSupportChance()) {
                computer.fixComputer();
            } else {
                angerMeter += 1;
            }
        }
        
    }
    
    /**
     * Teaches students increases their grades
     */
    private void teachStudents() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        
        for (Student student : students) {
            student.changeProjectedMark(Greenfoot.getRandomNumber(5)+3);
        }
        teachingTimer = 80;
        speech = new BubbleSpeech("study_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    /**
     * Makes Mr. Cohen rage
     */
    private void rage(int brokenCount) {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        angerMeter += 3*brokenCount;
        for (Student student : students) {
            student.changeProjectedMark(Greenfoot.getRandomNumber(angerMeter/10+5)-(angerMeter/10+5));
            if (angerMeter >= 100) {
                student.changeProjectedMark(-5);
            }
        }
        
        teachingTimer = 80;
        speech = new BubbleSpeech("angry_emotion.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        anger.play();
    }
    
    /**
     * Path finds to worst performing student and talks to them
     */
    private void talkToStudent() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        Student target = null;
        for (Student student : students) {
            if (student.canTalk() && (target == null || student.getProjectedMark() < target.getProjectedMark())) {
                target = student;
            }
        } 
        if (target != null && pathFind(target, 80, true)) {
            
            target.requestToTalk(this);
            talkStudent = target;
            
        }
        
    }
    
    /**
     * Method requestToTalk
     *
     *@param student The student that is requesting to talk
     */
    public void requestToTalk(Student student) {
        talkStudent = student;
    }
    
    /**
     * Cancels the talk request
     *
     */
    public void cancelTalk() {
        clearPath();
        talkStudent = null;
    }
    
    /**
     * Sets Mr Cohen for the beginning of a new day
     *
     */
    public void newDay() {
        frozen = false;
        boolean found = false;
        if (!startingComputer.isBroken() && currentComputer != startingComputer) {
            ((Simulator)getWorld()).updateComputer(startingComputer);
            currentComputer = startingComputer;
        }
        if (currentComputer.isBroken()) {
            angerMeter++;
            for (Computer computer : computerList) {
                if (!computer.isBroken()) {
                    currentComputer = computer;
                    ((Simulator)getWorld()).updateComputer(computer);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // switch to nothing
                // and have to check for nothing
                currentComputer = new NoComputer();
                ((Simulator)getWorld()).updateComputer(currentComputer);
            }
        }
        int brokenCount = 0;
        for (Computer computer : computerList) {
            if (computer.isBroken()) {
                brokenCount++;
            }
        }
        if (brokenCount > 0) {
            rage(brokenCount);
        } else {
            teachStudents();
            angerMeter = Math.max((int)(angerMeter-Math.sqrt(angerMeter)/2), 0);
        }
    }
    
    /**
     * Returns Mr Cohen to it's desk
     * The difference between this and newDay is that this gets called when the fade is at it's max and not when it's done
     *
     */
    public void returnToDesk() {
        setLocation(360, 55);
        getWorld().removeObject(speech);
        speech = null;
        clearPath();
        teachingTimer = -1;
        talkingTimer = -1;
        callingTimer = -1;
        dazeTimer = -1;
        angerMeter = Math.max(0, (int)(angerMeter-Math.sqrt(angerMeter)));
        talkStudent = null;
        setRotation(90);
        frozen = true;
        brokeToday = false;
    }
    
    /**
     * Returns the anger of Mr Cohen
     *
     * @return Returns the value of angerMeter
     */
    public int getAnger() {
        return angerMeter;
    }
    
    /**
     * Returns true of false depending on if Mr Cohen is doing anything
     *
     * @return Returns true if Mr Cohen is doing nothing
     */
    public boolean doingNothing() {
        return teachingTimer == -1 && callingTimer == -1 && talkingTimer == -1 && talkStudent == null && !frozen && currentPath.size() == 0 && dazeTimer == -1;
    }
    
    /**
     * Gets called when Mr Cohen gets robbed. The computer disappears
     *
     */
    public void getRobbed() {
        currentComputer.breakComputer();
        currentComputer = new NoComputer();
        ((Simulator)getWorld()).updateComputer(currentComputer);
    }
    
    /**
     * Dazes Mr Cohen
     *
     */
    public void daze() {
        cancelTalk();
        teachingTimer = -1;
        callingTimer = -1;
        talkingTimer = -1;
        getWorld().removeObject(speech);
        speech = new BubbleSpeech("dazed_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
        dazeTimer = 60;
        if (talkStudent != null) {
            talkStudent.cancelTalk();
        }
        
        angerMeter += 5;
    }
}
