package edu.asu.voctec.utilities;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.Position;
import edu.asu.voctec.utilities.UtilFunctions;

public class gameTemplate extends GUI {
	

	
	protected TextAreaX hintBox;
	protected TextAreaX instructionBox;
	protected TextAreaX topText;
	
	protected BasicComponent sidePanel;
	protected BasicComponent control;

	
	protected Button backButton;
	protected Button readyButton;
	protected Button hintButton;
	protected Button contButton;
	protected Button continueButton;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		intializeDefaults();
		
		
	}
	public void intializeDefaults() throws SlickException {
		//initialize top text
		
		Rectangle textLocation = new Rectangle(135, 15, 410, 150);
		topText = new TextAreaX(textLocation, new Rectangle(0, 0, 410, 150),
				null);
		topText.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		topText.setFontSize(Fonts.FONT_SIZE_LARGE);
		
		// Initialize Side Panel
		
		sidePanel = new BasicComponent(ImagePaths.SIDE_PANEL, 592, 0);
		sidePanel.rescale(208, 600);
		sidePanel.setX(592);
		sidePanel.setY(0);

		
		//Initialize Control
		control = new BasicComponent(ImagePaths.CONTROL_PANEL, 0, 167);
		control.rescale(592, 167);
		control.setX(0);
		control.setY(600-167);
		
		// Hint Box Initialization
		
		Rectangle hintBounds = new Rectangle(600, 208, 192, 192); 
		Rectangle relativeHintTextBounds = UtilFunctions.dialateRectangle(
				new Rectangle(0, 0, 192, 192), 0.92f);
		hintBox = new TextAreaX(hintBounds, relativeHintTextBounds,
				null);

		Image hintBoxBackground = new Image(
				ImagePaths.HINT_BOX_TEMPLATE);
		
		hintBox.setCurrentImage(hintBoxBackground, true);
		
		//Instruction Box Initialization
		hintBounds = new Rectangle(600, 8, 192, 192);
		instructionBox = new TextAreaX(hintBounds, relativeHintTextBounds,
				null);
		instructionBox.setCurrentImage(hintBoxBackground, true);

		// Format hint box and Intsturction Box
		hintBox.setFontSize(Fonts.FONT_SIZE_MEDIUM);
		hintBox.setFontColor(Fonts.FONT_COLOR);
		
		instructionBox.setFontSize(Fonts.FONT_SIZE_MEDIUM);
		instructionBox.setFontColor(Fonts.FONT_COLOR);

		// Back Button
		backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
	
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		// Ready Button
		Image readyButtonImage = new Image(ImagePaths.READY_BUTTON);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		readyButton = new Button(readyButtonImage, sidePanel.getX() + sidePanel.getBounds().width/2 - UtilFunctions.getImageBounds(readyButtonImage).width/2, hintBox.getY() + hintBox.getBounds().height + 50, textBounds,"Ready");
		readyButton.setFontColor(Fonts.BUTTON_FONT_COLOR);
		//Continue Button
		Image continueButtonImage = new Image(ImagePaths.CONTINUE_BUTTON_OFF);
		textBounds = UtilFunctions.getImageBounds(continueButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		continueButton = new Button(continueButtonImage, sidePanel.getX() + sidePanel.getBounds().width/2 - UtilFunctions.getImageBounds(readyButtonImage).width/2, readyButton.getY() + 73, textBounds,"Continue");
		continueButton.setFontColor(Fonts.DISABLED_BUTTON_FONT_COLOR);
		// Hint Button
		Image hintButtonImage = (new Image(ImagePaths.HINT_BUTTON));
		textBounds = UtilFunctions.getImageBounds(hintButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.75f);
		textBounds.y = textBounds.y - 2;
		hintButton = new Button(hintButtonImage, readyButton.getX() + 18,
				readyButton.getY() - textBounds.height - 5, textBounds,
				"Click for Hint");
	
	
		hintButton.setX(sidePanel.getX() + sidePanel.getBounds().width/2 - hintButton.getBounds().width/2);
		hintButton.setY(401);
		hintButton.getTextField().center();
		hintButton.setFontColor(Fonts.BUTTON_FONT_COLOR);

		this.addComponent(sidePanel);

		this.addComponent(control);
		this.addComponent(hintBox);
		this.addComponent(instructionBox);
		this.addComponent(hintButton);
		this.addComponent(readyButton);
		this.addComponent(backButton);
		this.addComponent(continueButton);
	

	}
	
	public void continueButtonOn() throws SlickException{
		continueButton.setFontColor(Fonts.BUTTON_FONT_COLOR);
		continueButton.setCurrentImage(new Image(ImagePaths.CONTINUE_BUTTON_ON), true);
	}

}
