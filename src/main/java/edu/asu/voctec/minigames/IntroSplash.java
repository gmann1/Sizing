package edu.asu.voctec.minigames;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.minigames.critical_design_month.Exit;
import edu.asu.voctec.minigames.critical_design_month.Start;
import edu.asu.voctec.minigames.critical_design_month.Text;

/**
 * Write a description of class IntroSplash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroSplash extends PortedGameState
{
	public static final int ID = 12;
    /**
     * Constructor for objects of class IntroSplash.
     * @throws SlickException 
     * 
     */
    public IntroSplash() throws SlickException
    {
        this.backgroundImage = new Image(ImagePaths.INTRO_BACKGROUND).getScaledCopy(800, 600);
        
        addObject(new Text(), 500,150);
        addObject(new Start(), 600,300);
        addObject(new Exit(), 350,300);
    }

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
}
