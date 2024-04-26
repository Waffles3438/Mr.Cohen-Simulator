import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class FinishedWorld here.
 * 
 * @author Andy Feng
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

    private boolean hasRobber;
    private boolean hasJanitor;
    private boolean chaosMode;

    private boolean added = false;

    private Image pizza;
    private Label displayText = new Label("", 25);
    private Button backToMenu = new Button("menu", 3, ".png");
    private Button tryAgain = new Button("tryagain", 3, ".png");

    private MrCohen cohen;
    private BubbleSpeech happy = new BubbleSpeech("happy_emotion0.png");
    private BubbleSpeech sleepy = new BubbleSpeech("happy_emotion1.png");
    private BubbleSpeech rage = new BubbleSpeech("angry_emotion.png");

    private ArrayList<Student> students;
    private HashMap<Student, Image> studentPizzaMap = new HashMap<>();

    /**
     * Constructor for objects of class FinishedWorld.
     * 
     */
    public FinishedWorld(int averageMark, int numDays, int studentIQ, int customerSupportChance, int computerBreakingChance, boolean hasRobber, boolean hasJanitor, boolean chaosMode)
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

        students = (ArrayList<Student>) getObjects(Student.class);
        if (averageMark >= 85)
        {
            addObject(new Confetti(800, 900), getWidth()/2-220, getHeight()/2);
        }
        if (averageMark >= 65 && averageMark < 85)
        {
            addObject(new Confetti(550, 400), getWidth()/2-250, getHeight()/2+100);
        }
        this.averageMark = averageMark;
        this.numDays = numDays;
        this.studentIQ = studentIQ;
        this.customerSupportChance = customerSupportChance;
        this.computerBreakingChance = computerBreakingChance;
        this.hasRobber = hasRobber;
        this.hasJanitor = hasJanitor;
        this.chaosMode = chaosMode;
    }

    public void act(){
        // the average mark the class has determine the type of ending of the simulation.
        if(averageMark > 100) averageMark = 100;
        if(averageMark < 0) averageMark = 0;
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
            addObject(happy, cohen.getX() + cohen.getImage().getWidth() / 2, cohen.getY() - cohen.getImage().getHeight());
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
        addObject(backToMenu, getWidth()/3, getHeight()/2 + 130);
        addObject(tryAgain, getWidth()/3, getHeight()/2 - 40);
    }

    private SimpleTimer timer = new SimpleTimer();
    private void updateFlashingOverlay() {
        GreenfootImage overlayImage = overlay.getImage();
        // Change overlay color with a flashing effect
        //if(timer.millisElapsed() < 50) return;
        //timer.mark();
        int red = 200 + Greenfoot.getRandomNumber(56); // Random red color component in the range 128-255
        int green = 200 + Greenfoot.getRandomNumber(56); // Random green color component in the range 128-255
        int blue = 200 + Greenfoot.getRandomNumber(56); // Random blue color component in the range 128-255
        overlayImage.setColor(new Color(red, green, blue, 7)); // Set the color with transparency
        overlayImage.fillRect(0, 0, overlayImage.getWidth(), overlayImage.getHeight()); // Fill the overlay with the color
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

    private void endingTwo(){
        if(!added){
            showStats();
            for(Student student : students){
                student.feelNothing();
            }
            addObject(sleepy, cohen.getX() + cohen.getImage().getWidth() / 2, cohen.getY() - cohen.getImage().getHeight());
        }
    }

    private void endingThree(){
        if(!added){
            showStats();
            for(Student student : students){
                student.sad();
            }
            addObject(sleepy, cohen.getX() + cohen.getImage().getWidth() / 2, cohen.getY() - cohen.getImage().getHeight());
        }
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
            Greenfoot.setWorld(new TitleScreen());
        } 
        if(Greenfoot.mouseClicked(tryAgain)){
            Greenfoot.setWorld(new Modifier());
        }
    }

    public void stopped(){

    }
}
