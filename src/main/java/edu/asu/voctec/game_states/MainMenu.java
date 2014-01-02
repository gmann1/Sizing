package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.information.ScenarioData;
import edu.asu.voctec.information.TaskData;
import edu.asu.voctec.information.UserProfile;
import edu.asu.voctec.step_selection.ScenarioIntroductionScreen;
import edu.asu.voctec.utilities.UtilFunctions;

public class MainMenu extends GUI implements GameDefaults
{
	public class StartButtonListener extends ButtonListener
	{
		
		@Override
		protected void actionPerformed()
		{
			ScenarioData scenario = Game.getCurrentScenario();
			if (scenario.getEntryStep().isComplete())
				Game.getCurrentGame().enterState(TaskScreen.class);
			else
				Game.getCurrentGame().enterState(
						ScenarioIntroductionScreen.class);
		}
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("\nMainMenu: Initializing...");
		
		// TODO Replace with profile
		UserProfile profile = new UserProfile("Default User");
		ScenarioData scenario = profile.getScenario(0);
		TaskData task = scenario.getEntryStep();
		Game.setCurrentUser(profile);
		Game.setCurrentScenario(scenario);
		Game.setCurrentTask(task);
		
		int buttonSpacing = 15;
		int buttonWidth = 350;
		int buttonHeight = 75;
		float borderScale = 0.9f;
		
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
		startButton.addActionListener(new StartButtonListener());
		
		// Language Button
		Button languageButton = new Button(ImagePaths.LANGUAGE_BUTTON,
				buttonBounds, relativeTextBounds, "Language");
		languageButton.addActionListener(new TransitionButtonListener(
				TaskScreen.class/* LanguageMenu.class */));
		
		// Instructor Control Panel Button
		Button instructorButton = new Button(
				ImagePaths.INSTRUCTOR_CONTROL_PANEL_BUTTON, buttonBounds,
				relativeTextBounds, "Instuctor");
		instructorButton.addActionListener(new TransitionButtonListener(
				InstructorControlPanel.class));
		
		// Color text
		setButtonFontColor(Color.darkGray, startButton, languageButton,
				instructorButton);
		
		// Add buttons to this menu
		this.addComponent(startButton);
		this.addComponent(languageButton);
		this.addComponent(instructorButton);
		
		this.centerComponentsStacked(buttonSpacing);
		
		Image background = new Image(ImagePaths.MainMenuBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
		
		System.out.println("MainMenu: Initialization Finished.\n");
	}
	
	private void setButtonFontColor(Color color, Button... buttons)
	{
		for (Button button : buttons)
			button.setFontColor(color);
	}
	
}
