package edu.asu.voctec;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.menu.Button;
import edu.asu.voctec.menu.Button.LayoutOption;
import edu.asu.voctec.menu.Menu;
import edu.asu.voctec.menu.buttons.*;

public class MainMenu extends Menu implements GameDefaults
{
	public static final int ID = 0;
	public static final Dimension DesignResolution = new Dimension(1280, 720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		//TODO declare & instantiate all buttons
		Button newGameButton = new NewGameButton(0, 100, LayoutOption.CENTER_HORIZONTALLY);
		Button optionsButton = new TransitionButton(Button.DefaultImagePaths.OPTIONS_BUTTON, 
													0, 200, 
													OptionsMenu.ID,
													LayoutOption.CENTER_HORIZONTALLY);
		
		//TODO add all buttons
		addButton(newGameButton);
		addButton(optionsButton);
		
		//TODO remove sizing test
		//resizing TEST
		addButton(new Button(Button.DefaultImagePaths.NEW_GAME_BUTTON,
				  new Point(-10, -10),
				  LayoutOption.BOTTOM_RIGHT_ALIGN){

			@Override
			public void actOnMouseClick()
			{
				Dimension d1280 = new Dimension(1280, 720);
				Dimension d800 = new Dimension(800, 600);
				
				if (Main.getScreenDimension().equals(d1280))
					Main.resize(d800);
				else if (Main.getScreenDimension().equals(d800))
					Main.resize(d1280);
			}
			
		}); //end resizing TEST
		
		//TODO initialize all resources
		
		//initialize background image
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		
		//ensure the appropriate scale is being used
		resize();
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