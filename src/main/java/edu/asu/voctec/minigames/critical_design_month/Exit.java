package edu.asu.voctec.minigames.critical_design_month;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.menu.buttons.LabelName;
import edu.asu.voctec.minigames.Actor;
/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exit extends Actor
{
    LabelName ln1 = new LabelName("Exit", "Exit");
    
    public Exit() throws SlickException
    {
    	//TODO replace with variable
    	this.image = new Image(ImagePaths.BASE_LABEL);
    }
    
    public void act()
    {
    }
    
    @Override
    public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.drawString(ln1.getTranslation(), this.getxLocation(), this.getyLocation());
	}
    
	@Override
	public void actOnMouseClick()
	{
		System.out.println("Exiting...");
	} 
   
}
