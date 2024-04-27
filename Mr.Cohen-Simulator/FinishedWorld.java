import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * This is the finished world which appears after the simulation. Depending on the results, there are different endings
 * There are three endings in total:   
 * <ul>
 * <li>Ending one: students having pizza party, if <code>averageMark >= 85</code>
 * <li>Ending two: students walking around in the classroom while Mr.Cohen is sleepy, if <code>65>= averageMark <85</code>
 * <li>Ending three: students feel sad while Mr.Cohen is angry, if <code>averageMark < 65</code>
 * </ul>
 * </p>
 * <p>
 * There are two buttons at the right hand side:
 * <ul>
 * <li> Main menu button(<code>backToMenue</code>), this can redirect the user to the <code>titleScreen</code>
 * <li> Try again button(<code>tryAgain</code>), this can redirect the user to the <code>Modifier</code>
 * </ul>
 * </p>
 * 
 * Edited By Dylan Dinesh<br>
 * <p>
 * <a href="https://www.youtube.com/watch?v=jRtDGwmgCR8">Link to music</a>
 * Music by Nintendo from New Super Mario Bros. Wii
 * </p>
 * <a href="https://www.youtube.com/watch?v=Y2qFzWLlOi8">Link to music</a>
 * Music by Nintendo from New Super Mario Bros. Wii
 * <p>
 * <a href="https://www.youtube.com/watch?v=po-0n1BKW2w">Link to music</a>
 * Music by Nintendo Wii
 * </p>
 * <p>
 * <a hreft="https://www.freepik.com/free-vector/colorful-round-tasty-pizza_3799722.htm#query=pizza&position=3&from_view=keyword&track=sph&uuid=c6396b5e-3e77-4d78-9945-a6180a7daebd"> Link to Pizza Image</a>
 * Art by macrovector
 * </p>
 * 
 * <a href="https://giphy.com/stickers/water-rain-raining-Wmp1EOzVybWd13s5DB"> Link to rain</a>
 * 
 * @author Andy Feng
 * @author Felix Zhao
 * @version 0.0.1 (April 25th, 2024)
 */
