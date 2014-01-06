package edu.asu.voctec.energy_assessment;


import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;

public class EAPart2ScoreScreen extends GUI
{
	
	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	
	//private static TextField congratulation;
	private static TextArea congratulation, congratulation2, congratulation3;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
			
		Rectangle textLocation = new Rectangle(35, 45, 600, 600);
		congratulation = new TextArea(textLocation, 0.95f,"Congratulations!");
		congratulation.setFontSize(40);
		congratulation.setFontColor(Color.white);
		this.addComponent(congratulation);
		
		Rectangle textLocation2 = new Rectangle(35, 140, 500, 500);
		congratulation2 = new TextArea(textLocation2, 0.95f,"You have successfully inventoried the energy consuming devices and determined their power rating.");
		congratulation2.setFontSize(25);
		congratulation2.setFontColor(Color.white);
		this.addComponent(congratulation2);
		
		Rectangle textLocation3 = new Rectangle(35, 300, 700, 700);
		congratulation3 = new TextArea(textLocation3, 0.95f,"");
		congratulation3.setFontSize(30);
		congratulation3.setFontColor(Color.white);
		this.addComponent(congratulation3);
		
		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,new Rectangle(0, 0, 50, 25), "Exit");
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new ExitListener());
		startButton.positionText(Position.LEFT);
		this.addComponent(startButton);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,new Rectangle(0, 0, 100, 25), "Play Again");
		backButton.addActionListener(new PlayAgainListener());
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		this.addComponent(backButton);
		
	}
	
	public class ExitListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			EAPart2.reset();
			Game.getCurrentGame().enterState(TaskScreen.class);
		}
	}
	
	public class PlayAgainListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			EAPart2.reset();
			Game.getCurrentGame().enterState(EAPart2.class);
		}
	}
	
	public static void passEndGameMessage(String gameStatusHeadline, String gameStatusMessage, String personalizedMessage, Color personalizedMessageColor)
	{
		congratulation.setText(gameStatusHeadline);
		congratulation2.setText(gameStatusMessage);
		congratulation3.setText(personalizedMessage);
		congratulation3.setFontColor(personalizedMessageColor);
	}
	
}

/*
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class EAPart2ScoreScreen extends GUI
{
	private static final String CONTINUE = "resources/default/img/minigames/energyAssessment/continueButton.png";
	private static final String REPLAY = "resources/default/img/minigames/energyAssessment/replayButton.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	private static final String ea2ScoreText = "You have successfully inventoried the energy consuming devices and determined their power rating.";

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		//load basic things
		this.backgroundImage = new Image(BACKGROUND);
				
		////Text////
		//top text
		Rectangle topTextLocation = new Rectangle(50, 50, 300, 50);
		TextField topTextField = new TextField(topTextLocation, 0.95f, "Congratulations!", TextDisplay.FormattingOption.FIT_TEXT);
		topTextField.setFontColor(Color.blue);
		this.addComponent(topTextField);
		
		//FeedBack text
		Rectangle scoreTextLocation = new Rectangle(50, 100, 700, 400);
		TextArea scoreTextArea = new TextArea(scoreTextLocation, 0.90f, ea2ScoreText);
		scoreTextArea.setFontColor(Color.black);
		scoreTextArea.setFillColor(Color.white);
		scoreTextArea.setFontSize(16);
		this.addComponent(scoreTextArea);
		
		//Next Button
		Button Next = new Button(new Image(CONTINUE), 575, 500, new Rectangle(50,50,300,50), "");
		Next.addActionListener(new NextButtonListener());
		this.addComponent(Next);
		
		//Replay Button
		Button replay = new Button(new Image(REPLAY), 50, 500, new Rectangle(50,50,300,50), "");
		replay.addActionListener(new  ReplayButtonListener());
		this.addComponent(replay);
	}
	
	private class NextButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			EAPart1.reset();
			EAPart2.reset();
			Game.getCurrentGame().enterState(TaskScreen.class);
		}
	}
	
	private class ReplayButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			EAPart2.reset();
			Game.getCurrentGame().enterState(EAPart2IntroScreen.class);
		}
	}
}
*/