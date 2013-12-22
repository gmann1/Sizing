package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.TransitionButtonListener;

public class MainMenu extends GUI implements GameDefaults
{
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		int buttonSpacing = 15;
		int buttonX = 100;
		int buttonY = 100;
		int buttonWidth = 350;
		int buttonHeight = 75;
		
		Rectangle buttonBounds = new Rectangle(buttonX, buttonY, buttonWidth, buttonHeight);
		
		// Declare Buttons
		// Start Button
		BasicComponent startButton = new BasicComponent(
				ImagePaths.NEW_GAME_BUTTON, buttonBounds);
		startButton.addActionListener(new TransitionButtonListener(Game.TaskScreenID));
		
		// Language Button
		buttonBounds.translate(0, (buttonSpacing + buttonHeight));
		BasicComponent languageButton = new BasicComponent(
				ImagePaths.LANGUAGE_BUTTON, buttonBounds);
		languageButton.addActionListener(new TransitionButtonListener(Game.LanguageMenuID));
		
		// Instructor Control Panel Button
		buttonBounds.translate(0, (buttonSpacing + buttonHeight));
		BasicComponent instructorButton = new BasicComponent(
				ImagePaths.INSTRUCTOR_CONTROL_PANEL_BUTTON, buttonBounds);
		instructorButton.addActionListener(new TransitionButtonListener(Game.InstructorControlPanelID));
		
		// Add buttons to this menu
		this.addComponent(startButton);
		this.addComponent(languageButton);
		this.addComponent(instructorButton);
	}
	
}
