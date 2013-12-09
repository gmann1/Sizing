package edu.asu.voctec;

import java.awt.Dimension;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.menu.Menu;

public class OptionsMenu extends Menu
{
	public static final int ID = 2;
	public static final Dimension DesignResolution = new Dimension(1280, 720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		//TODO add all buttons
		
		//TODO initialize all resources
		
		//TODO initialize background image
		this.backgroundImage = new Image("resources/default/img/blackBackground.jpg");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		
	}

	@Override
	public int getID()
	{
		return ID;
	}

	@Override
	public Dimension getDesignResolution()
	{
		return DesignResolution;
	}
}
