package edu.asu.voctec.minigames;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Main;
import edu.asu.voctec.minigames.critical_design_month.Battery;
import edu.asu.voctec.minigames.critical_design_month.BatteryCharge;
import edu.asu.voctec.minigames.critical_design_month.Panel;
import edu.asu.voctec.minigames.critical_design_month.Sun;

/**
 * Write a description of class CriticalDesignMonthGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CriticalDesignMonthGame extends PortedGameState
{
	public static final int ID = 90;
	protected Panel panel;
	
    /**
     * Constructor for objects of class CriticalDesignMonthGame.
     * @throws SlickException 
     * 
     */
    public CriticalDesignMonthGame() throws SlickException
    {
    	//TODO replace path & dimension constants
    	this.backgroundImage = new Image(ImagePaths.LIGHT_BACKGROUND).getScaledCopy(800, 600);
    	
    	//TODO verify replacement
        Main.getGameContainer().setTargetFrameRate(50); //REPLACED: Greenfoot.setSpeed(50);
        addObject(new Sun(this, 1), -40, 300);
        this.panel = new Panel(this);
        addObject(panel, 750, 580);
        addObject(new BatteryCharge(), 100, 100);
        addObject(new Battery(), 40, 40);
    }
    
    @Override
    public void keyPressed(int key, char c)
    {
    	panel.keyPressed(key, c);
    }

	@Override
	public boolean resize()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rescale()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTranslation()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return ID;
	}
}
