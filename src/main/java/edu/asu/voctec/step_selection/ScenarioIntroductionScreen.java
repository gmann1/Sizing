package edu.asu.voctec.step_selection;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
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
import edu.asu.voctec.game_states.SelectorTest;
import edu.asu.voctec.game_states.Task;
import edu.asu.voctec.utilities.Position;

public class ScenarioIntroductionScreen extends GUI implements Task
{
	private static final Color FONT_COLOR = Color.white;
	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;
	
	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		Rectangle textLocation = new Rectangle(0, 50, 300, 50);
		
		TextField welcome = new TextField(textLocation, 0.95f, "Welcome!",
				TextDisplay.FormattingOption.FIT_TEXT);
		welcome.setFontColor(FONT_COLOR);
		
		// introduction
		textLocation = new Rectangle(150, 100, 500, 400);
		TextArea introduction = new TextAreaX(textLocation, 0.95f,
				Step0.INTRODUCTION.getTranslation());
		introduction.setFontSize(12f);
		introduction.setFontSize(LARGE_FONT_SIZE);
		introduction.setFontColor(FONT_COLOR);
		
		// start button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		startButton.setFontColor(Color.white);
		startButton.addActionListener(new TransitionButtonListener(SelectorTest.class));
		startButton.positionText(Position.LEFT);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(MainMenu.class));
		backButton.setFontColor(Color.white);
		backButton.positionText(Position.RIGHT);
		
		welcome.center();
		
		this.addComponent(startButton);
		this.addComponent(welcome);
		this.addComponent(introduction);
		this.addComponent(backButton);
	}
	
	@Override
	public void load()
	{
		SelectorTest mainScreen = (SelectorTest) Game
				.getGameState(SelectorTest.class);
		mainScreen.load();
	}
}
