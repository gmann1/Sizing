package edu.asu.voctec.menu;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;
import edu.asu.voctec.menu.buttons.LabelName;
import edu.asu.voctec.menu.buttons.NewGameButton;
import edu.asu.voctec.menu.buttons.TransitionButton;

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