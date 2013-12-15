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
public class Text extends Actor
{
    LabelName ln1 = new LabelName("Welcome!", "Welcome!");
    LabelName ln2 = new LabelName("Collect energy with your solar panel.", "Collect energy with your solar panel.");
    LabelName ln3 = new LabelName("Controls:", "Controls:");
    LabelName ln4 = new LabelName("Move Left: left arrow", "Move Left: left arrow");
    LabelName ln5 = new LabelName("Move Right: right arrow", "Move Right: right arrow");
    LabelName ln6 = new LabelName("Rotate Panel: 'q' and 'r' keys", "Rotate Panel: 'q' and 'r' keys");
    
    public Text() throws SlickException
    {
    	this.image = new Image(ImagePaths.BASE_LABEL);
    }
    
    @Override
    public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.drawString(ln1.getTranslation(), 0, 25);  
        graphics.drawString(ln2.getTranslation(), 0, 75);
        graphics.drawString(ln3.getTranslation(), 0, 100); 
        graphics.drawString(ln4.getTranslation(), 0, 125); 
        graphics.drawString(ln5.getTranslation(), 0, 150);
        graphics.drawString(ln6.getTranslation(), 0, 175);
	}
    
    public void act() 
    {
    }

	@Override
	public void actOnMouseClick()
	{
		// TODO Auto-generated method stub
		
	} 
   
}
