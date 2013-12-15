package edu.asu.voctec.minigames.critical_design_month;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.minigames.Actor;

/**
 * Write a description of class Battery here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battery extends Actor
{
    /**
     * Act - do whatever the Battery wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * @throws SlickException 
     */
    public Battery() throws SlickException
    {
    	this.image = new Image(ImagePaths.BATTERY);
    	this.image = image.getScaledCopy(0.25f);
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
	@Override
	public void actOnMouseClick()
	{
		// TODO Auto-generated method stub
		
	}    
}