public class FinishedWorld extends World
{
    private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 128); // Translucent black color
    private final int OVERLAY_WIDTH = 2 * getWidth() / 3; // Width covering 2/3 of the screen
    private final int OVERLAY_HEIGHT = getHeight();
    private final int OVERLAY_X = getWidth() / 3; // X-coordinate to center the overlay horizontally

    protected int averageMark;
    private int numDays;
    private int studentIQ;
    private int customerSupportChance;
    private int computerBreakingChance;
    private TitleScreen titleScreen;

    private boolean hasRobber;
    private boolean hasJanitor;
    private boolean chaosMode;

    private boolean added = false;

    private Image pizza;
    private Label displayText = new Label("", 30);
    private Button backToMenu = new Button("menu", 3, ".png");
    private Button tryAgain = new Button("tryagain", 3, ".png");

    private MrCohen cohen;
    private BubbleSpeech happy = new BubbleSpeech("happy_emotion0.png");
    private BubbleSpeech sleepy = new BubbleSpeech("happy_emotion1.png");
    private BubbleSpeech rage = new BubbleSpeech("angry_emotion.png");
    private GifImage rain = new GifImage("rain.gif");
    private static GreenfootSound music;

    private ArrayList<Student> students;
    private HashMap<Student, Image> studentPizzaMap = new HashMap<>();
    private HashMap<Student, String> studentEmotions = new HashMap<>();

    /**
     * Constructor for objects of class FinishedWorld.
     * 
     * @param int averageMark: average mark of all students after the simulation is done
     * @param int numDays: number of days during the simulation
     * @param int studentIQ: average IQ of students during the simulation
     * @param int customerSupportChance: chance of customer support gets back to Mr.Cohen during the simulation
     * @param int computerBreakingChance: chance of Mr.Cohen's computer being damaged or stolen during the simulation
     * @param boolean hasRobber: whether or not the simulation contains robber
     * @param boolean hasJanitor: whether or not the simulation contains janitor
     * @param boolean chaosMode: whether or not chaos mode is activated
     * @param TitleScreen titleScreen: the user will be redirect to this titleWorld if <code>mainMenu</code> button is clicked
     */
    public FinishedWorld(int averageMark, int numDays, int studentIQ, int customerSupportChance, int computerBreakingChance, boolean hasRobber, boolean hasJanitor, boolean chaosMode, TitleScreen titleScreen)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1260, 720, 1, true); 
        if(averageMark > 100) averageMark = 100;
        if(averageMark < 0) averageMark = 0;
        
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

        students = (ArrayList<Student>) getObjects(Student.class);
        if (averageMark >= 65) {
            addObject(new Confetti(getWidth()*2/3, getHeight()), getWidth()/3, getHeight()/2);
        } 

        if(averageMark >= 85){
            music = new GreenfootSound("party.mp3");
        } else if (averageMark >= 65 && averageMark < 85) {
            music = new GreenfootSound("mid.mp3");
        } else {
            music = new GreenfootSound("class-did-bad.mp3");
        }
        this.averageMark = averageMark;
        this.numDays = numDays;
        this.studentIQ = studentIQ;
        this.customerSupportChance = customerSupportChance;
        this.computerBreakingChance = computerBreakingChance;
        this.hasRobber = hasRobber;
        this.hasJanitor = hasJanitor;
        this.chaosMode = chaosMode;
        music.setVolume((int) PauseScreen.getVolume() / 4);
        music.playLoop();
        this.titleScreen = titleScreen;
        addObject(backToMenu, getWidth()*5/6, getHeight() - backToMenu.getImage().getHeight()/2 - 15);
        addObject(tryAgain, getWidth()*5/6, tryAgain.getImage().getHeight() / 2 + 15);
    }
    
    /**
     * Stop the music which is currently playing
     */
    public void stopped(){
        music.pause();
    }
    
    /**
     * Start playing the music when the FinishedWorld is initialized
     */
    public void started(){
        music.playLoop();
    }

    /**
     * this act method determines the ending of the simulation based under the average mark of the simulation.
     * <br><br>
     * This method also check whether or not buttons are clicked when the program is running 
     */
    public void act(){
        // the average mark the class has determine the type of ending of the simulation.
        if(averageMark >= 85) {
            endingOne();
        } else if(averageMark >= 65 && averageMark < 85) {
            endingTwo();
        } else {
            endingThree();
        }
        checkButton();
    }

    private void endingOne() {
        if (!added) {
            showStats();
            // Add the happy speech bubble
            addObject(happy, cohen.getX() + cohen.getImage().getWidth() / 2, cohen.getY() - cohen.getImage().getHeight()+40);
            // Add pizzas to students
            
            for (Student student : students) {
                Image pizza = new Image("Pizza.png");
                student.happy();
                pizza.getImage().scale(45, 45);
                addObject(pizza, student.getX(), student.getY() - student.getImage().getHeight() / 2 - pizza.getImage().getHeight() / 2 - 10);
                studentPizzaMap.put(student, pizza);
            }
            // Add a flashing overlay
            addFlashingOverlay();
            added = true;
        }
        // Update pizzas position
        updatePizzas();
        // Update flashing overlay
        updateFlashingOverlay();
        addObject(displayText, getWidth() * 5/6, getHeight() / 2);
    }

    private Image overlay;
    private void addFlashingOverlay() {
        GreenfootImage overlayImage = new GreenfootImage(OVERLAY_WIDTH, OVERLAY_HEIGHT);
        overlayImage.setColor(OVERLAY_COLOR);
        overlayImage.fillRect(0, 0, OVERLAY_WIDTH, OVERLAY_HEIGHT);
        overlay = new Image(overlayImage);
        addObject(overlay, OVERLAY_X, OVERLAY_HEIGHT / 2); // Add the overlay at the center of the screen
    }

    private SimpleTimer timer = new SimpleTimer();
    private void updateFlashingOverlay() {
        GreenfootImage overlayImage = new GreenfootImage(OVERLAY_WIDTH, OVERLAY_HEIGHT);
        //Change overlay color with a flashing effect
        if(timer.millisElapsed() < 100) return;
        timer.mark();
        int red = 200 + Greenfoot.getRandomNumber(56); // Random red color component in the range 128-255
        int green = 200 + Greenfoot.getRandomNumber(56); // Random green color component in the range 128-255
        int blue = 200 + Greenfoot.getRandomNumber(56); // Random blue color component in the range 128-255
        
        overlayImage.setColor(new Color(red, green, blue, 120)); // Set the color with transparency
        overlayImage.fillRect(0, 0, overlayImage.getWidth(), overlayImage.getHeight()); // Fill the overlay with the color
        overlay.setImage(overlayImage);
    }

    private void updatePizzas() {
        for (Student student : studentPizzaMap.keySet()) {
            Image pizza = studentPizzaMap.get(student);
            // Adjust these offsets as necessary
            int pizzaOffsetX = student.getImage().getWidth() / 2 + pizza.getImage().getWidth() / 2 - 10;  // Offset to put pizza in front of the student
            int pizzaOffsetY = 0;  // No vertical offset

            // Calculate the new pizza position based on student rotation
            double angleRadians = Math.toRadians(student.getRotation());
            int dx = (int) (pizzaOffsetX * Math.cos(angleRadians));  // horizontal shift based on rotation
            int dy = (int) (pizzaOffsetX * Math.sin(angleRadians));  // vertical shift based on rotation

            // Set pizza location relative to the student's front side
            pizza.setLocation(student.getX() + dx, student.getY() + dy);
            pizza.setRotation(student.getRotation());
        }
    }

    // Meh Ending
    private void endingTwo() {
        if (!added) {
            showStats();
            addObject(displayText, getWidth() * 5/6, getHeight() / 2);
            addObject(sleepy, cohen.getX() + cohen.getImage().getWidth() / 2, cohen.getY() - cohen.getImage().getHeight());
    
            for (Student student : students) {
                if (!studentEmotions.containsKey(student)) {
                    int emotion = Greenfoot.getRandomNumber(3); // Generates a random number (0, 1, 2)
                    if (emotion == 0) {
                        student.happy();
                        studentEmotions.put(student, "happy");
                    } else if (emotion == 1) {
                        student.feelNothing(); // Assuming there is a 'neutral' method in the Student class
                        studentEmotions.put(student, "neutral");
                    } else {
                        student.sad();
                        studentEmotions.put(student, "sad");
                    }
                } else {
                    // Apply the stored emotion
                    switch (studentEmotions.get(student)) {
                        case "happy":
                            student.happy();
                            student.speed = 4;
                            break;
                        case "neutral":
                            student.feelNothing();
                            student.speed = 1.5; 
                            break;
                        case "sad":
                            student.sad();
                            student.freezeState(true);
                            break;
                    }
                }
            }
            added = true;
        }
    }


    // Sad ending
    private void endingThree(){
        if (!added) {
            addObject(displayText, getWidth() * 5/6, getHeight() / 2);
            showStats();
            addObject(displayText, getWidth() * 5/6, getHeight() / 2);
            // Add the raging bubble to Mr.Cohen
            addObject(rage, cohen.getX() + cohen.getImage().getWidth() / 2, cohen.getY() - cohen.getImage().getHeight()+40);
            for (Student student : students) {
                //make sure every student is not moving
                student.freezeState(true);
                student.sad();
            }
            // Add a blue overlay to show sadness
            added = true;
            GreenfootImage overlayImage = new GreenfootImage(OVERLAY_WIDTH, OVERLAY_HEIGHT);
            overlayImage.setColor(new Color(0, 0, 0, 64));
            overlayImage.fillRect(0, 0, OVERLAY_WIDTH, OVERLAY_HEIGHT);
            overlay = new Image(overlayImage);
            addObject(overlay, OVERLAY_X, OVERLAY_HEIGHT / 2);
            
            for (GreenfootImage image : rain.getImages()) {
                image.scale(840, 720);
            }
            
        }
        
        
        GreenfootImage overlayImage = new GreenfootImage(OVERLAY_WIDTH, OVERLAY_HEIGHT);
        overlayImage.setColor(new Color(0, 0, 100, 64));
        overlayImage.fillRect(0, 0, OVERLAY_WIDTH, OVERLAY_HEIGHT);
        overlay.setImage(overlayImage);
        overlay.getImage().drawImage(rain.getCurrentImage(), 0, 0);
    }

    private void showStats() {
        String stats = "";
        stats += "Average mark: " + averageMark + "\n\n";
        stats += "Number of Days: " + numDays + "\n\n";
        stats += "Student IQ: " + studentIQ + "\n\n";
        stats += "Customer Support Chance: " + customerSupportChance + "\n\n";
        stats += "Computer Breaking Chance: " + computerBreakingChance + "\n\n";
        stats += "Has Robber: " + hasRobber + "\n\n";
        stats += "Has Janitor: " + hasJanitor + "\n\n";
        stats += "Chaos Mode: " + chaosMode + "\n\n";

        displayText.setValue(stats);
        displayText.setFillColor(Color.WHITE);
        displayText.setLineColor(Color.WHITE); // Make the text visible
    }
    
    private void checkButton(){
        if(Greenfoot.mouseClicked(backToMenu)){
            music.pause();
            titleScreen.playMusic();
            Greenfoot.setWorld(titleScreen);
        } 
        if(Greenfoot.mouseClicked(tryAgain)){
            music.pause();
            titleScreen.playMusic();
            Greenfoot.setWorld(titleScreen.getModifierWorld());
        }
    }
}
