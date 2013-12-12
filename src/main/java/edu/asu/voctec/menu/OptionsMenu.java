package edu.asu.voctec.menu;

import java.awt.Dimension;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.TransitionButton;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;

public class OptionsMenu extends Menu implements GameDefaults
{
	public static final int ID = 2;
	public static final Dimension DesignResolution = new Dimension(1280, 720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		//TODO declare & instantiate all buttons
		Button backButton = new TransitionButton(ImagePaths.BACK_BUTTON,
												 10, 10, MainMenu.ID,
												 LayoutOption.TOP_LEFT_ALIGN);
		
		//TODO add all buttons
		this.addButton(backButton);
		
		//TODO initialize all resources
		
		//TODO initialize background image
		Image background = new Image(ImagePaths.OptionsMenuBackground);
		super.initializeBackgroundImage(background);
		
		//ensure the appropriate scale is being used
		resize();
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
