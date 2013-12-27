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
		this.backgroundImage = new Image(
				"resources/default/img/minigames/criticalDesign/space.jpg");
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
		textLocation = new Rectangle(150, 200, 500, 350);
		TextArea introduction = new TextArea(textLocation, 0.95f, "");
		introduction.setFontSize(LARGE_FONT_SIZE);
		introduction
				.setText("In this step you will be determining the critical design month. This step evaluates the month with the worst sun light to energy-use ratio. "
						+ "By designing the system to meet the requirements of the "
						+ "worst month, it will be able to deliver energy requirements year-round.");
		introduction.setFontColor(FONT_COLOR);

		// start button
		Button Start = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		Start.setFontColor(Color.darkGray);
		Start.addActionListener(new TransitionButtonListener(CDPart1.class));

		// Back Button
		Button Back = new Button(new Image(ImagePaths.BACK_BUTTON), 0, 0,
				new Rectangle(0, 0, 50, 25), "Back");
		Back.addActionListener(new TransitionButtonListener(TaskScreen.class));
		Back.setFontColor(Color.darkGray);

		welcome.center();

		this.addComponent(Start);
		this.addComponent(welcome);
		this.addComponent(introduction);
		this.addComponent(Back);
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
