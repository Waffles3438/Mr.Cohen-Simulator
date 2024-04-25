import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Mr Cohen is the teacher of the class
 * 
 * @author Felix Zhao
 * @version (a version number or a date)
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
    
    
    /**
     * Creates Mr Cohen
     *
     * @param computer Mr Cohen's computer
     * @Param startType The number that coresponds to the computer
     */
    public MrCohen(Computer computer, int startType) {
        computerList = new Computer[]{new Alienware(),  new Steamdeck(), new MacMini(), new Desktop()};
        currentComputer = computer;
        startingComputer = computer;
        computerList[startType] = currentComputer;
        teachingTimer = -1;
        callingTimer = -1;
        talkingTimer = -1;
        angerMeter = 0;
        speed = 3;
        talkStudent = null;
        setRotation(90);
        frozen = false;
        brokeToday = false;
        getImage().scale(66, 66);
    }
    
    public void addedToWorld(World w) {
        newDay();
    }
    
    public void act() {
        if (frozen) {
            return;
        }
        super.act();
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
            angerMeter = Math.max(angerMeter-1, 0);
            
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
    }
    
    private void callSupport() {
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
    
    private void teachStudents() {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        
        for (Student student : students) {
            student.changeProjectedMark(Greenfoot.getRandomNumber(7)+3);
        }
        teachingTimer = 80;
        speech = new BubbleSpeech("study_bubble.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
    private void rage(int brokenCount) {
        ArrayList<Student> students = (ArrayList<Student>)getWorld().getObjects(Student.class);
        angerMeter += brokenCount;
        for (Student student : students) {
            student.changeProjectedMark(Greenfoot.getRandomNumber(angerMeter/10+5)-(angerMeter/10+5));
            if (angerMeter >= 100) {
                student.changeProjectedMark(-5);
            }
        }
        
        teachingTimer = 80;
        speech = new BubbleSpeech("angry_emotion.png");
        getWorld().addObject(speech, getX()+getImage().getWidth()/2, getY()-getImage().getHeight());
    }
    
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
                ((Simulator)getWorld()).updateComputer(new NoComputer());
            }
        }
        int brokenCount = 0;
        for (Computer computer : computerList) {
            if (computer.isBroken()) {
                brokenCount++;
            }
        }
        if (brokenCount > 1) {
            rage(brokenCount);
        } else {
            teachStudents();
            angerMeter = Math.max(angerMeter-5, 0);
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
        return teachingTimer == -1 && callingTimer == -1 && talkingTimer == -1 && talkStudent == null && !frozen;
    }
}
