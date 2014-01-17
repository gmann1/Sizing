package edu.asu.voctec.cdmg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;
import edu.asu.voctec.utilities.gameTemplate;

/**
 * 
 * @author Gabriel Mann
 * 
 */

public class CDIntroScreen extends GUI {

	private static final Color FONT_COLOR = Color.white;
	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;

	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		Rectangle textLocation = new Rectangle(0, 50, 300, 50);
		// TextField textField = new TextField(textLocation, 0.95f,
		// "Cliped Text Field ... CLIP CLIP CLIP",
		// TextDisplay.FormattingOption.CLIP_TEXT);

		// textLocation.setLocation(50, 150);
		// welcome
		TextField welcome = new TextField(textLocation, 0.95f, "Welcome!",
				TextDisplay.FormattingOption.FIT_TEXT);
		welcome.setFontColor(FONT_COLOR);

		// introduction
		textLocation.setLocation(50, 250);
		
		textLocation = new Rectangle(150, 200, 500, 400);
		TextArea introduction = new TextArea(textLocation, 0.95f, "");
		introduction.setFontSize(LARGE_FONT_SIZE);
		introduction
				.setText("In the following game, you will be determining the peak sun hours (PSH) for the most appropriate month (critical design month) for a small DC lighting system with constant loads as well as the most appropriate array orientation and tilt angle for the conditions of that month for a location in Africa.");
		introduction.setFontColor(FONT_COLOR);

		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new TransitionButtonListener(
				CDPart1.class));
		startButton.positionText(Position.LEFT);

		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(
				TaskScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);

		welcome.center();

		this.addComponent(startButton);
		this.addComponent(welcome);
		this.addComponent(introduction);
		this.addComponent(backButton);
		// this.addComponent(new Selector<SelectorIcon>(100, 100));

		System.out
				.println("Listeners: " + Arrays.toString(this.getListeners()));
	}

	@Override
	public Dimension getDesignResolution() {
		// TODO Auto-generated method stub
		return null;
	}

}
