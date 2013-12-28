package edu.asu.voctec.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;

public class EAPart2 extends GUI
{
	private static final String READY = "resources/default/img/minigames/energyAssessment/readyButton.png";
	private static final String NOTREADY = "resources/default/img/minigames/energyAssessment/readyButtonGray.png";
	private static final String BACK = "resources/default/img/minigames/energyAssessment/backButton.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		
		////Ready Button////
		Button ready = new Button(new Image(READY), 575, 500, new Rectangle(50,50,300,50), "");
		ready.addActionListener(new TransitionButtonListener(EAPart2ScoreScreen.class));
		this.addComponent(ready);
		
		//Back Button
		Button back = new Button(new Image(BACK), 50, 500, new Rectangle(50,50,300,50), "");
		back.addActionListener(new TransitionButtonListener(EAPart2IntroScreen.class));
		this.addComponent(back);
		
		////Testing Stuff can be deleted later////
		System.out.println("EAPart1IntroScreen");
					
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart2ScoreScreen.class));
		this.addComponent(Start);
	}
}
