package edu.asu.voctec.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.SupportFunctions;
import edu.asu.voctec.GUI.*;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;

public class EAPart2IntroScreen extends GUI
{
	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	public static final String INTRODUCTION = "Now you will have to inventory the house for all the energy consuming devices to determine the power rating.";
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		
		// Title
		TextField welcomeLabel = SupportFunctions
				.generateWelcomeLabel("Welcome!");
		
		// Introduction Body
		TextArea introductionText = SupportFunctions
				.generateIntroductionDisplay(INTRODUCTION);
		
		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new TransitionButtonListener(
				EAPart2.class));
		startButton.positionText(Position.LEFT);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(
				TaskScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		
		// Add all components to this menu
		this.addComponent(startButton);
		this.addComponent(welcomeLabel);
		this.addComponent(introductionText);
		this.addComponent(backButton);
		
	}
	
}

/*
 * import java.awt.Dimension; import java.awt.Rectangle; import
 * java.util.Arrays;
 * 
 * import org.newdawn.slick.Color; import org.newdawn.slick.GameContainer;
 * import org.newdawn.slick.Graphics; import org.newdawn.slick.Image; import
 * org.newdawn.slick.SlickException; import
 * org.newdawn.slick.state.StateBasedGame;
 * 
 * 
 * import org.newdawn.slick.GameContainer; import org.newdawn.slick.Image;
 * import org.newdawn.slick.SlickException; import
 * org.newdawn.slick.state.StateBasedGame;
 * 
 * import edu.asu.voctec.Game; import edu.asu.voctec.GUI.*; import
 * edu.asu.voctec.GameDefaults.ImagePaths; import
 * edu.asu.voctec.game_states.GUI; import edu.asu.voctec.game_states.TaskScreen;
 * 
 * public class EAPart2IntroScreen extends GUI { private static final String
 * CONTINUE =
 * "resources/default/img/minigames/energyAssessment/continueButton.png";
 * private static final String EXIT =
 * "resources/default/img/minigames/energyAssessment/exitButton.png"; private
 * static final String BACKGROUND =
 * "resources/default/img/minigames/energyAssessment/background.png";
 * 
 * private static final String ea2IntroText =
 * "Now you will have to inventory the house for all the energy consuming devices to determine the power rating."
 * ;
 * 
 * @Override public void init(GameContainer container, StateBasedGame game)
 * throws SlickException { //load basic things this.backgroundImage = new
 * Image(BACKGROUND);
 * 
 * ////Text//// //top welcome text Rectangle topTextLocation = new Rectangle(50,
 * 50, 300, 50); TextField topTextField = new TextField(topTextLocation, 0.95f,
 * "Next Steps", TextDisplay.FormattingOption.FIT_TEXT);
 * topTextField.setFontColor(Color.blue); this.addComponent(topTextField);
 * 
 * //intro text Rectangle introTextLocation = new Rectangle(50, 100, 700, 400);
 * TextArea introTextArea = new TextArea(introTextLocation, 0.90f,
 * ea2IntroText); introTextArea.setFontColor(Color.black);
 * introTextArea.setFillColor(Color.white); introTextArea.setFontSize(16);
 * this.addComponent(introTextArea);
 * 
 * //Next Button Button Next = new Button(new Image(CONTINUE), 575, 500, new
 * Rectangle(50,50,300,50), ""); Next.addActionListener(new
 * TransitionButtonListener(EAPart2.class)); this.addComponent(Next);
 * 
 * //Exit Button Button exit = new Button(new Image(EXIT), 50, 500, new
 * Rectangle(50,50,300,50), ""); exit.addActionListener(new
 * TransitionButtonListener(TaskScreen.class)); this.addComponent(exit);
 * 
 * }
 * 
 * 
 * @Override public Dimension getDesignResolution() { // TODO Auto-generated
 * method stub return null; }
 * 
 * }
 */
