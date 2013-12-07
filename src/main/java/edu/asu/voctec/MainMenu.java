package edu.asu.voctec;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.menu.Button;
import edu.asu.voctec.menu.Menu;

public class MainMenu extends Menu
{
	public static final int ID = 0;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException 
	{
		//TODO add all buttons
		//addButton();
		//TODO initialize all resources
		//TODO initialize background image
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		
	}

	@Override
	public int getID()
	{
		return MainMenu.ID;
	}

}