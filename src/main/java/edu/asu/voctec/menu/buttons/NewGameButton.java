package edu.asu.voctec.menu.buttons;

import java.awt.Point;

import org.newdawn.slick.SlickException;

import edu.asu.voctec.ScenarioHub;
import edu.asu.voctec.menu.Button;

public class NewGameButton extends TransitionButton
{
	public static int TRANSITION_SCREEN = ScenarioHub.ID;
	
	/**
	 * Constructor. Sets the button image to the default image.
	 * 
	 * @param relativeX			x-location of this button relative to it's container
	 * @param relativeY			y-location of this button relative to it's container
	 * @throws SlickException	Indicates a failure to load the button's image
	 * 
	 * @see #NewGameButton(Point)
	 */
	public NewGameButton(int relativeX, int relativeY, LayoutOption... layoutOptions) throws SlickException
	{
		this(new Point(relativeX, relativeY), layoutOptions);
	}
	
	/**
	 * Constructor. Sets the button image to the default image.
	 * 
	 * @param relativeLocation	location of this button relative to it's container
	 * @throws SlickException	Indicates a failure to load the image
	 * 
	 * @see #NewGameButton(String, Point)
	 */
	public NewGameButton(Point relativeLocation, LayoutOption... layoutOptions) throws SlickException
	{
		//sets the image to the default NewGameButton image
		this(Button.DefaultImagePaths.NEW_GAME_BUTTON, relativeLocation, layoutOptions);
	}
	
	/**
	 * Omni-constructor. All other constructors lead here. Sets the transition
	 * screen to ScenarioHub
	 * 
	 * @param imagePath			path to load this button's image
	 * @param relativeLocation	location of this button relative to it's container
	 * @throws SlickException	Indicates a failure to load the image
	 * @see Button#Button(org.newdawn.slick.Image, Point)
	 */
	public NewGameButton(String imagePath, Point relativeLocation, LayoutOption... layoutOptions)
			throws SlickException
	{
		super(imagePath, relativeLocation, TRANSITION_SCREEN, layoutOptions);
	}
	
	@Override
	public void actOnMouseClick()
	{
		//transition to newScreen
		super.actOnMouseClick();
		//TODO create new profile
		//TODO backend tracking
	}

}
