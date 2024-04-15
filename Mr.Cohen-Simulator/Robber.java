import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Robber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robber extends Person
{
    private int counter = 0; 
    Black black = new Black();
    ExclamationMark mark = new ExclamationMark();
    private boolean robbed;
    public Robber()
    {
        super();
        getImage().rotate(90);
        robbed = false;
    }

    public void act()
    {
        super.act();
        steal();
    }

    // Add code to steal laptop
    protected void steal()
    {
        if (!robbed) {
            pathFind(360, 150, 120, true);
            robbed = true;
        }
        
        if (currentPath.size() == 0) 
        {

            getWorld().addObject(black, 420, getWorld().getHeight()/2);
            counter++;
            if (counter >= 70) 
            {
                getWorld().removeObject(black);
                getWorld().addObject(mark, getWorld().getWidth()/2-50, 100);
            }
            if (counter >= 400) 
            {
                getWorld().removeObject(mark);
            }

         }
    }

    private void shock(){
        List<Student> student = getWorld().getObjects(Student.class);
    }
}
