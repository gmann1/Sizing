package edu.asu.voctec.menu;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;
import edu.asu.voctec.ScreenResolution;
import edu.asu.voctec.AspectRatio.ResolutionNotSupportedException;
import edu.asu.voctec.menu.buttons.*;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;

public class MainMenu extends Menu implements GameDefaults
{
	public static final int ID = 0;
	public static final Dimension DesignResolution = new Dimension(1280, 720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		//TODO replace absolute positioning with ButtonBlock class
		//TODO declare & instantiate all buttons
		//TODO move setLabel() call to button constructor
		Button newGameButton = new NewGameButton(0, 248, LayoutOption.CENTER_HORIZONTALLY);
		newGameButton.setLabel(LabelName.startButton);
		
		Button optionsButton = new TransitionButton(ImagePaths.OPTIONS_BUTTON, 
													0, 348, 
													OptionsMenu.ID,
													LayoutOption.CENTER_HORIZONTALLY);
		optionsButton.setLabel(LabelName.optionsButton);
		
		//TODO add all buttons
		addButton(newGameButton);
		addButton(optionsButton);
		
		//TODO remove sizing test
		//resizing TEST
		addButton(new Button(ImagePaths.NEW_GAME_BUTTON,
				  new Point(-10, -10),
				  LayoutOption.BOTTOM_RIGHT_ALIGN){

			@Override
			public void actOnMouseClick()
			{
				ScreenResolution d1280 = null;
				ScreenResolution d800 = null;
				
				try
				{
					d1280 = new ScreenResolution(1280, 720);
					d800 = new ScreenResolution(800, 600);
				}
				catch (ResolutionNotSupportedException e)
				{
					e.printStackTrace();
				}
				
				//TODO improve algorithm to work with user-definaed resolutions
				if (Main.getCurrentScreenDimension().equals(d1280))
					Main.resize(d800);
				else if (Main.getCurrentScreenDimension().equals(d800))
					Main.resize(d1280);
			}
			
		}); //end resizing TEST
		
		//TODO initialize all resources
		
		//initialize background image
		Image background = new Image(ImagePaths.MainMenuBackground);
		super.initializeBackgroundImage(background);
		
		//ensure the appropriate scale is being used
		resize();
		
		//ensure the appropriate language is being used
		updateTranslation();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		//mouse events are handled by superclass in mousePressed()
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