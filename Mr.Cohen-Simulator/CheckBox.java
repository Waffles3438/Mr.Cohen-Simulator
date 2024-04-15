import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckBox extends Actor
{
    /**
     * Act - do whatever the CheckBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage empty = new GreenfootImage("");
    
    private boolean variable;
    public CheckBox(){
        variable = false;
    }
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)) variable = !variable;
    }
    
    public void updateBoolean(boolean yesOrNo){
        yesOrNo = variable;
    }
}
