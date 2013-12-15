package edu.asu.voctec.minigames;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.minigames.critical_design_month.Exit;
import edu.asu.voctec.minigames.critical_design_month.ExitText;
import edu.asu.voctec.minigames.critical_design_month.Start;

/**
 * Write a description of class ExitSplash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitSplash extends PortedGameState
{
	public static final int ID = 11;
	public static int score;
	
    public ExitSplash() throws SlickException
    {
        this.backgroundImage = new Image(ImagePaths.INTRO_BACKGROUND).getScaledCopy(800, 600);
        
        addObject(new ExitText(score), 500,150);
        addObject(new Start(), 600,300);
        addObject(new Exit(), 350,300);
    }

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
}
