package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.utilities.UtilFunctions;

public class MainMenu extends GUI implements GameDefaults
{
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		int buttonSpacing = 15;
		int buttonWidth = 350;
		int buttonHeight = 75;
		float borderScale = 0.99f;
		
		// Determine text and button bounds, relative to each button
		Rectangle buttonBounds = new Rectangle(0, 0, buttonWidth, buttonHeight);
		Rectangle relativeTextBounds = new Rectangle(0, 0, buttonWidth
				- buttonHeight, buttonHeight);
		relativeTextBounds = UtilFunctions.dialateRectangle(relativeTextBounds,
				borderScale);
		
		// Declare Buttons
		// Start Button
		Button startButton = new Button(ImagePaths.NEW_GAME_BUTTON,
				buttonBounds, relativeTextBounds, "Start");
		startButton.addActionListener(new TransitionButtonListener(
				TaskScreen.class));
		
		// Language Button
		Button languageButton = new Button(
				ImagePaths.LANGUAGE_BUTTON, buttonBounds, relativeTextBounds, "Language");
		languageButton.addActionListener(new TransitionButtonListener(
				LanguageMenu.class));
		
		// Instructor Control Panel Button
		Button instructorButton = new Button(
				ImagePaths.INSTRUCTOR_CONTROL_PANEL_BUTTON, buttonBounds, relativeTextBounds, "Instuctor");
		instructorButton.addActionListener(new TransitionButtonListener(
				InstructorControlPanel.class));
		
		// Color text
		setButtonFontColor(Color.darkGray, startButton, languageButton, instructorButton);
		
		// Add buttons to this menu
		this.addComponent(startButton);
		this.addComponent(languageButton);
		this.addComponent(instructorButton);
		
		this.centerComponentsStacked(buttonSpacing);
		
		Image background = new Image(ImagePaths.MainMenuBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
	}
	
	private void setButtonFontColor(Color color, Button... buttons)
	{
		for (Button button : buttons)
			button.setFontColor(color);
	}
	
}
