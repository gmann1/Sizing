package edu.asu.voctec.menu.buttons;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.Game;

public class TransitionButton extends Button
{
	private int newScreen; //screen to transition to when clicked

	/**
	 * Omni-constructor. 
	 * 
	 * @param image				how this button should appear
	 * @param relativeLocation	location of this button relative to it's container
	 * @param newScreen			screen that will be transitioned to when this button is pressed
	 */
	public TransitionButton(Image image, Point relativeLocation, int newScreen, LayoutOption... layoutOptions)
	{
		super(image, relativeLocation, layoutOptions);
		this.newScreen = newScreen;
	}

	/**
	 * @param imagePath			path of the desired image for this button
	 * @param relativeLocation	location of this button relative to it's container
	 * @param newScreen			screen that will be transitioned to when this button is pressed
	 * @throws SlickException 	Indicates a failure to load the image
	 */
	public TransitionButton(String imagePath, Point relativeLocation, int newScreen, LayoutOption... layoutOptions)
			throws SlickException
	{
		super(imagePath, relativeLocation, layoutOptions);
		this.newScreen = newScreen;
	}
	
	/**
	 * @see #TransitionButton(String, Point, int)
	 */
	public TransitionButton(String imagePath, int relativeX, int relativeY, int newScreen, LayoutOption... layoutOptions)
			throws SlickException
	{
		this(imagePath, new Point(relativeX, relativeY), newScreen, layoutOptions);
	}

	/**
	 * Transitions the game to the specified screen.
	 */
	@Override
	public void actOnMouseClick()
	{
		Game.getCurrentGame().enterState(newScreen);
	}
	
}
