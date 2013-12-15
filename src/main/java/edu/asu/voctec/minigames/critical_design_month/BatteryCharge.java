package edu.asu.voctec.minigames.critical_design_month;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.Game;
import edu.asu.voctec.minigames.Actor;
import edu.asu.voctec.minigames.ExitSplash;
/**
 * Write a description of class Battery here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatteryCharge extends Actor
{
    /**
     * Act - do whatever the Battery wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
	public static BatteryCharge currentBatteryCharge;
    private int totalScore; 
    
    public BatteryCharge() throws SlickException
    {
    	this.image = new Image(ImagePaths.BATTERY_CHARGE);
    	this.image = image.getScaledCopy(0.25f);
    	currentBatteryCharge = this;
    }
    
    @Override
    public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.drawString("%"+totalScore, this.getxLocation() + 75, this.getyLocation() + 50);
	}
    
    public void changeScore(int change)
    {
        totalScore = totalScore + change;
        //TODO why divide by 20?
        //img.drawString("%"+totalScore/20, 75, 50);
    }
    
    public void end()
    {
    	Game.getCurrentGame().enterState(ExitSplash.ID);
    	//TODO why divide by 20?
        //Greenfoot.setWorld(new ExitSplash(totalScore/20));
    }
    
    @Override
    public void act() 
    {
        // Add your action code here.
    }  
    
	@Override
	public void actOnMouseClick() {
		// TODO Auto-generated method stub
		
	}
}
