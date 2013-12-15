package edu.asu.voctec.minigames.critical_design_month;

import java.util.ArrayList;

import edu.asu.voctec.minigames.Actor;
import edu.asu.voctec.minigames.PortedGameState;

/**
 * Write a description of class SunBeam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SunBeam extends Actor
{
    /**
     * Act - do whatever the SunBeam wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int line_x;
    private int line_y;
    private int degrees = 0;
    private int move_x;
    private int move_y;
    private int speed = 100;
    private int angle;
    private boolean isRight;
    private PortedGameState associatedWorld;
    
    public static final ArrayList<SunBeam> liveObjects = new ArrayList<>();
    
    public SunBeam(PortedGameState associatedWorld, int x, int y, int xx, int yy)
    {
    	this.associatedWorld = associatedWorld;
        setImage("sunBeam.png");
        setImage(getImage().getScaledCopy(0.66f));
        line_x = x;
        line_y = y;
        angle = Math.abs((int)Math.toDegrees(Math.atan((double)(xx-x)/(yy-y))));
        
        if (xx >= x)
        {
            isRight = true;
        }
        else
        {
            isRight = false;
        }
        
        move_x = (xx - x)/speed;
        move_y = (yy - y)/speed;
        
        liveObjects.add(this);
    }
    public void act() 
    {
        line_x += move_x;
        line_y += move_y;
        setLocation(line_x + (int)(10*Math.sin(Math.toRadians(degrees))), line_y );
        degrees = degrees +30;
        
        if (this.getyLocation() >= 800)
        {
        	liveObjects.remove(this);
        	associatedWorld.removeObject(this);
        }
    } 
    
    public int getAngle()
    {
        return angle;
    }
    
    public boolean isRight()
    {
        return isRight;
    }
    
	@Override
	public void actOnMouseClick()
	{
		// TODO Auto-generated method stub
		
	}
    
}
