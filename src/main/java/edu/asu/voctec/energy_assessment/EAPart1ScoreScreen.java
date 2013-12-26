package edu.asu.voctec.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;

public class EAPart1ScoreScreen extends GUI
{
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";

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
		Rectangle introTextLocation = new Rectangle(50, 100, 700, 350);
		TextField introTextField = new TextField(introTextLocation, 0.90f, "Information...", null);
		introTextField.setFontColor(Color.black);
		introTextField.setFillColor(Color.white);
		this.addComponent(introTextField);
		
		//Next Button
		Button Next = new Button(new Image(ImagePaths.ARROW_RIGHT), 700, 465, new Rectangle(50,50,300,50), "Next");
		Next.addActionListener(new TransitionButtonListener(EAPart2IntroScreen.class));
		this.addComponent(Next);
		
		////Testing Stuff can be deleted later////
		System.out.println("EAPart1IntroScreen");
					
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart2IntroScreen.class));
		this.addComponent(Start);
	}
}
