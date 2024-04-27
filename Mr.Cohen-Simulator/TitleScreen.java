import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * <p>
 * The title screen of the simulation
 * </p>
 * <div>
 * <a href="https://pc98backgrounds.tumblr.com/image/172810349592"> Link to image</a>
 * Image by Tumblr
 * </div>
 * <a href="https://www.youtube.com/watch?v=idztNHePKMo"> Link to music</a>
 * Music by Nintendo from New Super Mario Bros. Wii
 * 
 * 
 * startMusic method by Andy Feng
 * 
 * <h2>List of Features</h2>
 * <p>
 * There's the Person Superclass and the Computer class<br>
 * Within the simulation itself, there are 9 students which have multiple actions.<br>
 * <ul>
 *  <li>Working</li>
 *  <li>Wasting Time (sleeping/having fun)</li>
 *  <li>Talking to Students</li>
 *  <li>Asking questions to Mr Cohen</li>
 * </ul>
 * All these actions have some sort of result. Either increasing or decreasing their mark.
 * Additionally, there's obstacles / projectiles that can intract with the students and cohen (these are enablable)
 * <ul>
 *  <li>Puddles - Will make students slip and cohen more angry</li>
 *  <li>Books - Will daze studetns and cohen, will also damage computers</li>
 *  <li>Broken glass - Will also daze students and cohen</li>
 * </ul>
 * Students and cohen have speech bubbles showing their emotions / actions<br>
 * Janitor - Will go around the class and will spill puddles on the floor<br>
 * Robber - Will break in at night and will rob Mr Cohen's computer, leaving glass shards<br>
 * <h3> Computers </h3>
 * The computers screen can be seen on the right.
 * Computers have a mouse that moves around and has a screen that changes to various images
 * Computers have a chance of breaking randomly every night (changable variable).
 * In addition, different computers have different durabilities and can break in different ways.
 * <h3>Modifiers</h3>
 * There are many modifiers which change many aspects of the simulation
 * <ul>
 *  <li>Student IQ</li>
 *  <li>Chaos Mode</li>
 *  <li>Number of days</li>
 *  <li>Has robbers</li>
 *  <li>has janitors</li>
 *  <li>Chance of computer breaking</li>
 *  <li>Chance of support to call back</li>
 * </ul>
 * 
 * <h2>Credits</h2>
 * Note: credits can also be found in the classes there were used in.<br>
 * <h3>Art and sounds</h3>
 * https://www.youtube.com/watch?v=idztNHePKMo - Music by Nintendo from New Super Mario Bros. Wii<br>
 * https://www.youtube.com/watch?v=jRtDGwmgCR8 - Nintendo<br>
 * https://www.youtube.com/watch?v=Y2qFzWLlOi8 - Nintendo<br>
 * https://www.youtube.com/watch?v=po-0n1BKW2w - Nintedno<br>
 * https://www.youtube.com/watch?v=259C4AaOHn0 - Nintendo / Pokemon<br>
 * https://www.youtube.com/watch?v=m5x_mxPsHx8 - Nintedno<br>
 * https://mixkit.co/free-sound-effects/break/ - Pixabay<br>
 * https://pc98backgrounds.tumblr.com/image/172810349592 - Tumblr<br>
 * https://www.youtube.com/watch?v=Nobn5wAenro - Bob Pellerin<br>
 * https://www.vectorstock.com/royalty-free-vector/desktop-monitor-pc-game-pixel-art-vector-47159299 - Vector Stock<br>
 * https://www.freepik.com/premium-vector/pixel-art-illustration-laptop-pixelated-notebook-classic-laptop-computer-icon-pixelated-game_80323384.htm - Freepik<br>
 * https://www.vecteezy.com/vector-art/22908990-slider-menu-set-with-different-color-in-pixel-art-style - Vecteezy<br>
 * https://www.vecteezy.com/vector-art/5146435-old-paper-in-pixel-art-style - Vecteezy<br>
 * https://crusenho.itch.io/complete-ui-essential-pack - crusenho<br>
 * https://www.innersloth.com/games/among-us/ - Innersloth<br>
 * https://www.vectorstock.com/royalty-free-vector/online-laptop-gaming-game-pixel-art-vector-47158987 - Vectors Stock<br>
 * https://www.vectorstock.com/royalty-free-vector/desktop-monitor-pc-game-pixel-art-vector-47159299 - Vector Stock<br>
 * https://www.reddit.com/r/Steam/comments/tvofg5/steam_deck_pixel_art_i_made_this_a_few_days_ago/ - ExxiIon on Reddit <br>
 * 
 * <h3>Code</h3>
 * SuperSmoothMover and SuperStatBar taken from Jordan Cohen<br>
 * GifImage, SimpleTimer, Label taken from Greenfoot<br>
 * 
 * <h2>Bugs</h2>
 * In chaos mode interactions may seem weird but that is the point.
 * <ol>
 *  <li>Memory issues may occur</li>
 * </ol>
 * 
 * </p>
 * 
 * @author Benny
 * @version 1.0.0
 */
public class TitleScreen extends World{
    private Button start = new Button("start", 3, ".png");
    private Button credits = new Button("credits", 3, ".png");
    protected Button back = new Button("back", 3, ".png");
    
    protected CreditsScreen creditsScreen;
    protected Modifier modifier;

    public static boolean firstTime = true;
    private static GreenfootSound mainmenu = new GreenfootSound("mainmenu.mp3");
    /**
     * Constructor for TitleScreen
     */
    public TitleScreen(){
        super(1260, 720, 1);
        addObject(start, getWidth()/2, getHeight()/2 + 110);
        addObject(credits, getWidth()/2, getHeight()/2 + 220);
        addObject(new Image("Banner.png"), getWidth()/2, getHeight()/2 - 200);
        if(firstTime){
            Button.init();
            CheckBox.init();
            firstTime = false;
        }
        creditsScreen = new CreditsScreen(this);
        modifier = new Modifier(this);
        mainmenu.setVolume(25);
    }
    
    /**
     * Stops music when greenfoot is stopped
     *
     */
    public void stopped() {
        mainmenu.pause();
    }
    
    /**
     * Starts playing music when greenfoot is started
     *
     */
    public void started() {
        mainmenu.playLoop();
    }
    
    /**
     * Checks the button every act
     *
     */
    public void act(){
        checkButtons();
        startMusic();
    }
    
    /**
     * Check if buttons are pressed
     */
    private void checkButtons(){
        if(start.isPressed()){
            mainmenu.setVolume(20);
            Greenfoot.setWorld(modifier);
            start.setPressedCondition(false);
        }
        
        if(credits.isPressed()){
            mainmenu.setVolume(15);
            Greenfoot.setWorld(creditsScreen);
            credits.setPressedCondition(false);
        }
    }
    private boolean musicStarted = false;
    
    /**
     * Start background music
     */
    public void startMusic(){
        if (!musicStarted) { // Start music only once
            mainmenu.setVolume(25);
            mainmenu.playLoop();
            musicStarted = true;
        }
    }
    
    /**
     * Plays music
     */
    public static void playMusic(){
        mainmenu.playLoop();
    }
    
    /**
     * Pauses music
     */
    public static void pauseMusic(){
        mainmenu.pause();
    }
    
    /**
     * Setter for mainmenu volume
     * 
     * @param volume New volume
     */
    public static void setMusicVolume(int volume){
        mainmenu.setVolume(volume);
    }
    
    public Modifier getModifierWorld() {
        return modifier;
    }
}
