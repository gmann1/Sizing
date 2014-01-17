package edu.asu.voctec.step_selection;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults.Labels.Step0;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.MainMenu;
import edu.asu.voctec.game_states.ScenarioHub;
import edu.asu.voctec.game_states.SelectorTest;
import edu.asu.voctec.game_states.Task;
import edu.asu.voctec.utilities.Position;
import edu.asu.voctec.utilities.UtilFunctions;

public class ScenarioIntroductionScreen extends GUI implements Task
{
	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		Rectangle textLocation = new Rectangle(0, 50, 300, 50);
		
		// Title
		TextField welcomeLabel = new TextField(textLocation, 0.95f, "Welcome!",
				TextDisplay.FormattingOption.FIT_TEXT);
		welcomeLabel.setFontColor(Fonts.FONT_COLOR);
		welcomeLabel.center();
		
		// Introduction Body
		textLocation = new Rectangle(0, 0, 500, 400);
		UtilFunctions.centerRectangle(new Rectangle(0, 0, 800, 600), textLocation);
		TextArea introductionText = new TextAreaX(textLocation, 0.95f,
				Step0.INTRODUCTION.getTranslation());
		introductionText.setFontSize(12f);
		introductionText.setFontSize(Fonts.FONT_SIZE_LARGE);
		introductionText.setFontColor(Fonts.FONT_COLOR);
		
		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new TransitionButtonListener(SelectorTest.class));
		startButton.positionText(Position.LEFT);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(ScenarioHub.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		
		// Add all components to this menu
		this.addComponent(startButton);
		this.addComponent(welcomeLabel);
		this.addComponent(introductionText);
		this.addComponent(backButton);
	}
	
	@Override
	public void onEnter()
	{
		Game.getExitScreen().updateExitScreen(this.getClass());
	}
	
	@Override
	public void load()
	{
		SelectorTest mainScreen = (SelectorTest) Game
				.getGameState(SelectorTest.class);
		mainScreen.load();
	}
}
