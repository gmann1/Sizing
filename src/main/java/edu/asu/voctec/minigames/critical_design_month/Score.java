package edu.asu.voctec.minigames.critical_design_month;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import edu.asu.voctec.minigames.Actor;
import edu.asu.voctec.minigames.PortedGameState;
/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x = 0;
    private int y;
    private int lifetime = 0;
    private double offSet = 0;
    private int score;
    private double percentage;
    ArrayList<BatteryCharge> BC = new ArrayList<BatteryCharge>();
	private PortedGameState associatedWorld;
	private Color scoreColor;
    
    public Score(PortedGameState associatedWorld, int panel_x, int panel_y, boolean isRight, int sunAngle, int panelAngle, int total)
    {
		this.associatedWorld = associatedWorld;

        x = panel_x;
        y = panel_y;
        if (isRight)
        {
            offSet = Math.abs(panelAngle - sunAngle);
        }
        else
        {
            offSet = Math.abs(panelAngle + sunAngle);
        }
        
        percentage = ((140-offSet)/140);
        score = (int)(total*10*percentage);
        
        if (score < 80)
        	this.scoreColor = Color.red;
        else if (score <90)
        	this.scoreColor = Color.orange;
        else
        	this.scoreColor = Color.red; 
        
    }
    
    public void draw(Graphics graphics)
    {
    	super.draw(graphics);
    	graphics.setColor(scoreColor);
    	graphics.drawString(""+score, 75, 50);
    }
    
    public void act() 
    {
        
        if (lifetime == 30)
        {
	        BatteryCharge batterycharge = BatteryCharge.currentBatteryCharge;
	        batterycharge.changeScore(score);
            this.associatedWorld.removeObject(this);
        }
        lifetime++;
        setLocation(x,y);
        // Add your action code here.
    }
    
	@Override
	public void actOnMouseClick() {
		// TODO Auto-generated method stub
		
	}    
}
