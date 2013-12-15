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
public class ExitText extends Actor
{
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
	LabelName ln1;
    LabelName ln2 = new LabelName("Collect energy with your solar panel.", "Collect energy with your solar panel.");
    LabelName ln3 = new LabelName("Controls:", "Controls:");
    LabelName ln4 = new LabelName("Move Left: left arrow", "Move Left: left arrow");
    LabelName ln5 = new LabelName("Move Right: right arrow", "Move Right: right arrow");
    LabelName ln6 = new LabelName("Rotate Panel: 'q' and 'r' keys", "Rotate Panel: 'q' and 'r' keys");
    
    public ExitText(int score) throws SlickException{
    	//TODO add constant
    	this.image = new Image(ImagePaths.BASE_LABEL);
    	
    	ln1 = new LabelName("Score: " + score, "Score: "+ score);
    }
    
    @Override
    public void draw(Graphics graphics)
	{
		super.draw(graphics);
	}
    
	@Override
	public void act()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actOnMouseClick()
	{
		// TODO Auto-generated method stub
		
	}
  
   
}
