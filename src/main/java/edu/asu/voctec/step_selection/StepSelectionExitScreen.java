package edu.asu.voctec.step_selection;

import java.awt.Rectangle;

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

public class StepSelectionExitScreen extends GUI
{
	private static final Color FONT_COLOR = Color.white;
	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;

	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	public TextArea introduction;
	public boolean updateHints = false;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		Rectangle textLocation = new Rectangle(0, 50, 300, 50);
		
		TextField topLine = new TextField(textLocation, 0.95f, "Good Job!",
				TextDisplay.FormattingOption.FIT_TEXT);
		topLine.setFontColor(FONT_COLOR);
		topLine.center();
		
		// introduction
		textLocation.setLocation(50, 250);
		textLocation = new Rectangle(150, 200, 500, 350);
		introduction = new TextArea(textLocation, 0.95f, "");
		introduction.setFontSize(LARGE_FONT_SIZE);
		introduction.setText("");
		introduction.setFontColor(FONT_COLOR);

		// start button
		Button Start = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Exit!");
		Start.setFontColor(Color.darkGray);
		Start.addActionListener(new TransitionButtonListener(TaskScreen.class));
		
		this.addComponent(Start);
		this.addComponent(topLine);
		this.addComponent(introduction);
		
	}
	
}
