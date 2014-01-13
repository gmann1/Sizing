package edu.asu.voctec.game_states;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Column;
import edu.asu.voctec.GUI.StarDisplay;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.information.AttemptData;
import edu.asu.voctec.information.TaskData;
import edu.asu.voctec.utilities.UtilFunctions;

public class ExitScreen extends GUI
{
	protected TextField titleField;
	protected TextAreaX feedback;
	protected StarDisplay starDisplay;
	protected Column<TextField> dataLabels;
	protected Column<TextField> dataDisplay;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// Divide the screen into 6 sections (2 rows, 2 columns)
		Rectangle[][] screenDivisions = UtilFunctions.divideScreen(
				Game.getCurrentScreenDimension(), 2, 2);
		
		// Setup Background
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		Rectangle componentBounds = new Rectangle(0, 50, 300, 50);
		
		// Title
		titleField = new TextField(componentBounds, 0.95f, "Good Job!",
				TextDisplay.FormattingOption.FIT_TEXT);
		titleField.setFontColor(Fonts.FONT_COLOR);
		titleField.center();
		this.addComponent(titleField);
		
		// Feedback Body
		componentBounds = new Rectangle(0, 0, 500, 225);
		UtilFunctions.centerRectangle(new Rectangle(0, 100, 800, 200),
				componentBounds);
		feedback = new TextAreaX(componentBounds, 0.95f,
				"Test Test Test \nTest 2 TEST 2\nTEST 3");
		feedback.setFontSize(Fonts.FONT_SIZE_LARGE);
		feedback.setFontColor(Fonts.FONT_COLOR);
		this.addComponent(feedback);
		
		// Star Display (center in bottom-right section)
		componentBounds = new Rectangle(0, 0, 300, 150);
		// UtilFunctions.centerRectangle(new Rectangle(400, 300, 400, 300),
		// componentBounds);
		UtilFunctions.centerRectangle(screenDivisions[1][1], componentBounds);
		starDisplay = new StarDisplay(0, 0, 0);
		starDisplay.setBounds(componentBounds);
		this.addComponent(starDisplay);
		
		// Label Column
		float fontSize = 26f;
		componentBounds = new Rectangle(0, 0, 250, 100);
		UtilFunctions.centerRectangle(screenDivisions[1][0], componentBounds);
		componentBounds.translate(-50, 0);
		dataLabels = new Column<>(componentBounds);
		componentBounds = new Rectangle(0, 0, 250, 50);
		TextField unit = new TextField(componentBounds, 0.95f, "Time Spent:",
				TextDisplay.FormattingOption.CLIP_TEXT);
		unit.setFontSize(fontSize);
		unit.center(true, false);
		dataLabels.add(unit);
		unit = new TextField(componentBounds, 0.95f, "Hints Used:",
				TextDisplay.FormattingOption.CLIP_TEXT);
		unit.setFontSize(fontSize);
		unit.center(true, false);
		dataLabels.add(unit);
		this.addComponent(dataLabels);
		
		// Data Column
		componentBounds = new Rectangle(0, 0, 150, 100);
		UtilFunctions.centerRectangle(screenDivisions[1][0], componentBounds);
		componentBounds.translate(80, 0);
		dataDisplay = new Column<>(componentBounds);
		componentBounds = new Rectangle(0, 0, 150, 50);
		unit = new TextField(componentBounds, 0.95f, "00:00",
				TextDisplay.FormattingOption.CLIP_TEXT);
		unit.setFontSize(fontSize);
		unit.center();
		dataDisplay.add(unit);
		unit = new TextField(componentBounds, 0.95f, "0",
				TextDisplay.FormattingOption.CLIP_TEXT);
		unit.setFontSize(fontSize);
		unit.center();
		dataDisplay.add(unit);
		this.addComponent(dataDisplay);
	}
	
	@Override
	public void onEnter()
	{
		load();
	}
	
	public void load()
	{
		TaskData currentTask = Game.getCurrentTask();
		AttemptData currentAttempt = null;
		
		if (currentTask != null)
			currentAttempt = currentTask.getCurrentAttempt();
		
		if (currentAttempt != null)
		{
			dataDisplay.getUnitAt(0).setText(
					UtilFunctions.formatTime(currentAttempt.getTimeSpent(), false, true));
			dataDisplay.getUnitAt(0).setText(
					Integer.toString(currentAttempt.getNumberOfUniqueHints()));
			starDisplay.setScore(currentAttempt.calculateStarScore());
		}
	}
	
	public void updateExitText(String titleField, String feedback)
	{
		this.titleField.setText(titleField);
		this.feedback.setText(feedback);
	}
	
	public void updateExitText(String titleField, ArrayList<String> feedback)
	{
		this.titleField.setText(titleField);
		this.feedback.setText(feedback);
	}
	
}
