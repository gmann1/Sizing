package edu.asu.voctec.controller_sizing;

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
import edu.asu.voctec.cdmg.CDPart1;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
public class ControllerSizingIntroScreen extends GUI
{

	private static final Color FONT_COLOR = Color.white;
	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;

	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(
				"resources/default/img/minigames/ControllerSizing/Step1Background.png");
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
				.setText("This is the final step you need to do in order to complete the sizing of this PV system. "
						+ "In this step you will be sizing the charge controller for the system. "
						+ "The charge controller will help in regulating and controlling, the flow of current to and from the battery in order to protect it from overcharging and to increase its life. ");
		introduction.setFontColor(FONT_COLOR);

		// start button
		Button Start = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		Start.setFontColor(Color.darkGray);
		Start.addActionListener(new TransitionButtonListener(ControllerSizingPart2.class));

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
