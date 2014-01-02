package edu.asu.voctec.energy_assessment;

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
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class EAPart1ScoreScreen extends GUI
{
	private static final String CONTINUE = "resources/default/img/minigames/energyAssessment/continueButton.png";
	private static final String REPLAY = "resources/default/img/minigames/energyAssessment/replayButton.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	private static final String ea1ScoreText = "You have successfully determined the correct order for the different processes needed to determine the correct daily energy requirements.";

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
		TextArea scoreTextArea = new TextArea(scoreTextLocation, 0.90f, ea1ScoreText);
		scoreTextArea.setFontColor(Color.black);
		scoreTextArea.setFillColor(Color.white);
		scoreTextArea.setFontSize(16);
		this.addComponent(scoreTextArea);
		
		//Next Button
		Button Next = new Button(new Image(CONTINUE), 575, 500, new Rectangle(50,50,300,50), "");
		Next.addActionListener(new TransitionButtonListener(EAPart2IntroScreen.class));
		this.addComponent(Next);
		
		//Replay Button
		Button replay = new Button(new Image(REPLAY), 50, 500, new Rectangle(50,50,300,50), "");
		replay.addActionListener(new TransitionButtonListener(EAPart1IntroScreen.class));
		this.addComponent(replay);
		
	}
}
