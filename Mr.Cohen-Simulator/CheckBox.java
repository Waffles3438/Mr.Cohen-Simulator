import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CheckBox here.
 * 
 * @author Andy Feng
 * @version 0.0.1
 */
public class CheckBox extends Actor
{

    private GreenfootImage empty = new GreenfootImage("empty.png");
    private GreenfootImage check = new GreenfootImage("check.png");
    
    private boolean variable;
    public CheckBox(){
        setImage(empty);
        variable = false;
    }
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            variable = !variable;
            if(getImage() != empty) setImage(empty);
            else setImage(check);
        }
    }
    
    public boolean updateBoolean(){
        return variable;
    }
}
