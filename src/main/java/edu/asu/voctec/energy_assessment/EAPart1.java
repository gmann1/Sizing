package edu.asu.voctec.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.cdmg.CDPart1;
import edu.asu.voctec.game_states.GUI;

public class EAPart1 extends GUI
{
	private static final String DEVICES = "resources/default/img/minigames/energyAssessment/devices.png";
	private static final String POWER_RATING = "resources/default/img/minigames/energyAssessment/powerRating.png";
	private static final String ENERGY_CONSUMED = "resources/default/img/minigames/energyAssessment/energyConsumed.png";
	private static final String ENERGY_LOSS = "resources/default/img/minigames/energyAssessment/energyLoss.png";
	private static final String BUFFER = "resources/default/img/minigames/energyAssessment/buffer.png";
	private static final String Extra1 = "resources/default/img/minigames/energyAssessment/extra1.png";
	private static final String Extra2 = "resources/default/img/minigames/energyAssessment/extra2.png";
	
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		//basic load things
		this.backgroundImage = new Image(BACKGROUND);
		
		//Text
		Rectangle topTextLocation = new Rectangle(450, 20, 300, 50);
		Rectangle instructionTextLocation = new Rectangle(450, 70, 300, 250);
		
		TextField topText = new TextField(topTextLocation, 0.95f, "Select which step to complete first", TextDisplay.FormattingOption.FIT_TEXT);
		TextField instructionText = new TextField(instructionTextLocation, 0.95f, "Instructions", null);
		
		topText.setFontColor(Color.white);
		instructionText.setFontColor(Color.lightGray);
		
		topText.center();
		instructionText.setFillColor(Color.darkGray);
		
		this.addComponent(topText);
		this.addComponent(instructionText);

		////Selector////
		Selector<SelectorIcon> selectorSteps = new Selector<>(50, 340, true);
		
		//needed
		selectorSteps.add(new SelectorIcon(DEVICES, "Inventory Devices", 0));
		selectorSteps.add(new SelectorIcon(POWER_RATING, "Determine Power Rating", 1));
		selectorSteps.add(new SelectorIcon(ENERGY_CONSUMED, "Calculate Energy consumed", 2));
		selectorSteps.add(new SelectorIcon(ENERGY_LOSS, "Account For Energy Loss", 3));
		selectorSteps.add(new SelectorIcon(BUFFER, "Include Buffer for Growth of the Load", 4));
		
		//extra
		selectorSteps.add(new SelectorIcon(Extra1, "Extra1", 5));
		selectorSteps.add(new SelectorIcon(Extra2, "Extra2", 6));
		
		////display the selector//
		//     selectorSteps.displayLabel(); //Causes Problems
		this.addComponent(selectorSteps);
		
		/////selectorSteps.getString(0)
		
		
		////Testing Stuff can be deleted later////
		System.out.println("EAPart1IntroScreen");
				
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart1ScoreScreen.class));
		this.addComponent(Start);
	}
}
