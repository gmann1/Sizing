package edu.asu.voctec.pv_game;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;

public class PVIntro extends GUI
{

	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	public static final String INTRODUCTION = "In this game you need to figure out the best combination of PV panels and how to connect them"
			+ " in order to achieve the required charging power and system voltage.";
	
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
		textLocation = new Rectangle(150, 200, 500, 400);
		TextArea introductionText = new TextAreaX(textLocation, 0.95f,
				INTRODUCTION);
		introductionText.setFontSize(12f);
		introductionText.setFontSize(Fonts.FONT_SIZE_LARGE);
		introductionText.setFontColor(Fonts.FONT_COLOR);
		
		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new TransitionButtonListener(PVGame.class));
		startButton.positionText(Position.LEFT);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(TaskScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		
		// Add all components to this menu
		this.addComponent(startButton);
		this.addComponent(welcomeLabel);
		this.addComponent(introductionText);
		this.addComponent(backButton);
	}
	
}
