package edu.asu.voctec.batter_sizing;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.*;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;

public class BatteryIntro extends GUI
{
	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	public static final String Right_ARROW_TEXT = "Begin!";
	public static final String LEFT_ARROW_TEXT = "Back";
	public static final String WELCOME_Text = "Welcome!";
	public static final String INTRODUCTION = "In this game you need to figure out the best combination of batteries and how to connect them"
				+ " in order to achieve the required Battery-Bank output and system voltage.";
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		Rectangle textLocation = new Rectangle(0, 50, 300, 50);
		
		// Title
		TextField welcomeLabel = new TextField(textLocation, 0.95f, WELCOME_Text,
				TextDisplay.FormattingOption.FIT_TEXT);
		welcomeLabel.setFontColor(Fonts.FONT_COLOR);
		welcomeLabel.center();
		
		// Introduction Body
		textLocation = new Rectangle(150, 200, 500, 400);
		TextArea introductionText = new TextAreaX(textLocation, 0.95f,
				INTRODUCTION);
		introductionText.setFontSize(12f);
		introductionText.setFontSize(Fonts.FONT_SIZE_LARGE);
		introductionText.setFontColor(Fonts.FONT_COLOR);
		
		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), Right_ARROW_TEXT);
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new StartListener());
		startButton.positionText(Position.LEFT);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), LEFT_ARROW_TEXT);
		backButton.addActionListener(new TransitionButtonListener(TaskScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		
		// Add all components to this menu
		this.addComponent(startButton);
		this.addComponent(welcomeLabel);
		this.addComponent(introductionText);
		this.addComponent(backButton);

	}
	
	public class StartListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			Game.getCurrentGame().enterState(BatteryGameScreen.class);
		}
	}
	
}