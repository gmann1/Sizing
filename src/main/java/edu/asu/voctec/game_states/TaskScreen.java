package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.TransitionButtonListener;

public class TaskScreen extends GUI
{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		int buttonSpacing = 15;
		int buttonWidth = 350;
		int buttonHeight = 75;
		
		Rectangle buttonBounds = new Rectangle(0, 0, buttonWidth, buttonHeight);
		
		// Declare Buttons
		// Start Button
		BasicComponent startButton = new BasicComponent(
				ImagePaths.NEW_GAME_BUTTON, buttonBounds);
		startButton.addActionListener(new TransitionButtonListener(MenuTest.class));

		// Add buttons to this menu
		this.addComponent(startButton);
		
		this.centerComponentsStacked(buttonSpacing, getComponents());
	}
	
}
